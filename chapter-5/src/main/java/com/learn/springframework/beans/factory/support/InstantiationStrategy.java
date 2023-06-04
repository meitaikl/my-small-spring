package com.learn.springframework.beans.factory.support;

import com.learn.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * @author meiguangya
 * @date 2023/4/3 5:42 下午
 * @description TODO
 */
public interface InstantiationStrategy {

    Object instantiation(String beanName, BeanDefinition beanDefinition, Constructor ctor, Object[] args);

}
