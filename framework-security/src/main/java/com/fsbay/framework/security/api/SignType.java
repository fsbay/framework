package com.fsbay.framework.security.api;

import com.fsbay.framework.security.api.impl.FormatedStrMD5Sign;
import com.fsbay.framework.security.api.impl.HMACSHA1Sign;
import com.fsbay.framework.security.api.impl.MD5Sign;
import com.fsbay.framework.security.api.impl.RSAPKCS1Sign;
import com.fsbay.framework.security.api.impl.RSAPKCS8Sign;
import com.fsbay.framework.security.api.impl.SHA1withRSAPKCS1Sign;
import com.fsbay.framework.security.api.impl.SHA1withRSAPKCS8Sign;
import com.fsbay.framework.security.api.impl.SHA256Sign;
import com.fsbay.framework.security.api.impl.SufMD5Sign;

/**
 * 支持的签名类型
 *
 * @author dengzhineng
 * @mail zhineng.deng@163.com
 * @date: 2019年6月15日 上午8:23:44
 * @version 1.0
 * @since JDK 1.8
 */
public enum SignType {

    /**
     * 基于PKCS8的Md5withRSA签名
     * 
     */
    MD5_RSA_PKCS8(RSAPKCS8Sign.class),

    /**
     * 基于PKCS8的SHA1withRSA签名
     * 
     */
    SHA1_RSA_PKCS8(SHA1withRSAPKCS8Sign.class),

    /**
     * 基于MD5的签名
     */
    MD5(MD5Sign.class),
    /**
     * 基于格式化字符串的Md5的签名
     */
    FORMATED_STR_MD5(FormatedStrMD5Sign.class),

    /**
     * 基于追加MD5key字符串的Md5的签名
     */
    SUFFIX_MD5(SufMD5Sign.class),

    /**
     * hmac with sha1
     */
    HMAC_SHA1(HMACSHA1Sign.class),

    /**
     * MD5 with rsa
     */
    MD5_RSA(RSAPKCS1Sign.class),

    /**
     * SHA-1 with rsa
     */
    SHA1_RSA(SHA1withRSAPKCS1Sign.class),
    /**
     * SHA256
     */
    SHA256(SHA256Sign.class);

    final Class<?> clazz;

    private SignType(Class<?> clazz) {
        this.clazz = clazz;
    }

    public Class<?> getClazz() {
        return clazz;
    }

}
