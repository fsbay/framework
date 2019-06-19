package com.fsbay.framework.security.api.impl;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import com.fsbay.framework.security.api.Constants;

/**
 * DES解密实现
 *
 * @author dengzhineng
 * @mail zhineng.deng@163.com
 * @date: 2019年6月15日 上午8:33:40
 * @version 1.0
 * @since JDK 1.8
 */
public class DESDecrypt extends BaseDecrypt {

    private SecretKey secretkey;

    public DESDecrypt() {
    }

    public DESDecrypt(String secretKey) throws Exception {
        init(secretKey);
    }

    public DESDecrypt(byte[] bytes) throws Exception {
        init(bytes);
    }

    protected SecretKey getSecretKey(byte[] bytes) throws Exception {
        DESKeySpec dks = new DESKeySpec(bytes);
        SecretKeyFactory keyFactory = SecretKeyFactory
                .getInstance(Constants.DES_KEY_ALGORITHM);
        return keyFactory.generateSecret(dks);
    }

    @Override
    protected Cipher createCipher() throws Exception {
        SecureRandom sr = new SecureRandom();
        Cipher cipher = Cipher.getInstance(Constants.DES_KEY_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretkey, sr);
        return cipher;
    }

    @Override
    public void init(byte[] bytes) throws Exception {
        secretkey = getSecretKey(bytes);
        cipher = createCipher();
    }

    @Override
    public void init(String secryptKey) throws Exception {
        init(secryptKey.getBytes(Constants.DEFAULT_CHARSET));
    }
}
