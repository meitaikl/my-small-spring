package com.learn.springframework.test;

import com.learn.springframework.BeanDefinition;
import com.learn.springframework.BeanFactory;
import com.learn.springframework.test.bean.UserService;
import org.junit.Test;

/**
 * @author meiguangya
 * @date 2023/4/3 4:54 下午
 * @description TODO
 */
public class ApiTest {


    @Test
    public void testBeanFactory(){

        // 初始化
        BeanFactory beanFactory = new BeanFactory();

        // 注入
        BeanDefinition beanDefinition = new BeanDefinition(new UserService());
        beanFactory.registerBeanDefinition("userService",beanDefinition);

        // 获取
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();
    }


}
