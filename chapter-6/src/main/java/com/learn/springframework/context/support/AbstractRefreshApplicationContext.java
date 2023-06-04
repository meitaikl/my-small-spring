package com.learn.springframework.context.support;

import com.learn.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.learn.springframework.beans.factory.support.DefaultListableBeanFactory;

/**
 * @author meiguangya
 * @date 2023/6/4 2:29 下午
 * @description TODO
 */
public abstract class AbstractRefreshApplicationContext extends AbstractApplicationContext{

    private DefaultListableBeanFactory beanFactory;

    @Override
    protected void refreshBeanFactory() {
        System.out.println("====refreshBeanFactory====");
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        loadBeanDefinitions(beanFactory);
        this.beanFactory = beanFactory;
    }


    protected abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory);

    @Override
    protected ConfigurableListableBeanFactory getBeanFactory() {
        return beanFactory;
    }
}
