<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page import="com.newversion.bean.SNSUserInfo" %>
<html>
<head>
	<title>OAuth 2.0网页授权</title>
	<meta name="viewport" content="width=device-width,user-scalable=0">
	<style type="text/css">
		* {}
		table{border:1px dashed #B9B9DD;font-size:12pt}
		td{border:1px dashed #B9B9DD; word-break:break-all; word-wrap:break-word;}
	</style>
</head>
<body>
<%
	SNSUserInfo user = (SNSUserInfo)request.getAttribute("snsUserInfo");
	if (null != user) {
%>
<table width="100%" cellspacing="0" cellpadding="0">
	<tr><td>属性</td><td>值</td></tr>
	<tr><td>OpenID</td><td><%= user.getOpenId() %></td></tr>
	<tr><td>昵称</td><td><%= user.getNickname() %></td></tr>
	<tr><td>性别</td><td><%= user.getSex() %></td></tr>
	<tr><td>省份</td><td><%= user.getProvince() %></td></tr>
	<tr><td>城市</td><td><%= user.getCity() %></td></tr>
	<tr><td>国家</td><td><%= user.getCountry() %></td></tr>
	<tr><td>头像</td><td><%= user.getHeadImgUrl() %></td></tr>
	<tr><td>特权</td><td><%= user.getPrivilegeList() %></td></tr>
</table>
<%
	} else {
		out.print("未获取到用户信息!");
	}
%>
</body>
</html>
