package com.xavier.pandora.fastjson;

import com.alibaba.fastjson.JSON;
import com.xavier.pandora.fastjson.data.Student;

public class JS02JavaBeanToString {
    public static void main(String[] args) {
        Student lily = new Student("Lily", 12);
        String s = JSON.toJSONString(lily);
        System.out.println(s);
    }
}
