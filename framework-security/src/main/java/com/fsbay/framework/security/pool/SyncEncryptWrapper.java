package com.fsbay.framework.security.pool;

import com.fsbay.framework.security.api.Encrypt;

/**
 * 加入对象池功能的加密实现包装类（线程安全）
 *
 * @author dengzhineng
 * @mail zhineng.deng@163.com
 * @date: 2019年6月15日 上午8:45:53
 * @version 1.0
 * @since JDK 1.8
 */
public class SyncEncryptWrapper<T extends Encrypt> implements Encrypt {

    private ObjectPool<T> pool;

    public SyncEncryptWrapper(ObjectFactory<T> factory) throws Exception {
        this.pool = new SimpleObjectPool<T>(factory);
    }

    @Override
    public String encrypt(byte[] src) throws Exception {
        T obj = null;
        try {
            obj = pool.borrowObject();
            return obj.encrypt(src);
        } finally {
            pool.returnObject(obj);
        }
    }

    @Override
    public String encrypt(String src, String charset) throws Exception {
        T obj = null;
        try {
            obj = pool.borrowObject();
            return obj.encrypt(src, charset);
        } finally {
            pool.returnObject(obj);
        }
    }

    @Override
    public byte[] encryptToBytes(byte[] src) throws Exception {
        T obj = null;
        try {
            obj = pool.borrowObject();
            return obj.encryptToBytes(src);
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
