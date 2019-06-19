package com.fsbay.framework.security.api.impl;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.X509EncodedKeySpec;

import org.apache.commons.codec.binary.Base64;

import com.fsbay.framework.security.api.Constants;

/**
 * 
 * 基于X509规范的RSA公钥验签SHA-1实现类
 * @author dengzhineng
 * @mail zhineng.deng@163.com
 * @date: 2019年6月15日 上午8:41:07
 * @version 1.0
 * @since JDK 1.8
 */
public class SHA1withRSAX509Verify extends BaseVerify {

    private PublicKey pubKey;

    public SHA1withRSAX509Verify() {
    }

    /**
     * 创建基于X509 + RSA的公钥鉴权对象
     * 
     * @param rsaPublicKey
     *            base64编码的rsa公钥
     * @throws Exception
     */
    public SHA1withRSAX509Verify(String rsaPublicKey) throws Exception {
        init(rsaPublicKey);
    }

    public SHA1withRSAX509Verify(byte[] bytes) throws Exception {
        init(bytes);
    }

    protected PublicKey getKey(byte[] bytes) throws Exception {
        // 构造X509EncodedKeySpec对象
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(bytes);
        // KEY_ALGORITHM 指定的加密算法
        KeyFactory keyFactory = KeyFactory
                .getInstance(Constants.RSA_KEY_ALGORITHM);
        // 取公钥匙对象
        return keyFactory.generatePublic(keySpec);
    }

    @Override
    protected Signature createSignature() throws Exception {
        // 用公钥对象校验签名
        Signature signature = Signature
                .getInstance(Constants.RSA_SIGNATURE_ALGORITHM_SHA1);
        signature.initVerify(pubKey);
        return signature;
    }

    @Override
    public void init(byte[] bytes) throws Exception {
        pubKey = getKey(bytes);
        signature = createSignature();
    }

    @Override
    public void init(String rsaPublicKey) throws Exception {
        init(Base64.decodeBase64(rsaPublicKey));
    }

}
