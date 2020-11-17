package com.xavier.pandora.httpcomponents.httpclient.fundamentals.redirecthandling;

import org.apache.http.HttpHost;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

public class RedirectHandling {
    public static void main(String[] args) throws IOException, URISyntaxException {
//        LaxRedirectStrategy redirectStrategy = new LaxRedirectStrategy();
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpClientContext context = HttpClientContext.create();
        HttpGet httpGet = new HttpGet("https://242223f1-f240-4823-abfc-1d32bfa2a0a8.mock.pstmn.io/mockup");
        CloseableHttpResponse response =  httpClient.execute(httpGet, context);

        //此处可以设置递归函数，循环处理嵌套重定向
        HttpHost target = context.getTargetHost();
        List<URI>  redirectLocations = context.getRedirectLocations();
        for (URI redirectLocation : redirectLocations) {
            System.out.println(redirectLocation.toString());
        }
        URI finalLocation = URIUtils.resolve(httpGet.getURI(), target, redirectLocations);
        System.out.println(finalLocation.toASCIIString());
    }
}
