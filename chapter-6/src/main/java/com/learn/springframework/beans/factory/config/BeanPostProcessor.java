package com.learn.springframework.beans.factory.config;

/**
 * @author meiguangya
 * @date 2023/6/4 2:04 下午
 * @description TODO
 */
public interface BeanPostProcessor {


    /**
     * 在Bean对象初始化方法之前，执行此方法
     *
     * @param bean
     * @param beanName
     * @return
     */
    Object postProcessBeforeInitialization(Object bean, String beanName);


    /**
     * 在Bean对象初始化方法之后，执行此方法
     *
     * @param bean
     * @param beanName
     * @return
     */
    Object postProcessAfterInitialization(Object bean, String beanName);

}
