package com.newversion.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.newversion.service.ChatService;
import com.newversion.service.CoreService;
import com.newversion.util.SignUtil;

public class CoreServlet extends HttpServlet {

	private static final long serialVersionUID = 3006530726220259533L;

	/**
	 * 请求校验（确认请求来自微信服务器）
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 微信加密签名
		String signature = request.getParameter("signature");
		// 时间戳
		String timestamp = request.getParameter("timestamp");
		// 随机数
		String nonce = request.getParameter("nonce");
		// 随机字符串
		String echostr = request.getParameter("echostr");

		PrintWriter out = response.getWriter();
		// 请求校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
		if (SignUtil.checkSignature(signature, timestamp, nonce)) {
			out.print(echostr);
		}
		out.close();
		out = null;
	}

	/**
	 * 处理微信服务器发来的消息
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 将请求、响应的编码均设置为UTF-8（防止中文乱码）
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		// 调用核心业务类接收消息、处理消息
		String respXml = CoreService.processRequest(request);

		// 响应消息
		PrintWriter out = response.getWriter();
		out.print(respXml);
		out.close();
	}


	/**
	 * 为防止重复创建索引,先判断索引文件目录是否存在,只有不存在时才创建索引
	 * TODO: 当问答知识表的数据发生变化(新增,修改或删除)时,需要同步更新索引
	 */
	public void init() throws ServletException {
		File indexDir = new File(ChatService.getIndexDir());
		if (!indexDir.exists()) {
			ChatService.createIndex();
		}
	}
}
