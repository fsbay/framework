package com.fsbay.framework.security.api.impl;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;

import org.apache.commons.codec.binary.Base64;

import com.fsbay.framework.security.api.Constants;

/**
 * 基于PKCS1规范的RSA私钥签名实现
 *
 * @author dengzhineng
 * @mail zhineng.deng@163.com
 * @date: 2019年6月15日 上午8:38:26
 * @version 1.0
 * @since JDK 1.8
 */
public class RSAPKCS8Sign extends BaseSign {

    private PrivateKey privateKey;

    public RSAPKCS8Sign() {
    }

    /**
     * 构造基于PKCS1+RSA的私钥签名对象
     * 
     * @param rsaPrivateKey
     *            base64编码的rsa私钥
     * @throws Exception
     */
    public RSAPKCS8Sign(String rsaPrivateKey) throws Exception {
        init(rsaPrivateKey);
    }

    public RSAPKCS8Sign(byte[] bytes) throws Exception {
        init(bytes);
    }

    protected PrivateKey getKey(byte[] bytes) throws Exception {
        // 解密由base64编码的私钥
        PKCS8EncodedKeySpec privateKeyPKCS8 = new PKCS8EncodedKeySpec(bytes);
        KeyFactory keyFactory = KeyFactory
                .getInstance(Constants.RSA_KEY_ALGORITHM);
        return keyFactory.generatePrivate(privateKeyPKCS8);
    }

    @Override
    protected Signature createSignature() throws Exception {
        // 用私钥对信息生成数字签名
        Signature signature = Signature
                .getInstance(Constants.RSA_SIGNATURE_ALGORITHM);
        // 初始化signature
        signature.initSign(privateKey);
        return signature;
    }

    @Override
    public void init(byte[] bytes) throws Exception {
        privateKey = getKey(bytes);
        signature = createSignature();
    }

    @Override
    public void init(String rsaPrivateKey) throws Exception {
        init(Base64.decodeBase64(rsaPrivateKey));
    }

}
