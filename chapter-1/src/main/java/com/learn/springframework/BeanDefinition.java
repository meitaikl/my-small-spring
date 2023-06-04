package com.learn.springframework;

/**
 * @author meiguangya
 * @date 2023/4/3 4:49 下午
 * @description bean的定义
 */
public class BeanDefinition {

    private Object bean;

    public BeanDefinition(Object bean) {
        this.bean = bean;
    }

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }
}
