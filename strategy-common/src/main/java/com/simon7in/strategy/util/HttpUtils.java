package com.simon7in.strategy.util;


import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: wb-shenjiayu
 * Date: 13-4-28
 * Time: 下午2:55
 * To change this template use File | Settings | File Templates.
 */
public class HttpUtils {

    private static Logger logger = LoggerFactory.getLogger(HttpUtils.class);

    public static DefaultHttpClient createHttpClientForNew() {
        return new DefaultHttpClient();
    }


    public static DefaultHttpClient createHttpClientNew() {
        return new DefaultHttpClient();
    }

    public static String get(String uri, DefaultHttpClient httpclient) {
        logger.info("request URL: " + uri);
        HttpGet httpGet = new HttpGet(uri);
        HttpEntity entity;
        try {
            httpclient.getParams().setParameter(ClientPNames.ALLOW_CIRCULAR_REDIRECTS, true);
            /*
            *入参的时候自己组装cookie
             */
//             httpclient.setCookieStore(cookieStore);
            entity = httpclient.execute(httpGet).getEntity();
            String responseString = EntityUtils.toString(entity, "UTF-8");
            EntityUtils.consume(entity);
            //logger.info("return info:" + responseString);
            return responseString;
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            logger.error(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            logger.error(e.getMessage());
        }
        System.out.println("http get error");
        logger.error("http get error");
        return null;
    }

    public static String post(String uri, DefaultHttpClient httpclient, Map<String, String> params) throws IOException {
        logger.info("request URL: " + uri);
        HttpPost httpPost = new HttpPost(uri);
        HttpEntity entity;
            /*
            *入参的时候自己组装cookie
             */
//        httpclient.setCookieStore(cookieStore);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        Set<String> keySet = params.keySet();
        for(String key : keySet) {
            nvps.add(new BasicNameValuePair(key, params.get(key)));
        }
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
            entity = httpclient.execute(httpPost).getEntity();
            String responseString = EntityUtils.toString(entity);
            EntityUtils.consume(entity);
            //logger.info("return info:" + responseString);
            return responseString;
        } catch (ClientProtocolException e) {
            logger.error(e.getMessage());
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        logger.error("http post error");
        return null;
    }

    public static  String put(String uri, DefaultHttpClient httpclient, Map<String, String> params) throws IOException{
        logger.info("request URL: " + uri);
        HttpPut httpPut = new HttpPut(uri);
        HttpEntity entity;
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        Set<String> keySet = params.keySet();
        for(String key : keySet) {
            nvps.add(new BasicNameValuePair(key, params.get(key)));
        }
        try {
            httpPut.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
            entity = httpclient.execute(httpPut).getEntity();
            String responseString = EntityUtils.toString(entity);
            EntityUtils.consume(entity);
            //logger.info("return info:" + responseString);
            return responseString;
        } catch (ClientProtocolException e) {
            logger.error(e.getMessage());
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        logger.error("http post error");
        return null;
    }


    public static void main(String args[]) throws IOException {

        String url = "http://www.baidu.com";
        Map<String, String> parMap = new HashMap<String, String>();
        parMap.put("method", "scanPackage");

        CookieStore cookieStore = new BasicCookieStore();
        BasicClientCookie cookie1 = new BasicClientCookie("JSESSIONID", "123");
        BasicClientCookie cookie2 = new BasicClientCookie("l", "123");
        BasicClientCookie cookie3 = new BasicClientCookie("tmp0", "123");
        BasicClientCookie cookie4 = new BasicClientCookie("abc", "123");
        BasicClientCookie cookie5 = new BasicClientCookie("wmporder0", "123");
        cookie1.setDomain("www.baidu.com");
        cookie2.setDomain("www.baidu.com");
        cookie3.setDomain("www.baidu.com");
        cookie4.setDomain("www.baidu.com");
        cookie5.setDomain("www.baidu.com");
        cookieStore.addCookie(cookie1);
        cookieStore.addCookie(cookie2);
        cookieStore.addCookie(cookie3);
        cookieStore.addCookie(cookie4);
        cookieStore.addCookie(cookie5);
        DefaultHttpClient httpClient = createHttpClientForNew();
        httpClient.setCookieStore(cookieStore);
        String response = post(url,httpClient, parMap);

        System.out.println(response);
    }
}