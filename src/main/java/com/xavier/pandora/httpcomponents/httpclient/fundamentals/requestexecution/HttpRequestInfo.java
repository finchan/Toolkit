package com.xavier.pandora.httpcomponents.httpclient.fundamentals.requestexecution;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;

import java.net.URI;
import java.net.URISyntaxException;

public class HttpRequestInfo {
    public static void main(String[] args) throws URISyntaxException {
        getHttpRequestInfo();
    }

    /**
     * Method - HttpGet, HttpHead, HttpPost, HttpPut, HttpDelete, HttpTrace, HttpOptions
     */
    public static void getHttpRequestInfo() throws URISyntaxException {
        HttpGet httpget = new HttpGet("https://www.baidu.com/");
        System.out.println(httpget.getURI());
        System.out.println(httpget.getRequestLine());

        //https://www.baidu.com/s?wd=httpclient&rsv_spt=1
        URI uri = new URIBuilder()
                .setScheme("https")
                .setHost("www.baidu.com")
                .setPath("s")
                .setParameter("wd", "httpclient")
                .setParameter("rsv_spt", "1")
                .build();
        HttpGet httpGet2 = new HttpGet(uri);
        System.out.println(httpGet2.getURI());
    }
}
