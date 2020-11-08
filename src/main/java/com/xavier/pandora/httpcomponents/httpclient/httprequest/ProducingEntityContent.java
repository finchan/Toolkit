package com.xavier.pandora.httpcomponents.httpclient.httprequest;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;

public class ProducingEntityContent {
    public static void main(String[] args) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        File file = new File("F:\\Projects\\Toolkit\\src\\main\\resources\\logback.xml");
        FileEntity entity = new FileEntity(file, ContentType.create("text/plain", "UTF-8"));
        HttpPost httpPost = new HttpPost("https://242223f1-f240-4823-abfc-1d32bfa2a0a8.mock.pstmn.io/post_file_entity");
        httpPost.setEntity(entity);
        CloseableHttpResponse response = client.execute(httpPost);
        System.out.println(EntityUtils.toString(response.getEntity()));
        response.close();
    }
}
