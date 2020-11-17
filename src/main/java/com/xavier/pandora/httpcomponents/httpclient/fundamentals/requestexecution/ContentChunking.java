package com.xavier.pandora.httpcomponents.httpclient.fundamentals.requestexecution;

import org.apache.http.Consts;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

public class ContentChunking {
    public static void main(String[] args) throws IOException {
        StringEntity entity = new StringEntity("important message", ContentType.create("plain/text", Consts.UTF_8));
        entity.setChunked(true);
        HttpPost post = new HttpPost("https://242223f1-f240-4823-abfc-1d32bfa2a0a8.mock.pstmn.io/mockup");
        post.setEntity(entity);
        CloseableHttpClient client = HttpClients.createDefault();
        CloseableHttpResponse response = client.execute(post);
        response.close();
        entity.writeTo(System.out);
    }
}
