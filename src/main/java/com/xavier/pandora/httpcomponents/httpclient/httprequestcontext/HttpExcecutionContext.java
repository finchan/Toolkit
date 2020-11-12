package com.xavier.pandora.httpcomponents.httpclient.httprequestcontext;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;

public class HttpExcecutionContext {
    public static void main(String[] args) throws IOException {
        HttpContext context = new BasicHttpContext();
        HttpClientContext clientContext = HttpClientContext.adapt(context);
        HttpHost target = clientContext.getTargetHost();
        HttpRequest request = clientContext.getRequest();
        HttpResponse response = clientContext.getResponse();
        RequestConfig config = clientContext.getRequestConfig();
        System.out.println("----------------------------------------");

        CloseableHttpClient httpClient = HttpClients.createDefault();
        RequestConfig requestConfig2 = RequestConfig.custom()
                .setSocketTimeout(10000)
                .setConnectTimeout(10000)
                .build();
        HttpGet httpget1 = new HttpGet("https://242223f1-f240-4823-abfc-1d32bfa2a0a8.mock.pstmn.io/dummy1");
        HttpGet httpget2 = new HttpGet("https://242223f1-f240-4823-abfc-1d32bfa2a0a8.mock.pstmn.io/dummy2");
        httpget1.setConfig(requestConfig2);
        CloseableHttpResponse response1 = null;
        CloseableHttpResponse response2 = null;
        try {
            response1 =  httpClient.execute(httpget1, context);
            HttpEntity entity1 = response1.getEntity();
            response2 = httpClient.execute(httpget2, context);
            HttpEntity entity2 = response2.getEntity();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            response1.close();
            response2.close();
            httpClient.close();
        }
    }

}
