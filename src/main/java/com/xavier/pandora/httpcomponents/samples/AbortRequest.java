package com.xavier.pandora.httpcomponents.samples;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

public class AbortRequest {
    public static void main(String[] args) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://www.tutorialspoint.com/");
        System.out.println(httpGet.getMethod());
        HttpResponse httpResponse = httpClient.execute(httpGet);
        System.out.println(httpResponse.getStatusLine());
        System.out.println(httpResponse.getEntity().getContentLength());
        System.out.println(httpResponse.getEntity().getContentLength());
        httpGet.abort();
        System.out.println(httpResponse.getEntity().getContentLength());

        httpResponse = httpClient.execute(httpGet);
        System.out.println(httpResponse.getEntity().getContentLength());
    }
}
