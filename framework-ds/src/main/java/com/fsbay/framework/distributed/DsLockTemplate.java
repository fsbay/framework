package com.fsbay.framework.distributed;

/**
   *   分布式锁模板类
 *
 * @author dengzhineng
 * @mail zhineng.deng@163.com
 * @date: 2019年6月18日 下午8:32:13
 * @version 1.0
 * @since JDK 1.8
 */
public interface DsLockTemplate{
    /**
     * @param lockId 锁id(对应业务唯一ID)
     * @param timeout 单位毫秒
     * @param callback 回调函数
     * 
     * @author dengzhineng 
     * @date: 2019年6月18日 下午8:32:41
     * @version 1.0
     *
     * @param lockId
     * @param timeout
     * @param callback
     * @return
     */
    public Object execute(String biz,String lockId, int timeout, LockCallback callback);
}
