package com.fsbay.framework.distributed.impl;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fsbay.framework.cache.CacheClient;
import com.fsbay.framework.distributed.DsLockTemplate;
import com.fsbay.framework.distributed.DsReentrantLock;
import com.fsbay.framework.distributed.LockCallback;

/**
 * 
 *
 * @author dengzhineng
 * @mail zhineng.deng@163.com
 * @date: 2019年6月18日 下午8:37:55
 * @version 1.0
 * @since JDK 1.8
 */
public class CacheDsLockTemplate implements DsLockTemplate {
    private static final Logger logger = LoggerFactory.getLogger(CacheDsLockTemplate.class);

    private CacheClient cacheClient;

    private String lockpre;

    private int retryAwait;


    public CacheDsLockTemplate(String lockpre, int retryAwait, CacheClient cacheClient) {
        this.lockpre = lockpre;
        this.retryAwait = retryAwait;
        this.cacheClient = cacheClient;
    }

    public Object execute(String biz, String lockId, int timeout, LockCallback callback) {
        DsReentrantLock distributedReentrantLock = null;
        String tlockpre = lockpre +"_"+ biz;
        boolean getLock = false;
        try {
            distributedReentrantLock = new CacheReentrantLock(tlockpre, lockId, retryAwait, timeout, cacheClient);
            long t = (long) timeout;
            if (distributedReentrantLock.tryLock(t, TimeUnit.MILLISECONDS)) {
                getLock = true;
                return callback.onGetLock();
            } else {
                return callback.onTimeout();
            }
        } catch (InterruptedException ex) {
            logger.error(ex.getMessage(), ex);
            Thread.currentThread().interrupt();
        } finally {
            if (getLock) {
                distributedReentrantLock.unlock();
            }
        }
        return null;
    }

}
