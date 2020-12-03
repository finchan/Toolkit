package com.xavier.pandora.httpcomponents.samples;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.util.Scanner;

public class HttpPostExample {
    public static void main(String[] args) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("https://www.baidu.com/");
        System.out.println("Result Type: " + httpPost.getMethod());
        HttpResponse response = httpClient.execute(httpPost);

        Scanner sc = new Scanner(response.getEntity().getContent());
        System.out.println(response.getStatusLine());
        while (sc.hasNext()) {
            System.out.println(sc.nextLine());
        }
    }
}
