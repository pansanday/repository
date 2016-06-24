package com.newversion.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

public class TokenTest {

	public static void main(String[] args) throws Exception {
		String tokenUrl = "";
		URL url = new URL(tokenUrl);
		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();

		TrustManager[] tm = { new MyX509TrustManager() };
		SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
		sslContext.init(null, tm, new java.security.SecureRandom());
		SSLSocketFactory ssf = sslContext.getSocketFactory();
		conn.setSSLSocketFactory(ssf);
		conn.setDoInput(true);
		conn.setRequestMethod("GET");
		InputStream inputStream = conn.getInputStream();
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");

		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		StringBuffer buffer = new StringBuffer();
		String str = null;

		while ((str = bufferedReader.readLine()) != null) {
			buffer.append(str);
		}

		bufferedReader.close();
		inputStreamReader.close();
		inputStream.close();
		conn.disconnect();
		System.out.println(buffer);

		// URL url = new URL(requestUrl);
		// HttpsURLConnection connection = (HttpsURLConnection)
		// url.openConnection();
		// connection.setSSLSocketFactory(ssf);
	}
}
