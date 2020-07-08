package com.xavier.pandora.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.xavier.pandora.fastjson.data.Student;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StringToJavaBeanList {
    public static void main(String[] args) {
        String JSON_ARRAY_STR = "[{\"studentName\":\"lily\",\"studentAge\":12}, {\"studentName\":\"lucy\",\"studentAge\":15}]";
        JSONArray jsonArray = JSON.parseArray(JSON_ARRAY_STR);
        //1. Constructor
        List<Student> students = new ArrayList<Student>();
        Iterator<Object> iterator = jsonArray.iterator();
        while (iterator.hasNext()){
            JSONObject next = (JSONObject) iterator.next();
            String studentName = next.getString("studentName");
            Integer studentAge = next.getInteger("studentAge");
            Student student = new Student(studentName, studentAge);
            students.add(student);
        }
        // 2. TypeReference
        List<Student> studentList = JSON.parseObject(JSON_ARRAY_STR,new TypeReference<ArrayList<Student>>() {});
        // 3. Reflection
        List<Student> students1 = JSON.parseArray(JSON_ARRAY_STR, Student.class);
        System.out.println(students1);
    }
}
