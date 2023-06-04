package com.learn.springframework.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import com.learn.springframework.beans.BeanException;
import com.learn.springframework.beans.PropertyValue;
import com.learn.springframework.beans.PropertyValues;
import com.learn.springframework.beans.factory.config.BeanDefinition;
import com.learn.springframework.beans.factory.config.BeanReference;

import java.lang.reflect.Constructor;

/**
 * @author meiguangya
 * @date 2023/4/3 5:14 下午
 * @description TODO
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    private InstantiationStrategy instantiationStrategy = new CglibSubClassingInstantiationStrategy();

    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) {
        Object bean = null;
        try {
            // bean = beanDefinition.getBeanClass().newInstance();
            bean = createBeanInstance(beanName, beanDefinition, args);

            // 填充属性
            applyPropertyValues(beanName,bean,beanDefinition);

        } catch (Exception e) {
            throw new BeanException("创建bean失败", e);
        }

        // 加入singletonList
        addSingleton(beanName, bean);

        return bean;
    }

    protected Object createBeanInstance(String beanName, BeanDefinition beanDefinition, Object[] args) {
        Constructor ctorToUse = null;
        Class<?> beanClass = beanDefinition.getBeanClass();
        Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();
        for (Constructor<?> constructor : declaredConstructors) {
            if (null != args && constructor.getParameterTypes().length == args.length) {
                ctorToUse = constructor;
                break;
            }
        }

        return getInstantiationStrategy().instantiation(beanName, beanDefinition, ctorToUse, args);
    }

    protected void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) {
        try {
            PropertyValues propertyValues = beanDefinition.getPropertyValues();
            PropertyValue[] propertyValuesArr = propertyValues.getPropertyValues();
            for (PropertyValue propertyValue : propertyValuesArr) {
                String name = propertyValue.getName();
                Object value = propertyValue.getValue();

                // 如果是引用类型 那么获取对应的bean
                if (value instanceof BeanReference) {
                    BeanReference beanReference = (BeanReference) value;
                    value = getBean(beanReference.getName());
                }

                BeanUtil.setFieldValue(bean,name,value);

            }


        } catch (Exception e) {
            throw new BeanException("填充属性是失败，beanName:" + beanName, e);
        }
    }

}
