package com.fsbay.framework.cache.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.fsbay.framework.cache.CacheClient;
import com.fsbay.framework.cache.exceptions.CacheException;
import com.fsbay.framework.cache.impl.JedisClusterPool;
import com.fsbay.framework.cache.impl.RedisClusterImpl;
import com.fsbay.framework.cache.impl.RedisImpl;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
@ComponentScan(basePackages = "com.fsbay.framework.cache.config,com.fsbay.framework.cache.impl")
public class CacheConfig {


    @Autowired
    private CacheProperties cp;

    @Bean
    public CacheClient getCacheClient() {
        if (cp == null) {
            throw new CacheException(CacheException.DEFAULT_CODE, "cache properties is null");
        }
        JedisPoolConfig jpc = new JedisPoolConfig();
        jpc.setMaxTotal(cp.getMaxTotal());
        jpc.setMaxIdle(cp.getMaxIdle());
        jpc.setMinIdle(cp.getMinIdle());
        jpc.setBlockWhenExhausted(cp.getBlockWhenExhausted());
        jpc.setMaxWaitMillis(cp.getMaxWaitMillis());
        jpc.setTestOnBorrow(cp.getTestOnBorrow());
        jpc.setTestOnReturn(cp.getTestOnReturn());
        jpc.setTestWhileIdle(cp.getTestWhileIdle());
        jpc.setMinEvictableIdleTimeMillis(cp.getMinEvictableIdleTimeMillis());
        jpc.setTimeBetweenEvictionRunsMillis(cp.getTimeBetweenEvictionRunsMillis());
        jpc.setNumTestsPerEvictionRun(cp.getNumTestsPerEvictionRun());
        if ("redisSingle".equals(cp.getType())) {
            String[] tss = cp.getAddress().split(":");
            // redis single
            JedisPool jp = new JedisPool(jpc, tss[0], Integer.valueOf(tss[1]), cp.getTimeout(), cp.getPassword(),
                    cp.getDatabase());
            return new RedisImpl(jp, cp.getCachePrefix(), cp.getCacheExpireTime());
        } else if ("redisCluster".equals(cp.getType())) {
            // redis cluster
            JedisClusterPool jcp = new JedisClusterPool(cp.getAddress(), cp.getTimeout(), cp.getMaxRedirections(), jpc,
                    cp.getPassword());

            return new RedisClusterImpl(jcp, cp.getCachePrefix(), cp.getCacheExpireTime());
        }
        return null;
    }
}
