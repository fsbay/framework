package com.fsbay.framework.distributed.exceptions;

/**
 * 
 *
 * @author dengzhineng
 * @mail zhineng.deng@163.com
 * @date: 2019年6月17日 上午11:50:55
 * @version 1.0
 * @since JDK 1.8
 */
public class DistributedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public static final int DEFAULT_CODE = 1;
    public static final String DEFAULT_MSG = "distributed error !";

    protected String msg;
    protected int code;

    public DistributedException(int code, String msgFormat, Object... args) {
        super(String.format(msgFormat, args));
        this.code = code;
        this.msg = String.format(msgFormat, args);
    }

    public DistributedException() {
        super();
    }

    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return code;
    }

    public DistributedException newInstance(String msgFormat, Object... args) {
        return new DistributedException(this.code, msgFormat, args);
    }

    public DistributedException(String message, Throwable cause) {
        super(message, cause);
    }

    public DistributedException(Throwable cause) {
        super(cause);
    }

    public DistributedException(String message) {
        super(message);
    }
}