package com.xavier.pandora.httpcomponents.samples;

import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

public class ResponseHandlerExample {
    public static void main(String[] args) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        ResponseHandler<String> responseHandler = new MyResponseHandler();
        HttpGet httpGet = new HttpGet("http://www.tutorialspoint.com/");
        String httpResponseInfo = client.execute(httpGet, responseHandler);
        System.out.println(httpResponseInfo);
    }
}
