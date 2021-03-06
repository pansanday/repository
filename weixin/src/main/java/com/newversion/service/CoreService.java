package com.newversion.service;

import java.util.Date;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import com.newversion.message.resp.TextMessage;
import com.newversion.util.MessageUtil;

/**
 * 核心服务类
 */
public class CoreService {
	
	/**
	 * 得到包含指定Unicode代码点的字符串
	 * @param codePoint
	 * @return
	 */
	private static String emoji(int codePoint) {
		return String.valueOf(Character.toChars(codePoint));
	}
	
	/**
	 * 处理微信发来的请求
	 * @param request
	 * @return xml
	 */
	public static String processRequest(HttpServletRequest request) {
		// xml格式的消息数据
		String respXml = null;
		// 默认返回的文本消息内容
		String respContent = "未知的消息类型！";
		try {
			// 调用parseXml方法解析请求消息
			Map<String, String> requestMap = MessageUtil.parseXml(request);
			// 发送方帐号(open_id)
			String fromUserName = requestMap.get("FromUserName");
			// 开发者微信号(公众账号)
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");
			// 消息创建时间
			String createTime = requestMap.get("CreateTime");
			// 语音识别内容
			String recognition = requestMap.get("Recognition");
			
			System.out.println("msgType为:" + msgType);
			
			// 定义消息内容(鬼节做南瓜灯)
			StringBuffer sBuffer = new StringBuffer();
			sBuffer.append("1.Unified Unicode").append("\n");
			sBuffer.append(emoji(0x1f47B)).append("节做").append(emoji(0x1f383)).append("灯\n\n");
			sBuffer.append("2.Softbank Unicode").append("\n");
			sBuffer.append(emoji(0xE11B)).append("节做").append(emoji(0xE445)).append("灯");

			// 回复文本消息
			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);

			// 文本消息
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				// 自定义表情的demo
				// respContent = "您发送的是文本消息！\n" + sBuffer.toString();
				respContent = ChatService.chat(fromUserName, createTime, requestMap.get("Content"));
			}
			// 图片消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
				respContent = "您发送的是图片消息！";
			}
			// 语音消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
				// respContent = "您发送的是语音消息！";
				String preStr = "语音识别结果为:" + recognition + "\n";
				respContent = preStr + ChatService.chat(fromUserName, createTime, recognition);
			}
			// 视频消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VIDEO)) {
				respContent = "您发送的是视频消息！";
			}
			// 地理位置消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
				respContent = "您发送的是地理位置消息！";
			}
			// 链接消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
				respContent = "您发送的是链接消息！";
			}
			// 事件推送
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// 事件类型
				String eventType = requestMap.get("Event");
				// 关注
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					StringBuffer buffer = new StringBuffer();
					buffer.append("您好,谢谢您的关注!请回复数字选择服务:").append("\n\n");
					buffer.append("1 天气预报").append("\n");
					buffer.append("2 公交查询").append("\n");
					buffer.append("3 周边搜索").append("\n");
					buffer.append("4 歌曲点播").append("\n");
					buffer.append("5 人脸识别").append("\n");
					buffer.append("6 聊天唠嗑").append("\n\n");
					buffer.append("回复“?”显示此帮助菜单");
					respContent = buffer.toString();
				}
				// 取消关注
				else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					// TODO 取消订阅后用户不会再收到公众账号发送的消息，因此不需要回复
				}
				// 扫描带参数二维码
				else if (eventType.equals(MessageUtil.EVENT_TYPE_SCAN)) {
					// TODO 处理扫描带参数二维码事件
				}
				// 上报地理位置
				else if (eventType.equals(MessageUtil.EVENT_TYPE_LOCATION)) {
					// TODO 处理上报地理位置事件
				}
				// 自定义菜单
				else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					// TODO 处理菜单点击事件
				}
			}
			// 设置文本消息的内容
			textMessage.setContent(respContent);
			// 将文本消息对象转换成xml
			respXml = MessageUtil.messageToXml(textMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return respXml;
	}
}
