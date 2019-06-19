package com.fsbay.framework.security.api.impl;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;

import com.fsbay.framework.security.api.Encrypt;

/**
 * 加密接口抽象实现类（非线程安全）
 *
 * @author dengzhineng
 * @mail zhineng.deng@163.com
 * @date: 2019年6月15日 上午8:25:46
 * @version 1.0
 * @since JDK 1.8
 */
public abstract class BaseEncrypt implements Encrypt {

    protected Cipher cipher;

    @Override
    public String encrypt(byte[] src) throws Exception {
        try {
            return Base64.encodeBase64String(encryptToBytes(src));
        } catch (Exception e) {
            this.cipher = createCipher();
            throw e;
        }
    }

    @Override
    public String encrypt(String src, String charset) throws Exception {
        return encrypt(src.getBytes(charset));
    }

    @Override
    public byte[] encryptToBytes(byte[] src) throws Exception {
        try {
            return cipher.doFinal(src);
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
