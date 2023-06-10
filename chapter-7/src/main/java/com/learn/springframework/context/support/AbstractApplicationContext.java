package com.learn.springframework.context.support;

import com.learn.springframework.beans.BeanException;
import com.learn.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.learn.springframework.beans.factory.config.BeanFactoryPostProcessor;
import com.learn.springframework.beans.factory.config.BeanPostProcessor;
import com.learn.springframework.context.ConfigurableApplicationContext;
import com.learn.springframework.core.io.DefaultResourceLoader;

import java.util.Map;

/**
 * @author meiguangya
 * @date 2023/6/4 2:12 下午
 * @description context的抽象模板类
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader
        implements ConfigurableApplicationContext {

    @Override
    public void refresh() throws BeanException {
        // 创建BeanFactory 加载BeanDefinition
        refreshBeanFactory();

        // 获取beanFactory
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();

        // 在将Bean对象实例化前，执行BeanFactoryPostProcessor操作
        invokeBeanFactoryPostProcessors(beanFactory);

        // 注册BeanPostProcessor
        registerBeanPostProcessors(beanFactory);

        // 提前实例化单例Bean对象
        beanFactory.preInstantiateSingletons();

    }

    protected abstract void refreshBeanFactory();

    protected abstract ConfigurableListableBeanFactory getBeanFactory();

    private void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        System.out.println("=======调用BeanFactoryPostProcessors开始=======");
        // 获取所有实现了 BeanFactoryPostProcessor 的bean

        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);

        System.out.println("=====getBeansOfType:BeanFactoryPostProcessor=====   size:" + beanFactoryPostProcessorMap.size());
        for (BeanFactoryPostProcessor beanFactoryPostProcessor : beanFactoryPostProcessorMap.values()) {
            beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        }

        System.out.println("=======调用BeanFactoryPostProcessors结束=======");

    }

    private void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory) {

        System.out.println("====注册BeanPostProcessors开始====");

        Map<String, BeanPostProcessor> beanPostProcessorMap = beanFactory.getBeansOfType(BeanPostProcessor.class);

        for (BeanPostProcessor beanPostProcessor : beanPostProcessorMap.values()) {
            beanFactory.addBeanPostProcessor(beanPostProcessor);
        }

        System.out.println("====注册BeanPostProcessors结束====");

    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeanException {
        return getBeanFactory().getBeansOfType(type);
    }

    @Override
    public Object getBean(String name) throws BeanException {
        return getBeanFactory().getBean(name);
    }

    @Override
    public Object getBean(String name, Object... args) throws BeanException {
        return getBeanFactory().getBean(name, args);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeanException {
        return getBeanFactory().getBean(name, requiredType);
    }

}
