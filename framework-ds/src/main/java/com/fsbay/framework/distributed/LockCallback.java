package com.fsbay.framework.distributed;

/**
 * 
 *
 * @author dengzhineng
 * @mail zhineng.deng@163.com
 * @date: 2019年6月18日 下午8:33:47
 * @version 1.0
 * @since JDK 1.8
 */
public interface LockCallback {
    /**
     * 
     * 
     * @author dengzhineng 
     * @date: 2019年6月18日 下午8:33:55
     * @version 1.0
     *
     * @return
     * @throws InterruptedException
     */
    public Object onGetLock() throws InterruptedException;

    /**
     * 
     * 
     * @author dengzhineng 
     * @date: 2019年6月18日 下午8:34:04
     * @version 1.0
     *
     * @return
     * @throws InterruptedException
     */
    public Object onTimeout() throws InterruptedException;
}
