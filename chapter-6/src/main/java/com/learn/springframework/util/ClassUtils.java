package com.learn.springframework.util;


/**
 * @author meiguangya
 * @date 2023/4/4 4:30 下午
 * @description TODO
 */
public class ClassUtils {

    public static ClassLoader getDefaultClassLoader() {

        ClassLoader ret = null;
        try {
            ret = Thread.currentThread().getContextClassLoader();
        } catch (Exception e) {
            //
        }

        if (ret == null) {
            ret = ClassUtils.class.getClassLoader();
        }

        return ret;
    }

}
