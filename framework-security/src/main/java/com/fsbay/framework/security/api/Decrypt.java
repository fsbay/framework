package com.fsbay.framework.security.api;

/**
 * 
 *
 * @author dengzhineng
 * @mail zhineng.deng@163.com
 * @date: 2019年6月15日 上午8:20:27
 * @version 1.0
 * @since JDK 1.8
 */
public interface Decrypt extends Initable {

    /**
     * 数据解密
     * 
     * @param encrypt
     *            加密数据
     * @return 解密后的数据
     * @throws Exception
     */
    public byte[] decrypt(byte[] encrypt) throws Exception;

    /**
     * 数据解密
     * 
     * @param encryptStr
     *            待解密的base64编码的数据
     * @return 解密后的数据
     * @throws Exception
     */
    public byte[] decrypt(String encryptStr) throws Exception;

    /**
     * 数据解密
     * 
     * @param encryptStr
     *            待解密的base64编码的数据
     * @param charset
     *            字符编码，charset为<code>null</code>时默认设值为"UTF-8"
     * @return 解密后的数据
     * @throws Exception
     */
    public String decrypt(String encryptStr, String charset) throws Exception;
}