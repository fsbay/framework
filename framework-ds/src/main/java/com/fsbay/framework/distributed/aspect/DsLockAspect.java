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

import com.fsbay.framework.distributed.DsLockTemplate;
import com.fsbay.framework.distributed.LockCallback;
import com.fsbay.framework.distributed.annotation.DsLock;
import com.fsbay.framework.distributed.exceptions.DistributedException;

/**
 * 
 *
 * @author dengzhineng
 * @mail zhineng.deng@163.com
 * @date: 2019年6月16日 上午10:07:09
 * @version 1.0
 * @since JDK 1.8
 */
@Aspect // 该注解标示该类为切面类
public class DsLockAspect {

	private DsLockTemplate dsLockTemplate;
	
	public DsLockAspect(DsLockTemplate dsLockTemplate) {
	    this.dsLockTemplate = dsLockTemplate;
	}

	private static final Logger logger = LoggerFactory.getLogger(DsLockAspect.class);

	@Around("@annotation(dl)")
	public Object dsbLockAn(ProceedingJoinPoint point, DsLock dl) throws Throwable {
		Object[] objs = point.getArgs();
		List<Fields> kList = processKey(dl, objs.length);
		String name;
		StringBuffer sbCacheKey = new StringBuffer();
		if (kList == null) {
			throw new DistributedException(dl.errCode(), "DsLockAspect paramter error");
		} else if (kList.size() == 0) {
			sbCacheKey.append(objs[0]);
		} else {
			for(Fields nfs : kList) {
				for(Object obj : objs) {
					name = obj.getClass().getSimpleName();
					if(name.equals(nfs.getName())) {
						for (String ts : nfs.getFields()) {
							Field f = obj.getClass().getDeclaredField(ts);
							f.setAccessible(true);
							sbCacheKey.append(f.get(obj)).append(":");
						}
					}
				}
			}
		}
		
		logger.info("key = "+ sbCacheKey.toString());
		return dsLockTemplate.execute(dl.biz(), sbCacheKey.toString(), dl.timeOut(), new LockCallback() {

			@Override
			public Object onGetLock() throws InterruptedException {
				try {
					return point.proceed();
				} catch (Throwable e1) {
					logger.error(e1.toString());
					throw new DistributedException(dl.errCode(), "internel exception" + e1.getMessage());
				}
			}

			@Override
			public Object onTimeout() throws InterruptedException {
				throw new DistributedException(dl.errCode(), dl.errMsg());
			}
			
		});

	}

	private List<Fields> processKey(DsLock dl, int len) {
		List<Fields> keyList = new ArrayList<>();
		if (StringUtils.isBlank(dl.key())) {
			return null;
		}

		String[] keys = dl.key().split(",");
		if (keys.length == 1 && keys[0].indexOf(".") == -1 && len == 1) {
			return keyList;
		}

		String k[];
		for (String key : keys) {
			k = key.split("\\.");
			if (k.length != 2) {
				throw new DistributedException(dl.errCode(), dl.errMsg() + key);
			}

			Fields tfs = new Fields(k[0]);
			int index = keyList.indexOf(tfs);
			if(index == -1) {
				tfs.setFields(new ArrayList<>());
				keyList.add(tfs);
			}else {
				tfs = keyList.get(index);
			}
			tfs.getFields().add(k[1]);
		}
		return keyList;
	}
}
