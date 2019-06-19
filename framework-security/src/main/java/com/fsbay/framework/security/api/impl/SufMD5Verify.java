package com.fsbay.framework.security.api.impl;

import java.util.Arrays;

import org.apache.commons.codec.binary.StringUtils;

import com.fsbay.framework.security.api.Verify;

/**
 *  * 基于MD5的验签实现（线程安全）:<br/>
 * 计算Md5签名采用的<code>org.apache.commons.codec.digest.DigestUtils</code>的
 * <code>md5Hex</code>实现<br/>
 *
 * @author dengzhineng
 * @mail zhineng.deng@163.com
 * @date: 2019年6月15日 上午8:43:01
 * @version 1.0
 * @since JDK 1.8
 */
public class SufMD5Verify implements Verify {
    private SufMD5Sign sign;

    @Override
    public boolean verify(final byte[] data, final String sign)
            throws Exception {
        if (sign == null) {
            return false;
        }
        return sign.equalsIgnoreCase(this.sign.sign(data));
    }

    @Override
    public boolean verify(String data, String charset, String sign)
            throws Exception {
        if (sign == null) {
            return false;
        }
        return sign.equalsIgnoreCase(this.sign.sign(data, charset));
    }

    @Override
    public void init(byte[] secryptKeys) throws Exception {
        if (secryptKeys == null) {
            throw new NullPointerException();
        }
        this.sign = new SufMD5Sign();
        this.sign.init(secryptKeys);
    }

    @Override
    public void init(String secryptKey) throws Exception {
        this.init(StringUtils.getBytesUtf8(secryptKey));
    }

    @Override
    public boolean verify(byte[] data, byte[] sign) throws Exception {
        if (sign == null) {
            return false;
        }
        return Arrays.equals(this.sign.sign2Bytes(data), sign);
    }

    @Override
    public boolean verify(String data, String charset, byte[] sign)
            throws Exception {
        return Arrays.equals(this.sign.sign2Bytes(data, charset), sign);
    }

}
