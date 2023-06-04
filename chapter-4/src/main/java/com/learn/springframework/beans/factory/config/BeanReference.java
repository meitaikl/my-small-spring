package com.learn.springframework.beans.factory.config;

/**
 * @author meiguangya
 * @date 2023/4/4 11:31 上午
 * @description TODO
 */
public class BeanReference {

    private final String name;

    public BeanReference(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
