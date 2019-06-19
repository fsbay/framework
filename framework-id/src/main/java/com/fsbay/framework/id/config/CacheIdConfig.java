/**
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Company: fsbay.com </p>
 */
package com.fsbay.framework.id.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fsbay.framework.cache.CacheClient;
import com.fsbay.framework.id.IDGenerator;
import com.fsbay.framework.id.impl.CacheIDGenerator;

/**
 *
 * @author dengzhineng
 * @mail zhineng.deng@163.com
 * @date: 2019年6月19日 下午4:05:22
 * @version 1.0
 * @since JDK 1.8
 */
@Configuration
@EnableConfigurationProperties(CacheIdProperties.class)
@ConditionalOnProperty(name = "com.fsbay.id.cache.enabled", havingValue = "true")
public class CacheIdConfig {
    @Autowired
    private CacheClient cacheClient;
    @Autowired
    private CacheIdProperties cip;

    @Bean(name = "cacheIDGenerator")
    public IDGenerator getIDGenerator() {
        return new CacheIDGenerator(cacheClient, cip.getPre());
    }
}
