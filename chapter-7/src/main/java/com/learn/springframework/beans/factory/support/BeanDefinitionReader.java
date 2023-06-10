package com.learn.springframework.beans.factory.support;

import com.learn.springframework.beans.BeanException;
import com.learn.springframework.core.io.Resource;
import com.learn.springframework.core.io.ResourceLoader;

import java.io.IOException;

/**
 * @author meiguangya
 * @date 2023/4/10 8:22 下午
 * @description TODO
 */
public interface BeanDefinitionReader {

    BeanDefinitionRegistry getBeanDefinitionRegistry();

    ResourceLoader getResourceLoader();

    void loadBeanDefinitions(Resource resource);

    void loadBeanDefinitions(Resource... resources);

    void loadBeanDefinitions(String location);

    void loadBeanDefinitions(String... locations) throws BeanException;


}
