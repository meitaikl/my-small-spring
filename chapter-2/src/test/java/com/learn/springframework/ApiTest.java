package com.learn.springframework;

import com.learn.springframework.bean.UserService;
import com.learn.springframework.beans.factory.config.BeanDefinition;
import com.learn.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.junit.Test;

/**
 * @author meiguangya
 * @date 2023/4/3 5:23 下午
 * @description TODO
 */
public class ApiTest {

    @Test
    public void testBeanFactory() {

        // 初始化
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 注册
        beanFactory.registryBeanDefinition("userService", new BeanDefinition(UserService.class));

        // 获取
        UserService u1 = (UserService) beanFactory.getBean("userService");
        u1.queryUserInfo();

        UserService u2 = (UserService) beanFactory.getBean("userService");
        u2.queryUserInfo();

        System.out.println("u1 equals u2 = " + u1.equals(u2));

    }


}
