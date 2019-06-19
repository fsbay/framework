package com.fsbay.framework.security.api;

/**
 * 
 * 签名接口：<br/>
 * 使用私钥加密数据，并对生成后的数据进行摘要操作
 * @author dengzhineng
 * @mail zhineng.deng@163.com
 * @date: 2019年6月15日 上午8:22:57
 * @version 1.0
 * @since JDK 1.8
 */
public interface Sign extends Initable {

    /**
     * 对数据进行签名操作
     * 
     * @param data
     *            原始数据
     * @return 数字签名
     * @throws Exception
     */
    public String sign(byte[] data) throws Exception;

    /**
     * 数据签名
     * 
     * @param data
     *            原始数据
     * @param charset
     *            原始数据的字符编码
     * @return 数字签名
     * @throws Exception
     */
    public String sign(String data, String charset) throws Exception;

    /**
     * 对数据进行签名操作
     * 
     * @param data
     *            原始数据
     * @return 数字签名
     * @throws Exception
     */
    public byte[] sign2Bytes(byte[] data) throws Exception;

    /**
     * 数据签名
     * 
     * @param data
     *            原始数据
     * @param charset
     *            原始数据的字符编码
     * @return 数字签名
     * @throws Exception
     */
    public byte[] sign2Bytes(String data, String charset) throws Exception;
}