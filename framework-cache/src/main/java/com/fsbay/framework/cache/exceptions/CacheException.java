package com.fsbay.framework.cache.exceptions;

/**
 * 
 *
 * @author dengzhineng
 * @mail zhineng.deng@163.com
 * @date: 2019年6月17日 上午11:50:55
 * @version 1.0
 * @since JDK 1.8
 */
public class CacheException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public static final int DEFAULT_CODE = 1;
    public static final String DEFAULT_MSG = "System error !";

    protected String msg;
    protected int code;

    public CacheException(int code, String msgFormat, Object... args) {
        super(String.format(msgFormat, args));
        this.code = code;
        this.msg = String.format(msgFormat, args);
    }

    public CacheException() {
        super();
    }

    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return code;
    }

    public CacheException newInstance(String msgFormat, Object... args) {
        return new CacheException(this.code, msgFormat, args);
    }

    public CacheException(String message, Throwable cause) {
        super(message, cause);
    }

    public CacheException(Throwable cause) {
        super(cause);
    }

    public CacheException(String message) {
        super(message);
    }
}