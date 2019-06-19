package com.fsbay.framework.security.api.impl;

import java.util.Arrays;

import org.apache.commons.codec.digest.DigestUtils;

import com.fsbay.framework.security.api.Verify;

/**
 * 基于MD5的验签实现（线程安全）:<br/>
 * 计算Md5签名采用的<code>org.apache.commons.codec.digest.DigestUtils</code>的
 * <code>md5Hex</code>实现<br/>
 *
 * @author dengzhineng
 * @mail zhineng.deng@163.com
 * @date: 2019年6月15日 上午8:36:48
 * @version 1.0
 * @since JDK 1.8
 */
public class MD5Verify implements Verify {

    @Override
    public boolean verify(final byte[] data, final String sign)
            throws Exception {
        String md5 = DigestUtils.md5Hex(data);
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
        return Arrays.equals(DigestUtils.md5(data), sign);
    }

    @Override
    public boolean verify(String data, String charset, byte[] sign)
            throws Exception {
        return verify(data.getBytes(charset), sign);
    }
}
