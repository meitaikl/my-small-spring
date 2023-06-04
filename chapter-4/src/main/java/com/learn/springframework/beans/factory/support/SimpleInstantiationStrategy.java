package com.learn.springframework.beans.factory.support;

import com.learn.springframework.beans.BeanException;
import com.learn.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * @author meiguangya
 * @date 2023/4/3 5:45 下午
 * @description TODO
 */
public class SimpleInstantiationStrategy implements InstantiationStrategy {

    @Override
    public Object instantiation(String beanName, BeanDefinition beanDefinition, Constructor ctor, Object[] args) {

        try {
            Class beanClass = beanDefinition.getBeanClass();
            if (ctor != null) {
                return beanClass.getDeclaredConstructor(ctor.getParameterTypes()).newInstance(args);
            } else {
                return beanClass.getDeclaredConstructor().newInstance();
            }

        } catch (Exception e) {
            throw new BeanException("simpleInstantiation初始化失败", e);
        }

    }
}
