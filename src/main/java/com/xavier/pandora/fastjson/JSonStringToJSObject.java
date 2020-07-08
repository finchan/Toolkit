package com.xavier.pandora.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.Iterator;

public class JSonStringToJSObject {
    public static void main(String[] args) {
        String COMPLEX_JSON_STR = "{\"teacherName\":\"crystall\",\"teacherAge\":27,\"course\":{\"courseName\":\"english\",\"code\":1270},\"students\":[{\"studentName\":\"lily\",\"studentAge\":12},{\"studentName\":\"lucy\",\"studentAge\":15}]}";
        JSONObject jsonObject = JSON.parseObject(COMPLEX_JSON_STR);
        System.out.println(JSON.toJSONString(jsonObject, true));
        String teacherName = jsonObject.getString("teacherName");
        Integer teacherAge = jsonObject.getInteger("teacherAge");
        System.out.println("teacherName: " + teacherName + ",teacherAge " + teacherAge);
        JSONObject course = jsonObject.getJSONObject("course");
        String courseName = course.getString("courseName");
        Integer code = course.getInteger("code");
        System.out.println("courseName:  " + courseName + "   code:  " + code);
        JSONArray students = jsonObject.getJSONArray("students");
        Iterator<Object> iterator = students.iterator();
        while (iterator.hasNext()) {
            JSONObject jsonObject1 = (JSONObject) iterator.next();
            System.out.println("studentName: " + jsonObject1.getString("studentName") + ",StudentAge: "
                    + jsonObject1.getInteger("studentAge"));
        }
    }
}
