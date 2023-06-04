package com.learn.springframework.beans.factory.config;

import com.learn.springframework.beans.factory.ConfigurableListableBeanFactory;

/**
 * @author meiguangya
 * @date 2023/6/4 1:53 下午
 * @description TODO
 */
public interface BeanFactoryPostProcessor {


    /**
     * 在所有的BeanDefinition加载完成后，且将Bean对象实例化前，提供修改BeanDefinition属性的机制s
     * @param beanFactory
     */
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory);


}
