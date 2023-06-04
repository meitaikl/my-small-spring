package com.learn.springframework.beans.factory.support;

import com.learn.springframework.beans.factory.config.BeanDefinition;

/**
 * @author meiguangya
 * @date 2023/4/3 5:18 下午
 * @description TODO
 */
public interface BeanDefinitionRegistry {


    void registryBeanDefinition(String beanName, BeanDefinition beanDefinition);


    boolean containsBeanDefinition(String beanName);

}
