package com.fsbay.framework.security.api.impl;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;

import com.fsbay.framework.security.api.Constants;

/**
 * 基于X509规范的RSA公钥加密
 *
 * @author dengzhineng
 * @mail zhineng.deng@163.com
 * @date: 2019年6月15日 上午8:39:27
 * @version 1.0
 * @since JDK 1.8
 */
public class RSAX509PublicKeyEncrypt extends BaseEncrypt {

    private PublicKey publicKey;

    public RSAX509PublicKeyEncrypt() {
    }

    /**
     * 构造X509+RSA公钥加密对象
     * 
     * @param rsaPublicKey
     *            base64编码的ras公钥
     * @throws Exception
     */
    public RSAX509PublicKeyEncrypt(String rsaPublicKey) throws Exception {
        init(rsaPublicKey);
    }

    public RSAX509PublicKeyEncrypt(byte[] bytes) throws Exception {
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
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
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
