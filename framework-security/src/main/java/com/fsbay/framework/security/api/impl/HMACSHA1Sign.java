package com.fsbay.framework.security.api.impl;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import com.fsbay.framework.security.api.Constants;
import com.fsbay.framework.security.api.Sign;

/**
 * 
 *
 * @author dengzhineng
 * @mail zhineng.deng@163.com
 * @date: 2019年6月15日 上午8:35:23
 * @version 1.0
 * @since JDK 1.8
 */
public class HMACSHA1Sign implements Sign {

    private Mac mac;

    public HMACSHA1Sign() {
    }

    public HMACSHA1Sign(String secretKey) throws Exception {
        init(secretKey);
    }

    public HMACSHA1Sign(byte[] keys) throws Exception {
        init(keys);
    }

    @Override
    public String sign(byte[] data) throws Exception {
        return Base64.encodeBase64String(sign2Bytes(data));
    }

    @Override
    public String sign(String data, String charset) throws Exception {
        return sign(data.getBytes(charset));
    }

    @Override
    public void init(byte[] keys) throws Exception {
        mac = Mac.getInstance(Constants.SHA_HMAC_ALGORITHM);
        SecretKey secretKey = new SecretKeySpec(keys,
                Constants.SHA_HMAC_ALGORITHM);
        mac.init(secretKey);
    }

    @Override
    public void init(String secretKey) throws Exception {
        init(secretKey.getBytes(Constants.DEFAULT_CHARSET));
    }

    @Override
    public byte[] sign2Bytes(byte[] data) throws Exception {
        return mac.doFinal(data);
    }

    @Override
    public byte[] sign2Bytes(String data, String charset) throws Exception {
        return sign2Bytes(data.getBytes(charset));
    }

}
