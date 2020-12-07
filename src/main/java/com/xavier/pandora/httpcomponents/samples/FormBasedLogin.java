package com.xavier.pandora.httpcomponents.samples;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class FormBasedLogin {
    public static void main(String[] args) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        //Creating RequestBuilder
        RequestBuilder requestBuilder = RequestBuilder.post();
        //Setting URI and parameters
        RequestBuilder requestBuilder1 = requestBuilder.setUri("http://httpbin.org/post");
        RequestBuilder requestBuilder2 = requestBuilder1.addParameter("Name", "username")
                .addParameter("password", "password");
        HttpUriRequest httpPost = requestBuilder2.build();

        HttpResponse httpResponse = httpClient.execute(httpPost);

        System.out.println(EntityUtils.toString(httpResponse.getEntity()));
        System.out.println(httpResponse.getStatusLine());
    }
}
