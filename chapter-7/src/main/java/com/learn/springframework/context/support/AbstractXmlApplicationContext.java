package com.learn.springframework.context.support;

import com.learn.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.learn.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * @author meiguangya
 * @date 2023/6/4 2:36 下午
 * @description TODO
 */
public abstract class AbstractXmlApplicationContext extends AbstractRefreshApplicationContext {


    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) {

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory, this);

        String[] configLocations = getConfigLocations();
        if (null != configLocations) {
            beanDefinitionReader.loadBeanDefinitions(configLocations);
        }

    }

    /**
     * 获取配置文件
     *
     * @return
     */
    protected abstract String[] getConfigLocations();
}
