package com.xavier.pandora.httpcomponents.httpclient.connectionmanagement.poolingconnectionmanager;

import org.apache.http.HttpHost;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

public class PoolingConnectionManager {
    public static void main(String[] args) {
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(200);
        cm.setDefaultMaxPerRoute(20);
        //Increase max connections for specific host
        HttpHost host = new HttpHost("www.baidu.com", 80);
        cm.setMaxPerRoute(new HttpRoute(host), 50);
        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(cm)
                .build();
    }
}
