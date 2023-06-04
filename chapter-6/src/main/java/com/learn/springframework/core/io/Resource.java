package com.learn.springframework.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author meiguangya
 * @date 2023/4/4 4:22 下午
 * @description TODO
 */
public interface Resource {

    InputStream getInputStream() throws IOException;

}
