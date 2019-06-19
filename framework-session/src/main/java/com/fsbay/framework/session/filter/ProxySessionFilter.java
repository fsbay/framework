/**
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Company: fsbay.com </p>
 */
package com.fsbay.framework.session.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fsbay.framework.session.wrapper.HttpProxySessionServletRequestWrapper;
import com.fsbay.framework.session.wrapper.ProxySessionManager;
import com.fsbay.framework.session.wrapper.RequestEventSubject;

/**
 *
 * @author dengzhineng
 * @mail zhineng.deng@163.com
 * @date: 2019年6月14日 上午11:15:24
 * @version 1.0
 * @since JDK 1.8
 */
@Component
public class ProxySessionFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(ProxySessionFilter.class);
    public static final String[] IGNORE_SUFFIX = {  ".css", ".js", ".png", ".jpg", ".jpeg", ".gif",".html", ".htm" };
    @Autowired
    private ProxySessionManager proxySessionManager;

    private boolean ignoreFilter(HttpServletRequest request) {
        String uri = request.getRequestURI().toLowerCase();
        for (String suffix : IGNORE_SUFFIX) {
            if (uri.endsWith(suffix))
                return false;
        }
        return true;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        RequestEventSubject eventSubject = new RequestEventSubject();
        if (!ignoreFilter(request)) {
            chain.doFilter(servletRequest, servletResponse);
            return;
        }
        try {
            HttpProxySessionServletRequestWrapper requestWrapper = new HttpProxySessionServletRequestWrapper(request, response,
                    this.proxySessionManager, eventSubject);
            chain.doFilter(requestWrapper, servletResponse);
        } catch (Exception e) {
            logger.error("ProxySessionFilter => ", e);
        } finally {
            eventSubject.completed(request, response);
        }

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        
    }

    @Override
    public void destroy() {
        
    }
}
