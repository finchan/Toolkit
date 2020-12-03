package com.xavier.pandora.httpcomponents.samples;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.util.Scanner;

public class ClosingConnection {
    public static void main(String[] args) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try{
            HttpGet httpGet = new HttpGet("http://www.tutorialspoint.com/");
            CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
            try {
                Scanner sc = new Scanner(httpResponse.getEntity().getContent());
                while(sc.hasNext()) {
                    System.out.println(sc.nextLine());
                }
            } finally {
                httpResponse.close();
            }
        } finally {
            httpClient.close();
        }
    }
}
