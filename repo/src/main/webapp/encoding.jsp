<%@ page import="java.net.URLEncoder" %><%--
  Created by IntelliJ IDEA.
  User: newbie
  Date: 2017/8/24
  Time: 12:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>编码</title>
</head>
<body>
<a href="www.baidu.com">无http，大道至简</a>
<a href="http://www.baidu.com">baidu</a>

<a href=<%="https://www.baidu.com/s?wd=" + URLEncoder.encode("中文内容","GBK")%>>测试连接</a>
</body>
</html>
