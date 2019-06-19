/**
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Company: fsbay.com </p>
 */
package com.fsbay.framework.cache.impl;

import com.fsbay.framework.cache.CacheClient;

/** 
 *
 * @author dengzhineng
 * @mail zhineng.deng@163.com
 * @date: 2019年6月14日 上午10:52:16
 * @version 1.0
 * @since JDK 1.8
 */
public abstract class AbstractCacheClient implements CacheClient{
    /** 默认缓存时间 */
    protected int defaultCacheTime = 60 * 60 * 1;
    
    /** 缓存前缀     */
    protected String kPrefix;

    public int getDefaultCacheTime() {
        return defaultCacheTime;
    }

    public void setDefaultCacheTime(int defaultCacheTime) {
        this.defaultCacheTime = defaultCacheTime;
    }

    public String getkPrefix() {
        return kPrefix;
    }

    public void setkPrefix(String kPrefix) {
        this.kPrefix = kPrefix;
    }

 
}
