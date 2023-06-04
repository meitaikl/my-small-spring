package com.learn.springframework.beans.factory.config;

/**
 * @author meiguangya
 * @date 2023/4/3 5:02 下午
 * @description TODO
 */
public class BeanDefinition {

    public Class beanClass;

    public BeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }
}
