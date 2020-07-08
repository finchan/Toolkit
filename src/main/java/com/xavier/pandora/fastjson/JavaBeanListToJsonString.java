package com.xavier.pandora.fastjson;

import com.alibaba.fastjson.JSON;
import com.xavier.pandora.fastjson.data.Student;

import java.util.ArrayList;
import java.util.List;

public class JavaBeanListToJsonString {
    public static void main(String[] args){
        Student student = new Student("lily", 12);
        Student student1 = new Student("lucy", 13);
        List<Student> students = new ArrayList<Student>();
        students.add(student);
        students.add(student1);
        String s = JSON.toJSONString(student);
        System.out.println(s);
    }
}
