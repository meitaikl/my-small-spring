package com.learn.springframework.beans;

/**
 * @author meiguangya
 * @date 2023/4/4 11:16 上午
 * @description TODO
 */
public class PropertyValue {

    private final String name;
    private final Object value;


    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }

}
