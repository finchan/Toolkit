package com.xavier.pandora.fastjson;

import com.alibaba.fastjson.JSON;
import com.xavier.pandora.fastjson.data.ComplexBean;
import com.xavier.pandora.fastjson.data.User;

import java.util.*;

public class JS14SetMapToJSonString {
    public static void main(String[] args) {
        Set<User> users = new HashSet<User>();
        users.add(new User("Username1", "Password1"));
        users.add(new User("Username2", "Password2"));
        List<User> userList1 = new ArrayList<User>();
        userList1.add(new User("Username3", "Password3"));
        userList1.add(new User("Username4", "Password4"));
        userList1.add(new User("Username5", "Password5"));
        List<User> userList2 = new ArrayList<User>();
        userList2.add(new User("Username6", "Password6"));
        userList2.add(new User("Username7", "Password7"));
        userList2.add(new User("Username8", "Password8"));
        Map<String, List<User>> mapUsers = new HashMap<String, List<User>>();
        mapUsers.put("userList1", userList1);
        mapUsers.put("userList2", userList2);

        ComplexBean bean = new ComplexBean(users, mapUsers);

        String jsString = JSON.toJSONString(bean, true);
        System.out.println(jsString);
    }
}
