package com.fsbay.framework.security.api;

/**
 * 
 *
 * @author dengzhineng
 * @mail zhineng.deng@163.com
 * @date: 2019年6月15日 上午8:21:04
 * @version 1.0
 * @since JDK 1.8
 */
public interface Encrypt extends Initable {

    /**
     * 数据加密
     * 
     * @param src
     *            原始数据
     * @return 加密后的base64编码数据
     * @throws Exception
     */
    public byte[] encryptToBytes(byte[] src) throws Exception;

    /**
     * 数据加密
     * 
     * @param src
     *            原始数据
     * @return 加密后的base64编码数据
     * @throws Exception
     */
    public String encrypt(byte[] src) throws Exception;

    /**
     * 数据加密
     * 
     * @param src
     *            原始数据
     * @param charset
     *            原始数据的字符编码
     * @return 加密后的base64编码数据
     * @throws Exception
     */
    public String encrypt(String src, String charset) throws Exception;
}