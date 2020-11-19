package com.xavier.pandora.httpcomponents.httpclient.connectionmanagement.multithreadedrequestexecution;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;

public class GetThread extends Thread {
    private final CloseableHttpClient httpClient;
    private final HttpContext context;
    private  final HttpGet httpGet;

    public GetThread(CloseableHttpClient httpClient, HttpGet httpGet) {
        this.httpClient = httpClient;
        this.httpGet = httpGet;
        this.context = HttpClientContext.create();
    }

    @Override
    public void run() {
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet, context);
            HttpEntity entity = response.getEntity();
            System.out.println(httpGet.getRequestLine());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
