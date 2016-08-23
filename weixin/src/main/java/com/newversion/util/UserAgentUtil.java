package com.newversion.util;

import javax.servlet.http.HttpServletRequest;

public class UserAgentUtil {

	/**
	 * 判断是不是微信浏览器
	 * @param request
	 * @return
	 */
	public static boolean isMicroMessenger(HttpServletRequest request) {
		boolean result = false;
		String userAgent = request.getHeader("User-Agent");
		if (userAgent.contains("MicroMessenger")) {
			result = true;
		}
		return result;
	}
}
