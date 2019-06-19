package com.fsbay.framework.security.api.impl;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;

import com.fsbay.framework.security.api.Constants;

/**
 * 基于X509规范的RSA公钥解密实现
 *
 * @author dengzhineng
 * @mail zhineng.deng@163.com
 * @date: 2019年6月15日 上午8:38:55
 * @version 1.0
 * @since JDK 1.8
 */
public class RSAX509PublicKeyDecrypt extends BaseDecrypt {

    private PublicKey publicKey;

    public RSAX509PublicKeyDecrypt() {
    }

    /**
     * 创建基于X509 + RSA的公钥解密对象
     * 
     * @param rsaPublicKey
     *            base64编码的rsa公钥
     * @throws Exception
     */
    public RSAX509PublicKeyDecrypt(String rsaPublicKey) throws Exception {
        init(rsaPublicKey);
    }

    public RSAX509PublicKeyDecrypt(byte[] bytes) throws Exception {
        init(bytes);
    }

    protected PublicKey getKey(byte[] bytes) throws Exception {
        X509EncodedKeySpec publicKeyX509 = new X509EncodedKeySpec(bytes);
        KeyFactory keyFactory = KeyFactory
                .getInstance(Constants.RSA_KEY_ALGORITHM);
        return keyFactory.generatePublic(publicKeyX509);
    }

    @Override
    protected Cipher createCipher() throws Exception {
        Cipher cipher = Cipher.getInstance(Constants.RSA_PKCS1_KEY);
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        return cipher;
    }

    @Override
    public void init(byte[] bytes) throws Exception {
        publicKey = getKey(bytes);
        cipher = createCipher();
    }

    @Override
    public void init(String rsaPublicKey) throws Exception {
        init(Base64.decodeBase64(rsaPublicKey));
    }

}
