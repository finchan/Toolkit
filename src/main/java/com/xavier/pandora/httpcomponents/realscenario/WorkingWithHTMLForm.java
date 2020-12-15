package com.xavier.pandora.httpcomponents.realscenario;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WorkingWithHTMLForm {
    Logger logger = LoggerFactory.getLogger(WorkingWithHTMLForm.class);

    public String submitForm() {
        try(CloseableHttpClient httpClient = HttpClients.createDefault()) {
            List<NameValuePair> form = new ArrayList<> ();
            form.add(new BasicNameValuePair("John", "Cena"));
            form.add(new BasicNameValuePair("Tom", "Cruise"));
            form.add(new BasicNameValuePair("Tony", "Stark"));
            // The UrlEncodedFormEntity instance will use the so-called URL encoding to
            // encode parameters and produce the following content:
            // param1=value1&param2=value2
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(form, Consts.UTF_8);

            HttpPost httpPost = new HttpPost("https://a7796048-8c8c-4e1e-96ee-7306fcb56465.mock.pstmn.io/api/v1/submitform");
            httpPost.setEntity(entity);

            String responseBody = httpClient.execute(httpPost, handleResponse());
            logger.info(responseBody);
            return responseBody;
        } catch(IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        new WorkingWithHTMLForm().submitForm();
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

}
