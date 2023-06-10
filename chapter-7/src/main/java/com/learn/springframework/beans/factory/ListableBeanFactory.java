package com.learn.springframework.beans.factory;

import java.util.Map;

/**
 * @author meiguangya
 * @date 2023/6/4 2:01 下午
 * @description TODO
 */
public interface ListableBeanFactory extends BeanFactory{


    /**
     * 通过class类型获取bean实例
     * @param type
     * @param <T>
     * @return
     */
    <T> Map<String,T> getBeansOfType(Class<T> type);


}
