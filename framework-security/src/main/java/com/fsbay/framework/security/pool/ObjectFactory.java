package com.fsbay.framework.security.pool;

/**
 * 对象工厂接口，用于创建对象池管理的对象
 *
 * @author dengzhineng
 * @mail zhineng.deng@163.com
 * @date: 2019年6月15日 上午8:44:27
 * @version 1.0
 * @since JDK 1.8
 */
public interface ObjectFactory<T> {
    /**
     * 创建对象
     * 
     * @return 创建好的对象
     * @throws Exception
     */
    T create() throws Exception;
}
