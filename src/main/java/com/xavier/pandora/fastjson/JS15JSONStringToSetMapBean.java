package com.xavier.pandora.fastjson;

import com.alibaba.fastjson.JSON;
import com.xavier.pandora.fastjson.data.ComplexBean;

public class JS15JSONStringToSetMapBean {
    public static void main(String [] args) {
        String json= "{\n" +
                "\t\"mapUsers\":{\n" +
                "\t\t\"userList1\":[\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"password\":\"Password3\",\n" +
                "\t\t\t\t\"username\":\"Username3\"\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"password\":\"Password4\",\n" +
                "\t\t\t\t\"username\":\"Username4\"\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"password\":\"Password5\",\n" +
                "\t\t\t\t\"username\":\"Username5\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"userList2\":[\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"password\":\"Password6\",\n" +
                "\t\t\t\t\"username\":\"Username6\"\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"password\":\"Password7\",\n" +
                "\t\t\t\t\"username\":\"Username7\"\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"password\":\"Password8\",\n" +
                "\t\t\t\t\"username\":\"Username8\"\n" +
                "\t\t\t}\n" +
                "\t\t]\n" +
                "\t},\n" +
                "\t\"users\":[{\n" +
                "\t\t\"password\":\"Password2\",\n" +
                "\t\t\"username\":\"Username2\"\n" +
                "\t},{\n" +
                "\t\t\"password\":\"Password1\",\n" +
                "\t\t\"username\":\"Username1\"\n" +
                "\t}]\n" +
                "}";
        ComplexBean bean = JSON.parseObject(json, ComplexBean.class);
        System.out.println(bean);
    }
}
