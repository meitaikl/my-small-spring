package com.learn.springframework.context;

import com.learn.springframework.beans.BeanException;

/**
 * @author meiguangya
 * @date 2023/6/4 2:10 下午
 * @description TODO
 */
public interface ConfigurableApplicationContext extends ApplicationContext{


    /**
     * 刷新容器
     * @throws BeanException
     */
    void refresh() throws BeanException;

}
