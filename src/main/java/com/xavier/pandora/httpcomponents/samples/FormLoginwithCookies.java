package com.xavier.pandora.httpcomponents.samples;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.util.List;

public class FormLoginwithCookies {
    public static void main(String[] args) throws IOException {
        BasicCookieStore cookieStore = new BasicCookieStore();
        HttpClientBuilder clientBuilder = HttpClients.custom();
        clientBuilder = clientBuilder.setDefaultCookieStore(cookieStore);
        CloseableHttpClient httpClient = clientBuilder.build();

//        HttpUriRequest httpRequest = RequestBuilder.post().setUri("http://httpbin.org/post")
//                .addParameter("Name", "username")
//                .addParameter("password", "password")
//                .build();
//
//        httpClient.execute(httpRequest);
        httpClient.execute(new HttpGet("http://www.baidu.com"));
        System.out.println("List of cookies");
        List<Cookie> list = cookieStore.getCookies();
        for (Cookie o : list) {
            System.out.println(o.getName() + "\t" + o.getValue());
        }
    }
}
