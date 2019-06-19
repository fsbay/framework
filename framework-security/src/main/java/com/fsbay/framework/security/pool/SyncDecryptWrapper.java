package com.fsbay.framework.security.pool;

import com.fsbay.framework.security.api.Decrypt;

/**
 * 加入对象池功能的解密实现包装类（线程安全）
 *
 * @author dengzhineng
 * @mail zhineng.deng@163.com
 * @date: 2019年6月15日 上午8:45:32
 * @version 1.0
 * @since JDK 1.8
 */
public class SyncDecryptWrapper<T extends Decrypt> implements Decrypt {

    private ObjectPool<T> pool;

    public SyncDecryptWrapper(ObjectFactory<T> factory) throws Exception {
        this.pool = new SimpleObjectPool<T>(factory);
    }

    @Override
    public byte[] decrypt(String encryptStr) throws Exception {
        T obj = null;
        try {
            obj = pool.borrowObject();
            return obj.decrypt(encryptStr);
        } finally {
            pool.returnObject(obj);
        }
    }

    @Override
    public String decrypt(String encryptStr, String charset) throws Exception {
        T obj = null;
        try {
            obj = pool.borrowObject();
            return obj.decrypt(encryptStr, charset);
        } finally {
            pool.returnObject(obj);
        }
    }

    @Override
    public byte[] decrypt(byte[] encrypt) throws Exception {
        T obj = null;
        try {
            obj = pool.borrowObject();
            return obj.decrypt(encrypt);
        } finally {
            pool.returnObject(obj);
        }
    }

    @Override
    public void init(byte[] secryptKeys) throws Exception {
    }

    @Override
    public void init(String secryptKey) throws Exception {
    }
}
