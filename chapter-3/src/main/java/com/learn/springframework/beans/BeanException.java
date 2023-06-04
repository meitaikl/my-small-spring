package com.learn.springframework.beans;

/**
 * @author meiguangya
 * @date 2023/4/3 5:00 下午
 * @description TODO
 */
public class BeanException extends RuntimeException{


    public BeanException(String message) {
        super(message);
    }

    public BeanException(String message, Throwable cause) {
        super(message, cause);
    }

}
