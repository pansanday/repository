<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 注意下面这行的写法,默认生成的jsp含有http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd的引用,加入后样式无法显示!原因未知,但这句话必须删! -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="">
<script type="text/javascript" src="../js/PopupWindow.js"></script>
<script type="text/javascript">
	function popup() {
		new PopupWindow({
			text : "鱼香肉丝<br>青椒土豆丝",
			level : "INFO",
			callback : function () {
				alert('haha');
			}
		});
	}
</script>
<title>我是标题</title>
</head>

<body onload="popup()">
	<div>This is demo for popupWindow</div>
	<button onclick="popup();">Press me</button>
</body>
</html>