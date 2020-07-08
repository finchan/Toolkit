package com.xavier.pandora.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.xavier.pandora.fastjson.data.Student;

public class StringToJavaBean {
    public static void main(String[] args) {
        String  JSON_OBJ_STR = "{\"studentName\":\"lily\",\"studentAge\":12}";
        JSONObject jsonObject = JSON.parseObject(JSON_OBJ_STR);

        //1. Constructor
        String studentName = jsonObject.getString("studentName");
        Integer studentAge = jsonObject.getInteger("studentAge");
        Student student = new Student(studentName, studentAge);

        //2. TypeReference<T>
        Student student1 = JSON.parseObject(JSON_OBJ_STR, new TypeReference<Student>(){});

        //3. Reflection
        Student student2 = JSON.parseObject(JSON_OBJ_STR, Student.class);
    }
}
