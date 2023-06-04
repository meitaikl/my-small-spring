package com.learn.springframework.beans.factory;

import com.learn.springframework.beans.BeanException;
import com.learn.springframework.beans.factory.config.AutowireCapableBeanFactory;
import com.learn.springframework.beans.factory.config.BeanDefinition;
import com.learn.springframework.beans.factory.config.BeanPostProcessor;
import com.learn.springframework.beans.factory.config.ConfigurableBeanFactory;

/**
 * @author meiguangya
 * @date 2023/6/4 2:03 下午
 * @description TODO
 */
public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {

    BeanDefinition getBeanDefinition(String beanName) throws BeanException;

    /**
     * 实例化单例对象
     */
    void preInstantiateSingletons();

    /**
     * 添加BeanPostProcessor
     * @param beanPostProcessor
     */
    @Override
    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

}
