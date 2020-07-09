package com.xavier.pandora.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class JS08StringToJSObject {
    public static void main(String[] args) {
        String  JSON_OBJ_STR = "              {\"studentName\":\"lily\",\"studentAge\":12}         ";
        String  JSON_ARRAY_STR = "[{\"studentName\":\"lily\",\"studentAge\":12}, {\"studentName\":\"lucy\",\"studentAge\":15}]";

        JSONObject jsonObject = JSON.parseObject(JSON_OBJ_STR);
        JSONArray jsonArray = JSON.parseArray(JSON_ARRAY_STR);

        System.out.println(jsonObject.getString("studentName") + "\t" + jsonObject.getInteger("studentAge") + "\t" + jsonObject.getString("studentAge"));
        System.out.println(jsonArray);
        for(Object obj : jsonArray) {
            System.out.println(((JSONObject) obj).getString("studentName") + "\t" + ((JSONObject) obj).getString("studentAge"));
        }
        //.JSONException: JSONArray cannot be cast to JSONObject. [] -> {}
//        JSONObject jsonObject3 = JSON.parseObject(JSON_ARRAY_STR);
//        System.out.println(jsonObject3);
        //JSON Exception: From JSONObject to JSONArray {}-> []
        JSONArray jsonArray2 = JSON.parseArray(JSON_OBJ_STR);
        System.out.println(jsonArray2);
    }
}
