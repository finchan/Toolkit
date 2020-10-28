package com.xavier.pandora.httpcomponents.httpclient.httprequest;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.message.BasicHttpResponse;


public class HttpResponseInfo {

    public static void getHttpResponseInfo() {
        HttpResponse response = new BasicHttpResponse(HttpVersion.HTTP_1_1,
                HttpStatus.SC_OK, "OK");
        System.out.println(response.getStatusLine());
        System.out.println(response.getStatusLine().getStatusCode());
        System.out.println(response.getStatusLine().getReasonPhrase());
        System.out.println(response.getProtocolVersion());
        System.out.println(response.getStatusLine().getProtocolVersion());
    }

    //Intellij -> psvm to type main method quickly
    public static void main(String[] args) {
        getHttpResponseInfo();
    }
}
