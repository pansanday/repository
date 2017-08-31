package com.pandaria.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.*;
import java.util.Scanner;

public class WebpageDownloadUtil {

    public static void downloadPage(String url) throws IOException {

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = httpClient.execute(httpGet);

        InputStream is = null;
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            try {
                HttpEntity entity = response.getEntity();
                is = entity.getContent();
                String fileName = getFileName(url);
                saveToFile("D:\\tmp\\", fileName, is);
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } finally {
                if (is != null) {
                    is.close();
                }
                response.close();
            }
        }

    }

    // 将输入流中的内容输出到path指定的路径，fileName指定的文件名
    private static void saveToFile(String sourceFolder, String fileName, InputStream is) {
        Scanner sc = new Scanner(is);
        Writer os = null;
        try {
            File file = new File(sourceFolder);
            file.mkdirs();
            /*if (!file.exists()) {
                file.mkdirs();
            }*/

            os = new PrintWriter(sourceFolder + fileName);
            while (sc.hasNext()) {
                os.write(sc.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            sc.close();
            if (os != null) {
                try {
                    os.flush();
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("输出流关闭失败！");
                }
            }
        }
    }

    // 将url中的特殊字符用下划线代替
    private static String getFileName(String url) {
        url = url.substring(7);
        return url.replaceAll("[\\?:*|<>\"/]", "_") + ".html";
    }

    public static void main(String[] args) throws IOException {
        downloadPage("http://www.baidu.com");
    }
}
