package com.learn.springframework.core.io;

import cn.hutool.core.lang.Assert;

/**
 * @author meiguangya
 * @date 2023/4/4 4:39 下午
 * @description TODO
 */
public class DefaultResourceLoader implements ResourceLoader {

    @Override
    public Resource getResource(String location) {

        Assert.notBlank(location, "location不能为空");

        if (location.startsWith(CLASSPATH_URL_PREFIX)) {
            return new ClassPathResource(location.substring(CLASSPATH_URL_PREFIX.length()));
        } else {
            return new FileSystemResource(location);
        }

    }

}
