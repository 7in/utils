package com.simon7in.strategy.util;


import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.lang.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Created with IntelliJ IDEA.
 * User: wb-shenjiayu
 * Date: 13-4-28
 * Time: 下午2:55
 * To change this template use File | Settings | File Templates.
 */
public class HttpUtils {
    private static Logger logger = LoggerFactory.getLogger(HttpUtils.class);
    private final static Integer TIME_OUT_CONN = 30000;
    private final static Integer TIME_OUT_SO = 30000;

    public static String getHttpResponse(String url) {
        if (StringUtils.isEmpty(url)) {
            return null;
        }
        HttpClient httpClient = new HttpClient();
        //设置默认超时时间
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(TIME_OUT_CONN);
        httpClient.getHttpConnectionManager().getParams().setSoTimeout(TIME_OUT_SO);

        GetMethod httpGet = new GetMethod(url);
        //设置默认超时时间
        httpGet.getParams().setSoTimeout(TIME_OUT_SO);
        httpGet.addRequestHeader("content-type", "application/x-www-form-urlencoded;charset=GBK");
        try {
            if (httpClient.executeMethod(httpGet) != HttpStatus.SC_OK) {
                logger.warn("httpGet(\"" + url + "\") failed: "
                        + httpGet.getStatusLine());
                return null;
            }
            return httpGet.getResponseBodyAsString();
        } catch (Exception e) {
            logger.error("[HttpUtils.getHttpResponse] error", e);
        } finally {
            httpGet.releaseConnection();
        }
        return null;
    }

    public static String urlToStringForPost(Map parameters, String apiUrl, String charset) {
        URL url = null;
        HttpURLConnection urlconnection = null;
        DataInputStream input = null;
        String str = "";
        try {

            StringBuffer params = new StringBuffer();
            for (Iterator iter = parameters.entrySet().iterator(); iter
                    .hasNext(); ) {
                Entry element = (Entry) iter.next();
                params.append(element.getKey().toString());
                params.append("=");
                if (element.getValue() != null) {
                    params.append(URLEncoder.encode(element.getValue().toString(), charset));
                }
                params.append("&");
            }

            if (params.length() > 0) {
                params = params.deleteCharAt(params.length() - 1);
            }


            url = new URL(apiUrl);
            urlconnection = (HttpURLConnection) url.openConnection();
            urlconnection.setRequestMethod("POST");
            urlconnection.setDoOutput(true);
            byte[] b = params.toString().getBytes();
            urlconnection.getOutputStream().write(b, 0, b.length);
            urlconnection.getOutputStream().flush();
            urlconnection.getOutputStream().close();


            input = new DataInputStream(urlconnection.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(input, charset));
            String line = "";
            while ((line = reader.readLine()) != null) {
                if (null != line && line != "") {
                    str += line;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (urlconnection != null) {
                urlconnection.disconnect();
            }
            try {
                input.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return str;
    }

    public static void main(String args[]) {
        String arrCharsEnc = URLEncoder.encode("[]");
        String url = "http://kelude.taobao.net/api/issues/search.json?" +
                "aone2_id[]=7989";
//                "&status"+arrCharsEnc+"New";
//                &status[]=Reopen&status[]=Open&status[]=Fixed&status[]=Closed&status[]=Later&page=1&per_page=500";
        String baiduUrl = "http://www.baidu.com";
        String response = getHttpResponse(url);
        System.out.println(response);
    }
}
