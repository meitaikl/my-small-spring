package com.learn.springframework.beans.factory.config;

import com.learn.springframework.beans.factory.HierarchicalBeanFactory;

/**
 * @author meiguangya
 * @date 2023/6/4 2:00 下午
 * @description TODO
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory {

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

    /**
     * 销毁单例对象
     */
    void destroySingletons();

}
