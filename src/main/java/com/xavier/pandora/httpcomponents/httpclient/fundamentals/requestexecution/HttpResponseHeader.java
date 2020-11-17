package com.xavier.pandora.httpcomponents.httpclient.fundamentals.requestexecution;

import org.apache.http.*;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.message.BasicHttpResponse;

public class HttpResponseHeader {
    public static void main(String[] args) {
        getHttpResponseMessageHeader();
    }

    public static void getHttpResponseMessageHeader() {
        HttpResponse response = new BasicHttpResponse(HttpVersion.HTTP_1_1,
                HttpStatus.SC_OK, "OK");
        response.addHeader("Set-Cookie", "c1=a; path=/;domain=localhost");
        response.addHeader("Set-Cookie", "c2 = b; path=\"/\", c3=c; domain=\"localhost\"");
        Header h1 = response.getFirstHeader("Set-Cookie");
        System.out.println(h1);
        Header h2 = response.getLastHeader("Set-Cookie");
        System.out.println(h2);
        Header[] headers = response.getHeaders("Set-Cookie");
        System.out.println(headers.length);
        HeaderIterator it = response.headerIterator("Set-Cookie");
        while(it.hasNext()) {
            System.out.println(it.next());
        }

        HeaderElementIterator heit = new BasicHeaderElementIterator(
                response.headerIterator("Set-Cookie")
        );
        while(heit.hasNext()) {
            HeaderElement elem = heit.nextElement();
            System.out.println(elem.getName() + "=" +  elem.getValue());
            NameValuePair[] params = elem.getParameters();
            for (int i=0; i< params.length; i++) {
                System.out.println(" " + params[i]);
            }
        }
    }
}
