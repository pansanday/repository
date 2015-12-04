<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="/git-common/userController/listUsers.do">查询</a>
	<a href="/git-common/userController/addUsers.do">新增</a>
	<a href="/git-common/userController/updateUser.do">更新</a>
	<form action="/git-common/userController/listUsers.do" method="post">
		<input type="submit" value="提交" />
	</form>
</body>
</html>