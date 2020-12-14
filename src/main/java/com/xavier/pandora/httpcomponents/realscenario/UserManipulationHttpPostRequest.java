package com.xavier.pandora.httpcomponents.realscenario;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class UserManipulationHttpPostRequest {
    Logger logger = LoggerFactory.getLogger(UserManipulationHttpPostRequest.class);

    public static void main(String[] args) {
        UserManipulationHttpPostRequest userManipulationHttpPostRequest = new UserManipulationHttpPostRequest();
        userManipulationHttpPostRequest.addUser();
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

    public String addUser() {
        try(CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost("https://a7796048-8c8c-4e1e-96ee-7306fcb56465.mock.pstmn.io/api/v1/users");
            String json = "{\"firstName\":\"Ram\",\"lastName\":\"Jadhav\",\"emailId\":\"ramesh1234@gmail.com\",\n" +
                    "\"createdAt\":\"2018-10-29T09:37:09.821+0000\",\"createdBy\":\"Ramesh\",\"updatedAt\":\"2018-10-29T09:37:09.821+0000\",\n" +
                    "\"updatedby\":\"Ramesh\"}";
            StringEntity stringEntity = new StringEntity(json);
            httpPost.setEntity(stringEntity);
            logger.info("Executing request: " + httpPost.getRequestLine());
            String responseBody = httpClient.execute(httpPost, handleResponse());
            logger.info(responseBody);
            return responseBody;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
