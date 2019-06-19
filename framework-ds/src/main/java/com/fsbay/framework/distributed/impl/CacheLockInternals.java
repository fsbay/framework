package com.fsbay.framework.distributed.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

import com.fsbay.framework.cache.CacheClient;

/**
 * 
 *
 * @author dengzhineng
 * @mail zhineng.deng@163.com
 * @date: 2019年6月18日 下午8:52:53
 * @version 1.0
 * @since JDK 1.8
 */
class CacheLockInternals {

    private CacheClient cacheClient;

    /**
     * 重试等待时间
     */
    private int retryAwait;

    private int lockTimeout;

    CacheLockInternals(int retryAwait, int lockTimeout, CacheClient cacheClient) {
        this.retryAwait = retryAwait;
        this.lockTimeout = lockTimeout;
        this.cacheClient = cacheClient;
    }

    String tryCacheLock(String lockId, long time, TimeUnit unit) {
        final long startMillis = System.currentTimeMillis();
        final Long millisToWait = (unit != null) ? unit.toMillis(time) : null;
        String lockValue = null;
        while (true) {
            lockValue = createCacheKey(lockId);
            if ((lockValue != null) ||(System.currentTimeMillis() - startMillis - retryAwait > millisToWait)) {
                break;
            }
            LockSupport.parkNanos(TimeUnit.MILLISECONDS.toNanos(retryAwait));
        }
        return lockValue;
    }

    private String createCacheKey(String lockId) {
        String value = lockId;
        String luaScript = "" + "\nlocal r = tonumber(redis.call('SETNX', KEYS[1],ARGV[1]));"
                + "\nredis.call('PEXPIRE',KEYS[1],ARGV[2]);" + "\nreturn r";
        List<String> keys = new ArrayList<>();
        keys.add(lockId);
        List<String> args = new ArrayList<>();
        args.add(value);
        args.add(String.valueOf(lockTimeout));
        Long ret = (Long) cacheClient.eval(luaScript, keys, args);
        Long one = 1L;
        if (one.equals(ret)) {
            return value;
        }
        return null;
    }

    void unlockCacheLock(String key, String value) {
        String luaScript = "" + "\nlocal v = redis.call('GET', KEYS[1]);" + "\nlocal r= 0;" + "\nif v == ARGV[1] then"
                + "\nr =redis.call('DEL',KEYS[1]);" + "\nend" + "\nreturn r";
        List<String> keys = new ArrayList<>();
        keys.add(key);
        List<String> args = new ArrayList<>();
        args.add(value);
        cacheClient.eval(luaScript, keys, args);
    }

    public static void main(String[] args) {
       // System.out.println(System.currentTimeMillis());
        LockSupport.parkNanos(TimeUnit.MILLISECONDS.toNanos(300));
        //System.out.println(System.currentTimeMillis());
    }
}
