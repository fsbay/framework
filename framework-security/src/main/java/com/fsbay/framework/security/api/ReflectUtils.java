package com.fsbay.framework.security.api;

/**
 * 
 *
 * @author dengzhineng
 * @mail zhineng.deng@163.com
 * @date: 2019年6月15日 上午8:21:49
 * @version 1.0
 * @since JDK 1.8
 */
public class ReflectUtils {

    @SuppressWarnings("unchecked")
    public static <T> T getInstance(Class<?> clazz, String params)
            throws Exception {
        Initable obj = (Initable) clazz.getConstructor().newInstance();
        obj.init(params);
        return (T) obj;
    }

    @SuppressWarnings("unchecked")
    public static <T> T getInstance(Class<?> clazz, byte[] params)
            throws Exception {
        Initable obj = (Initable) clazz.getConstructor().newInstance();
        obj.init(params);
        return (T) obj;
    }
}
