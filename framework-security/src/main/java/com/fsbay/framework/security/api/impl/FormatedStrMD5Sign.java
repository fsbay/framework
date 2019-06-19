package com.fsbay.framework.security.api.impl;

import java.text.MessageFormat;

import org.apache.commons.codec.digest.DigestUtils;

import com.fsbay.framework.security.api.Sign;

/**
 * 基于MD5的签名实现(线程安全)：<br/>
 * 采用的org.apache.commons.codec.digest.DigestUtils的md5Hex实现<br/>
 * 签名前，会使用MessageFormat.format(data, md5key)对原始字符串做处理后做MD5签名。
 *
 *
 * @author dengzhineng
 * @mail zhineng.deng@163.com
 * @date: 2019年6月15日 上午8:34:28
 * @version 1.0
 * @since JDK 1.8
 */
public class FormatedStrMD5Sign implements Sign {
    private String md5key;

    @Override
    public String sign(final byte[] data) throws Exception {
        throw new UnsupportedOperationException();
    }

    @Override
    public String sign(String data, String charset) throws Exception {
        data = MessageFormat.format(data, this.md5key);
        return DigestUtils.md5Hex(data.getBytes(charset));
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
    public byte[] sign2Bytes(byte[] data) throws Exception {
        throw new UnsupportedOperationException();
    }

    @Override
    public byte[] sign2Bytes(String data, String charset) throws Exception {
        data = MessageFormat.format(data, this.md5key);
        return DigestUtils.md5(data.getBytes(charset));
    }

}
