package com.learn.springframework.beans.factory.support;

import com.learn.springframework.beans.BeanException;
import com.learn.springframework.beans.factory.BeanFactory;
import com.learn.springframework.beans.factory.config.BeanDefinition;

/**
 * @author meiguangya
 * @date 2023/4/3 5:09 下午
 * @description TODO
 */
public abstract class AbstractBeanFactory extends DefaultSingletonRegistry implements BeanFactory {



    @Override
    public Object getBean(String name) throws BeanException {
        return doGetBean(name, null);
    }

    @Override
    public Object getBean(String name, Object... args) throws BeanException {
        return doGetBean(name, args);
    }

    @Override
    public <T> T getBean(String name, Class<T> requireClz) {
        return (T)getBean(name);
    }

    protected <T> T doGetBean(final String beanName, final Object[] args) {
        Object bean = getSingleton(beanName);
        if (bean != null) {
            // 存在 直接返回
            return (T) bean;
        }

        // 获取beanDefinition
        BeanDefinition beanDefinition = getBeanDefinition(beanName);

        // 创建bean
        return (T) createBean(beanName, beanDefinition, args);
    }


    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args);

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeanException;

}
