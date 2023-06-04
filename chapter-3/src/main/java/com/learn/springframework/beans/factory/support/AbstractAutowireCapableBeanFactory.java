package com.learn.springframework.beans.factory.support;

import com.learn.springframework.beans.BeanException;
import com.learn.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * @author meiguangya
 * @date 2023/4/3 5:14 下午
 * @description TODO
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    private InstantiationStrategy instantiationStrategy = new CglibSubClassingInstantiationStrategy();

    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) {
        Object bean = null;
        try {
            // bean = beanDefinition.getBeanClass().newInstance();
            bean = createBeanInstance(beanName, beanDefinition, args);
        } catch (Exception e) {
            throw new BeanException("创建bean失败", e);
        }

        // 加入
        addSingleton(beanName, bean);

        return bean;
    }

    protected Object createBeanInstance(String beanName, BeanDefinition beanDefinition, Object[] args) {
        Constructor ctorToUse = null;
        Class<?> beanClass = beanDefinition.getBeanClass();
        Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();
        for (Constructor<?> constructor : declaredConstructors) {
            if (null != args && constructor.getParameterTypes().length == args.length) {
                ctorToUse = constructor;
                break;
            }
        }

        return getInstantiationStrategy().instantiation(beanName, beanDefinition, ctorToUse, args);
    }

}
