package com.xavier.pandora.httpcomponents.samples;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

public class ClientCustomSSL {
    private static Logger logger = LoggerFactory.getLogger(ClientCustomSSL.class);
    public static void main(String[] args) throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException, KeyManagementException {
        SSLContextBuilder SSLBuilder = SSLContexts.custom();
        String currentPath = ClientCustomSSL.class.getResource("").getPath();
        logger.info(currentPath);
        File file = new File(currentPath+ File.separator+"mykeystore.jks");
        SSLBuilder = SSLBuilder.loadTrustMaterial(file, "changeit".toCharArray());
        SSLContext sslContext = SSLBuilder.build();
        SSLConnectionSocketFactory sslConSocketFactory = new SSLConnectionSocketFactory(sslContext, new NoopHostnameVerifier());
        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslConSocketFactory).build();
        HttpGet httpGet = new HttpGet("Https://example.com/");
        HttpResponse httpResponse = httpClient.execute(httpGet);
        logger.info(httpResponse.getStatusLine().toString());
        HttpEntity entity = httpResponse.getEntity();
        if(entity != null) {
            logger.info(String.valueOf(EntityUtils.toByteArray(entity).length));
        }
    }
}
