<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>

<link rel="stylesheet" type="text/css"
	href="<%=basePath%>zg/css/bootstrap.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>zg/lib/font-awesome/css/font-awesome.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>zg/css/theme.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>zg/css/premium.css">
<script src="<%=basePath%>zg/js/jquery-1.11.1.min.js" type="text/javascript"></script>
<script src="<%=basePath %>zg/js/common.js"></script>
<script src="<%=basePath %>zg/js/login.js"></script>
<script src="<%=basePath %>zg/js/index.js"></script>
</head>
<body style="background-image: url(<%=basePath%>zg/images/bg.png)">
<input type="hidden" id='base' value="<%=basePath %>"/>
	<div class="navbar navbar-default login-pane" role="navigation">
		<div class="navbar-header">
			<a class="" href=""><span class="navbar-brand"><span
					class="fa fa-video-camera"></span> 自贡公安监控系统管理平台</span></a>
		</div>

		<div class="navbar-collapse collapse" style="height: 1px;"></div>

	</div>



	<div class="dialog">
		<div class="panel panel-default">
			<p class="panel-heading no-collapse">登录</p>
			<div class="panel-body">
				<form>
					<div class="form-group">
						<label>用户名</label> <input id="inputUsername" type="text"
							class="form-control span12">
					</div>
					<div class="form-group">
						<label>密码</label> <input id="inputPassword" type="password"
							class="form-control span12 ">
					</div>
					<a id="userlog" class="btn btn-primary pull-right">登录</a> <label
						class="remember-me"><input type="checkbox"> 记住我</label>
					<div class="clearfix"></div>
					<div style="color: #F00">
						<p id="message"></p>
					</div>
				</form>
			</div>
		</div>
		<!--  <p class="pull-right" style=""><a href="http://www.portnine.com" target="blank" style="font-size: .75em; margin-top: .25em;">Design by Portnine</a></p>
    <p><a href="reset-password.html">Forgot your password?</a></p> -->
	</div>

</body>
</html>