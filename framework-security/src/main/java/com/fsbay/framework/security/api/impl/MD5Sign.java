package com.fsbay.framework.security.api.impl;

import org.apache.commons.codec.digest.DigestUtils;

import com.fsbay.framework.security.api.Sign;

/**
 * 基于MD5的签名实现(线程安全)：<br/>
 * 采用的org.apache.commons.codec.digest.DigestUtils的md5Hex实现<br/>
 * 
 *
 * @author dengzhineng
 * @mail zhineng.deng@163.com
 * @date: 2019年6月15日 上午8:36:06
 * @version 1.0
 * @since JDK 1.8
 */
public class MD5Sign implements Sign {

    @Override
    public String sign(final byte[] data) throws Exception {
        return DigestUtils.md5Hex(data);
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
        return DigestUtils.md5(data);
    }

    @Override
    public byte[] sign2Bytes(String data, String charset) throws Exception {
        return sign2Bytes(data.getBytes(charset));
    }
}
