package com.fsbay.framework.cache.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * 
 *
 * @author dengzhineng
 * @mail zhineng.deng@163.com
 * @date: 2019年6月13日 下午6:04:48
 * @version 1.0
 * @since JDK 1.8
 */
public class RedisImpl extends AbstractCacheClient {
    private static Logger logger = LoggerFactory.getLogger(RedisImpl.class);
    /** jedis连接池 **/
    private JedisPool pool;
    
    public RedisImpl(JedisPool pool, String kPrefix) {
        this.pool =  pool;
        this.kPrefix = kPrefix;
    }
    
    public RedisImpl(JedisPool pool, String kPrefix, int defaultCacheTime) {
        this.pool =  pool;
        this.kPrefix = kPrefix;
        this.defaultCacheTime = defaultCacheTime;
    }

    public JedisPool getPool() {
        return pool;
    }

    public void setPool(JedisPool pool) {
        this.pool = pool;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T get(String key) {
        Jedis jedis = pool.getResource();
        try {
            key = kPrefix+key;
            byte[] tObj = jedis.get(key.getBytes());
            if (tObj == null) {
                return null;
            }
            return (T)SerializeUtils.unSerialize(tObj);
        } catch (Exception e) {
            logger.info("get cache failure !" ,e);
            return null;
        } finally {
            jedis.close();
        }
    }

    @Override
    public <T> boolean set(String key, T o) {
        return set(key, o, defaultCacheTime);
    }

    @Override
    public <T> boolean set(String key, T o, int timeout) {
        Jedis jedis = pool.getResource();
        try {
            String tkey = kPrefix +key;
            jedis.setex(tkey.getBytes(), timeout, SerializeUtils.serialize(o));
            return true;
        } catch (Exception e) {
            logger.info("set cache failure !" ,e);
            return false;
        } finally {
            jedis.close();
        }
    }

    @Override
    public boolean delete(String key) {
        Jedis jedis = pool.getResource();
        try {
            key = kPrefix + key;
            jedis.del(key.getBytes());
            return true;
        } finally {
            jedis.close();
        }
    }

    @Override
    public long incr(String key) {
        return incr(key, -1);
    }
    
    @Override
    public long incr(String key, int expire) {
        Jedis jedis = pool.getResource();
        try {
            key = kPrefix + key;
            long value = jedis.incr(key.getBytes());
            switch(expire) 
            { 
            case -1: 
                jedis.persist(key.getBytes()); 
                break; 
            case 0: 
                break;
            default:  
                jedis.expire(key.getBytes(), expire);
                break;
            }
            return value;
        } finally {
            jedis.close();
        }
    } 
    @Override
    public Long getIncrValue(String key) {
        Jedis jedis = pool.getResource();
        try {
            key = kPrefix + key;
            String value = new String(jedis.get(key.getBytes()));
            return value ==null ? null :Long.valueOf(value);
        } finally {
            jedis.close();
        }
    }
    
    @Override
    public void setIncrValue(String key, long value, int expire) {
        Jedis jedis = pool.getResource();
        try {
            key = kPrefix + key;
            jedis.set(key.getBytes(), String.valueOf(value).getBytes());
            switch(expire) 
            { 

            case -1: 
                jedis.persist(key.getBytes()); 
                break; 
            case 0: 
                break;
            default:  
                jedis.expire(key.getBytes(), expire);
                break;
            }
        } finally {
            jedis.close();
        }
        
    }
    
    @Override
    public Object getCacheObject() {
        return getPool();
    }

    @Override
    public Object eval(String script, List<String> keys, List<String> args) {
        Jedis jedis = pool.getResource();
        try {
            return jedis.eval(script, keys, args);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Boolean exists(String key) {
        Jedis jedis = pool.getResource();
        try {
            key = kPrefix + key;
            return jedis.exists(key);
        } finally {
            jedis.close();
        }
    }

    @Override
    public void setExpire(String key, int expire) {
        Jedis jedis = pool.getResource();
        try {
            jedis.expire(key, expire);
        } finally {
            jedis.close();
        }
    }

}
