package com.fsbay.framework.security.api;

import com.fsbay.framework.security.api.impl.AESPKCS5Decrypt;
import com.fsbay.framework.security.api.impl.DESDecrypt;
import com.fsbay.framework.security.api.impl.RSAPKCS1PrivateKeyDecrypt;
import com.fsbay.framework.security.api.impl.RSAX509PublicKeyDecrypt;
import com.fsbay.framework.security.api.impl.TripleDESDecrypt;


/**
 * 
 *
 * @author dengzhineng
 * @mail zhineng.deng@163.com
 * @date: 2019年6月15日 上午8:20:44
 * @version 1.0
 * @since JDK 1.8
 */
public enum DecryptType {

    /**
     * 基于PKCS1规范的RSA私钥解密
     */
    RSA_PRIVATE(RSAPKCS1PrivateKeyDecrypt.class),

    /**
     * 基于X509规范的RSA公钥解密
     */
    RSA_PUBLIC(RSAX509PublicKeyDecrypt.class),

    /**
     * 基于PKCS5规范的AES解密
     */
    AES(AESPKCS5Decrypt.class),

    /**
     * DES解密
     */
    DES(DESDecrypt.class),
    /**
     * 3DES解密
     */
    TRIPLE_DES(TripleDESDecrypt.class);

    final Class<?> clazz;

    public Class<?> getClazz() {
        return clazz;
    }

    private DecryptType(Class<?> clazz) {
        this.clazz = clazz;
    }

}
