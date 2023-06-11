package com.learn.springframework;

import com.learn.springframework.bean.UserService;
import com.learn.springframework.context.support.ClassPathXmlApplicationContext;
import org.junit.Test;

/**
 * @author meiguangya
 * @date 2023/4/3 5:23 下午
 * @description 测试
 */
public class ApiTest {


    @Test
    public void testXml() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring.xml");
        context.registerShutDownHook();

        UserService userService = context.getBean("userService", UserService.class);
        System.out.println(userService.queryUserInfo());
    }

}
