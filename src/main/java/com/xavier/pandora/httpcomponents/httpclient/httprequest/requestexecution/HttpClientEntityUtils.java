package com.xavier.pandora.httpcomponents.httpclient.httprequest.requestexecution;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpClientEntityUtils {
    public static void main(String[] args) throws IOException {
        System.out.println(getEntityUtilsToString());
    }

    public static String getEntityUtilsToString () {
        String responseStr = "";
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("https://242223f1-f240-4823-abfc-1d32bfa2a0a8.mock.pstmn.io/get_entity_utils_to_string");
        CloseableHttpResponse response = null;
        try{
            response = client.execute(httpGet);
            if(response.getStatusLine().getStatusCode()==200) {
                HttpEntity entity = response.getEntity();
                //By default, response.getEntity can be consumed for only once.
                //Wrapping the original entity with the BufferedHttpEntity Class,
                //This will cause the content of the original entity to be read into a in-memory buffer.
                entity = new BufferedHttpEntity(entity);
                responseStr = EntityUtils.toString(entity);
            }
        } catch(IOException e) {
            e.printStackTrace();
        } finally{
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return responseStr;
    }
}
