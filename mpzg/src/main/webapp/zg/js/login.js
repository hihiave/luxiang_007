
$(function() {
	//点击登录
	$("#userlog").click(function() {
		var username = $("#inputUsername").val();
		var userpwd = $("#inputPassword").val();
//		console.log("username=" + username);
//		console.log("userpwd=" + userpwd);
		
		if (username == "") {
			$("#message")[0].innerText = "用户名不能为空!";
			return;
		}
		if (userpwd == "") {
			$("#message")[0].innerText = "密码不能为空!";
			return;
		}
		$.ajax({
			type : "POST",
			url : getBasePath()+"login",
			data : {
				userName : username,
				userPwd : userpwd
			},
			dataType : "text",
			success : function(result) {
				if (result == "true") {
					 /*console.log("111true");*/
				}

				if (result == "none") {
					$("#message")[0].innerText = "用户名不存在!";
					return;
				} else if (result == "wrong") {
					$("#message")[0].innerText = "用户名或密码不正确";
					return;
				} else {
					/*console.log("222true");*/
					window.location.href =getBasePath()+"zg/view/index_monitoring.jsp";
				}
			},
			error : function(a, b, c) {
				$("#message")[0].innerText = "登录异常，请稍后重试!";
			}
		});
	});

	// 对输入框添加回车敲击事件
	$("#inputUsername").keydown(function(e) {
		if (e.keyCode == 13) {
			$("#userlog").click();
		}
	});
	$("#inputPassword").keydown(function(e) {
		if (e.keyCode == 13) {
			$("#userlog").click();
		}
	});

})