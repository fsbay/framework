package com.fsbay.framework.security.api.impl;

import java.security.KeyFactory;
import java.security.PrivateKey;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;

import com.fsbay.framework.security.api.Constants;

import net.oauth.signature.pem.PKCS1EncodedKeySpec;

/**
 * 基于PKCS1规范的RSA私钥解密实现
 *
 * @author dengzhineng
 * @mail zhineng.deng@163.com
 * @date: 2019年6月15日 上午8:37:20
 * @version 1.0
 * @since JDK 1.8
 */
public class RSAPKCS1PrivateKeyDecrypt extends BaseDecrypt {

    private PrivateKey privateKey;

    public RSAPKCS1PrivateKeyDecrypt() {
    }

    /**
     * 构造PKCS1+RSA私钥解密对象
     * 
     * @param rsaPrivateKey
     *            base64编码的rsa私钥
     * @throws Exception
     */
    public RSAPKCS1PrivateKeyDecrypt(String rsaPrivateKey) throws Exception {
        init(rsaPrivateKey);
    }

    public RSAPKCS1PrivateKeyDecrypt(byte[] bytes) throws Exception {
        init(bytes);
    }

    protected PrivateKey getKey(byte[] bytes) throws Exception {
        // 生成基于PKCS1的私钥编码对象
        PKCS1EncodedKeySpec privateKeyPKCS1 = new PKCS1EncodedKeySpec(bytes);
        KeyFactory keyFactory = KeyFactory
                .getInstance(Constants.RSA_KEY_ALGORITHM);
        // 提取私钥
        return keyFactory.generatePrivate(privateKeyPKCS1.getKeySpec());
    }

    @Override
    protected Cipher createCipher() throws Exception {
        Cipher cipher = Cipher.getInstance(Constants.RSA_PKCS1_KEY);
        // 初始化cipher
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher;
    }

    @Override
    public void init(byte[] bytes) throws Exception {
        this.privateKey = getKey(bytes);
        this.cipher = createCipher();
    }

    @Override
    public void init(String rsaPrivateKey) throws Exception {
        init(Base64.decodeBase64(rsaPrivateKey));
    }

}
