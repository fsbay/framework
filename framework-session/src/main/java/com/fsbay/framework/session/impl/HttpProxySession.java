package com.fsbay.framework.session.impl;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

import com.fsbay.framework.session.wrapper.ProxySessionListener;

public class HttpProxySession implements HttpSession, Serializable {
	private static final long serialVersionUID = 1L;
	protected long creationTime = 0L;
	protected String id;
	protected int maxInactiveInterval;
	protected long lastAccessedTime = 0L;
	protected transient boolean expired = false;
	protected transient boolean isNew = false;
	protected transient boolean isDirty = false;

	private transient ProxySessionListener listener;
	private Map<String, Object> data = new HashMap<String, Object>();

	public void setListener(ProxySessionListener listener) {
		this.listener = listener;
	}

	public long getCreationTime() {
		return this.creationTime;
	}

	public String getId() {
		return this.id;
	}

	public long getLastAccessedTime() {
		return this.lastAccessedTime;
	}

	public void setLastAccessedTime(long lastAccessedTime) {
		this.lastAccessedTime = lastAccessedTime;
	}

	public ServletContext getServletContext() {
		return null;
	}

	public void setMaxInactiveInterval(int i) {
		this.maxInactiveInterval = i;
	}

	public int getMaxInactiveInterval() {
		return this.maxInactiveInterval;
	}

	public HttpSessionContext getSessionContext() {
		return null;
	}

	public Object getAttribute(String key) {
		return this.data.get(key);
	}

	public Object getValue(String key) {
		return this.data.get(key);
	}

	public Enumeration getAttributeNames() {
		final Iterator iterator = this.data.keySet().iterator();
		return new Enumeration() {
			public boolean hasMoreElements() {
				return iterator.hasNext();
			}

			public Object nextElement() {
				return iterator.next();
			}
		};
	}

	public String[] getValueNames() {
		String[] names = new String[this.data.size()];
		return (String[]) this.data.keySet().toArray(names);
	}

	public void setAttribute(String s, Object o) {
		this.data.put(s, o);
		this.isDirty = true;
	}

	public void putValue(String s, Object o) {
		this.data.put(s, o);
		this.isDirty = true;
	}

	public void removeAttribute(String s) {
		this.data.remove(s);
		this.isDirty = true;
	}

	public void removeValue(String s) {
		this.data.remove(s);
		this.isDirty = true;
	}

	public void invalidate() {
		this.expired = true;
		this.isDirty = true;
		if (this.listener != null)
			this.listener.onInvalidated(this);
	}

	public boolean isNew() {
		return this.isNew;
	}

	public boolean isExpired() {
		return expired;
	}

	public void setExpired(boolean expired) {
		this.expired = expired;
	}

	public boolean isDirty() {
		return isDirty;
	}

	public void setDirty(boolean isDirty) {
		this.isDirty = isDirty;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	public ProxySessionListener getListener() {
		return listener;
	}

	public void setCreationTime(long creationTime) {
		this.creationTime = creationTime;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setNew(boolean isNew) {
		this.isNew = isNew;
	}

}