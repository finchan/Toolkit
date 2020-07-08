package com.xavier.pandora.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class JSObjectToString {
    public static void main(String[] args) {
        String  JSON_OBJ_STR = "              {\"studentName\":\"lily\",\"studentAge\":12}         ";
        JSONObject obj = JSON.parseObject(JSON_OBJ_STR);
        String s = JSON.toJSONString(obj);
        System.out.println(s);
    }
}
