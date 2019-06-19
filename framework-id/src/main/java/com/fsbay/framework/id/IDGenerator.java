package com.fsbay.framework.id;

/**
 * 
 * ID生成接口
 * @author dengzhineng
 * @mail zhineng.deng@163.com
 * @date: 2019年6月16日 上午9:35:49
 * @version 1.0
 * @since JDK 1.8
 */
public interface IDGenerator {
    
    /**
     * 根据默认key生成编号
     * 
     * @author dengzhineng 
     * @date: 2019年6月16日 上午9:37:27
     * @version 1.0
     *
     * @return
     */
    public ID next();

    /**
     * 适用于自增id
     * 
     * @author dengzhineng 
     * @date: 2019年6月16日 上午9:38:56
     * @version 1.0
     *
     * @param key
     * @return
     */
    public ID next(String key);

}
