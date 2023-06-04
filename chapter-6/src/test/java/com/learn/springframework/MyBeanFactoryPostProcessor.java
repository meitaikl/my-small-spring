package com.learn.springframework;

import com.learn.springframework.beans.PropertyValue;
import com.learn.springframework.beans.PropertyValues;
import com.learn.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.learn.springframework.beans.factory.config.BeanDefinition;
import com.learn.springframework.beans.factory.config.BeanFactoryPostProcessor;

/**
 * @author meiguangya
 * @date 2023/6/4 3:11 下午
 * @description TODO
 */
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {


    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) {
        System.out.println("MyBeanFactoryPostProcessor postProcessBeanFactory...");
        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("userService");

        PropertyValues propertyValues = beanDefinition.getPropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("company", "养猪厂"));

    }
}
