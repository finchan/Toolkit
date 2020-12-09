package com.xavier.pandora.httpcomponents.samples;

import org.apache.http.client.CookieStore;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.cookie.BasicClientCookie;

import java.util.Iterator;
import java.util.List;

public class CookieManagement {
    public static void main(String[] args) {
        CookieStore cookieStore = new BasicCookieStore();

        BasicClientCookie clientCookie1 = new BasicClientCookie("name", "Raju");
        BasicClientCookie clientCookie2 = new BasicClientCookie("age","28");
        BasicClientCookie clientCookie3 = new BasicClientCookie("place", "Hyderbad");

        clientCookie1.setDomain(".sample.com");
        clientCookie2.setDomain(".sample.com");
        clientCookie3.setDomain(".sample.com");

        clientCookie1.setPath("/");
        clientCookie2.setPath("/");
        clientCookie3.setPath("/");

        cookieStore.addCookie(clientCookie1);
        cookieStore.addCookie(clientCookie2);
        cookieStore.addCookie(clientCookie3);

        List<Cookie> list = cookieStore.getCookies();
        Iterator<Cookie> it = list.iterator();
        while(it.hasNext()) {
            System.out.println(it.next());
        }
    }
}
