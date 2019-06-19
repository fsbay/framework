package com.fsbay.framework.distributed.impl;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import com.fsbay.framework.cache.CacheClient;
import com.fsbay.framework.distributed.DsReentrantLock;
import com.google.common.collect.Maps;

/**
 * 
 *
 * @author dengzhineng
 * @mail zhineng.deng@163.com
 * @date: 2019年6月18日 下午8:46:01
 * @version 1.0
 * @since JDK 1.8
 */
public class CacheReentrantLock implements DsReentrantLock {

    private final ConcurrentMap<Thread, LockData> threadData = Maps.newConcurrentMap();

    private CacheLockInternals internals;

    private String lockId;

    public CacheReentrantLock(String lockpre, String lockId, int retryAwait, int lockTimeout, CacheClient cacheClient) {
        this.lockId = lockpre + "_" + lockId;
        this.internals = new CacheLockInternals(retryAwait, lockTimeout, cacheClient);
    }

    private static class LockData {
        final String lockVal;
        final AtomicInteger lockCount = new AtomicInteger(1);

        private LockData(String lockVal) {
            this.lockVal = lockVal;
        }
    }

    @Override
    public boolean tryLock(long timeout, TimeUnit unit) throws InterruptedException {
        Thread currentThread = Thread.currentThread();
        LockData lockData = threadData.get(currentThread);
        if (lockData != null) {
            lockData.lockCount.incrementAndGet();
            return true;
        }
        String lockVal = internals.tryCacheLock(lockId, timeout, unit);
        if (lockVal != null) {
            LockData newLockData = new LockData(lockVal);
            threadData.put(currentThread, newLockData);
            return true;
        }
        return false;
    }

    @Override
    public void unlock() {
        Thread currentThread = Thread.currentThread();
        LockData lockData = threadData.get(currentThread);
        if (lockData == null) {
            throw new IllegalMonitorStateException("You do not own the lock: " + lockId);
        }
        int newLockCount = lockData.lockCount.decrementAndGet();
        if (newLockCount > 0) {
            return;
        }
        if (newLockCount < 0) {
            throw new IllegalMonitorStateException("Lock count has gone negative for lock: " + lockId);
        }
        try {
            internals.unlockCacheLock(lockId, lockData.lockVal);
        } finally {
            threadData.remove(currentThread);
        }
    }

}
