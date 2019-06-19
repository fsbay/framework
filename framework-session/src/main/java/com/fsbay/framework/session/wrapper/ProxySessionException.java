package com.fsbay.framework.session.wrapper;

public class ProxySessionException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public static final int DEFAULT_CODE = 1;
    public static final String DEFAULT_MSG = "System error !";

    public static final int SESSION_EXPIRED_CODE = 2;
    public static final String SESSION_EXPIRED_MSG = "session expired !";

    public static final int PARAM_ERROR_CODE = 3;
    public static final String PARAM_ERROR_MSG = "param error !";

    protected String msg;
    protected int code;

    public ProxySessionException(int code, String msgFormat, Object... args) {
        super(String.format(msgFormat, args));
        this.code = code;
        this.msg = String.format(msgFormat, args);
    }

    public ProxySessionException() {
        super();
    }

    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return code;
    }

    public ProxySessionException newInstance(String msgFormat, Object... args) {
        return new ProxySessionException(this.code, msgFormat, args);
    }

    public ProxySessionException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProxySessionException(Throwable cause) {
        super(cause);
    }

    public ProxySessionException(String message) {
        super(message);
    }
}