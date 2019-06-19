/**
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Company: fsbay.com </p>
 */
package com.fsbay.framework.distributed.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

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
@ConfigurationProperties(prefix="com.fsbay.lock")
@Getter
@Setter
public class DsLockProperties {
    /**缓存前缀*/
    private String lockpre="com.fsbay.lock";
    /**毫秒**/
    private int retryAwait = 300;
    
}
