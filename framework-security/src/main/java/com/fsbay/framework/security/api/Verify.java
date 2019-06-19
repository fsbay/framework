package com.fsbay.framework.security.api;

/**
 * 验签接口：<br/>
 * 通过公钥，签名校验数据的有效性
 * 
 * @author dengzhineng
 * @date: 2017年1月22日 上午11:13:23
 * @version 1.0
 * @since JDK 1.7
 */
public interface Verify extends Initable {

    /**
     * 校验数字签名
     * 
     * @param data
     *            原始数据
     * @param sign
     *            数字签名
     * @return 验签成功:true，验签失败:false
     * @throws Exception
     */
    public boolean verify(byte[] data, String sign) throws Exception;

    /**
     * 验签
     * 
     * @param data
     *            原始数据
     * @param charset
     *            原始数据字符编码
     * @param sign
     *            数字签名
     * @return 验签结果：成功则true，失败则false
     * @throws Exception
     */
    public boolean verify(String data, String charset, String sign)
            throws Exception;

    /**
     * 校验数字签名
     * 
     * @param data
     *            原始数据
     * @param sign
     *            数字签名
     * @return 验签成功:true，验签失败:false
     * @throws Exception
     */
    public boolean verify(byte[] data, byte[] sign) throws Exception;

    /**
     * 验签
     * 
     * @param data
     *            原始数据
     * @param charset
     *            原始数据字符编码
     * @param sign
     *            数字签名
     * @return 验签结果：成功则true，失败则false
     * @throws Exception
     */
    public boolean verify(String data, String charset, byte[] sign)
            throws Exception;
}