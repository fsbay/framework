package com.fsbay.framework.security.api.impl;

import java.security.Signature;
import java.security.SignatureException;

import org.apache.commons.codec.binary.Base64;

import com.fsbay.framework.security.api.Verify;

/**
 * 验签接口抽象实现类（非线程安全）
 *
 * @author dengzhineng
 * @mail zhineng.deng@163.com
 * @date: 2019年6月15日 上午8:33:20
 * @version 1.0
 * @since JDK 1.8
 */
public abstract class BaseVerify implements Verify {

    protected Signature signature;

    @Override
    public boolean verify(byte[] data, String sign) throws Exception {
        try {
            signature.update(data);
            return signature.verify(Base64.decodeBase64(sign));
        } catch (SignatureException e) {
            this.signature = createSignature();
            throw e;
        }
    }

    @Override
    public boolean verify(String data, String charset, String sign)
            throws Exception {
        return verify(data.getBytes(charset), sign);
    }

    /**
     * 创建签名验签使用的Signature
     */
    protected abstract Signature createSignature() throws Exception;

    @Override
    public boolean verify(byte[] data, byte[] sign) throws Exception {
        try {
            signature.update(data);
            return signature.verify(sign);
        } catch (SignatureException e) {
            this.signature = createSignature();
            throw e;
        }
    }

    @Override
    public boolean verify(String data, String charset, byte[] sign)
            throws Exception {
        return verify(data.getBytes(charset), sign);
    }

}
