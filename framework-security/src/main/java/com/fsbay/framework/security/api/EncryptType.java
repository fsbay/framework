package com.fsbay.framework.security.api;

import com.fsbay.framework.security.api.impl.AESPKCS5Encrypt;
import com.fsbay.framework.security.api.impl.DESEncrypt;
import com.fsbay.framework.security.api.impl.RSAPKCS1PrivateKeyEncrypt;
import com.fsbay.framework.security.api.impl.RSAX509PublicKeyEncrypt;
import com.fsbay.framework.security.api.impl.TripleDESEncrypt;

/**
 * 
 *
 * @author dengzhineng
 * @mail zhineng.deng@163.com
 * @date: 2019年6月15日 上午8:21:20
 * @version 1.0
 * @since JDK 1.8
 */
public enum EncryptType {
    /**
     * 基于PKCS1规范的RSA私钥加密
     */
    RSA_PRIVATE(RSAPKCS1PrivateKeyEncrypt.class),

    /**
     * 基于X509规范的RSA公钥加密
     */
    RSA_PUBLIC(RSAX509PublicKeyEncrypt.class),

    /**
     * 基于PKCS5规范的AES加密
     */
    AES(AESPKCS5Encrypt.class),

    /**
     * DES加密
     */
    DES(DESEncrypt.class),

    /**
     * 3DES加密
     */
    TRIPLE_DES(TripleDESEncrypt.class);

    final Class<?> clazz;

    public Class<?> getClazz() {
        return clazz;
    }

    private EncryptType(Class<?> clazz) {
        this.clazz = clazz;
    }
}
