package com.learn.springframework.bean;

/**
 * @author meiguangya
 * @date 2023/4/3 4:54 下午
 * @description TODO
 */
public class UserService {

    private String name;

    private String uid;

    private Integer age;

    private UserDao userDao;

    public UserService() {

    }

    public UserService(String name) {
        this.name = name;
    }

    public void queryUserInfo(String uid) {
        System.out.println(this.age + "==" + this.name + "===" + this.uid);
        System.out.println("查询用户信息:" + userDao.queryUserName(uid));
    }

}
