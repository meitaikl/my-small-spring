package com.learn.springframework.beans.factory.support;

import com.learn.springframework.beans.BeanException;
import com.learn.springframework.beans.factory.config.BeanDefinition;

/**
 * @author meiguangya
 * @date 2023/4/3 5:14 下午
 * @description TODO
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {


    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) {
        Object bean = null;
        try {
            bean = beanDefinition.getBeanClass().newInstance();
        } catch (Exception e) {
            throw new BeanException("创建bean失败", e);
        }

        // 加入
        addSingleton(beanName, bean);

        return bean;
    }


}
