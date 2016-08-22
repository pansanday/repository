package com.newversion.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.newversion.bean.SNSUserInfo;
import com.newversion.bean.WeixinOauth2Token;
import com.newversion.util.AdvancedUtil;

/**
 * 授权后的回调请求处理
 */
public class OAuthServlet extends HttpServlet {

	private static final long serialVersionUID = -8696384102915707828L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		// 用户同意授权后,能获取到code
		String code = req.getParameter("code");
		System.out.println("code is:" + code);
		try {
			// 用户同意授权
			if (!"authdeny".equals(code)) {
				String appId = "";
				String appSecret = "";
				// 获取网页授权access_token
				WeixinOauth2Token weixinOauth2Token = AdvancedUtil.getOauth2AccessToken(appId, appSecret, code);
				if (weixinOauth2Token == null) {
					System.out.println("weixinOauth2Token为空");
				}
				// 网页授权接口访问凭证
				String accessToken = weixinOauth2Token.getAccessToken();
				// 用户标识
				String openId = weixinOauth2Token.getOpenId();
				// 获取用户信息
				SNSUserInfo snsUserInfo = AdvancedUtil.getSNSUserInfo(accessToken, openId);
				// 设置要传递的参数(将用户信息放到req对象中)
				req.setAttribute("snsUserInfo", snsUserInfo);
			}
			// 跳转到index.jsp
			req.getRequestDispatcher("index.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}