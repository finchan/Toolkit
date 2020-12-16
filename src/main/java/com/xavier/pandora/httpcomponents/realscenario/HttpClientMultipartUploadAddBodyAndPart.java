package com.xavier.pandora.httpcomponents.realscenario;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

/**
 * AddBinaryBody/TextBody methods are implemented via AddPart Method.
 * No need to investigate too much, just read the source code of class - MultipartEntityBuilder
 */
public class HttpClientMultipartUploadAddBodyAndPart {
    Logger logger = LoggerFactory.getLogger(HttpClientMultipartUploadAddBodyAndPart.class);

    public String fileUploadAddBinaryBodyAndTextBody() {
        try(CloseableHttpClient httpClient = HttpClients.createDefault()) {
            String message = "This is a multipart post";
            String currentPath = HttpClientMultipartUploadAddBodyAndPart.class.getResource("").getPath();
            File file = new File(currentPath+File.separator+"sample.jpg");
            HttpPost httpPost = new HttpPost("http://httpbin.org/post");
            HttpEntity entity = MultipartEntityBuilder.create()
                    .setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
                    .addTextBody("text", message, ContentType.DEFAULT_BINARY)
                    .addBinaryBody("upload_file", file, ContentType.DEFAULT_BINARY, file.getName())
                    .build();
            httpPost.setEntity(entity);
            String responseBody = httpClient.execute(httpPost, handleResponse());
            logger.info(responseBody);
            return responseBody;
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
        return null;
    }

    public String fileUploadAddPart() {
        try(CloseableHttpClient httpClient = HttpClients.createDefault()) {
            String message = "This is a multipart post";
            String currentPath = HttpClientMultipartUploadAddBodyAndPart.class.getResource("").getPath();
            FileBody fileBody = new FileBody(new File(currentPath+File.separator+"sample.jpg"));
            StringBody comment = new StringBody("A binary file of some kind", ContentType.TEXT_PLAIN);
            HttpEntity entity = MultipartEntityBuilder.create()
                    .setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
                    .addPart("fileBody", fileBody)
                    .addPart("message", comment)
                    .build();
            HttpUriRequest request = RequestBuilder.post("http://httpbin.org/post").setEntity(entity).build();
            String responseBody = httpClient.execute(request, handleResponse());
            logger.info(responseBody);
            return responseBody;

        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
        return null;
    }

    public ResponseHandler<String> handleResponse () {
        ResponseHandler<String> responseHandler = response -> {
            int status = response.getStatusLine().getStatusCode();
            logger.info("Response Status: " + status);
            if(status >= 200 && status < 300) {
                HttpEntity entity = response.getEntity();
                return entity != null ? EntityUtils.toString(entity) : null;
            } else {
                throw new ClientProtocolException("Unexpected response status: "  + status);
            }
        };
        return responseHandler;
    }

    public static void main(String[] args) {
        HttpClientMultipartUploadAddBodyAndPart httpClientMultipartUploadAddBodyAndPart = new HttpClientMultipartUploadAddBodyAndPart();
        httpClientMultipartUploadAddBodyAndPart.fileUploadAddPart();
        httpClientMultipartUploadAddBodyAndPart.fileUploadAddBinaryBodyAndTextBody();
    }
}
