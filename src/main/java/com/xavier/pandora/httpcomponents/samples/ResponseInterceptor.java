package com.xavier.pandora.httpcomponents.samples;

import org.apache.http.Header;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;

public class ResponseInterceptor {
    public static void main(String[] args) throws IOException {
        HttpResponseInterceptor responseInterceptor = new HttpResponseInterceptor() {
            @Override
            public void process(HttpResponse response, HttpContext context) throws HttpException, IOException {
                System.out.println("Adding additional response headers");
                response.addHeader("Demo-Header", "Demo Header");
                response.addHeader("test-header", "Test Header");
            }
        };

        CloseableHttpClient client = HttpClients.custom().addInterceptorFirst(responseInterceptor).build();
        HttpGet httpGet = new HttpGet("https://www.tutorialspoint.com/");
        HttpResponse response = client.execute(httpGet);
        Header[] headers = response.getAllHeaders();
        for (Header header : headers) {
            System.out.println(header.getName() + "\t" + header.getValue());
        }
    }
}
