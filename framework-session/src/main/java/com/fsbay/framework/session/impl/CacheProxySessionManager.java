package com.fsbay.framework.session.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fsbay.framework.cache.CacheClient;
import com.fsbay.framework.cache.CacheKeyUtils;
import com.fsbay.framework.session.utils.SessionUtils;
import com.fsbay.framework.session.wrapper.HttpProxySessionServletRequestWrapper;
import com.fsbay.framework.session.wrapper.ProxySessionException;
import com.fsbay.framework.session.wrapper.ProxySessionListener;
import com.fsbay.framework.session.wrapper.ProxySessionManager;
import com.fsbay.framework.session.wrapper.RequestEventObserver;
import com.fsbay.framework.session.wrapper.RequestEventSubject;

import lombok.Setter;

/**
 * 
 *
 * @author dengzhineng
 * @mail zhineng.deng@163.com
 * @date: 2019年6月14日 下午5:07:41
 * @version 1.0
 * @since JDK 1.8
 */
@Setter
public class CacheProxySessionManager implements ProxySessionManager {
    private static final Logger logger = LoggerFactory.getLogger(CacheProxySessionManager.class);

    private CacheClient cacheClient;
    /**if the sParamNams is null sName is session name other sName is prefix**/
    private String sName;
    /**create session by parameter split ,**/
    private String sParamNams;
    /** the value is [token | cookie] **/
    private String sType;
    private String cachePre;
    private int maxInactiveInterval = 1800;
    private int expirationUpdateInterval = 300;
    /**domain for cookie**/
    private String domain;

    @Override
    public HttpProxySession createSession(HttpProxySessionServletRequestWrapper request, HttpServletResponse response,
            RequestEventSubject requestEventSubject, boolean create) {
        String sessionName = SessionUtils.getCName(SessionUtils.splitParamNams(sParamNams), sName, request);
        String sid = SessionUtils.getSid(sessionName, sType, request);

        HttpProxySession session = null;
        if ((StringUtils.isEmpty(sid)) && (!create))
            return null;
        if (StringUtils.isNotEmpty(sid)) {
            session = loadSession(sid);
        }
        if ((session == null) && (create)) {
            session = createEmptySession(sessionName, response);
        }
        if (session != null)
            attachEvent(sessionName, session, request, response, requestEventSubject);
        return session;
    }

    @Override
    public void saveSession(HttpProxySession session, HttpServletRequest request, HttpServletResponse response) {
        try {
            logger.debug("HttpProxySession saveSession sid:{},isNew:{},isDiry:{},isExpired:{}",session.id,session.isNew,session.isDirty,session.isExpired());
            String cKey = CacheKeyUtils.genDefaultKey(cachePre, session.id);
            if (session.expired) {
                cacheClient.delete(cKey);
            } else {
                cacheClient.set(cKey, session, session.maxInactiveInterval + this.expirationUpdateInterval);
            }
        } catch (Exception e) {
            throw new ProxySessionException(e);
        }

    }

    /**
     * 
     * 
     * @author dengzhineng 
     * @date: 2019年6月14日 下午5:04:01
     * @version 1.0
     *
     * @param sid
     * @return
     */
    private HttpProxySession loadSession(String sid) {
        String cKey = null;
        try {
            cKey = CacheKeyUtils.genDefaultKey(cachePre, sid);
            HttpProxySession session = cacheClient.get(cKey);
            if (session != null) {
                session.isNew = false;
                session.isDirty = false;
            }
            logger.debug("loadSession sid:{},cKey:{} session exist:{}", sid, cKey, session != null);
            return session;
        } catch (Exception e) {
            logger.warn("exception loadSession sid:" + sid + ",ckey:" + cKey, e);
        }
        return null;
    }

    /**
     * 
     * 
     * @author dengzhineng 
     * @date: 2019年6月14日 下午5:04:07
     * @version 1.0
     *
     * @param sessionName
     * @param response
     * @return
     */
    private HttpProxySession createEmptySession(String sessionName, HttpServletResponse response) {
        HttpProxySession session = new HttpProxySession();
        session.id = SessionUtils.createSid(cacheClient);
        session.creationTime = System.currentTimeMillis();
        session.maxInactiveInterval = maxInactiveInterval;
        session.isNew = true;
        SessionUtils.setHeader(domain, sessionName, sType, session, response);
        logger.debug("HttpProxySession Create sid:{},sname:{}", session.id, sessionName);
        return session;
    }

    /**
     * 
     * 
     * @author dengzhineng 
     * @date: 2019年6月14日 下午5:04:13
     * @version 1.0
     *
     * @param sessionName
     * @param session
     * @param request
     * @param response
     * @param requestEventSubject
     */
    private void attachEvent(final String sessionName, final HttpProxySession session, final HttpServletRequestWrapper request,
            final HttpServletResponse response, RequestEventSubject requestEventSubject) {
        session.setListener(new ProxySessionListener() {

            @Override
            public void onAttributeChanged(HttpProxySession httpProxySession) {
            }

            @Override
            public void onInvalidated(HttpProxySession httpProxySession) {
                saveSession(session, request, response);
                SessionUtils.setHeader(domain, sessionName, sType, session, response);
            }

        });
        requestEventSubject.attach(new RequestEventObserver() {
            @Override
            public void completed(HttpServletRequest request, HttpServletResponse response) {
                int updateInterval = (int) ((System.currentTimeMillis() - session.lastAccessedTime) / 1000L);
                logger.debug("HttpProxySession request sid:{},lastAccessedTime:{},updateInterval:{}",session.id,session.lastAccessedTime,updateInterval);
                if ((!session.isNew) && (!session.isDirty) && (updateInterval < expirationUpdateInterval)) {
                    return;
                }
                if ((session.isNew) && (session.expired))
                    return;
                session.lastAccessedTime = System.currentTimeMillis();
                saveSession(session, request, response);
            }

        });
    }

}