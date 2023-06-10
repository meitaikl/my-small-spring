package com.learn.springframework.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import com.learn.springframework.beans.BeanException;
import com.learn.springframework.beans.PropertyValue;
import com.learn.springframework.beans.PropertyValues;
import com.learn.springframework.beans.factory.config.AutowireCapableBeanFactory;
import com.learn.springframework.beans.factory.config.BeanDefinition;
import com.learn.springframework.beans.factory.config.BeanPostProcessor;
import com.learn.springframework.beans.factory.config.BeanReference;

import java.lang.reflect.Constructor;
import java.util.List;

/**
 * @author meiguangya
 * @date 2023/4/3 5:14 下午
 * @description TODO
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {

    private InstantiationStrategy instantiationStrategy = new CglibSubClassingInstantiationStrategy();

    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) {
        System.out.println("createBean:" + beanName);
        Object bean = null;
        try {
            bean = createBeanInstance(beanName, beanDefinition, args);

            // 填充属性
            applyPropertyValues(beanName, bean, beanDefinition);

            // 执行Bean对象的初始化方法和BeanPostProcessor接口的前置和后置处理方法
            bean = initializeBean(beanName, bean, beanDefinition);

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

                BeanUtil.setFieldValue(bean, name, value);

            }


        } catch (Exception e) {
            throw new BeanException("填充属性是失败，beanName:" + beanName, e);
        }
    }

    private Object initializeBean(String beanName, Object bean, BeanDefinition beanDefinition) {

        // 执行BeanPostProcess Before处理
        Object wrappedBean = applyBeanPostProcessorsBeforeInitialization(bean, beanName);

        // todo
        invokeInitMethods(beanName, wrappedBean, beanDefinition);

        wrappedBean = applyBeanPostProcessorsAfterInitialization(bean, beanName);

        return wrappedBean;
    }

    private void invokeInitMethods(String beanName, Object wrappedBean, BeanDefinition beanDefinition) {
        // todo
        System.out.println("invokeInitMethods...");
    }

    @Override
    public Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeanException {
        System.out.println(beanName + "--applyBeanPostProcessorsBeforeInitialization");
        Object result = existingBean;
        List<BeanPostProcessor> beanPostProcessors = getBeanPostProcessors();
        System.out.println("======beanPostProcessors.size():" + beanPostProcessors.size());
        for (BeanPostProcessor processor : beanPostProcessors) {
            Object current = processor.postProcessBeforeInitialization(result, beanName);
            if (null == current) {
                return result;
            }
            result = current;
        }
        return result;
    }

    @Override
    public Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeanException {
        System.out.println(beanName + "--applyBeanPostProcessorsAfterInitialization");
        Object result = existingBean;
        for (BeanPostProcessor processor : getBeanPostProcessors()) {
            Object current = processor.postProcessAfterInitialization(result, beanName);
            if (null == current) {
                return result;
            }
            result = current;
        }
        return result;
    }

}