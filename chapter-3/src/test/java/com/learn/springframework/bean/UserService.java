package com.learn.springframework.bean;

/**
 * @author meiguangya
 * @date 2023/4/3 4:54 下午
 * @description TODO
 */
public class UserService {

    private String name;

    public UserService() {

    }

    public UserService(String name) {
        this.name = name;
    }

    public void queryUserInfo() {
        System.out.println("查询用户信息:" + name);
    }

}
