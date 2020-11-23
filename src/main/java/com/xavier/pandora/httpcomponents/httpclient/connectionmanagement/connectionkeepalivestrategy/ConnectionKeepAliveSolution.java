package com.xavier.pandora.httpcomponents.httpclient.connectionmanagement.connectionkeepalivestrategy;

import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;

public class ConnectionKeepAliveSolution {
    public static void main(String[] args) {
        ConnectionKeepAliveStrategy strategy = new ConnectionKeepAliveStrategy(){
            @Override
            public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
                HeaderElementIterator it = new BasicHeaderElementIterator(response.headerIterator(HTTP.CONN_KEEP_ALIVE));
                while(it.hasNext()) {
                    HeaderElement he = it.nextElement();
                    String param = he.getName();
                    String value = he.getValue();
                    if (value != null && ("timeout").equalsIgnoreCase(param)) {
                        return Long.parseLong(value)*1000;
                    }
                }

                HttpHost target = (HttpHost)context.getAttribute(HttpClientContext.HTTP_TARGET_HOST);
                if("www.baidu.com".equalsIgnoreCase(target.getHostName())) {
                    return 5*1000;
                } else {
                    return 30* 1000;
                }
            }
        };

        CloseableHttpClient client = HttpClients.custom()
                .setKeepAliveStrategy(strategy)
                .build();
    }
}
