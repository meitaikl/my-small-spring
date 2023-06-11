package com.learn.springframework.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * @author meiguangya
 * @date 2023/4/4 11:34 上午
 * @description TODO
 */
public class UserDao {

    private static Map<String, String> map = new HashMap<>();

    //static {
    //    map.put("1", "孙悟空");
    //    map.put("2", "猪八戒");
    //}

    public void initDataMethod() {
        map.put("1", "孙悟空");
        map.put("2", "猪八戒");
    }

    public void destroyDataMethod() {
        System.out.println("=======执行 destroyDataMethod======");
        map.clear();
    }

    public String queryUserName(String uid) {
        return map.get(uid);
    }


}
