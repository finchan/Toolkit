package com.xavier.pandora.httpcomponents.httpclient.httprequest.requestexecution;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xavier.pandora.fastjson.data.Student;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ResponseHandlerJson {
    public static void main(String[] args) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet get = new HttpGet("https://242223f1-f240-4823-abfc-1d32bfa2a0a8.mock.pstmn.io/mockup");
        ResponseHandler<List<Map<String,String>>> rh = new ResponseHandler<List<Map<String, String>>>() {
            @Override
            public List<Map<String,String>> handleResponse(HttpResponse httpResponse) throws ClientProtocolException, IOException {
                StatusLine statusLine = httpResponse.getStatusLine();
                HttpEntity entity = httpResponse.getEntity();
                if(statusLine.getStatusCode() >= 300){
                    throw new HttpResponseException(statusLine.getStatusCode(), statusLine.getReasonPhrase());
                }
                ContentType contentType = ContentType.getOrDefault(entity);
                Charset charset = contentType.getCharset();
                Gson gson = new GsonBuilder().create();
//                Reader reader = new InputStreamReader(entity.getContent(), charset);
//                return gson.fromJson(reader, List.class);
                return gson.fromJson(EntityUtils.toString(entity), List.class);
                //使用两种方式 - Gson和FastJson解析Json字符串，
                //Gson可以自动映射，将double转换为double...但FastJson不能，统一转换为String
//                List<Map<String,String>> list = JSON.parseObject(EntityUtils.toString(entity),new TypeReference<ArrayList<Map<String,String>>>() {});
//                return list;
            }
        };
        List<Map<String,String>> list = client.execute(get, rh);
        System.out.println(list.size());
    }
}
/*
[
  {
    "_id": "5fa80690653842e0b7887b1a",
    "index": 0,
    "guid": "4c61d530-6544-4131-9c6d-a356d92c2f77",
    "isActive": false,
    "balance": "$2,390.23",
    "picture": "http://placehold.it/32x32",
    "age": 29,
    "eyeColor": "green",
    "name": "Gladys David",
    "gender": "female",
    "company": "LUDAK",
    "email": "gladysdavid@ludak.com",
    "phone": "+1 (865) 430-2585",
    "address": "320 Monroe Place, Rivers, West Virginia, 8343",
    "about": "Veniam consequat cupidatat reprehenderit incididunt irure nostrud minim exercitation mollit. Consectetur ex quis exercitation occaecat amet et exercitation aute quis deserunt eu. Ullamco adipisicing cillum ullamco minim sit irure laborum est mollit qui.\r\n",
    "registered": "2018-11-30T03:06:33 -08:00",
    "latitude": -5.895988,
    "longitude": 77.459684,
    "tags": [
      "ex",
      "cillum",
      "minim",
      "culpa",
      "proident",
      "ea",
      "commodo"
    ],
    "friends": [
      {
        "id": 0,
        "name": "Kerry Jennings"
      },
      {
        "id": 1,
        "name": "Dean Diaz"
      },
      {
        "id": 2,
        "name": "Pearl Caldwell"
      }
    ],
    "greeting": "Hello, Gladys David! You have 4 unread messages.",
    "favoriteFruit": "apple"
  }
]
 */
