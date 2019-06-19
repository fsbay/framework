package com.fsbay.framework.session.wrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 
 *
 * @author dengzhineng
 * @mail zhineng.deng@163.com
 * @date: 2019年6月14日 下午2:04:43
 * @version 1.0
 * @since JDK 1.8
 */
public abstract interface RequestEventObserver
{
  void completed(HttpServletRequest request, HttpServletResponse response);
}