package com.fsbay.framework.cache;

import java.util.List;

/**
 * 
 * Description: Cache Interface   <br/>
 *
 * @author dengzhineng
 * @mail zhineng.deng@163.com
 * @date: 2019年6月13日 下午5:44:50
 * @version 1.0
 * @since JDK 1.8
 */
public interface CacheClient {

    /**
     * 
     * @author dengzhineng
     * @date: 2019年6月13日 下午5:48:30
     * @version 1.0
     *
     * @param key
     * @return
     */
    <T> T get(String key);

    /**
     * 
     * @author dengzhineng
     * @date: 2019年6月13日 下午5:49:23
     * @version 1.0
     *
     * @param key
     * @param o
     * @return
     */
    <T> boolean set(String key, T o);

    /**
     * 
     * 
     * @author dengzhineng
     * @date: 2019年6月13日 下午5:51:26
     * @version 1.0
     *
     * @param key
     * @param o
     * @param timeout 单位为秒
     * @return
     */
    <T> boolean set(String key, T o, int timeout);

    /**
     * 
     * 
     * @author dengzhineng
     * @date: 2019年6月13日 下午5:51:47
     * @version 1.0
     *
     * @param key
     * @return
     */
    boolean delete(String key);

    /**
     * 
     * 
     * @author dengzhineng
     * @date: 2019年6月13日 下午5:52:12
     * @version 1.0
     *
     * @param key
     * @return
     */
    public long incr(String key);

    /**
     * 
     * 
     * @author dengzhineng
     * @date: 2019年6月13日 下午5:52:30
     * @version 1.0
     *
     * @param key
     * @param expire -1:永久不过期 0:不改变过期时间
     * @return
     */
    public long incr(String key, int expire);

    /**
     * 
     * 
     * @author dengzhineng
     * @date: 2019年6月13日 下午5:54:04
     * @version 1.0
     *
     * @param key
     * @return
     */
    public Long getIncrValue(String key);

    /**
     * 
     * 
     * @author dengzhineng
     * @date: 2019年6月13日 下午5:54:23
     * @version 1.0
     *
     * @param key
     * @param value
     * @param expire
     */
    public void setIncrValue(String key, long value, int expire);

    /**
     * 
     * 
     * @author dengzhineng
     * @date: 2019年6月13日 下午5:54:42
     * @version 1.0
     *
     * @param script
     * @param keys
     * @param args
     * @return
     */
    Object eval(final String script, final List<String> keys, final List<String> args);

    /**
     * 
     * 
     * @author dengzhineng
     * @date: 2019年6月13日 下午5:55:14
     * @version 1.0
     *
     * @param key
     * @return
     */
    Boolean exists(final String key);

    /**
     * 设置过期时间
     * 
     * @author dengzhineng
     * @date: 2019年6月13日 下午5:55:28
     * @version 1.0
     *
     * @param key
     * @param expire
     */
    public void setExpire(String key, int expire);

    /**
     * 获取真实的缓存对象
     * 
     * @author dengzhineng
     * @date: 2019年6月13日 下午5:55:44
     * @version 1.0
     *
     * @return
     */
    public Object getCacheObject();
}
