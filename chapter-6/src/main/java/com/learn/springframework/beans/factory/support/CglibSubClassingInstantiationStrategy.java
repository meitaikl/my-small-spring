package com.learn.springframework.beans.factory.support;

import com.learn.springframework.beans.factory.config.BeanDefinition;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

import java.lang.reflect.Constructor;

/**
 * @author meiguangya
 * @date 2023/4/3 5:50 下午
 * @description TODO
 */
public class CglibSubClassingInstantiationStrategy implements InstantiationStrategy {

    @Override
    public Object instantiation(String beanName, BeanDefinition beanDefinition, Constructor ctor, Object[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(beanDefinition.getBeanClass());
        enhancer.setCallback(new NoOp() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        });

        if (ctor == null) {
            return enhancer.create();
        }
        return enhancer.create(ctor.getParameterTypes(), args);
    }
}
