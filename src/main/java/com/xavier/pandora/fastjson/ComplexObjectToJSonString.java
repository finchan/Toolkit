package com.xavier.pandora.fastjson;

import com.alibaba.fastjson.JSON;
import com.xavier.pandora.fastjson.data.User;
import com.xavier.pandora.fastjson.data.UserGroup;

import java.util.ArrayList;
import java.util.List;

public class ComplexObjectToJSonString {
    public static void main(String[] args) {
        User user1 = new User("zhangsan", "123123");
        User user2 = new User("lisi", "321321");
        List<User> users = new ArrayList<User>();
        users.add(user1);
        users.add(user2);
        UserGroup userGroup = new UserGroup("userGroup", users);
        String userGroupJson = JSON.toJSONString(userGroup);
        String userGroupJson2 = JSON.toJSONString(userGroup, true);
        System.out.println(userGroupJson);
        System.out.println(userGroupJson2);
    }
}
