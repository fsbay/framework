package com.fsbay.framework.session.wrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fsbay.framework.session.impl.HttpProxySession;
/**
 * 
 *
 * @author dengzhineng
 * @mail zhineng.deng@163.com
 * @date: 2019年6月14日 下午2:09:48
 * @version 1.0
 * @since JDK 1.8
 */
public interface ProxySessionManager {
    
    /**
     * 
     * 
     * @author dengzhineng 
     * @date: 2019年6月14日 下午2:09:55
     * @version 1.0
     *
     * @param request
     * @param response
     * @param requestEventSubject
     * @param create
     * @return
     */
    HttpProxySession createSession(HttpProxySessionServletRequestWrapper request, HttpServletResponse response,
            RequestEventSubject requestEventSubject, boolean create);
    
    /**
     * 
     * 
     * @author dengzhineng 
     * @date: 2019年6月14日 下午2:10:01
     * @version 1.0
     *
     * @param session
     * @param request
     * @param response
     */
    public void saveSession(HttpProxySession session,HttpServletRequest request,HttpServletResponse response);
}