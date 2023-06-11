package com.learn.springframework.beans.factory.support;

import com.learn.springframework.beans.BeanException;
import com.learn.springframework.beans.factory.DisposableBean;
import com.learn.springframework.beans.factory.config.SingletonBeanRegistry;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author meiguangya
 * @date 2023/4/3 5:06 下午
 * @description TODO
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    private Map<String, Object> singletonObjects = new ConcurrentHashMap<>();

    private Map<String, DisposableBean> disposableBeans = new ConcurrentHashMap<>();

    public void registerDisposableBean(String beanName, DisposableBean bean) {
        disposableBeans.put(beanName, bean);
    }

    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }

    protected void addSingleton(String beanName, Object obj) {
        singletonObjects.put(beanName, obj);
    }

    public void destroySingletons() {
        Set<String> keySet = this.disposableBeans.keySet();
        Object[] disposableBeanNames = keySet.toArray();

        for (int i = disposableBeanNames.length - 1; i >= 0; i--) {
            Object beanName = disposableBeanNames[i];
            DisposableBean disposableBean = disposableBeans.remove(beanName);
            try {
                disposableBean.destroy();
            } catch (Exception e) {
                throw new BeanException("Destroy method on bean with name '" + beanName + "' threw an exception", e);
            }
        }
    }

}
