package com.xavier.pandora.fastjson;

import com.alibaba.fastjson.JSON;
import com.xavier.pandora.fastjson.data.User;

public class JS01ObjectToJSonString {
    public static void main(String[] args) {
        User user = new User("Xavier", "123456");
        String userJSONString = JSON.toJSONString(user);
        System.out.println(userJSONString);
    }
}
