package com.fsbay.framework.distributed;

/**
 * 
 * 分布式锁模板类
 * @author dengzhineng
 * @mail zhineng.deng@163.com
 * @date: 2019年6月18日 下午8:44:02
 * @version 1.0
 * @since JDK 1.8
 */
public interface DsTokenTemplate {

    /**
     * 
     * 
     * @author dengzhineng 
     * @date: 2019年6月18日 下午8:44:30
     * @version 1.0
     *
     * @param lockId 锁id(对应业务唯一ID)
     * @param callback 回调函数
     * @return
     */
    public Object execute(String biz,String lockId,int lockTimeout, TokenCallback callback);
}
