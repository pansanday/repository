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
import com.git.po.TextMessage;
import com.thoughtworks.xstream.XStream;

public class MessageUtil {

    public static final String MESSAGE_TEXT = "text";
    public static final String MESSAGE_IMAGE = "image";
    public static final String MESSAGE_VOICE = "voice";
    public static final String MESSAGE_VIDEO = "video";
    public static final String MESSAGE_LINK = "link";
    public static final String MESSAGE_LOCATION = "location";
    public static final String MESSAGE_EVENT = "event";
    public static final String MESSAGE_SUBSCRIBE = "subscribe";
    public static final String MESSAGE_UNSUBSCRIBE = "unsubscribe";
    public static final String MESSAGE_CLICK = "CLICK";
    public static final String MESSAGE_VIEW = "VIEW";

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

    public static String initText(String toUserName, String fromUserName, String content) {
        TextMessage text = new TextMessage();
        text.setFromUserName(toUserName);
        text.setToUserName(fromUserName);
        text.setMsgType(MessageUtil.MESSAGE_TEXT);
        text.setCreateTime(new Date().getTime());
        text.setContent(content);
        return MessageUtil.textMessageToXml(text);
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
