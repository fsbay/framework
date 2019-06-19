package cn.fsbay.edu1.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fsbay.framework.cache.CacheClient;
import com.fsbay.framework.id.IDGenerator;

import cn.fsbay.edu1.entity.Demo;
import cn.fsbay.edu1.service.IUserService;

@RestController
@RequestMapping("/api/edu1")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    
    @Autowired
    private CacheClient cacheClient;

    @Autowired
    private IUserService userService;
    @Autowired
    private IDGenerator iDGenerator;

    @RequestMapping("/selectPlus")
    public Demo selectPlus() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        HttpSession s = request.getSession();
        s.setAttribute("aaa", "bbbb");

        cacheClient.set("aaa", "dddddddddddddddddddd");
        System.out.println((String)cacheClient.get("aaa"));
        Demo record = new Demo();
        record.setId(100000000);
        record.setName("张小花");
        s.setAttribute("xxx", record);
        
        System.out.println(s.getAttribute("aaa"));
        System.out.println(s.getAttribute("xxx"));
        System.out.println("id:"+iDGenerator.next().id);
        System.out.println("id:"+iDGenerator.next().id);
        return userService.selectByIdPlus(record);
    }


   
}
