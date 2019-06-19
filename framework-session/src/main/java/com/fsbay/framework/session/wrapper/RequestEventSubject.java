package com.fsbay.framework.session.wrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 
 *
 * @author dengzhineng
 * @mail zhineng.deng@163.com
 * @date: 2019年6月14日 下午2:05:00
 * @version 1.0
 * @since JDK 1.8
 */
public class RequestEventSubject {
    private RequestEventObserver listener;

    public void attach(RequestEventObserver eventObserver) {
        this.listener = eventObserver;
    }

    public void detach() {
        this.listener = null;
    }

    public void completed(HttpServletRequest servletRequest, HttpServletResponse response) {
        if (this.listener != null) {
            this.listener.completed(servletRequest, response);
        }
    }
}