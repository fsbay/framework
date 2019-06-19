package com.fsbay.framework.distributed;

import java.util.concurrent.TimeUnit;

/**
   *  分布式锁接口
 *
 * @author dengzhineng
 * @mail zhineng.deng@163.com
 * @date: 2019年6月18日 下午8:35:48
 * @version 1.0
 * @since JDK 1.8
 */
public interface DsReentrantLock {
    /**
     * 
     * 
     * @author dengzhineng 
     * @date: 2019年6月18日 下午8:35:31
     * @version 1.0
     *
     * @param timeout
     * @param unit
     * @return
     * @throws InterruptedException
     */
    public boolean tryLock(long timeout, TimeUnit unit) throws InterruptedException;

    /**
     * 
     * 
     * @author dengzhineng 
     * @date: 2019年6月18日 下午8:36:06
     * @version 1.0
     *
     */
    public void unlock();
}
