$(document).ready(function() {
	$("#load").click(function() {
		$.ajax('/git-common/userController/getData.do', {
			type : "post",
			async : true,
			data : {
				'userId' : 'panda',
				'password' : 'iampassword'
			},
			// dataType : "html",
			timeout : "1000",
			error : function(data) {
				alert(data.status);
				alert(data.statusText);
				alert("服务加载出错");
			},
			success : function(data, status, xhr) {
				$("#target").html(data);
				alert("服务加载成功");
			}
		});
	});
});
