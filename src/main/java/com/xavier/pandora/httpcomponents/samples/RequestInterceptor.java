package com.xavier.pandora.httpcomponents.samples;

import org.apache.http.*;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;

public class RequestInterceptor {
    public static void main(String[] args) throws IOException {
        HttpRequestInterceptor requestInterceptor = new HttpRequestInterceptor() {
            @Override
            public void process(HttpRequest request, HttpContext context) throws HttpException, IOException {
                if(request.containsHeader("sample-header")) {
                    System.out.println("Contains header sample-header, removing it...");
                    request.removeHeaders("sample-header");
                }
                Header[] headers = request.getAllHeaders();
                for (Header header : headers) {
                    System.out.println(header.getName() + ":" + header.getValue());
                }
            }
        };

        CloseableHttpClient httpClient = HttpClients.custom().addInterceptorFirst(requestInterceptor).build();
        HttpGet httpGet = new HttpGet("https://www.tutorialspoint.com/");
        httpGet.setHeader(new BasicHeader("sample-header", "My first Header"));
        httpGet.setHeader(new BasicHeader("demo-header", "Demo Header"));
        httpGet.setHeader(new BasicHeader("test-header", "My Third Header"));

        HttpResponse httpResponse = httpClient.execute(httpGet);
        System.out.println(httpResponse.getStatusLine());
    }
}
