package com.fsbay.framework.distributed.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fsbay.framework.cache.CacheClient;
import com.fsbay.framework.distributed.DsTokenTemplate;
import com.fsbay.framework.distributed.TokenCallback;
import com.fsbay.framework.distributed.exceptions.DistributedException;

/**
 * 
 *
 * @author dengzhineng
 * @mail zhineng.deng@163.com
 * @date: 2019年6月19日 上午10:16:48
 * @version 1.0
 * @since JDK 1.8
 */
public class CacheDsTokenTemplate implements DsTokenTemplate {

    private static final Logger logger = LoggerFactory.getLogger(CacheDsTokenTemplate.class);

    private String tokenPre;

    private CacheClient cacheClient;

    public CacheDsTokenTemplate(String tokenPre, CacheClient cacheClient) {
        this.tokenPre = tokenPre;
        this.cacheClient = cacheClient;
    }

    @Override
    public Object execute(String biz, String tokenId, int lockTimeout, TokenCallback callback) {
        if (lockTimeout == 0) {
            lockTimeout = 2000;
        }
        tokenId = tokenPre + "_" + biz + tokenId;
        boolean getToken = false;
        String value = null;
        try {
            value = tryGetToken(tokenId, lockTimeout);
            if (value != null) {
                getToken = true;
                return callback.onCreateSucess();
            } else {
                return callback.onCreateFail();
            }
        } catch (InterruptedException ex) {
            logger.error(ex.getMessage(), ex);
            Thread.currentThread().interrupt();
            throw new DistributedException(ex);
        } finally {
            if (getToken) {
                realseToken(tokenId, value);
            }
        }
    }

    private String tryGetToken(String tokenId, int lockTimeout) {
        String value = tokenId;
        String luaScript = "" + "\nlocal r = tonumber(redis.call('SETNX', KEYS[1],ARGV[1]));"
                + "\nredis.call('PEXPIRE',KEYS[1],ARGV[2]);" + "\nreturn r";
        List<String> keys = new ArrayList<>();
        keys.add(tokenId);
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

    private void realseToken(String key, String value) {
        String luaScript = "" + "\nlocal v = redis.call('GET', KEYS[1]);" + "\nlocal r= 0;" + "\nif v == ARGV[1] then"
                + "\nr =redis.call('DEL',KEYS[1]);" + "\nend" + "\nreturn r";
        List<String> keys = new ArrayList<>();
        keys.add(key);
        List<String> args = new ArrayList<>();
        args.add(value);
        cacheClient.eval(luaScript, keys, args);
    }
}
