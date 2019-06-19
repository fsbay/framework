package com.fsbay.framework.security.api.impl;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.codec.digest.DigestUtils;

import com.fsbay.framework.security.api.Sign;

/**
 * 
 * 基于MD5的签名实现(线程安全)：<br/>
 * 采用的org.apache.commons.codec.digest.DigestUtils的md5Hex实现<br/>
 * 签名前，会对原始字符串后追加MD5key处理后做MD5签名。
 * @author dengzhineng
 * @mail zhineng.deng@163.com
 * @date: 2019年6月15日 上午8:42:20
 * @version 1.0
 * @since JDK 1.8
 */
public class SufMD5Sign implements Sign {
    private byte[] md5key;

    @Override
    public String sign(final byte[] data) throws Exception {
        return DigestUtils.md5Hex(getSignData(data));
    }

    @Override
    public String sign(String data, String charset) throws Exception {
        return DigestUtils.md5Hex(getSignData(data, charset));
    }

    @Override
    public void init(byte[] secryptKeys) throws Exception {
        if (secryptKeys == null) {
            throw new NullPointerException();
        }
        this.md5key = secryptKeys;
    }

    @Override
    public void init(String secryptKey) throws Exception {
        this.init(StringUtils.getBytesUtf8(secryptKey));
    }

    @Override
    public byte[] sign2Bytes(byte[] data) throws Exception {
        return DigestUtils.md5(getSignData(data));
    }

    @Override
    public byte[] sign2Bytes(String data, String charset) throws Exception {
        return DigestUtils.md5(getSignData(data, charset));
    }

    private byte[] getSignData(byte[] data) {
        return merge(data, this.md5key);
    }

    static byte[] merge(byte[] data, byte[] md5key) {
        if (data == null) {
            data = md5key;
        } else {
            int length = data.length;
            data = Arrays.copyOf(data, length + md5key.length);
            System.arraycopy(md5key, 0, data, length, md5key.length);
        }
        return data;
    }

    private byte[] getSignData(String data, String charset)
            throws UnsupportedEncodingException {
        return this.getSignData(data.getBytes(charset));
    }
}
