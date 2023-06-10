package com.learn.springframework.core.io;

import cn.hutool.core.lang.Assert;
import com.learn.springframework.util.ClassUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author meiguangya
 * @date 2023/4/4 4:23 下午
 * @description TODO
 */
public class ClassPathResource implements Resource {

    private final String path;

    private ClassLoader classLoader;

    public ClassPathResource(String path) {
        this(path, null);
    }

    public ClassPathResource(String path, ClassLoader classLoader) {
        Assert.notNull(path, "path不能为空");
        this.path = path;
        if (classLoader == null) {
            this.classLoader = ClassUtils.getDefaultClassLoader();
        } else {
            this.classLoader = classLoader;
        }
    }

    @Override
    public InputStream getInputStream() throws IOException {

        InputStream inputStream = classLoader.getResourceAsStream(path);

        if (inputStream == null) {
            throw new FileNotFoundException(this.path + " 文件不存在");
        }

        return inputStream;
    }

}
