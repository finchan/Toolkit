package com.xavier.pandora.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.xavier.pandora.fastjson.data.Teacher;

public class ComplextJSonStringToJavaBean {
    public static void main(String[] args) {
        String COMPLEX_JSON_STR = "{\"teacherName\":\"crystall\",\"teacherAge\":27,\"course\":{\"courseName\":\"english\",\"code\":1270},\"students\":[{\"studentName\":\"lily\",\"studentAge\":12},{\"studentName\":\"lucy\",\"studentAge\":15}]}";
        //1. TypeReference
        Teacher teacher = JSON.parseObject(COMPLEX_JSON_STR, new TypeReference<Teacher>() {});
        // 2. Reflection
        Teacher teacher1 = JSON.parseObject(COMPLEX_JSON_STR, Teacher.class);
        System.out.println(teacher);
    }
}
