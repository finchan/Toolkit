package com.xavier.pandora.httpcomponents.samples;

import com.xavier.pandora.basic.ClassPath;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class MultipartUploadExample {
    private static Logger logger = LoggerFactory.getLogger(MultipartUploadExample.class);
    public static void main(String[] args) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String currentPath = ClassPath.class.getResource("").getPath();

        File file = new File("");
    }
}
