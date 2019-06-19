package com.fsbay.framework.security.api.impl;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;

import com.fsbay.framework.security.api.Constants;
import com.fsbay.framework.security.api.Decrypt;

/**
 * 解密接口抽象实现类（非线程安全）
 *
 * @author dengzhineng
 * @mail zhineng.deng@163.com
 * @date: 2019年6月15日 上午8:25:26
 * @version 1.0
 * @since JDK 1.8
 */
public abstract class BaseDecrypt implements Decrypt {

    protected Cipher cipher;

    @Override
    public byte[] decrypt(String encryptStr) throws Exception {
        try {
            return this.decrypt(Base64.decodeBase64(encryptStr));
        } catch (Exception e) {
            this.cipher = createCipher();
            throw e;
        }
    }

    @Override
    public String decrypt(String encryptStr, String charset) throws Exception {
        byte[] bytes = decrypt(encryptStr);
        if (bytes == null || bytes.length == 0) {
            return "";
        } else {
            if (charset == null) {
                charset = Constants.DEFAULT_CHARSET;
            }
            return new String(bytes, charset);
        }
    }

    @Override
    public byte[] decrypt(byte[] encrypt) throws Exception {
        try {
            return this.cipher.doFinal(encrypt);
        } catch (Exception e) {
            this.cipher = createCipher();
            throw e;
        }
    }

    /**
     * 创建对应的加密解密Cipher
     */
    protected abstract Cipher createCipher() throws Exception;

}
