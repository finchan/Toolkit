package com.xavier.pandora.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.Iterator;

public class TraverseJSONArray {
    public static void main(String[] args) {
        String  JSON_ARRAY_STR = "[{\"studentName\":\"lily\",\"studentAge\":12}, {\"studentName\":\"lucy\",\"studentAge\":15}]";
        JSONArray jsonArray = JSON.parseArray(JSON_ARRAY_STR);

        for (int i=0; i<jsonArray.size(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            System.out.println(obj.getString("studentName") + "\t" + obj.getInteger("studentAge"));
        }

        Iterator<Object> iterator = jsonArray.iterator();
        while (iterator.hasNext()){
            JSONObject jsonObject = (JSONObject) iterator.next();
            System.out.println("studentName: " + jsonObject.getString("studentName") + ",StudentAge: " + jsonObject.getInteger("studentAge"));
        }
    }
}
