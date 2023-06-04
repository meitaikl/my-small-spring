package com.learn.springframework.beans.factory.support;

import com.learn.springframework.beans.factory.config.SingletonRegistry;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author meiguangya
 * @date 2023/4/3 5:06 下午
 * @description TODO
 */
public class DefaultSingletonRegistry implements SingletonRegistry {

    private Map<String, Object> singletonObjects = new ConcurrentHashMap<>();

    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }

    protected void addSingleton(String beanName, Object obj) {
        singletonObjects.put(beanName, obj);
    }

}
