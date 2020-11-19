package com.xavier.pandora.httpcomponents.httpclient.connectionmanagement.multithreadedrequestexecution;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

public class MultiThreadedRequestExecution {
    public static void main(String[] args) {
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(cm)
                .build();
        String[] urlToGet = {
                "http://www.baidu.com",
                "http://www.sohu.com",
                "http://www.sina.com",
                "http://www.163.com"
        };
        GetThread[] threads = new GetThread[urlToGet.length];
        for (int i = 0; i < threads.length; i++) {
            HttpGet httpGet = new HttpGet(urlToGet[i]);
            threads[i] = new GetThread(httpClient, httpGet);
        }
        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }
    }
}
