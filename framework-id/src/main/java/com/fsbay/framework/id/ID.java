package com.fsbay.framework.id;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * 
 *
 * @author dengzhineng
 * @mail zhineng.deng@163.com
 * @date: 2019年6月16日 上午9:35:08
 * @version 1.0
 * @since JDK 1.8
 */
public class ID implements Externalizable {
    private static final long serialVersionUID = 1L;
    /**
     * 生成的ID值
     */
    public long id;
    /**
     * 生成该ID的时间（秒数）
     */
    public long timeSeconds;

    public ID(long id, long timeSeconds) {
        super();
        this.id = id;
        this.timeSeconds = timeSeconds;
    }

    public long getTimeMillis() {
        return this.timeSeconds * 1000;
    }

    @Override
    public String toString() {
        return "ID [id=" + id + ", timeSeconds=" + timeSeconds + "]";
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeByte(0);
        out.writeLong(id);
        out.writeLong(timeSeconds);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        in.readByte();
        this.id = in.readLong();
        this.timeSeconds = in.readLong();

    }

}
