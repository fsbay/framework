package com.fsbay.framework.security.api;

/**
 * 
 *
 * @author dengzhineng
 * @mail zhineng.deng@163.com
 * @date: 2019年6月15日 上午8:21:35
 * @version 1.0
 * @since JDK 1.8
 */
public interface Initable {

    /**
     * 初始化（配合默认构造器）
     * 
     * @param secryptKeys
     */
    void init(byte[] bytes) throws Exception;

    /**
     * 初始化（配合默认构造器）
     * 
     * @param secryptKey
     */
    void init(String secryptKey) throws Exception;

}
