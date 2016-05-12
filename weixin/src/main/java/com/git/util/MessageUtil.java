package com.git.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import com.git.po.ImageMessage;
import com.git.po.TextMessage;
import com.thoughtworks.xstream.XStream;

public class MessageUtil {

    // 微信公众号能够接收的消息类型有如下类型
    public static final String MESSAGE_TEXT = "text"; // 文本
    public static final String MESSAGE_IMAGE = "image"; // 图片
    public static final String MESSAGE_VOICE = "voice"; // 语音
    public static final String MESSAGE_VIDEO = "video"; // 视频
    public static final String MESSAGE_LINK = "link"; // 链接消息
    public static final String MESSAGE_LOCATION = "location"; // 地理位置
    public static final String MESSAGE_EVENT = "event"; // 事件
    public static final String MESSAGE_SUBSCRIBE = "subscribe"; // 关注
    public static final String MESSAGE_UNSUBSCRIBE = "unsubscribe"; // 取消关注
    public static final String MESSAGE_CLICK = "CLICK"; // 菜单点击
    public static final String MESSAGE_VIEW = "VIEW"; // 菜单查看

    /**
     * xml转为map集合
     * 
     * @param request
     * @return
     * @throws DocumentException
     * @throws IOException
     */
    public static Map<String, String> xmlToMap(HttpServletRequest request) throws DocumentException, IOException {
        Map<String, String> map = new HashMap<String, String>();
        SAXReader reader = new SAXReader();

        InputStream ins = request.getInputStream();
        Document doc = reader.read(ins);

        Element root = doc.getRootElement();

        List<Element> list = root.elements();

        for (Element e : list) {
            map.put(e.getName(), e.getText());
        }

        ins.close();

        return map;
    }

    /**
     * 将文本消息对象转为xml
     * 
     * @param textMessage
     * @return
     */
    public static String textMessageToXml(TextMessage textMessage) {
        XStream xStream = new XStream();
        xStream.alias("xml", textMessage.getClass());
        return xStream.toXML(textMessage);
    }
    
    /**
     * 将图片消息对象转为xml
     * @param imageMessage
     * @return
     */
    public static String imageMessageToXml(ImageMessage imageMessage) {
        XStream xStream = new XStream();
        xStream.alias("xml", imageMessage.getClass());
        return xStream.toXML(imageMessage);
    }

    public static String initText(String toUserName, String fromUserName, String content) {
        TextMessage text = new TextMessage();
        text.setFromUserName(toUserName);
        text.setToUserName(fromUserName);
        text.setMsgType(MessageUtil.MESSAGE_TEXT);
        text.setCreateTime(new Date().getTime());
        text.setContent(content);
        return MessageUtil.textMessageToXml(text);
    }
    
    public static String initImage(String toUserName, String fromUserName, String picUrl, String mediaId, String msgId, String content) {
        ImageMessage imageMessage = new ImageMessage();
        imageMessage.setFromUserName(toUserName);
        imageMessage.setToUserName(fromUserName);
        imageMessage.setMsgType(MessageUtil.MESSAGE_IMAGE);
        imageMessage.setCreateTime(new Date().getTime());
//        imageMessage.setPicUrl(picUrl);
//        imageMessage.setMediaId(mediaId);
//        imageMessage.setMsgId(msgId);
        imageMessage.setContent(content);
        return MessageUtil.imageMessageToXml(imageMessage);
    }

    /**
     * 主菜单
     * 
     * @return
     */
    public static String menuText() {
        StringBuffer sBuffer = new StringBuffer();
        sBuffer.append("欢迎您的关注,请按照菜单提示进行操作：\n\n");
        sBuffer.append("1.课程介绍\n");
        sBuffer.append("2.网站介绍\n\n");
        sBuffer.append("回复?调出此菜单");
        return sBuffer.toString();
    }

    public static String firstMenu() {
        StringBuffer sBuffer = new StringBuffer();
        sBuffer.append("第一个菜单");
        return sBuffer.toString();
    }

    public static String secondMenu() {
        StringBuffer sBuffer = new StringBuffer();
        sBuffer.append("第二个菜单");
        return sBuffer.toString();
    }

}
