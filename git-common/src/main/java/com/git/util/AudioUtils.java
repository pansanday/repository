package com.git.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @see http://www.ffmpeg.org/
 */
public class AudioUtils {

	/**
	 * ffmpeg文件所在的路径
	 */
	private final static String FFMPEG_PATH;
	private static String osname = System.getProperty("os.name");
	static {
		if (osname.contains("Mac")) {
			FFMPEG_PATH = AudioUtils.class.getResource("ffmpeg-mac").getFile();
		} else if (osname.contains("Windows")) {
			FFMPEG_PATH = AudioUtils.class.getResource("ffmpeg.exe").getFile();
		} else {
			FFMPEG_PATH = AudioUtils.class.getResource("ffmpeg-linux").getFile();
		}
	}
	
	/**
	 * 将一个amr文件转换成wav文件
	 * 
	 * @param sourceFilePath
	 * @param targetFilePath
	 */
	public static void amr2wav(String sourceFilePath, String targetFilePath) {
		try {
			amr2wav(sourceFilePath, targetFilePath, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 将一个amr文件转换成wav文件
	 * 
	 * @param sourceFilePath 
	 * @param targetFilePath
	 * @param targetSamplingRate 目标文件采样率,单位Hz
	 * @param targetChannels 目标文件声道数,1表示双声道,0表示单声道
	 * @param targetBitRate 目标文件比特率
	 */
	public static void amr2wav(String sourceFilePath, String targetFilePath, String targetSamplingRate,
			String targetChannels, String targetBitRate) throws Exception {
		targetSamplingRate = isBlank(targetBitRate) ? "16000" : targetSamplingRate;
		targetChannels = isBlank(targetSamplingRate) ? "0" : targetSamplingRate;
		targetBitRate = isBlank(targetSamplingRate) ? "256k" : targetSamplingRate;
		
		Runtime runtime = Runtime.getRuntime();
		/**
		 * -i ：指定输入文件 -ar ： 指定sampling rate(采样率)，它的单位是HZ(SamplingRate);采样数(Hz)
		 * -ac：指定声道，1表示双声道，0表示单声道(Channels);声道数
		 * -ab：指定转换后的比特率(BitRate);音频编码(kb/s或kbps)
		 */
		Process process = runtime.exec(FFMPEG_PATH + " -i " + sourceFilePath + " -ar " + targetSamplingRate + " -ac "
				+ targetChannels + " -y -ab " + targetBitRate + " " + targetFilePath);
		InputStream in = process.getErrorStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		try {
			String line = null;
			while ((line = br.readLine()) != null) {
				// System.out.println(line);
				/*int p = process.waitFor();
				System.out.println(p);*/
			}
			/*if (process.exitValue() != 0) {
				// 如果转换失败，这里需要删除这个文件（因为有时转换失败后的文件大小为0）
				new File(targetFilePath).delete();
				throw new RuntimeException("转换失败！");
			}*/
		} finally {
			// 为了避免这里抛出的异常会覆盖上面抛出的异常，这里需要用捕获异常。
			try {
				in.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 判断文件是否为空
	 * @param str
	 * @return
	 */
	public static boolean isBlank(String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if ((Character.isWhitespace(str.charAt(i)) == false)) {
				return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		String sourceFilePath = "/Users/pansanday/Documents/workspace-sts/yuyin/src/main/resources/54903372-fa9d-41e7-b98f-bd19dc3ec9d9.amr";
		String targetFilePath = "/Users/pansanday/Documents/workspace-sts/yuyin/src/main/java/jj.wav";
		try {
			amr2wav(sourceFilePath, targetFilePath, null, null, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
