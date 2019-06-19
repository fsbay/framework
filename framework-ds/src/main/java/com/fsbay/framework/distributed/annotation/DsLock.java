package com.fsbay.framework.distributed.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 分布式锁  支持入参对象#成员变量
 *
 * @author dengzhineng
 * @mail zhineng.deng@163.com
 * @date: 2019年6月16日 上午9:59:52
 * @version 1.0
 * @since JDK 1.8
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface DsLock {
 
	// 异常码
    public int errCode() default 10010001;

    // 异常信息
    public String errMsg() default "获取锁失败";
    
    //业务类型
    public String biz() default "def";
    
    //锁的值
    public String key();
    
    //超时时间毫秒
    public int  timeOut() default 2000;
}