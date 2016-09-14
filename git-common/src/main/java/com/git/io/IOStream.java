package com.git.io;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.IOUtils;

public class IOStream {
	
	public static void main(String[] args) throws Exception {
		String file = IOStream.class.getResource("file.txt").getFile();
		System.out.println(new IOStream().fileInputSteam2InputStream(new FileInputStream(new File(file))).toString());
	}

	/**
	 * 将FileInputStream转为InputStream
	 * @param fis
	 * @return
	 */
	public InputStream fileInputSteam2InputStream(FileInputStream fis) {
		InputStream inputStream = null;
		
		byte[] byteArray = null;
		try {
			// 将fileInputStream转为byte数组
			byteArray = IOUtils.toByteArray(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
		inputStream = new ByteArrayInputStream(byteArray);
		
		return inputStream;
	}
}
