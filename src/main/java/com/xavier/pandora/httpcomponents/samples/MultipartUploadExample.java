package com.xavier.pandora.httpcomponents.samples;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class MultipartUploadExample {
    private static Logger logger = LoggerFactory.getLogger(MultipartUploadExample.class);
    public static void main(String[] args) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String currentPath = MultipartUploadExample.class.getResource("").getPath();

        File file = new File(currentPath + File.separator + "sample.txt");
        FileBody fileBody = new FileBody(file, ContentType.DEFAULT_BINARY);
        MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create();
        entityBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        entityBuilder.addTextBody("sample_text", "This is the text part of our file");
        entityBuilder.addBinaryBody("image", new File(currentPath + File.separator + "sample.jpg"));
        entityBuilder.addPart("file", fileBody);

        HttpEntity multipartHttpEntity = entityBuilder.build();
        RequestBuilder requestBuilder = RequestBuilder.post("http://httpbin.org/post");
        requestBuilder.setEntity(multipartHttpEntity);
        HttpUriRequest multipartRequest = requestBuilder.build();
        HttpResponse httpResponse = httpClient.execute(multipartRequest);

        logger.info(EntityUtils.toString(httpResponse.getEntity()));
        logger.info(String.valueOf(httpResponse.getStatusLine()));
    }
}
