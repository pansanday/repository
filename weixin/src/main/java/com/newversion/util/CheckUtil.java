package com.newversion.util;

import java.security.MessageDigest;
import java.util.Arrays;

public class CheckUtil {

    private static final String token = "panda";

    /**
     * 
     * @param signature	微信加密签名,signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数
     * @param timestamp	时间戳
     * @param nonce	随机数
     * @return
     */
    public static boolean checkSignature(String signature, String timestamp, String nonce) {
        String[] arr = new String[] { token, timestamp, nonce };

        // 将token、timestamp、nonce三个参数进行字典序排序
        Arrays.sort(arr);

        // 将排序后的结果拼接成一个字符串 
        // 拼接方式一
        StringBuffer content = new StringBuffer();
        for (int i = 0; i < arr.length; i++) {
            content.append(arr[i]);
        }
        
        // 拼接方式二
        // String content = arr[0].concat(arr[1]).concat(arr[2]);
        
        // 将拼接成的字符串进行sha1加密
        String temp = getSha1(content.toString());
        
        // 开发者获得加密后的字符串可与signature对比,标识该请求来源于微信
        return temp.equals(signature);
    }
    
    /**
     * sha1加密
     * @param str
     * @return
     */
    public static String getSha1(String str) {
        if (null == str || 0 == str.length()){
            return null;
        }
        char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
                'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
            mdTemp.update(str.getBytes("UTF-8"));
             
            byte[] md = mdTemp.digest();
            int j = md.length;
            char[] buf = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
                buf[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(buf);
        } catch (Exception e) {
            return null;
        }
    }
}
