package com.xavier.pandora.httpcomponents.realscenario;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class UserManipulationHttpGetRequest {
    Logger logger = LoggerFactory.getLogger(UserManipulationHttpGetRequest.class);

    public static void main(String[] args) {
        UserManipulationHttpGetRequest userManipulationHttpGetRequest = new UserManipulationHttpGetRequest();
        userManipulationHttpGetRequest.getAllUsers();
        userManipulationHttpGetRequest.getUser(5);
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

    public String getAllUsers() {
        try(CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet("https://a7796048-8c8c-4e1e-96ee-7306fcb56465.mock.pstmn.io/api/v1/users");
            logger.info(httpGet.getRequestLine().toString());
            String responseBody = httpClient.execute(httpGet, handleResponse());
            logger.info(responseBody);
            return responseBody;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getUser(int userId) {
        try(CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet("https://a7796048-8c8c-4e1e-96ee-7306fcb56465.mock.pstmn.io/api/v1/users/" + userId);
            logger.info(httpGet.getRequestLine().toString());
            String responseBody = httpClient.execute(httpGet, handleResponse());
            logger.info(responseBody);
        } catch(IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
