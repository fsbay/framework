package com.fsbay.framework.security.api.impl;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

import com.fsbay.framework.security.api.Constants;

/**
 * 3DES解密实现
 *
 * @author dengzhineng
 * @mail zhineng.deng@163.com
 * @date: 2019年6月15日 上午8:43:29
 * @version 1.0
 * @since JDK 1.8
 */
public class TripleDESDecrypt extends BaseDecrypt {

    private SecretKey secretkey;

    public TripleDESDecrypt() {
    }

    public TripleDESDecrypt(String secretKey) throws Exception {
        init(secretKey);
    }

    public TripleDESDecrypt(byte[] bytes) throws Exception {
        init(bytes);
    }

    protected SecretKey getSecretKey(byte[] bytes) throws Exception {
        DESedeKeySpec dks = new DESedeKeySpec(bytes);
        SecretKeyFactory keyFactory = SecretKeyFactory
                .getInstance(Constants.TRIPLE_DES_KEY_ALGORITHM);
        return keyFactory.generateSecret(dks);
    }

    @Override
    protected Cipher createCipher() throws Exception {
        SecureRandom sr = new SecureRandom();
        Cipher cipher = Cipher.getInstance(Constants.TRIPLE_DES_KEY_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretkey, sr);
        return cipher;
    }

    @Override
    public void init(byte[] bytes) throws Exception {
        secretkey = getSecretKey(bytes);
        cipher = createCipher();
    }

    @Override
    public void init(String secretKey) throws Exception {
        init(secretKey.getBytes(Constants.DEFAULT_CHARSET));
    }
}
