package com.learn.springframework.context.support;

import com.learn.springframework.beans.BeanException;

import java.util.Map;

/**
 * @author meiguangya
 * @date 2023/6/4 2:43 下午
 * @description TODO
 */
public class ClassPathXmlApplicationContext extends AbstractXmlApplicationContext {

    private String[] configLocations;

    public ClassPathXmlApplicationContext() {
    }

    /**
     * 从 XML 中加载 BeanDefinition，并刷新上下文
     *
     * @param configLocations
     * @throws BeanException
     */
    public ClassPathXmlApplicationContext(String configLocations) throws BeanException {
        this(new String[]{configLocations});
    }

    /**
     * 从 XML 中加载 BeanDefinition，并刷新上下文
     *
     * @param configLocations
     * @throws BeanException
     */
    public ClassPathXmlApplicationContext(String[] configLocations) throws BeanException {
        this.configLocations = configLocations;
        refresh();
    }

    @Override
    protected String[] getConfigLocations() {
        return configLocations;
    }


}
