package com.fsbay.framework.security.api.impl;

import java.text.MessageFormat;
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
 * @date: 2019年6月15日 上午8:34:55
 * @version 1.0
 * @since JDK 1.8
 */
public class FormatedStrMD5Verify implements Verify {
    private String md5key;

    @Override
    public boolean verify(final byte[] data, final String sign)
            throws Exception {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean verify(String data, String charset, String sign)
            throws Exception {
        data = MessageFormat.format(data, this.md5key);
        String md5 = DigestUtils.md5Hex(data);
        return sign.equals(md5);
    }

    @Override
    public void init(byte[] secryptKeys) throws Exception {
        this.md5key = new String(secryptKeys, "UTF-8");
    }

    @Override
    public void init(String secryptKey) throws Exception {
        this.md5key = secryptKey;
    }

    @Override
    public boolean verify(byte[] data, byte[] sign) throws Exception {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean verify(String data, String charset, byte[] sign)
            throws Exception {
        data = MessageFormat.format(data, this.md5key);
        return Arrays.equals(DigestUtils.md5(data.getBytes(charset)), sign);
    }
}
