package com.learn.springframework.beans.factory.config;

import com.learn.springframework.beans.BeanException;
import com.learn.springframework.beans.factory.BeanFactory;

/**
 * @author meiguangya
 * @date 2023/6/4 2:57 下午
 * @description
 */
public interface AutowireCapableBeanFactory extends BeanFactory {


    /**
     * 执行 BeanPostProcessors 接口实现类的 postProcessBeforeInitialization 方法
     *
     * @param existingBean
     * @param beanName
     * @return
     * @throws BeanException
     */
    Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeanException;

    /**
     * 执行 BeanPostProcessors 接口实现类的 postProcessorsAfterInitialization 方法
     *
     * @param existingBean
     * @param beanName
     * @return
     * @throws BeanException
     */
    Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeanException;



}
