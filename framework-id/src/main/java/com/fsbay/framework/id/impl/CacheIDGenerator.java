package com.fsbay.framework.id.impl;

import com.fsbay.framework.cache.CacheClient;
import com.fsbay.framework.id.ID;
import com.fsbay.framework.id.IDGenerator;

/**
 * 
 *
 * @author dengzhineng
 * @mail zhineng.deng@163.com
 * @date: 2019年6月16日 上午9:40:23
 * @version 1.0
 * @since JDK 1.8
 */
public class CacheIDGenerator implements IDGenerator {

    private CacheClient cacheClient;

    private String pre ;

    public CacheIDGenerator(CacheClient cacheClient, String pre) {
        this.cacheClient = cacheClient;
        this.pre = pre;
    }

    @Override
    public ID next() {
        long id;
        try {
            id = cacheClient.incr(pre);
        } catch (Exception e) {
            throw new RuntimeException("when generat id for " + pre, e);
        }
        if (id == -1) {
            throw new RuntimeException("the generate id cache has not be init.");
        }
        return new ID(id, System.currentTimeMillis() / 1000);
    }

    @Override
    public ID next(String key) {
        long id;
        try {
            id = cacheClient.incr(pre + key);
        } catch (Exception e) {
            throw new RuntimeException("when generat id for " + pre + "_" + key, e);
        }
        if (id == -1) {
            throw new RuntimeException("the generate id cache has not be init.");
        }
        return new ID(id, System.currentTimeMillis() / 1000);
    }

}
