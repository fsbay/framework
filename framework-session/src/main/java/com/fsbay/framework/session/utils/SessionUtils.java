/**
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Company: fsbay.com </p>
 */
package com.fsbay.framework.session.utils;

import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.fsbay.framework.cache.CacheClient;
import com.fsbay.framework.session.impl.HttpProxySession;
import com.fsbay.framework.session.wrapper.ProxySessionException;

/** 
 *
 * @author dengzhineng
 * @mail zhineng.deng@163.com
 * @date: 2019年6月14日 下午2:27:19
 * @version 1.0
 * @since JDK 1.8
 */
public class SessionUtils {
    private static final Logger logger = LoggerFactory.getLogger(SessionUtils.class);
    /**
     * 缓存自增key
     */
    private static final String CACHE_SESSION_INCR_KEY="com.fsbay.session.incr.key";
    
    /**
     * split the string by ,
     * 
     * @author dengzhineng 
     * @date: 2019年6月14日 下午3:23:16
     * @version 1.0
     *
     * @param sParamNams
     * @return
     */
    public static String[] splitParamNams(String sParamNams) {
        if(StringUtils.isBlank(sParamNams))
            return null;        
        return sParamNams.split(",");
    }
    
    /**
     * 获取sessionid
     * 
     * @author dengzhineng 
     * @date: 2019年6月14日 下午3:14:13
     * @version 1.0
     *
     * @param sessionType  session类型
     * @param paramNames   参数数组
     * @param cName        Session名称标示或者前缀 
     * @param request
     * @param response
     * @return
     */
    public static String getSid(String sessionName,String sessionType,HttpServletRequestWrapper request) {
        String sessionId = null;
        if("token".equals(sessionType)) {
            return request.getParameter(sessionName);
        }
        
        Cookie[] cookies = request.getCookies();
        if ((cookies == null) || (cookies.length == 0))
            return null;
        for (Cookie cookie : cookies) {
            if (sessionName.equals(cookie.getName()))
                sessionId = cookie.getValue();
        }
        return sessionId;
    }
    /**
     * create session ID
     * 
     * @author dengzhineng 
     * @date: 2019年6月14日 下午4:05:47
     * @version 1.0
     *
     * @param cacheClient
     * @return
     */
    public static String createSid(CacheClient cacheClient) {
        long seq = cacheClient.incr(CACHE_SESSION_INCR_KEY);
        return System.currentTimeMillis() + "-" + UUID.randomUUID().toString().replace("-", "").toUpperCase().substring(10)+seq;
    }
    
    public static void setHeader(String domain,String sessionName,String sessionType,HttpProxySession session,HttpServletResponse response) {
        if ((!session.isNew()) && (!session.isExpired())) {
            return;
        }
        if("token".equals(sessionType)) {
            response.setHeader(sessionName, session.getId());
            return;
        }else {
            Cookie cookie = new Cookie(sessionName, null);
            cookie.setPath("/");
            if (StringUtils.isNoneBlank(domain)) {
                cookie.setDomain(domain);
            }
            if (session.isExpired()) {
                cookie.setMaxAge(0);
            } else if (session.isNew()) {
                cookie.setValue(session.getId());
            }
            response.addCookie(cookie);
            logger.debug("add response cookie sid:{},sname:{},cookie:{}" ,session.getId(),sessionName,JSON.toJSONString(cookie));
        }
    }
    
    public static String getCName(String[] paramNames,String sName,HttpServletRequest request) {
        if(paramNames == null || paramNames.length==0) {
            return sName;
        }
        StringBuilder cookiesName = new StringBuilder();
        cookiesName.append(sName);
        for (String paramName : paramNames) {
            String tmp = request.getParameter(paramName);
            if(StringUtils.isBlank(tmp)) {
                    logger.error("parameter:{} is null uri:{} queryString:{}",tmp,request.getRequestURI(),request.getQueryString());
                throw new ProxySessionException(ProxySessionException.PARAM_ERROR_CODE,ProxySessionException.PARAM_ERROR_MSG);
            }
            cookiesName.append("-").append(tmp);
        }
            logger.debug("cookie name:"+cookiesName.toString());
            return cookiesName.toString();

    }

}
