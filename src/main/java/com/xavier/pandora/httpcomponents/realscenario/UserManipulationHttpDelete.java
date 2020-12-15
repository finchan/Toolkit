package com.xavier.pandora.httpcomponents.realscenario;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class UserManipulationHttpDelete {
    Logger logger = LoggerFactory.getLogger(UserManipulationHttpDelete.class);

    public static void main(String[] args) {
        new UserManipulationHttpDelete().deleteUser(5);
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

    public String deleteUser(int userId) {
        try(CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpDelete httpDelete = new HttpDelete("https://a7796048-8c8c-4e1e-96ee-7306fcb56465.mock.pstmn.io/api/v1/users/"+userId);
            String responseString = httpClient.execute(httpDelete, handleResponse());
            logger.info(responseString);
            return responseString;
        } catch(IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
