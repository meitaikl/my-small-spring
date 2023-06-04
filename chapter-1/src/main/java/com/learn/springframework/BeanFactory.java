package com.learn.springframework;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author meiguangya
 * @date 2023/4/3 4:51 下午
 * @description TODO
 */
public class BeanFactory {

    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    public Object getBean(String name) {
        return beanDefinitionMap.get(name).getBean();
    }

    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(name, beanDefinition);
    }

}
