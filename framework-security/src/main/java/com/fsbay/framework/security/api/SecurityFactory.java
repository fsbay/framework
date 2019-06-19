package com.fsbay.framework.security.api;

import com.fsbay.framework.security.api.impl.MD5Sign;
import com.fsbay.framework.security.api.impl.MD5Verify;
import com.fsbay.framework.security.pool.ObjectFactory;
import com.fsbay.framework.security.pool.SyncDecryptWrapper;
import com.fsbay.framework.security.pool.SyncEncryptWrapper;
import com.fsbay.framework.security.pool.SyncSignWrapper;
import com.fsbay.framework.security.pool.SyncVerifyWrapper;

/**
 * 加密，解密，验签，签名工厂类
 *
 * @author dengzhineng
 * @mail zhineng.deng@163.com
 * @date: 2019年6月15日 上午8:22:09
 * @version 1.0
 * @since JDK 1.8
 */
public class SecurityFactory {

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
    public static Encrypt getEncryptInstance(final EncryptType encryptType,
            final String secretKey) throws Exception {
        return new SyncEncryptWrapper<Encrypt>(new ObjectFactory<Encrypt>() {
            @Override
            public Encrypt create() throws Exception {
                return ReflectUtils.getInstance(encryptType.getClazz(),
                        secretKey);
            }
        });
    }

    /**
     * 根据加密类型获取加密实现
     * 
     * @param encryptType
     *            加密类型
     * @param bytes
     *            密钥byte数组
     * @return 加密实现
     * @throws Exception
     */
    public static Encrypt getEncryptInstanceByBytes(
            final EncryptType encryptType, final byte[] bytes) throws Exception {
        return new SyncEncryptWrapper<Encrypt>(new ObjectFactory<Encrypt>() {
            @Override
            public Encrypt create() throws Exception {
                return ReflectUtils.getInstance(encryptType.getClazz(), bytes);
            }
        });
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
    public static Decrypt getDecryptInstance(final DecryptType decryptType,
            final String secretKey) throws Exception {
        return new SyncDecryptWrapper<Decrypt>(new ObjectFactory<Decrypt>() {
            @Override
            public Decrypt create() throws Exception {
                return ReflectUtils.getInstance(decryptType.getClazz(),
                        secretKey);
            }
        });
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
    public static Decrypt getDecryptInstanceByBytes(
            final DecryptType decryptType, final byte[] bytes) throws Exception {
        return new SyncDecryptWrapper<Decrypt>(new ObjectFactory<Decrypt>() {
            @Override
            public Decrypt create() throws Exception {
                return ReflectUtils.getInstance(decryptType.getClazz(), bytes);
            }
        });
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
    public static Sign getSignInstance(final SignType signType,
            final String secretKey) throws Exception {
        if (SignType.MD5 == signType) {
            return new MD5Sign(); // thread safe
        }
        return new SyncSignWrapper<Sign>(new ObjectFactory<Sign>() {
            @Override
            public Sign create() throws Exception {
                return ReflectUtils.getInstance(signType.getClazz(), secretKey);
            }
        });
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
    public static Sign getSignInstanceByBytes(final SignType signType,
            final byte[] bytes) throws Exception {
        if (SignType.MD5 == signType) {
            return new MD5Sign(); // thread safe
        }
        return new SyncSignWrapper<Sign>(new ObjectFactory<Sign>() {
            @Override
            public Sign create() throws Exception {
                return ReflectUtils.getInstance(signType.getClazz(), bytes);
            }
        });
    }

    /**
     * 根据验签类型获取对应的验签实现
     * 
     * @param verifyType
     *            验签算法类型
     * @param secretKey
     *            密钥
     * @return 验签实现
     * @throws Exception
     */
    public static Verify getVerifyInstance(final VerifyType verifyType,
            final String secretKey) throws Exception {
        if (VerifyType.MD5 == verifyType) {
            return new MD5Verify();
        }
        return new SyncVerifyWrapper<Verify>(new ObjectFactory<Verify>() {
            @Override
            public Verify create() throws Exception {
                return ReflectUtils.getInstance(verifyType.getClazz(),
                        secretKey);
            }
        });
    }

    /**
     * 根据验签类型获取对应的验签实现
     * 
     * @param verifyType
     *            验签算法类型
     * @param bytes
     *            密钥byte数组
     * @return 验签实例
     * @throws Exception
     */
    public static Verify getVerifyInstanceByBytes(final VerifyType verifyType,
            final byte[] bytes) throws Exception {
        if (VerifyType.MD5 == verifyType) {
            return new MD5Verify();
        }
        return new SyncVerifyWrapper<Verify>(new ObjectFactory<Verify>() {
            @Override
            public Verify create() throws Exception {
                return ReflectUtils.getInstance(verifyType.getClazz(), bytes);
            }
        });
    }
}
