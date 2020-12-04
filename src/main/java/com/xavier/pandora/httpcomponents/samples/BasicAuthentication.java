package com.xavier.pandora.httpcomponents.samples;

import org.apache.http.Header;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class BasicAuthentication {
    public static void main(String[] args) throws IOException {
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        AuthScope authScope = new AuthScope("postman-echo.com", AuthScope.ANY_PORT);

        Credentials credentials = new UsernamePasswordCredentials("postman", "password");
        credentialsProvider.setCredentials(authScope,credentials);
        //Below code is OK
        //credentialsProvider.setCredentials(AuthScope.ANY,credentials);
        CloseableHttpClient httpClient = HttpClients.custom()
                .setDefaultCredentialsProvider(credentialsProvider)
                .build();
        HttpGet httpGet = new HttpGet("https://postman-echo.com/basic-auth");
        String responseStr = httpClient.execute(httpGet, response -> {
            System.out.println(response.getStatusLine());
            System.out.println(response.getStatusLine().getStatusCode());
            Header[] headers = response.getAllHeaders();
            for (Header header : headers) {
                System.out.println(header.getName() + "\t" + header.getValue());
            }
            return EntityUtils.toString(response.getEntity());
        });
        System.out.println(responseStr);
    }
}
