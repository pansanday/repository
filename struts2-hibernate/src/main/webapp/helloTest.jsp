<%@page import="java.io.PrintWriter"%>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	System.out.println(path);
	String basePath = request.getScheme() + "://" + request.getServerName() + "://" + request.getServerPort() + path + "/";
	System.out.println(basePath);
	String message = "Hello World!";
	if(request.getAttribute("message") != null){
		message = (String)request.getAttribute("message");
		System.out.println("====>" + message);
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Insert title here</title>
</head>
<body>
	<center>
		<form name="aa" action="servlet/HelloServlet" method="post">
			<br><br>
			<table border="0">
				<tr align="center">
					<td><font color="red" style="font-size:16pt">
					<b><%=message %></b></font></td>
				</tr>
				<tr align="center">
					<td>请输入姓名:<input type="text" name="userName" size="30">
					<input type="submit" value="确定"/></td>
				</tr>
			</table>
		</form>
	</center>
</body>
</html>