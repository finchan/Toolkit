package com.xavier.pandora.httpcomponents.httpclient.httprequest;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.message.BasicHttpResponse;

public class HttpResponseHeader {
    public static void main(String[] args) {
        getHttpResponseMessageHeader();
    }

    public static void getHttpResponseMessageHeader() {
        HttpResponse response = new BasicHttpResponse(HttpVersion.HTTP_1_1,
                HttpStatus.SC_OK, "OK");
        response.addHeader("Set-Cookie", "c1=a; path=/;domain=localhost");
        response.addHeader("Set-Cookie", "c2 = b; path=\"/\", c3=c; domain=\"localhost\"");
        Header h1 = response.getFirstHeader("Set-Cookie");
        System.out.println(h1);
        Header h2 = response.getLastHeader("Set-Cookie");
        System.out.println(h2);
        Header[] headers = response.getHeaders("Set-Cookie");
        System.out.println(headers.length);
    }
}
