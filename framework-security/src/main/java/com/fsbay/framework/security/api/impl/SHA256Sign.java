package com.fsbay.framework.security.api.impl;

import org.apache.commons.codec.digest.DigestUtils;

import com.fsbay.framework.security.api.Sign;

/**
 * 
 *
 * @author dengzhineng
 * @mail zhineng.deng@163.com
 * @date: 2019年6月15日 上午8:41:28
 * @version 1.0
 * @since JDK 1.8
 */
public class SHA256Sign implements Sign {

    @Override
    public String sign(final byte[] data) throws Exception {
        return DigestUtils.sha256Hex(data);
    }

    @Override
    public String sign(String data, String charset) throws Exception {
        return sign(data.getBytes(charset));
    }

    @Override
    public void init(byte[] secryptKeys) throws Exception {
    }

    @Override
    public void init(String secryptKey) throws Exception {
    }

    @Override
    public byte[] sign2Bytes(byte[] data) throws Exception {
        return DigestUtils.sha256(data);
    }

    @Override
    public byte[] sign2Bytes(String data, String charset) throws Exception {
        return sign2Bytes(data.getBytes(charset));
    }
}
