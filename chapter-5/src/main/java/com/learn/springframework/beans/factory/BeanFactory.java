package com.learn.springframework.beans.factory;

import com.learn.springframework.beans.BeanException;

/**
 * @author meiguangya
 * @date 2023/4/3 4:59 下午
 * @description TODO
 */
public interface BeanFactory {

    Object getBean(String name) throws BeanException;

    Object getBean(String name,Object[] args) throws BeanException;

    <T> T getBean(String name,Class<T> requireClz);

}
