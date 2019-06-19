package com.fsbay.framework.distributed.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.fsbay.framework.distributed.config.DsLockConfig;
import com.fsbay.framework.distributed.config.DsTokenConfig;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import({DsLockConfig.class,DsTokenConfig.class})
@Configuration
public @interface EnableDsModule {
}
