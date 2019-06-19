package com.fsbay.framework.security.api.impl;

import java.security.Signature;

import org.apache.commons.codec.binary.Base64;

import com.fsbay.framework.security.api.Sign;

/**
 * 签名接口抽象实现类（非线程安全）
 *
 * @author dengzhineng
 * @mail zhineng.deng@163.com
 * @date: 2019年6月15日 上午8:26:07
 * @version 1.0
 * @since JDK 1.8
 */
public abstract class BaseSign implements Sign {

    protected Signature signature;

    @Override
    public String sign(byte[] data) throws Exception {
        try {
            signature.update(data);
            return Base64.encodeBase64String(signature.sign());
        } catch (Exception e) {
            this.signature = createSignature();
            throw e;
        }
    }

    @Override
    public String sign(String data, String charset) throws Exception {
        return sign(data.getBytes(charset));
    }

    /**
     * 创建签名验签使用的Signature
     */
    protected abstract Signature createSignature() throws Exception;

    @Override
    public byte[] sign2Bytes(byte[] data) throws Exception {
        try {
            signature.update(data);
            return signature.sign();
        } catch (Exception e) {
            this.signature = createSignature();
            throw e;
        }
    }

    @Override
    public byte[] sign2Bytes(String data, String charset) throws Exception {
        return sign2Bytes(data.getBytes(charset));
    }
}
