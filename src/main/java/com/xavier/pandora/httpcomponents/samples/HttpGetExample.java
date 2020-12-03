package com.xavier.pandora.httpcomponents.samples;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.util.Scanner;

public class HttpGetExample {
    public static void main(String[] args) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("https://www.tutorialspoint.com/");
        System.out.println("Result Type: " + httpGet.getMethod());
        HttpResponse response = httpClient.execute(httpGet);

        Scanner sc = new Scanner(response.getEntity().getContent());
        System.out.println(response.getStatusLine());
        while (sc.hasNext()) {
            System.out.println(sc.nextLine());
        }
    }
}
