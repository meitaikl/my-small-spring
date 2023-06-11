package com.learn.springframework.beans.factory;

/**
 * @author meiguangya
 * @date 2023/6/10 9:16 下午
 * @description Bean销毁接口
 */
public interface DisposableBean {

    /**
     * bean销毁时调用
     * @throws Exception
     */
    void destroy() throws Exception;

}
