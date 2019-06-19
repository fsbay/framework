package com.fsbay.framework.security.api.impl;

import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import com.fsbay.framework.security.api.Constants;

/**
 * 基于PKCS5规范的AES解密实现
 *
 * @author dengzhineng
 * @mail zhineng.deng@163.com
 * @date: 2019年6月15日 上午8:24:37
 * @version 1.0
 * @since JDK 1.8
 */
public class AESPKCS5Decrypt extends BaseDecrypt {

    private SecretKeySpec key;

    public AESPKCS5Decrypt() {
    }

    public AESPKCS5Decrypt(String password) throws Exception {
        init(password);
    }

    public AESPKCS5Decrypt(byte[] bytes) throws Exception {
        init(bytes);
    }

    public SecretKeySpec getKey(byte[] bytes) {
        if (bytes.length < 16) {
            bytes = Arrays.copyOf(bytes, 16);
        }
        return new SecretKeySpec(bytes, 0, 16, Constants.AES_KEY_ALGORITHM);
    }

    @Override
    protected Cipher createCipher() throws Exception {
        Cipher cipher = Cipher.getInstance(Constants.AES_PKCS5_KEY);
        cipher.init(Cipher.DECRYPT_MODE, key);
        return cipher;
    }

    @Override
    public void init(byte[] bytes) throws Exception {
        key = getKey(bytes);
        this.cipher = createCipher();
    }

    @Override
    public void init(String secryptKey) throws Exception {
        init(secryptKey.getBytes(Constants.DEFAULT_CHARSET));
    }

}
