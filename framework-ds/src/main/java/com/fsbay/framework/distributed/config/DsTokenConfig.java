package com.fsbay.framework.distributed.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.fsbay.framework.cache.CacheClient;
import com.fsbay.framework.distributed.DsTokenTemplate;
import com.fsbay.framework.distributed.aspect.DsTokenAspect;
import com.fsbay.framework.distributed.impl.CacheDsTokenTemplate;

@Configuration
@EnableConfigurationProperties(DsTokenProperties.class)
@ConditionalOnProperty(name = "com.fsbay.dstoken.enabled", havingValue = "true")
public class DsTokenConfig {
    @Autowired
    private CacheClient cacheClient;
    @Autowired
    private DsTokenProperties dtp;

    public DsTokenAspect getDsTokenAspect() {
        DsTokenTemplate dtt = new CacheDsTokenTemplate(dtp.getTokenPre(), cacheClient);
        return new DsTokenAspect(dtt);
    }
}
