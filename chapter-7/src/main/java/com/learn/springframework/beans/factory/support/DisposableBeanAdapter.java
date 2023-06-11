package com.learn.springframework.beans.factory.support;

import com.learn.springframework.beans.BeanException;
import com.learn.springframework.beans.factory.DisposableBean;
import com.learn.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Method;

/**
 * @author meiguangya
 * @date 2023/6/10 9:32 下午
 * @description 销毁方法适配器
 */
public class DisposableBeanAdapter implements DisposableBean {

    private final Object bean;
    private final String beanName;
    private final String destroyMethodName;
    private final String methodName = "destroy";

    public DisposableBeanAdapter(Object bean, String beanName, BeanDefinition beanDefinition) {
        this.bean = bean;
        this.beanName = beanName;
        this.destroyMethodName = beanDefinition.getDestroyMethodName();
    }

    @Override
    public void destroy() throws Exception {

        if (bean instanceof DisposableBean) {
            DisposableBean disposableBean = (DisposableBean) bean;
            disposableBean.destroy();
            return;
        }

        if (methodName.equals(destroyMethodName)) {
            Method method = bean.getClass().getMethod(methodName);
            method.invoke(bean);
        }

    }

}
