package com.fsbay.framework.security.pool;

import com.fsbay.framework.security.api.Verify;

/**
 * 加入对象池功能的验签实现包装类（线程安全）
 *
 * @author dengzhineng
 * @mail zhineng.deng@163.com
 * @date: 2019年6月15日 上午8:46:39
 * @version 1.0
 * @since JDK 1.8
 */
public class SyncVerifyWrapper<T extends Verify> implements Verify {

    private ObjectPool<T> pool;

    public SyncVerifyWrapper(ObjectFactory<T> factory) throws Exception {
        this.pool = new SimpleObjectPool<T>(factory);
    }

    @Override
    public boolean verify(byte[] data, String sign) throws Exception {
        T obj = null;
        try {
            obj = pool.borrowObject();
            return obj.verify(data, sign);
        } finally {
            pool.returnObject(obj);
        }
    }

    @Override
    public boolean verify(String data, String charset, String sign)
            throws Exception {
        T obj = null;
        try {
            obj = pool.borrowObject();
            return obj.verify(data, charset, sign);
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

    @Override
    public boolean verify(byte[] data, byte[] sign) throws Exception {
        T obj = null;
        try {
            obj = pool.borrowObject();
            return obj.verify(data, sign);
        } finally {
            pool.returnObject(obj);
        }
    }

    @Override
    public boolean verify(String data, String charset, byte[] sign)
            throws Exception {
        T obj = null;
        try {
            obj = pool.borrowObject();
            return obj.verify(data, charset, sign);
        } finally {
            pool.returnObject(obj);
        }
    }
}
