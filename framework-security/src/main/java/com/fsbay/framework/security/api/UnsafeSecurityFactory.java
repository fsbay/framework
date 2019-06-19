package com.fsbay.framework.security.api;

/**
 * 加密，解密，验签，签名工厂类。不保证具体实现类的线程安全性
 * 
 * @author dengzhineng
 * @date: 2017年1月22日 下午3:02:47
 * @version 1.0
 * @since JDK 1.7
 */
public class UnsafeSecurityFactory {

    /**
     * 根据加密类型获取加密实现
     * 
     * @param encryptType
     *            加密类型
     * @param secretKey
     *            密钥
     * @return 加密实现
     * @throws Exception
     */
    public static Encrypt getEncryptInstance(EncryptType encryptType,
            final String secretKey) throws Exception {
        return ReflectUtils.getInstance(encryptType.getClazz(), secretKey);
    }

    /**
     * 根据加密类型获取加密实现
     * 
     * @param encryptType
     *            加密类型
     * @param byte[] 密钥byte数组
     * @return 加密实现
     * @throws Exception
     */
    public static Encrypt getEncryptInstanceByBytes(EncryptType encryptType,
            final byte[] bytes) throws Exception {
        return ReflectUtils.getInstance(encryptType.getClazz(), bytes);
    }

    /**
     * 根据解密类型获取对应的解密实现
     * 
     * @param decryptType
     *            解密类型
     * @param secretKey
     *            密钥
     * @return 解密实现
     * @throws Exception
     */
    public static Decrypt getDecryptInstance(DecryptType decryptType,
            final String secretKey) throws Exception {
        return ReflectUtils.getInstance(decryptType.getClazz(), secretKey);
    }

    /**
     * 根据解密类型获取对应的解密实现
     * 
     * @param decryptType
     *            解密类型
     * @param byte[] 密钥byte数组
     * @return 解密实现
     * @throws Exception
     */
    public static Decrypt getDecryptInstanceByBytes(DecryptType decryptType,
            final byte[] bytes) throws Exception {
        return ReflectUtils.getInstance(decryptType.getClazz(), bytes);
    }

    /**
     * 根据签名类型获取对应的签名实现
     * 
     * @param signType
     *            签名类型
     * @param secretKey
     *            密钥
     * @return 签名实现
     * @throws Exception
     */
    public static Sign getSignInstance(SignType signType, final String secretKey)
            throws Exception {
        return ReflectUtils.getInstance(signType.getClazz(), secretKey);
    }

    /**
     * 根据签名类型获取对应的签名实现
     * 
     * @param signType
     *            签名类型
     * @param byte[] 密钥byte数组
     * @return 签名实现
     * @throws Exception
     */
    public static Sign getSignInstanceByBytes(SignType signType,
            final byte[] bytes) throws Exception {
        return ReflectUtils.getInstance(signType.getClazz(), bytes);
    }

    /**
     * 根据验签类型获取对应的验签实现
     * 
     * @param verifyType
     *            验签类型
     * @param secretKey
     *            密钥
     * @return 验签实现
     * @throws Exception
     */
    public static Verify getVerifyInstance(VerifyType verifyType,
            final String secretKey) throws Exception {
        return ReflectUtils.getInstance(verifyType.getClazz(), secretKey);
    }

    /**
     * 根据验签类型获取对应的验签实现
     * 
     * @param verifyType
     *            验签类型
     * @param byte[] 密钥byte数组
     * @return 验签实现
     * @throws Exception
     */
    public static Verify getVerifyInstanceByBytes(VerifyType verifyType,
            final byte[] bytes) throws Exception {
        return ReflectUtils.getInstance(verifyType.getClazz(), bytes);
    }
}
