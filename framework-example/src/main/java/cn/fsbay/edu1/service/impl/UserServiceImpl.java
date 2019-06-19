package cn.fsbay.edu1.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.fsbay.framework.distributed.annotation.DsLock;

import cn.fsbay.edu1.entity.Demo;
import cn.fsbay.edu1.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

        @DsLock(key = "Demo.id")
	@Override
	public Demo selectByIdPlus(Demo record) {
		logger.info("UserServiceImpl selectByIdPlus"+JSON.toJSONString(record));
		return record;
	}
   
}
