package com.fsbay.framework.cache.impl;

import org.nustaq.serialization.FSTConfiguration;

/**
 * 
 *
 * @author dengzhineng
 * @mail zhineng.deng@163.com
 * @date: 2019年6月18日 下午4:43:05
 * @version 1.0
 * @since JDK 1.8
 */
public class SerializeUtils {

    static FSTConfiguration configuration = FSTConfiguration.createDefaultConfiguration();

    /**
     * 序列化
     * 
     * @param object
     * @return
     * @throws Exception
     */
    public static byte[] serialize(Object object) throws Exception {
        if (object == null)
            return null;
        return configuration.asByteArray(object);
    }

    /**
     * 反序列化
     * 
     * @param bytes
     * @return
     * @throws Exception
     */
    public static Object unSerialize(byte[] bytes) throws Exception {
        if (bytes == null)
            return null;
        return configuration.asObject(bytes);
    }
}
