package com.newversion.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 过滤器(限制只有微信浏览器能访问)
 */
public class BrowserFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	/**
	 * 过滤器的核心是doFilter()方法,对于客户端请求进行过滤.首先获取到客户端浏览器的User Agent,然后判断是不是微信浏览器,如果是就继续往下走(允许访问被保护的资源),如果不是将会在页面显示提示"请使用微信浏览器访问!"
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		String userAgent = req.getHeader("User-Agent");
		// 微信内置浏览器
		if (userAgent.contains("MicroMessager")) {
			chain.doFilter(request, response);
		} else { 
			// 其他浏览器
			HttpServletResponse resp = (HttpServletResponse) response;
			resp.setCharacterEncoding("UTF-8");
			// 输出提示
			PrintWriter out = resp.getWriter();
			out.write("请使用微信浏览器访问!");
			out.close();
		}
	}

	@Override
	public void destroy() {
	}
}
