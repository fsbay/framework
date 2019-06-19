/**
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Company: fsbay.com </p>
 */
package com.fsbay.framework.session.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author dengzhineng
 * @mail zhineng.deng@163.com
 * @date: 2019年6月18日 下午3:33:55
 * @version 1.0
 * @since JDK 1.8
 */
@Component
@ConfigurationProperties(prefix = "com.fsbay.session")
@Getter
@Setter
public class SessionProperties {
    /**
     * if the sParamNams is null sName is session name other sName is prefix
     **/
    private String sName = "sid";
    /** create session by parameter split , **/
    private String sParamNams;
    /** the value is [token | cookie] **/
    private String sType = "cookie";
    private String cachePre = "com.fsbay.session";
    private int maxInactiveInterval = 1800;
    private int expirationUpdateInterval = 300;
    /** domain for cookie **/
    private String domain;
    /** filter path ***/
    private String path = "/*";
}
