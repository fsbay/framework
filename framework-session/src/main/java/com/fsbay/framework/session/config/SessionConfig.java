/**
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Company: fsbay.com </p>
 */
package com.fsbay.framework.session.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fsbay.framework.cache.CacheClient;
import com.fsbay.framework.session.filter.ProxySessionFilter;
import com.fsbay.framework.session.impl.CacheProxySessionManager;

/** 
 *
 * @author dengzhineng
 * @mail zhineng.deng@163.com
 * @date: 2019年6月14日 下午5:27:51
 * @version 1.0
 * @since JDK 1.8
 */
@Configuration
@ComponentScan(basePackages = "com.fsbay.framework.session.config,com.fsbay.framework.session.filter")
public class SessionConfig extends WebMvcConfigurerAdapter{
    @Autowired
    private SessionProperties sp;
    @Autowired
    private CacheClient cc;
    @Autowired
    private ProxySessionFilter psf;
    
    @Bean
    public CacheProxySessionManager getCacheProxySessionManager() {
        CacheProxySessionManager csm =  new CacheProxySessionManager();
        csm.setCacheClient(cc);
        csm.setCachePre(sp.getCachePre());
        csm.setDomain(sp.getDomain());
        csm.setExpirationUpdateInterval(sp.getExpirationUpdateInterval());
        csm.setMaxInactiveInterval(sp.getMaxInactiveInterval());
        csm.setSName(sp.getSName());
        csm.setSParamNams(sp.getSParamNams());
        csm.setSType(sp.getSType());
        return csm;
    }
    
    @Bean
    public FilterRegistrationBean testFilterRegistration() {
      FilterRegistrationBean registration = new FilterRegistrationBean();
      registration.setFilter(psf);
      registration.addUrlPatterns(sp.getPath());
      return registration;
    }
}
