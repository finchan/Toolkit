package com.xavier.pandora.httpcomponents.httpclient.requestcontext;


import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class ConnectionKeepAliveStrategySample {
    public static void main(String[] args) {
        ConnectionKeepAliveStrategy keepAliveStra = new DefaultConnectionKeepAliveStrategy() {
            @Override
            public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
                long keepAlive =  super.getKeepAliveDuration(response, context);
                if (keepAlive == -1) {
                    keepAlive = 5000; //如果默认没有设置，则设置连接时间5秒钟
                }
                return keepAlive;
            }
        };

        CloseableHttpClient client = HttpClients.custom()
                 .setKeepAliveStrategy(keepAliveStra)
                .build();
        HttpGet get = new HttpGet("https://242223f1-f240-4823-abfc-1d32bfa2a0a8.mock.pstmn.io/mockup");
        CloseableHttpResponse response = null;
        try {
            response = client.execute(get);
            System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
