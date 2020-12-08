package com.xavier.pandora.httpcomponents.samples;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public class ResponsedCookiesManipulation {
    public static void main(String[] args) throws IOException {
        BasicCookieStore cookieStore = new BasicCookieStore();
        HttpClientContext context = HttpClientContext.create();
        context.setCookieStore(cookieStore);
        HttpGet httpGet = new HttpGet("http://stackoverflow.com/");
        CloseableHttpClient client = HttpClients.createDefault();
        client.execute(httpGet, context);
        List<Cookie> list = cookieStore.getCookies();
        for (Cookie o : list) {
            System.out.println("Cookie Name: " + o.getName() + "\t Cookie Value: " + o.getValue());
            System.out.println("Cookie Comment: " + o.getComment());
            System.out.println("Cookie Domain: " + o.getDomain());
            System.out.println("Cookie Expiry Date: " + o.getExpiryDate());
            System.out.println("Cookie Path: " + o.getPath());
            System.out.println("Is Secure: " + o.isSecure());
            System.out.println("Cookie Version: " + o.getVersion());
            Field[] fields = o.getClass().getDeclaredFields();
            Arrays.stream(fields)
                    .filter(field -> "attribs".equals(field.getName()))
                    .forEach(field-> {
                        try {
                            field.setAccessible(true);
                            System.out.println(field.getName() + ": " + field.get(o));
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    });
        }
    }
}
