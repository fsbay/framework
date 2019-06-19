package com.fsbay.framework.security.pool;

import com.fsbay.framework.security.api.Sign;

/**
 * 加入对象池功能的签名实现包装类（线程安全）
 *
 * @author dengzhineng
 * @mail zhineng.deng@163.com
 * @date: 2019年6月15日 上午8:46:16
 * @version 1.0
 * @since JDK 1.8
 */
public class SyncSignWrapper<T extends Sign> implements Sign {

    private ObjectPool<T> pool;

    public SyncSignWrapper(ObjectFactory<T> factory) throws Exception {
        this.pool = new SimpleObjectPool<T>(factory);
    }

    @Override
    public String sign(byte[] data) throws Exception {
        T obj = null;
        try {
            obj = pool.borrowObject();
            return obj.sign(data);
        } finally {
            pool.returnObject(obj);
        }
    }

    @Override
    public String sign(String data, String charset) throws Exception {
        T obj = null;
        try {
            obj = pool.borrowObject();
            return obj.sign(data, charset);
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
    public byte[] sign2Bytes(byte[] data) throws Exception {
        T obj = null;
        try {
            obj = pool.borrowObject();
            return obj.sign2Bytes(data);
        } finally {
            pool.returnObject(obj);
        }
    }

    @Override
    public byte[] sign2Bytes(String data, String charset) throws Exception {
        T obj = null;
        try {
            obj = pool.borrowObject();
            return obj.sign2Bytes(data, charset);
        } finally {
            pool.returnObject(obj);
        }
    }
}
