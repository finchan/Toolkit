package com.xavier.pandora.httpcomponents.httpclient.exceptionhandling;

import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HttpContext;

import javax.net.ssl.SSLException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;

public class ExceptionHandling {
    public static void main(String[] args) throws IOException {
        HttpRequestRetryHandler myRetryHandler = new HttpRequestRetryHandler() {
            @Override
            public boolean retryRequest(IOException exception, int executeCount, HttpContext httpContext) {
                if(executeCount >= 5) {
                    return false;
                }
                if(exception instanceof InterruptedIOException) {
                    return false;
                }
                if (exception instanceof UnknownHostException) {
                    return false;
                }
                if(exception instanceof ConnectTimeoutException) {
                    return false;
                }
                if(exception instanceof SSLException ) {
                    return false;
                }
                HttpClientContext context  = HttpClientContext.adapt(httpContext);
                HttpRequest request = context.getRequest();
                /*
                    Tells if this request should use the expect-continue handshake.
                    The expect continue handshake gives the server a chance to decide
                    whether to accept the entity enclosing request before the possibly
                    engthy entity is sent across the wire.
                    return true if the expect continue handshake should be used, false if
                    not.
                 */
                boolean idempotent =!(request instanceof HttpEntityEnclosingRequest);
                if(idempotent) {
                    //Retry if the request is considered idempotent
                    return true;
                }
                return true;
            }
        };
        CloseableHttpClient httpClient = HttpClients.custom()
                .setRetryHandler(myRetryHandler)
                .build();
        HttpGet httpGet = new HttpGet("https://242223f1-f240-4823-abfc-1d32bfa2a0a8.mock.pstmn.io.unknownhost");
        CloseableHttpResponse response = httpClient.execute(httpGet);
        System.out.println("response = " + response);
    }
}
