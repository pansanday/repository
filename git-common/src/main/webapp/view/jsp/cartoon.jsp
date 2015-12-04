<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="foo" style="position:absolute">This is a sample</div>
	<script>
		var elem = document.getElementById('foo');
		var frame = 0;
		setInterval(function(){
			frame += 1;
			if (frame < 100) {
				elem.style.left = frame * 10 + 'px';
			}
		}, 100)
	</script>
</body>
</html>