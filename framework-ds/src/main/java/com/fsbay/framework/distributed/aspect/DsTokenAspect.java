/**
 * 
 */
package com.fsbay.framework.distributed.aspect;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fsbay.framework.distributed.DsTokenTemplate;
import com.fsbay.framework.distributed.TokenCallback;
import com.fsbay.framework.distributed.annotation.DsToken;
import com.fsbay.framework.distributed.exceptions.DistributedException;

/**
 * @author John
 *
 */
@Aspect // 该注解标示该类为切面类
public class DsTokenAspect {
    private static final Logger logger = LoggerFactory.getLogger(DsTokenAspect.class);
    private DsTokenTemplate dsTokenTemplate;

    public DsTokenAspect(DsTokenTemplate dsTokenTemplate) {
        this.dsTokenTemplate = dsTokenTemplate;
    }

    @Around("@annotation(dt)")
    public Object dsbLockAn(ProceedingJoinPoint point, DsToken dt) throws Throwable {
        Object[] objs = point.getArgs();
        List<Fields> kList = processKey(dt, objs.length);
        String name;
        StringBuffer sbCacheKey = new StringBuffer();
        if (kList == null) {
            throw new DistributedException(dt.errCode(), "DsTokenAspect parameter error");
        } else if (kList.size() == 0) {
            sbCacheKey.append(objs[0]);
        } else {
            for (Fields nfs : kList) {
                for (Object obj : objs) {
                    name = obj.getClass().getSimpleName();
                    if (name.equals(nfs.getName())) {
                        for (String ts : nfs.getFields()) {
                            Field f = obj.getClass().getDeclaredField(ts);
                            f.setAccessible(true);
                            sbCacheKey.append(f.get(obj)).append(":");
                        }
                    }
                }
            }
        }

        logger.info("key = " + sbCacheKey.toString());
        return dsTokenTemplate.execute(dt.biz(), sbCacheKey.toString(), dt.timeOut(), new TokenCallback() {

            @Override
            public Object onCreateSucess() throws InterruptedException {
                try {
                    return point.proceed();
                } catch (Throwable e1) {
                    logger.error(e1.toString());
                    throw new DistributedException(dt.errCode(), "DsTokenAspect internal exception " + e1.getMessage());
                }
            }

            @Override
            public Object onCreateFail() throws InterruptedException {
                throw new DistributedException(dt.errCode(), dt.errMsg());
            }
        });

    }

    private List<Fields> processKey(DsToken dt, int len) {
        List<Fields> keyList = new ArrayList<>();
        if (StringUtils.isBlank(dt.key())) {
            return null;
        }

        String[] keys = dt.key().split(",");
        if (keys.length == 1 && keys[0].indexOf(".") == -1 && len == 1) {
            return keyList;
        }

        String k[];
        for (String key : keys) {
            k = key.split("\\.");
            if (k.length != 2) {
                throw new DistributedException(dt.errCode(), dt.errMsg() + key);
            }

            Fields tfs = new Fields(k[0]);
            int index = keyList.indexOf(tfs);
            if (index == -1) {
                tfs.setFields(new ArrayList<>());
                keyList.add(tfs);
            } else {
                tfs = keyList.get(index);
            }
            tfs.getFields().add(k[1]);
        }
        return keyList;
    }
}
