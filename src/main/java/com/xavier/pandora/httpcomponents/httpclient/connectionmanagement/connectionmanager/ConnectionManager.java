package com.xavier.pandora.httpcomponents.httpclient.connectionmanagement.connectionmanager;

import org.apache.http.HttpClientConnection;
import org.apache.http.HttpHost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ConnectionPoolTimeoutException;
import org.apache.http.conn.ConnectionRequest;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class ConnectionManager {
    public static void main(String[] args) throws InterruptedException, ExecutionException, ConnectionPoolTimeoutException {
        HttpClientContext context = HttpClientContext.create();
        HttpClientConnectionManager connMngr = new BasicHttpClientConnectionManager();
        HttpRoute route = new HttpRoute(new HttpHost("www.baidu.com"));
        ConnectionRequest connRequest = connMngr.requestConnection(route, null);
        HttpClientConnection conn = connRequest.get(10, TimeUnit.SECONDS);
        if(!conn.isOpen()){
            try {
                //establish connection based on its route info
                connMngr.connect(conn, route, 1000, context);
                //and mark it as route complete
                connMngr.routeComplete(conn, route, context);
                //Do useful things with the connection
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                connMngr.releaseConnection(conn, null, 1, TimeUnit.MINUTES);
            }
        }
    }
}
