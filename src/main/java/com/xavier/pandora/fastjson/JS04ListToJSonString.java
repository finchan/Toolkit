package com.xavier.pandora.fastjson;

import com.alibaba.fastjson.JSON;
import com.xavier.pandora.fastjson.data.User;

import java.util.ArrayList;
import java.util.List;

public class JS04ListToJSonString {
    public static void main(String[] args) {
        User user1 = new User("zhangsan", "123123");
        User user2 = new User("lisi", "321321");
        List<User> users = new ArrayList<User>();
        users.add(user1);
        users.add(user2);
        String listJSONString = JSON.toJSONString(users);
        System.out.println(listJSONString);
    }
}
