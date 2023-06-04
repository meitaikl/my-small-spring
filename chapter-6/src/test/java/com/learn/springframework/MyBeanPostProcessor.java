package com.learn.springframework;

import com.learn.springframework.bean.UserService;
import com.learn.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author meiguangya
 * @date 2023/6/4 3:18 下午
 * @description TODO
 */
public class MyBeanPostProcessor implements BeanPostProcessor {


    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        if ("userService".equals(beanName)) {
            System.out.println("postProcessBeforeInitialization...");
            UserService userService = (UserService) bean;
            userService.setLocation("海底十万里");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        return null;
    }
}
