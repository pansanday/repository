package com.git.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.dom4j.DocumentException;
import com.git.util.CheckUtil;
import com.git.util.MessageUtil;

public class WeixinServlet extends HttpServlet {

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    /**
     * 参见接入指南：https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421135319&token=&lang=zh_CN
     */
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String signature = req.getParameter("signature"); // 微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
        String timestamp = req.getParameter("timestamp"); // 时间戳
        String nonce = req.getParameter("nonce"); // 随机数
        String echostr = req.getParameter("echostr"); // 随机字符串

        PrintWriter out = resp.getWriter();
        if (CheckUtil.checkSignature(signature, timestamp, nonce)) {
            out.print(echostr);
        }
    }

    /**
     * 参加消息接收和响应：https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140453&token=&lang=zh_CN
     */
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        try {
            Map<String, String> map = MessageUtil.xmlToMap(req);
            String fromUserName = map.get("FromUserName"); // 发送方微信账号
            String toUserName = map.get("ToUserName"); // 开发者微信公众号
            String createTime = map.get("CreateTime"); // 消息创建时间
            String msgType = map.get("MsgType"); // 消息类型
            String picUrl = map.get("PicUrl"); // 存储用户发来的图片链接地址,通过这个地址可以将图片另存为本地
            String mediaId = map.get("MediaId"); // 图片消息媒体ID
            String msgId = map.get("MsgId"); // 消息内容的随机ID
            String content = map.get("Content");

            String message = null;
            // 文本消息text
            if (MessageUtil.MESSAGE_TEXT.equals(msgType)) {
                if ("1".equals(content)) {
                    message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.firstMenu());
                } else if ("2".equals(content)) {
                    message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.secondMenu());
                } else if ("?".equals(content) || "？".equals(content)) {
                    message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.menuText());
                }
            } else if (MessageUtil.MESSAGE_EVENT.equals(msgType)) {
                String eventType = map.get("Event");
                if (MessageUtil.MESSAGE_SUBSCRIBE.equals(eventType)) {
                    message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.menuText());
                }
            } else if (MessageUtil.MESSAGE_LINK.equals(msgType)) {
            	message = MessageUtil.initText(toUserName, fromUserName, "你发送的是链接");
            } else if (MessageUtil.MESSAGE_IMAGE.equals(msgType)) {
                StringBuffer sBuffer = new StringBuffer();
                sBuffer.append("开发者id: " + toUserName + "\n");
                sBuffer.append("用户id: " + fromUserName + "\n");
                sBuffer.append("图片消息id: " + msgId + "\n");
                sBuffer.append("图片消息发送过来的时间戳: " + createTime + "\n");
                sBuffer.append("图片消息类型: " + msgType + "\n");
                sBuffer.append("图片消息链接地址: " + picUrl + "\n");
                sBuffer.append("图片消息媒体的id: " + mediaId);
                message = MessageUtil.initText(toUserName, fromUserName, sBuffer.toString());
//            	message = MessageUtil.initImage(toUserName, fromUserName, picUrl, mediaId, msgId, sBuffer.toString());
            	System.out.println(message);
            }
            out.print(message);
        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
    }

}
