package com.learn.springframework;

import com.learn.springframework.bean.UserDao;
import com.learn.springframework.bean.UserService;
import com.learn.springframework.beans.PropertyValue;
import com.learn.springframework.beans.PropertyValues;
import com.learn.springframework.beans.factory.config.BeanDefinition;
import com.learn.springframework.beans.factory.config.BeanReference;
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

        // 注册userDao
        beanFactory.registryBeanDefinition("userDao", new BeanDefinition(UserDao.class));

        // 注册userService
        BeanDefinition userServiceDefinition = new BeanDefinition(UserService.class);
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addProperty(new PropertyValue("name", "蜡笔小新"));
        propertyValues.addProperty(new PropertyValue("userDao", new BeanReference("userDao")));
        userServiceDefinition.setPropertyValues(propertyValues);
        beanFactory.registryBeanDefinition("userService", userServiceDefinition);


        // 获取
        //UserService u = (UserService) beanFactory.getBean("userService", "阿呆");
        UserService u = (UserService) beanFactory.getBean("userService");
        u.queryUserInfo("2");

        UserService u1 = (UserService) beanFactory.getBean("userService", "阿呆");
        u1.queryUserInfo("1");

    }


}
