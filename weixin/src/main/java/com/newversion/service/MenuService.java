package com.newversion.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import com.newversion.message.resp.Article;
import com.newversion.message.resp.NewsMessage;
import com.newversion.message.resp.TextMessage;
import com.newversion.util.MessageUtil;

public class MenuService {
	/**
	 * 处理微信发来的请求
	 * 
	 * @param request
	 * @return xml
	 */
	public static String processRequest(HttpServletRequest request) {
		// xml格式的消息数据
		String respXml = null;
		try {
			// 调用parseXml方法解析请求消息
			Map<String, String> requestMap = MessageUtil.parseXml(request);
			// 发送方帐号
			String fromUserName = requestMap.get("FromUserName");
			// 开发者微信号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");

			// 回复的消息
			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			
			System.out.println("msgType is : " + msgType);
			// 事件推送
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// 事件类型
				String eventType = requestMap.get("Event");
				System.out.println("eventType值为:" + eventType);
				// 订阅
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
//					textMessage.setContent("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx1bb0d511204184a0&redirect_uri=http%3A%2F%2Fvip.ngrok.cc%2Fweixin%2FoauthServlet&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect");
					// 您好，欢迎关注网址导航！
					textMessage.setContent("想你了，怎么现在才来呢?\n我们致力于打造精品网址聚合应用，为用户提供便捷的上网导航服务。体验生活，从这里开始！");
					// 将消息对象转换成xml
					respXml = MessageUtil.messageToXml(textMessage);
				}
				// 取消订阅
				else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					// TODO 暂不做处理
				}
				// 自定义菜单点击事件
				else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					// 事件KEY值，与创建菜单时的key值对应
					String eventKey = requestMap.get("EventKey");
					System.out.println("eventKey值为:" + eventKey);
					// 根据key值判断用户点击的按钮
					if (eventKey.equals("oschina")) {
						Article article = new Article();
						article.setTitle("开源中国");
						article.setDescription(
								"开源中国社区成立于2008年8月，是目前中国最大的开源技术社区。\n\n开源中国的目的是为中国的IT技术人员提供一个全面的、快捷更新的用来检索开源软件以及交流开源经验的平台。\n\n经过不断的改进,目前开源中国社区已经形成了由开源软件库、代码分享、资讯、讨论区和博客等几大频道内容。");
						article.setPicUrl("");
						article.setUrl("http://m.oschina.net");
						List<Article> articleList = new ArrayList<Article>();
						articleList.add(article);
						// 创建图文消息
						NewsMessage newsMessage = new NewsMessage();
						newsMessage.setToUserName(fromUserName);
						newsMessage.setFromUserName(toUserName);
						newsMessage.setCreateTime(new Date().getTime());
						newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
						newsMessage.setArticleCount(articleList.size());
						newsMessage.setArticles(articleList);
						System.out.println(newsMessage);
						respXml = MessageUtil.messageToXml(newsMessage);
						System.out.println("78=>" + respXml);
					} else if (eventKey.equals("iteye")) {
						textMessage.setContent(
								"ITeye即创办于2003年9月的JavaEye,从最初的以讨论Java技术为主的技术论坛，已经逐渐发展成为涵盖整个软件开发领域的综合性网站。\n\nhttp://www.iteye.com");
						respXml = MessageUtil.messageToXml(textMessage);
					} else if (eventKey.equals("pansanday")) {
						Article article = new Article();
						article.setTitle("热烈欢迎聪明睿智的Panda同学");
						article.setDescription("Panda同学神一样的存在! \n\n Long long ago...");
						article.setPicUrl("");
						article.setUrl("http://www.pansanday.com");
						List<Article> articleList = new ArrayList<Article>();
						articleList.add(article);
						
						NewsMessage newsMessage = new NewsMessage();
						newsMessage.setArticleCount(articleList.size());
						newsMessage.setArticles(articleList);
						newsMessage.setFromUserName(toUserName);
						newsMessage.setToUserName(fromUserName);
						newsMessage.setCreateTime(new Date().getTime());
						newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
						respXml = MessageUtil.messageToXml(newsMessage);
						System.out.println("100=>" + respXml);
					}
				}
			} else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				// 文本消息内容
				String content = requestMap.get("Content");
				// 呲牙表情的文本代码/::D
				if (content.equals("/::D")) {
					textMessage.setContent("什么事情这么高兴啊?[疑问] /疑问 /:?");
				} else {
					// #wechat_webview_type=1
					textMessage.setContent("买书上<a href=\"http://m.dangdang.com\">当当网</a>!\n The message you sent is: " + requestMap.get("Content"));
				}
				// 将文本消息对象转换成XML字符串
				respXml = MessageUtil.messageToXml(textMessage);
				System.out.println("respXml=>\n" + respXml);
			} else {
				textMessage.setContent("Please use the menu provided！");
				respXml = MessageUtil.messageToXml(textMessage);
				System.out.println("respXml=>\n" + respXml);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return respXml;
	}
}
