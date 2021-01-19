package com.xavier.stamps.utils;

import com.xavier.stamps.entity.Stamp;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ParseHTML {
    public static final Logger log = LoggerFactory.getLogger(ParseHTML.class);

    public static String parseHTML(String stampURL) {
        try(CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet(stampURL);
            log.info(httpGet.getRequestLine().toString());
            return httpClient.execute(httpGet, handleResponse());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ResponseHandler<String> handleResponse () {
        ResponseHandler<String> responseHandler = response -> {
            int status = response.getStatusLine().getStatusCode();
            log.info("Response Status: " + status);
            if(status >= 200 && status < 300) {
                HttpEntity entity = response.getEntity();
                return entity != null ? EntityUtils.toString(entity) : null;
            } else {
                throw new ClientProtocolException("Unexpected response status: "  + status);
            }
        };
        return responseHandler;
    }

    public static byte[] getImageStream(String stampImageURL) {
        try(CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet(stampImageURL);
            log.info(httpGet.getRequestLine().toString());
            return httpClient.execute(httpGet, handleStreamResponse());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ResponseHandler<byte[]> handleStreamResponse () {
        ResponseHandler<byte[]> responseHandler = response -> {
            int status = response.getStatusLine().getStatusCode();
            log.info("Response Status: " + status);
            if(status >= 200 && status < 300) {
                HttpEntity entity = response.getEntity();
                return entity != null ? EntityUtils.toByteArray(entity) : null;
            } else {
                throw new ClientProtocolException("Unexpected response status: "  + status);
            }
        };
        return responseHandler;
    }

    public static Stamp generateStampInfo(String html) {
        Stamp stamp = new Stamp();
        Document doc = Jsoup.parse(html);
        String name = doc.getElementsByTag("h1").get(0).children().get(2).text();
        stamp.setName(name);
        String imgLink = doc.getElementById("item_pics_row").child(0).child(0).attr("src");
        stamp.setImgLink("https:"+imgLink);
        //set Blob Property
        stamp.setImg(getImageStream("https:"+imgLink));
        String imgName = imgLink.substring(imgLink.lastIndexOf("/")+1);
        stamp.setImgName(imgName);
        Elements details = doc.getElementById("item_full_details").child(0).getElementsByTag("dl").get(0).children();
        for(int i=0; i< details.size(); i++) {
            if(i% 2 == 1) {
                String infoTag = details.get(i-1).text();
                String infoDetail = details.get(i).text();
                switch(infoTag.substring(0, infoTag.lastIndexOf(":"))){
                    case "Country":
                        stamp.setCountry(infoDetail);
                        break;
                    case "Series":
                        stamp.setSeries(infoDetail);
                        break;
                    case "Catalog codes":
                        infoDetail = details.get(5).html().replaceAll("</*strong>", "").replaceAll("<br>","");
                        stamp.setCodes(infoDetail);
                        break;
                    case "Themes":
                        stamp.setThemes(infoDetail);
                        break;
                    case "Issued on":
                        stamp.setIssuedOn(infoDetail);
                        break;
                    case "Expiry date":
                        stamp.setExpiryDate(infoDetail);
                        break;
                    case "Size":
                        stamp.setDimension(infoDetail);
                        break;
                    case "Colors":
                        stamp.setColors(infoDetail);
                        break;
                    case "Emission":
                        stamp.setEmission(infoDetail);
                        break;
                    case "Perforation":
                        stamp.setPerforation(infoDetail);
                        break;
                    case "Printing":
                        stamp.setPrinting(infoDetail);
                        break;
                    case "Paper":
                        stamp.setPaper(infoDetail);
                        break;
                    case "Face value":
                        stamp.setFaceValue(infoDetail);
                        break;
                    case "Print run":
                        stamp.setPrintRun(Integer.parseInt(infoDetail.replaceAll(",", "")));
                        break;
                    case "Score":
                        stamp.setScore(infoDetail.substring(0, infoDetail.indexOf("%")+1));
                        break;
                    default:
                        break;
                }
            }
        }

        SimpleDateFormat formatter  = new SimpleDateFormat("yyyy-MM-dd");
        String  dString = formatter.format(new Date());
        stamp.setCollectingDate(dString);
        return stamp;
    }

    public static Stamp generateEntireStamp(String id, String links) {
        String html = ParseHTML.parseHTML(links);
        Stamp stamp = generateStampInfo(html);
        stamp.setId(id);
        stamp.setLinks(links);
        return stamp;
    }

    public static void main(String[] args) {
        String id = "CZE000001";
        String links = "https://colnect.com/en/stamps/stamp/121111-Eurasian_Lynx_Lynx_lynx_Willow_Gentian_Gentiana_asclepia-Protection_of_Nature-Czechoslovakia";
        Stamp stamp = generateEntireStamp(id, links);
        log.info(stamp.toString());
    }
}
