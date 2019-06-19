package com.fsbay.framework.distributed;

/**
 * 
 * @author dengzhineng
 * @date: 2017年8月30日 下午3:05:42
 * @version 1.0
 * @since JDK 1.7
 */
public interface TokenCallback {
	/**
	 * 获取到token
	 * @return
	 * @throws InterruptedException
	 */
    public Object onCreateSucess() throws InterruptedException;

    
    /**
     * 创建token失败，代表重复提交订单
     * @return
     * @throws InterruptedException
     */
    public Object onCreateFail() throws InterruptedException;
}
