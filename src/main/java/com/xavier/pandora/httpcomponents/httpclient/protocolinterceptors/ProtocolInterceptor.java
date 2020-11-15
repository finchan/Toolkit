package com.xavier.pandora.httpcomponents.httpclient.protocolinterceptors;

import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class ProtocolInterceptor {
    public static void main(String[] args) {
        CloseableHttpClient httpClient = HttpClients.custom()
                .addInterceptorLast(new HttpRequestInterceptor() {
                    @Override
                    public void process(HttpRequest httpRequest, HttpContext httpContext) throws HttpException, IOException {
                        AtomicInteger count = (AtomicInteger) httpContext.getAttribute("Count");
                        httpRequest.addHeader("Count", Integer.toString(count.getAndIncrement()));
                    }
                })
                .build();

        AtomicInteger count = new AtomicInteger(1);
        HttpClientContext localContext = HttpClientContext.create();
        localContext.setAttribute("Count", count);

        HttpGet httpGet = new HttpGet("https://242223f1-f240-4823-abfc-1d32bfa2a0a8.mock.pstmn.io/dummy1");
        for (int i = 0; i < 10; i++) {
            CloseableHttpResponse response = null;
                    try {
                        response = httpClient.execute(httpGet, localContext);
                        HttpEntity entity = response.getEntity();
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

}
