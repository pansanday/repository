package com.git.io;

import java.net.URL;

/**
 * 去你妹的FileNotFoundException
 */
public class FilePath {

	public static void main(String[] args) throws Exception {
		/**
		 * 1.可以获取src/main/java目录下的文件
		 * 2.可以获取src/main/resources目录下的文件
		 */
		URL url = ClassLoader.getSystemResource("data.txt");
		System.out.println("url => " + url);
		/**
		 * 获取某java包下的文件(方法一)
		 */
		URL url1 = ClassLoader.getSystemResource("com/git/io/file.txt");
		System.out.println("url1 => " + url1);
		/**
		 * 获取某java包下的文件(方法二)
		 */
		URL url2 = FilePath.class.getResource("file.txt");
		System.out.println("url2 => " + url2);
		/**
		 * 获取项目目录
		 */
		System.out.println("url3 => " + System.getProperty("user.dir"));
	}
}
