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

        Object bean = getSingleton(name);
        if (bean != null) {
            // 存在 直接返回
            return bean;
        }

        // 获取beanDefinition
        BeanDefinition beanDefinition = getBeanDefinition(name);

        // 创建bean
        return createBean(name,beanDefinition);
    }

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition);

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeanException;

}
