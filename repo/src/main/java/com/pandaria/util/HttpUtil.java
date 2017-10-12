package com.pandaria.util;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class HttpUtil {

    private static final Logger logger = LogManager.getLogger();

    public static String httpClientPost(String url) {
        String result = "";
        HttpClient client = new HttpClient();
        GetMethod getMethod = new GetMethod(url);
        try {
            client.executeMethod(getMethod);

            BufferedReader reader = new BufferedReader(new InputStreamReader(getMethod.getResponseBodyAsStream(), "ISO-8859-1"));
            String tmp;
            StringBuilder htmlRet = new StringBuilder();
            while ((tmp = reader.readLine()) != null) {
                htmlRet.append(tmp).append("\r\n");
            }

            result = new String(htmlRet.toString().getBytes("ISO-8859-1"), "UTF-8");
        } catch (Exception e) {
            logger.error(e);
        } finally {
            getMethod.releaseConnection();
        }
        return result;
    }
}
