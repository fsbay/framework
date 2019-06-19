package com.fsbay.framework.security.api.impl;

import java.util.Arrays;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import com.fsbay.framework.security.api.Constants;
import com.fsbay.framework.security.api.Verify;

/**
 * 
 *
 * @author dengzhineng
 * @mail zhineng.deng@163.com
 * @date: 2019年6月15日 上午8:35:40
 * @version 1.0
 * @since JDK 1.8
 */
public class HMACSHA1Verify implements Verify {

    private Mac mac;

    public HMACSHA1Verify() {
    }

    public HMACSHA1Verify(String secretKey) throws Exception {
        init(secretKey);
    }

    public HMACSHA1Verify(byte[] keys) throws Exception {
        init(keys);
    }

    @Override
    public boolean verify(byte[] data, String sign) throws Exception {
        String encryptStr = Base64.encodeBase64String(mac.doFinal(data));
        return encryptStr.equals(sign);
    }

    @Override
    public boolean verify(String data, String charset, String sign)
            throws Exception {
        return verify(data.getBytes(charset), sign);
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
    public boolean verify(byte[] data, byte[] sign) throws Exception {
        return Arrays.equals(mac.doFinal(data), sign);
    }

    @Override
    public boolean verify(String data, String charset, byte[] sign)
            throws Exception {
        return verify(data.getBytes(charset), sign);
    }

}
