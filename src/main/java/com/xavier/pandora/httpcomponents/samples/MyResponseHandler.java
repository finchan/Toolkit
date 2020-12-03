package com.xavier.pandora.httpcomponents.samples;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class MyResponseHandler implements ResponseHandler<String> {
    @Override
    public String handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
        int status = response.getStatusLine().getStatusCode();
        if (status >= 200 & status < 300) {
            HttpEntity entity = response.getEntity();
            if (entity == null) {
                return "";
            } else {
                return EntityUtils.toString(entity);
            }
        }
        return "" + status + "\t";
    }
}
