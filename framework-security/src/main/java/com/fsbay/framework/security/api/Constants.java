package com.fsbay.framework.security.api;
/**
 * 
 *
 * @author dengzhineng
 * @mail zhineng.deng@163.com
 * @date: 2019年6月15日 上午8:20:08
 * @version 1.0
 * @since JDK 1.8
 */
public interface Constants {

    /**
     * RAS算法key
     */
    String RSA_KEY_ALGORITHM = "RSA";

    /**
     * RSA签名算法:MD5+RSA
     */
    String RSA_SIGNATURE_ALGORITHM = "MD5withRSA";

    /**
     * RSA签名算法:SHA-1+RSA
     */
    String RSA_SIGNATURE_ALGORITHM_SHA1 = "SHA1withRSA";

    /**
     * RSA PKCS1加密解密算法key
     */
    String RSA_PKCS1_KEY = "RSA/ECB/PKCS1Padding";

    /**
     * AES PKCS5加密解密算法key
     */
    String AES_PKCS5_KEY = "AES/ECB/PKCS5Padding";

    /**
     * AES算法key
     */
    String AES_KEY_ALGORITHM = "AES";

    /**
     * DES算法key
     */
    String DES_KEY_ALGORITHM = "DES";

    /**
     * 3DES算法key
     */
    String TRIPLE_DES_KEY_ALGORITHM = "DESede";

    /**
     * HMAC SHA加密算法
     */
    String SHA_HMAC_ALGORITHM = "HMACSHA1";

    /**
     * 默认编码
     */
    String DEFAULT_CHARSET = "UTF-8";
}
