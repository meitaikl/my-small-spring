package com.learn.springframework.beans.factory;

/**
 * @author meiguangya
 * @date 2023/6/10 9:14 下午
 * @description 初始化bean接口
 */
public interface InitializingBean {


    /**
     * 在Bean对象属性填充完成后调用
     */
    void afterPropertiesSet();

}
