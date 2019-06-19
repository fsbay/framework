package com.fsbay.framework.session.wrapper;

import com.fsbay.framework.session.impl.HttpProxySession;

/**
 * 
 *
 * @author dengzhineng
 * @mail zhineng.deng@163.com
 * @date: 2019年6月14日 上午11:47:38
 * @version 1.0
 * @since JDK 1.8
 */
public abstract interface ProxySessionListener {
    /**
     * 
     * 
     * @author dengzhineng
     * @date: 2019年6月14日 下午2:13:33
     * @version 1.0
     *
     * @param httpProxySession
     */
    public abstract void onAttributeChanged(HttpProxySession httpProxySession);

    /**
     * 
     * 
     * @author dengzhineng
     * @date: 2019年6月14日 下午2:13:38
     * @version 1.0
     *
     * @param httpProxySession
     */
    public abstract void onInvalidated(HttpProxySession httpProxySession);
}