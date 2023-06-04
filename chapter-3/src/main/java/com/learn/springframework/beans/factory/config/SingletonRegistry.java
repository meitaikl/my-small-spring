package com.learn.springframework.beans.factory.config;

/**
 * @author meiguangya
 * @date 2023/4/3 5:05 下午
 * @description TODO
 */
public interface SingletonRegistry {

    Object getSingleton(String beanName);

}
