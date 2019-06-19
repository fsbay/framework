/**
 * 
 */
package com.fsbay.framework.cache.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.JedisCluster;

/**
 * 
 *
 * @author dengzhineng
 * @mail zhineng.deng@163.com
 * @date: 2019年6月14日 上午10:50:39
 * @version 1.0
 * @since JDK 1.8
 */
public class RedisClusterImpl extends AbstractCacheClient {
    private static Logger logger = LoggerFactory.getLogger(RedisClusterImpl.class);

    /** 集群链接 **/
    private JedisClusterPool jedisClusterPool;

    public RedisClusterImpl(JedisClusterPool jcp, String kPrefix, int defaultCacheTime) {
        this.jedisClusterPool = jcp;
        this.kPrefix = kPrefix;
        this.defaultCacheTime = defaultCacheTime;
    }

    public JedisCluster getJedisClusterPool() {
        return jedisClusterPool.getJedisCluster();
    }

    public void setJedisClusterPool(JedisClusterPool jedisClusterPool) {
        this.jedisClusterPool = jedisClusterPool;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T get(String key) {
        try {
            String tKey = kPrefix + String.valueOf(key);
            byte[] tObj = getJedisClusterPool().get(tKey.getBytes());
            if (tObj == null) {
                return null;
            }
            return (T) SerializeUtils.unSerialize(tObj);
        } catch (Exception e) {
            logger.info("get 获取缓存失败", e);
            return null;
        }
    }

    @Override
    public <T> boolean set(String key, T o) {
        return set(key, o, defaultCacheTime);
    }

    @Override
    public <T> boolean set(String key, T o, int timeout) {
        if (o == null || key == null)
            return false;
        try {
            String tKey = kPrefix + String.valueOf(key);
            getJedisClusterPool().setex(tKey.getBytes(), timeout, SerializeUtils.serialize(o));
            return true;
        } catch (Exception e) {
            logger.error("Cache save failure !" + e);
            return false;
        }
    }

    @Override
    public boolean delete(String key) {
        String tKey = kPrefix + String.valueOf(key);
        return getJedisClusterPool().del(tKey.getBytes()) > 0;
    }

    @Override
    public long incr(String key) {
        return incr(key, -1);
    }

    @Override
    public long incr(String key, int expire) {
        String tkey = kPrefix + key;
        long value = getJedisClusterPool().incr(tkey.getBytes());
        switch (expire) {
            case -1:
                getJedisClusterPool().persist(tkey);
                break;
            case 0:
                break;
            default:
                getJedisClusterPool().expire(tkey, expire);
                break;
        }
        return value;
    }

    @Override
    public Long getIncrValue(String key) {
        String tkey = kPrefix + key;
        String value = getJedisClusterPool().get(tkey);
        return value == null ? null : Long.valueOf(value);
    }

    @Override
    public void setIncrValue(String key, long value, int expire) {
        String tkey = kPrefix + key;
        getJedisClusterPool().set(tkey, String.valueOf(value));
        switch (expire) {
            case -1:
                getJedisClusterPool().persist(tkey);
                break;
            case 0:
                break;
            default:
                getJedisClusterPool().expire(tkey, expire);
                break;
        }
    }

    public int getDefaultCacheTime() {
        return defaultCacheTime;
    }

    public void setDefaultCacheTime(int defaultCacheTime) {
        this.defaultCacheTime = defaultCacheTime;
    }

    @Override
    public Object getCacheObject() {
        return getJedisClusterPool();
    }

    @Override
    public Object eval(String script, List<String> keys, List<String> args) {
        return getJedisClusterPool().eval(script, keys, args);
    }

    @Override
    public Boolean exists(String key) {
        key = kPrefix + key;
        return getJedisClusterPool().exists(key);
    }

    @Override
    public void setExpire(String key, int expire) {
        key = kPrefix + key;
        getJedisClusterPool().expire(key, expire);
    }

}
