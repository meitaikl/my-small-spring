package com.learn.springframework;

import cn.hutool.core.io.IoUtil;
import com.learn.springframework.bean.UserDao;
import com.learn.springframework.bean.UserService;
import com.learn.springframework.beans.PropertyValue;
import com.learn.springframework.beans.PropertyValues;
import com.learn.springframework.beans.factory.config.BeanDefinition;
import com.learn.springframework.beans.factory.config.BeanReference;
import com.learn.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.learn.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import com.learn.springframework.core.io.DefaultResourceLoader;
import com.learn.springframework.core.io.Resource;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author meiguangya
 * @date 2023/4/3 5:23 下午
 * @description TODO
 */
public class ApiTest {


    private DefaultResourceLoader resourceLoader;

    @Before
    public void init(){
        resourceLoader = new DefaultResourceLoader();
    }

    @Test
    public void testClassResource() throws IOException {
        String path = "classpath:test.txt";
        Resource resource = resourceLoader.getResource(path);
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
    }

    @Test
    public void testFile() throws IOException {
        String path = "/Users/meiguangya/today.py";
        Resource resource = resourceLoader.getResource(path);
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
    }


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

    @Test
    public void testXml(){

        // beanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 读取配置文件
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        xmlBeanDefinitionReader.loadBeanDefinitions("classpath:spring.xml");

        // 获取bean
        UserService userService = beanFactory.getBean("userService", UserService.class);
        userService.queryUserInfo("1");

    }


}
