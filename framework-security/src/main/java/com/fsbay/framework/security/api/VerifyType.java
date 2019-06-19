package com.fsbay.framework.security.api;

import com.fsbay.framework.security.api.impl.FormatedStrMD5Verify;
import com.fsbay.framework.security.api.impl.HMACSHA1Verify;
import com.fsbay.framework.security.api.impl.MD5Verify;
import com.fsbay.framework.security.api.impl.RSAX509Verify;
import com.fsbay.framework.security.api.impl.SHA1withRSAX509Verify;
import com.fsbay.framework.security.api.impl.SHA256Verify;
import com.fsbay.framework.security.api.impl.SufMD5Verify;

/**
 * 支持的验签类型
 *
 * @author dengzhineng
 * @mail zhineng.deng@163.com
 * @date: 2019年6月15日 上午8:24:09
 * @version 1.0
 * @since JDK 1.8
 */
public enum VerifyType {

    /**
     * 基于MD5的验签
     */
    MD5(MD5Verify.class),

    /**
     * 基于格式化字符串的Md5验签
     */
    FORMATED_STR_MD5(FormatedStrMD5Verify.class),

    /**
     * 基于追加MD5key字符串的Md5验签
     */
    SUFFIX_MD5(SufMD5Verify.class),

    /**
     * hmac with sha1
     */
    HMAC_SHA1(HMACSHA1Verify.class),

    /**
     * MD5 with rsa
     */
    MD5_RSA(RSAX509Verify.class),

    /**
     * SHA-1 with rsa
     */
    SHA1_RSA(SHA1withRSAX509Verify.class),
    
    SHA256(SHA256Verify.class);

    final Class<?> clazz;

    private VerifyType(Class<?> clazz) {
        this.clazz = clazz;
    }

    public Class<?> getClazz() {
        return clazz;
    }

}
