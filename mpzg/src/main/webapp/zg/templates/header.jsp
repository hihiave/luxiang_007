<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>


</head>
<body>
	<input type="hidden" id='base' value="<%=basePath%>" />
	
	
	<div class="header">

		<div class="logo">
			<img src="<%=basePath%>zg/images/logo.png" />
		</div>

		<div class="header-left" id="menu">
			<ul class="nav nav-pills">
				<li onclick="checkMonitoring()" ><a href="#">监控功能</a></li>
				<li onclick="checkUserInfo();" ><a href="#">用户管理</a></li>
				<li onclick="checkUserManage();" ><a href="#">后台管理</a></li>
			</ul>
		</div>

		<div class="header-right">
			<a href="#"> <i class="fa fa-user"></i> ${UserInfo.userName }
			</a> <a id="logout"><i class="fa fa-power-off"></i> 注销</a>
		</div>
	</div>
	<!-- 顶部 -->

	<!-- 弹出的提示框 -->
	<div id="alert-box" class="modal fade" tabindex="-1" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-body">
					<div class="container-fluid" style="text-align: center">
						<img src="<%=basePath%>zg/images/Warning_128px.png" />
						<h2>温馨提示</h2>
						<p id="alert-content" style="display: block;"></p>
					</div>
				</div>
				<div class="modal-footer" style="text-align: center">
					<button type="button" class="btn btn-primary" data-dismiss="modal"
						id="alert-confirm-btn">确定</button>
				</div>
			</div>
		</div>
	</div>

	<!-- 弹出成功提示框 -->
	<div id="success-box" class="modal fade" tabindex="-1" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-body">
					<div class="container-fluid" style="text-align: center">
						<img src="<%=basePath%>zg/images/Success_128px.png" />

						<h4 id="success-content" style="display: block;"></h4>
					</div>
				</div>
				<div class="modal-footer" style="text-align: center">
					<button type="button" class="btn btn-primary" data-dismiss="modal">确定</button>
				</div>
			</div>
		</div>
	</div>

	<script src="<%=basePath%>zg/js/metismenu.js"></script>

	<script>
		$(function() {
			$('.metisFolder').metisMenu({
				toggle : false
			});
		});
	</script>
	<script>
	$("#div1").addClass("active");
	</script>
</body>
</html>