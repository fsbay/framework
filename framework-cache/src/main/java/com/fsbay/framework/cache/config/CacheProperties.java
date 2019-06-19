/**
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Company: fsbay.com </p>
 */
package com.fsbay.framework.cache.config;

import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author dengzhineng
 * @mail zhineng.deng@163.com
 * @date: 2019年6月17日 下午4:35:49
 * @version 1.0
 * @since JDK 1.8
 */
@Component
@ConfigurationProperties(prefix = "com.fsbay.cache")
@Getter
@Setter
@Validated
public class CacheProperties {
    /** JedisPoolConfig pool **/
    private Integer maxTotal = 100;
    private Integer maxIdle = 20;
    private Integer minIdle = 10;
    private Boolean blockWhenExhausted = true;
    private Integer maxWaitMillis = 3000;
    private Boolean testOnBorrow = false;
    private Boolean testOnReturn = false;
    private Boolean testWhileIdle = true;
    private Integer minEvictableIdleTimeMillis = 60000;
    private Integer timeBetweenEvictionRunsMillis = 30000;
    private Integer numTestsPerEvictionRun = -1;

    /** common **/
    /** redisSingle, redisCluster **/
    private String type = "redisSingle";
    @NotNull
    private String cachePrefix;
    private Integer cacheExpireTime = 3600;
    /** redis address:127.0.0.1:6379 the cluster like **/
    @NotNull
    private String address;
    private Integer timeout = 2000;
    private String password = "";
    /** for redis */
    private Integer database = 0;
    /** for redis cluster **/
    private Integer maxRedirections = 100;

}
