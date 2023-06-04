package com.learn.springframework.core.io;

/**
 * @author meiguangya
 * @date 2023/4/4 4:37 下午
 * @description TODO
 */
public interface ResourceLoader {

    String CLASSPATH_URL_PREFIX = "classpath:";

    Resource getResource(String location);

}
