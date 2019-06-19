package com.fsbay.framework.distributed.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fsbay.framework.cache.CacheClient;
import com.fsbay.framework.distributed.DsLockTemplate;
import com.fsbay.framework.distributed.aspect.DsLockAspect;
import com.fsbay.framework.distributed.impl.CacheDsLockTemplate;

@Configuration
@EnableConfigurationProperties(DsLockProperties.class)
@ConditionalOnProperty(name = "com.fsbay.dslock.enabled", havingValue = "true")
public class DsLockConfig {
    @Autowired
    private DsLockProperties dlp;

    @Autowired
    private CacheClient cacheClient;

    @Bean
    public DsLockAspect getDsLockAspect() {
        DsLockTemplate dlt = new CacheDsLockTemplate(dlp.getLockpre(), dlp.getRetryAwait(), cacheClient);
        return new DsLockAspect(dlt);
    }
}
