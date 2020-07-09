package com.xavier.pandora.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xavier.pandora.fastjson.data.Result;
import org.springframework.boot.jackson.JsonObjectDeserializer;

import java.util.List;

public class JS13ReversedObjectDesignFromString {
    /**
     * [{
     * 		"id": "user_list",
     * 		"key": "id",
     * 		"tableName": "用户列表",
     * 		"className": "cn.dmego.domain.User",
     * 		"column": [{
     * 				"key": "rowIndex",
     * 				"header": "序号",
     * 				"width": "50",
     * 				"allowSort": "false"
     *                        },
     *            {
     * 				"key": "id",
     * 				"header": "id",
     * 				"hidden": "true"
     *            },
     *            {
     * 				"key": "name",
     * 				"header": "姓名",
     * 				"width": "100",
     * 				"allowSort": "true"
     *            }
     * 		]
     * 	},
     * 	{
     * 		"id": "role_list",
     * 		"key": "id",
     * 		"tableName": "角色列表",
     * 		"className": "cn.dmego.domain.Role",
     * 		"column": [{
     * 				"key": "rowIndex",
     * 				"header": "序号",
     * 				"width": "50",
     * 				"allowSort": "false"
     *            },
     *            {
     * 				"key": "id",
     * 				"header": "id",
     * 				"hidden": "true"
     *            },
     *            {
     * 				"key": "name",
     * 				"header": "名称",
     * 				"width": "100",
     * 				"allowSort": "true"
     *            }
     * 		]
     * 	}
     * ]
     */
    public static void main(String[] args){
        String objString = "[{\n" +
                "\t\t\"id\": \"user_list\",\n" +
                "\t\t\"key\": \"id\",\n" +
                "\t\t\"tableName\": \"用户列表\",\n" +
                "\t\t\"className\": \"cn.dmego.domain.User\",\n" +
                "\t\t\"column\": [{\n" +
                "\t\t\t\t\"key\": \"rowIndex\",\n" +
                "\t\t\t\t\"header\": \"序号\",\n" +
                "\t\t\t\t\"width\": \"50\",\n" +
                "\t\t\t\t\"allowSort\": \"false\"\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"key\": \"id\",\n" +
                "\t\t\t\t\"header\": \"id\",\n" +
                "\t\t\t\t\"hidden\": \"true\"\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"key\": \"name\",\n" +
                "\t\t\t\t\"header\": \"姓名\",\n" +
                "\t\t\t\t\"width\": \"100\",\n" +
                "\t\t\t\t\"allowSort\": \"true\"\n" +
                "\t\t\t}\n" +
                "\t\t]\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"id\": \"role_list\",\n" +
                "\t\t\"key\": \"id\",\n" +
                "\t\t\"tableName\": \"角色列表\",\n" +
                "\t\t\"className\": \"cn.dmego.domain.Role\",\n" +
                "\t\t\"column\": [{\n" +
                "\t\t\t\t\"key\": \"rowIndex\",\n" +
                "\t\t\t\t\"header\": \"序号\",\n" +
                "\t\t\t\t\"width\": \"50\",\n" +
                "\t\t\t\t\"allowSort\": \"false\"\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"key\": \"id\",\n" +
                "\t\t\t\t\"header\": \"id\",\n" +
                "\t\t\t\t\"hidden\": \"true\"\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"key\": \"name\",\n" +
                "\t\t\t\t\"header\": \"名称\",\n" +
                "\t\t\t\t\"width\": \"100\",\n" +
                "\t\t\t\t\"allowSort\": \"true\"\n" +
                "\t\t\t}\n" +
                "\t\t]\n" +
                "\t}\n" +
                "]";
        List<Result> results = JSON.parseArray(objString, Result.class);
        System.out.println(results);
        System.out.println(JSON.toJSONString(results, true));
    }
}
