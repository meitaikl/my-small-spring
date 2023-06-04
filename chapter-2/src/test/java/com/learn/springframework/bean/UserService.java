package com.learn.springframework.bean;

/**
 * @author meiguangya
 * @date 2023/4/3 4:54 下午
 * @description TODO
 */
public class UserService {

    String name;

    Integer age;

    public void queryUserInfo(){
        System.out.println("查询用户信息");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
