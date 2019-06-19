package com.fsbay.framework.security.api.impl;

import java.util.Arrays;

import org.apache.commons.codec.digest.DigestUtils;

import com.fsbay.framework.security.api.Verify;

/**
 * 
 *
 * @author dengzhineng
 * @mail zhineng.deng@163.com
 * @date: 2019年6月15日 上午8:41:55
 * @version 1.0
 * @since JDK 1.8
 */
public class SHA256Verify implements Verify {

    @Override
    public boolean verify(final byte[] data, final String sign)
            throws Exception {
        String md5 = DigestUtils.sha256Hex(data);
        return sign.equals(md5);
    }

    @Override
    public boolean verify(String data, String charset, String sign)
            throws Exception {
        return verify(data.getBytes(charset), sign);
    }

    @Override
    public void init(byte[] secryptKeys) throws Exception {
    }

    @Override
    public void init(String secryptKey) throws Exception {
    }

    @Override
    public boolean verify(byte[] data, byte[] sign) throws Exception {
        return Arrays.equals(DigestUtils.sha256(data), sign);
    }

    @Override
    public boolean verify(String data, String charset, byte[] sign)
            throws Exception {
        return verify(data.getBytes(charset), sign);
    }
}
