package com.fsbay.framework.session.wrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

import com.fsbay.framework.session.impl.HttpProxySession;
/**
 * 
 *
 * @author dengzhineng
 * @mail zhineng.deng@163.com
 * @date: 2019年6月14日 上午11:31:38
 * @version 1.0
 * @since JDK 1.8
 */
@Component
public class HttpProxySessionServletRequestWrapper extends HttpServletRequestWrapper {

    private HttpServletResponse response;
    private HttpProxySession httpProxySession;
    private ProxySessionManager proxySessionManager;
    private RequestEventSubject requestEventSubject;
    
    public HttpProxySessionServletRequestWrapper(HttpServletRequest request, HttpServletResponse response,
            ProxySessionManager proxySessionManager, RequestEventSubject requestEventSubject) {
        super(request);
        this.response = response;
        this.proxySessionManager = proxySessionManager;
        this.requestEventSubject = requestEventSubject;
    }

    public HttpSession getSession(boolean create) {
        if ((this.httpProxySession != null) && (!this.httpProxySession.isExpired()))
            return this.httpProxySession;
        this.httpProxySession = this.proxySessionManager.createSession(this, this.response, this.requestEventSubject, create);
        return this.httpProxySession;
    }

    public HttpSession getSession() {
        return getSession(true);
    }
    
}