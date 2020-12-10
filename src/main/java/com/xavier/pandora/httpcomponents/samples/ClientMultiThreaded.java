package com.xavier.pandora.httpcomponents.samples;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class ClientMultiThreaded extends Thread {
    CloseableHttpClient httpClient;
    HttpGet httpGet;
    int id;

    private static Logger log = LoggerFactory.getLogger(ClientMultiThreaded.class);

    public ClientMultiThreaded(CloseableHttpClient httpClient, HttpGet httpGet, int id) {
        this.httpClient = httpClient;
        this.httpGet = httpGet;
        this.id = id;
    }

    public void run() {
        try {
            CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
            log.info("Status of thread " + id + " : " + httpResponse.getStatusLine());
            HttpEntity entity = httpResponse.getEntity();
            if(entity != null) {
                log.info("Bytes read by thread THREAD ID- " + id + " : "
                        + EntityUtils.toByteArray(entity).length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager();
        connManager.setMaxTotal(100);

        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(connManager).build();
        HttpGet httpGet1 = new HttpGet("http://www.baidu.com");
        HttpGet httpGet2 = new HttpGet("http://www.163.com");
        HttpGet httpGet3 = new HttpGet("http://www.126.com");
        HttpGet httpGet4 = new HttpGet("http://www.sina.com");

        ClientMultiThreaded thread1 = new ClientMultiThreaded(httpClient, httpGet1, 1);
        ClientMultiThreaded thread2 = new ClientMultiThreaded(httpClient, httpGet2, 2);
        ClientMultiThreaded thread3 = new ClientMultiThreaded(httpClient, httpGet3, 3);
        ClientMultiThreaded thread4 = new ClientMultiThreaded(httpClient, httpGet4, 4);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
