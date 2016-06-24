package com.newversion.util;

import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.newversion.message.resp.Article;
import com.newversion.message.resp.ImageMessage;
import com.newversion.message.resp.MusicMessage;
import com.newversion.message.resp.NewsMessage;
import com.newversion.message.resp.TextMessage;
import com.newversion.message.resp.VideoMessage;
import com.newversion.message.resp.VoiceMessage;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

public class MessageUtil {

	// 请求消息类型:文本
	public static final String REQ_MESSAGE_TYPE_TEXT = "text";
	// 请求消息类型:图片
	public static final String REQ_MESSAGE_TYPE_IMAGE = "image";
	// 请求消息类型:语音
	public static final String REQ_MESSAGE_TYPE_VOICE = "voice";
	// 请求消息类型:视频
	public static final String REQ_MESSAGE_TYPE_VIDEO = "video";
	// 请求消息类型:地理位置
	public static final String REQ_MESSAGE_TYPE_LOCATION = "location";
	// 请求消息类型:链接
	public static final String REQ_MESSAGE_TYPE_LINK = "link";
	// 请求消息类型:事件推送
	public static final String REQ_MESSAGE_TYPE_EVENT = "event";

	// 事件类型:subscribe(订阅)
	public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";
	// 事件类型:unsubscribe(取消订阅)
	public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";
	// 事件类型:scan(关注用户扫描带参数二维码)
	public static final String EVENT_TYPE_SCAN = "scan";
	// 事件类型:LOCATION(上报地理位置)
	public static final String EVENT_TYPE_LOCATION = "LOCATION";
	// 事件类型:CLICK(自定义菜单)
	public static final String EVENT_TYPE_CLICK = "CLICK";

	// 响应消息类型:文本
	public static final String RESP_MESSAGE_TYPE_TEXT = "text";
	// 响应消息类型:图片
	public static final String RESP_MESSAGE_TYPE_IMAGE = "image";
	// 响应消息类型:语音
	public static final String RESP_MESSAGE_TYPE_VOICE = "voice";
	// 响应消息类型:视频
	public static final String RESP_MESSAGE_TYPE_VIDEO = "video";
	// 响应消息类型:音乐
	public static final String RESP_MESSAGE_TYPE_MUSIC = "music";
	// 响应消息类型:图文
	public static final String RESP_MESSAGE_TYPE_NEWS = "news";

	/**
	 * 解析微信发来的请求(XML)
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> parseXml(HttpServletRequest request) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		InputStream inputStream = request.getInputStream();
		SAXReader reader = new SAXReader();
		Document document = reader.read(inputStream);
		Element root = document.getRootElement();
		List<Element> elementList = root.elements();

		for (Element e : elementList) {
			map.put(e.getName(), e.getText());
		}

		inputStream.close();
		inputStream = null;
		return map;
	}

	/**
	 * 扩展stream使其支持CDATA
	 */
	private static XStream xStream = new XStream(new XppDriver() {
		public HierarchicalStreamWriter createWriter(Writer out) {
			return new PrettyPrintWriter(out) {
				// 对所有XML节点的转换都增加CDATA标记
				boolean cdata = true;

				@SuppressWarnings("rawtypes")
				public void startNode(String name, Class clazz) {
					super.startNode(name, clazz);
				}

				protected void writeText(QuickWriter writer, String text) {
					if (cdata) {
						writer.write("<![CDATA[");
						writer.write(text);
						writer.write("]]>");
					} else {
						writer.write(text);
					}
				}
			};
		}
	});

	/**
	 * 文本消息对象转换成XML
	 * 
	 * @param textMessage
	 * @return
	 */
	public static String messageToXml(TextMessage textMessage) {
		xStream.alias("xml", textMessage.getClass());
		return xStream.toXML(textMessage);
	}

	/**
	 * 图片消息对象转换成XML
	 * 
	 * @param imageMessage
	 * @return
	 */
	public static String messageToXml(ImageMessage imageMessage) {
		xStream.alias("xml", imageMessage.getClass());
		return xStream.toXML(imageMessage);
	}

	/**
	 * 语音消息对象转换成XML
	 * 
	 * @param voiceMessage
	 * @return
	 */
	public static String messageToXml(VoiceMessage voiceMessage) {
		xStream.alias("xml", voiceMessage.getClass());
		return xStream.toXML(voiceMessage);
	}

	/**
	 * 视频消息对象转换成XML
	 * 
	 * @param videoMessage
	 * @return
	 */
	public static String messageToXml(VideoMessage videoMessage) {
		xStream.alias("xml", videoMessage.getClass());
		return xStream.toXML(videoMessage);
	}

	/**
	 * 音乐消息对象转换成XML
	 * 
	 * @param musicMessage
	 * @return
	 */
	public static String messageToXml(MusicMessage musicMessage) {
		xStream.alias("xml", musicMessage.getClass());
		return xStream.toXML(musicMessage);
	}

	/**
	 * 图文消息转换成XML
	 * 
	 * @param newsMessage
	 * @return
	 */
	public static String messageToXml(NewsMessage newsMessage) {
		xStream.alias("xml", newsMessage.getClass());
		xStream.alias("item", new Article().getClass());
		return xStream.toXML(newsMessage);
	}

}
