/**
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Company: fsbay.com </p>
 */
package com.fsbay.framework.distributed.config;

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
public class DsTokenProperties {
    private String tokenPre = "com.fsbay.token";
}
