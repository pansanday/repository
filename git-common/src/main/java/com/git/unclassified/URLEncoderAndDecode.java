package com.git.unclassified;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * URL编码解码,譬如 %20 -> 空格
 */
public class URLEncoderAndDecode {

    public static void main(String[] args) {
        String string = "panda";
        String string2 = "Highway+110+N+Km+5+1";
        String string3 = "10%2F25%2F2012+00%3A00%3A00";
        String string4 = "http://www.baidu.com/s?wd=%E6%B1%9F%E8%8B%8F%E7%9C%81%20%E5%8D%97%E4%BA%AC&tn=baidu&ie=utf-8&f=8&rsv_bp=1&rsv_sug3=7&rsv_sug4=863&rsv_sug1=3&rsv_sug2=0&inputT=8867&bs=%E6%B1%9F%E8%8B%8F%E7%9C%81&rsv_spt=3";

        try {
            System.out.println(URLEncoder.encode(string, "UTF-8"));
            System.out.println(URLDecoder.decode(string, "UTF-8"));

            System.out.println(URLEncoder.encode(string2, "UTF-8"));
            System.out.println(URLDecoder.decode(string2, "UTF-8"));

            System.out.println(URLEncoder.encode(string3, "UTF-8"));
            System.out.println(URLDecoder.decode(string3, "UTF-8"));

            System.out.println(URLEncoder.encode(string4, "UTF-8"));
            System.out.println(URLDecoder.decode(string4, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
