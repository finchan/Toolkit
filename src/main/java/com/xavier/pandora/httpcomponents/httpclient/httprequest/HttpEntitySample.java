package com.xavier.pandora.httpcomponents.httpclient.httprequest;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStream;

public class HttpEntitySample {
    public static void main(String[] args) throws IOException {
        getEntityContentBasicInfo();
        releaseTheLowerResource();
        readLessInfoFromResponse();
    }

    public static void getEntityContentBasicInfo() throws IOException {
        StringEntity myEntity = new StringEntity("important message", ContentType.create("text/plain", "UTF-8"));
        System.out.println(myEntity.getContentType());
        System.out.println(myEntity.getContentLength());
        //Util class to read content of the response - Avoid reading inputStream
        System.out.println(EntityUtils.toString(myEntity));
        System.out.println(EntityUtils.toByteArray(myEntity).length);
    }

    public static void releaseTheLowerResource() throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("https://www.baidu.com");
        CloseableHttpResponse response = httpClient.execute(httpGet);
        try{
            HttpEntity entity = response.getEntity();
            if(entity != null) {
                InputStream inputStream = entity.getContent();
                try{
                    byte[] bytes = new byte[2048];
                    int len ;
                    while(  (len=inputStream.read(bytes))!=-1 ){
                        System.out.print(new String(bytes));
                    }
                } finally {
                    inputStream.close();
                }
            }
        } finally {
            response.close();
        }
    }

    public static void readLessInfoFromResponse() throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("https://www.baidu.com");
        CloseableHttpResponse response = httpClient.execute(httpGet);
        try{
            HttpEntity entity = response.getEntity();
            if(entity != null) {
                InputStream inputStream = entity.getContent();
                try{
                    System.out.println(inputStream.read());
                    System.out.println(inputStream.read());
                } finally {
                    inputStream.close();
                }
            }
        } finally {
            response.close();
        }
    }
}
