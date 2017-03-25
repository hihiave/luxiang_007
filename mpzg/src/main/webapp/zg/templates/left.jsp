<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<%-- <link rel="stylesheet" href="<%=basePath %>zg/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=basePath %>zg/css/font-awesome.min.css">
    <link rel="stylesheet" href="<%=basePath %>zg/css/metisfolder.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath %>zg/css/default.css"> --%>
<!--[if IE]>
		<script src="http://libs.useso.com/js/html5shiv/3.7/html5shiv.min.js"></script>
		<script src="js/respond.min.js"></script>
	<![endif]-->
</head>
<body>
	<div class="sidebar-nav" >
		<ul class="metisFolder">
			<li><a href="#" data-target=".dashboard-menu" class="nav-header"
				data-toggle="collapse"><i class="fa fa-fw fa-signal"></i> 流量监控<i class="fa fa-collapse"></i></a></li>
			<li><ul class="dashboard-menu nav nav-list collapse in">
					<li><a href="monitor.html"><span class="fa fa-caret-right"></span>
							实时监控</a></li>
					<li><a href="car-flow.html"><span
							class="fa fa-caret-right"></span> 车流量查看</a></li>
					<li><a href="hositry.html"><span class="fa fa-caret-right"></span>
							历史记录</a>
				</ul></li>



			<li><a href="#" data-target=".accounts-menu" class="nav-header "
				data-toggle="collapse"><i class="fa fa-fw fa-briefcase"></i> 事故处理 <i class="fa fa-collapse"></i></a></li>
			<li><ul class="accounts-menu nav nav-list collapse in">
					<li><a href="car-track.html"><span
							class="fa fa-caret-right"></span> 车辆追踪</a></li>
					<li><a href="accident-predict.html"><span
							class="fa fa-caret-right"></span> 事故预警</a></li>
					<li><a href="risk-warning.html"><span
							class="fa fa-caret-right"></span> 危险评估</a></li>
				</ul></li>

			<li><a href="#" data-target=".legal-menu" class="nav-header "
				data-toggle="collapse"><i class="fa fa-fw fa-users"></i> 用户信息<i class="fa fa-collapse"></i></a></li>
			<li>
				<ul class="legal-menu nav nav-list collapse in">
					<li onclick="checkUserInfo();"><a href="#"><span
							class="fa fa-caret-right"></span> 个人信息</a></li>
				</ul>
			</li>
			
			<li><a href="#" data-target=".backend-manage" class="nav-header "
				data-toggle="collapse"><i class="fa fa-fw fa-cog"></i> 后台管理<i
					class="fa fa-collapse"></i></a></li>
			<li>
				<ul class="backend-manage nav nav-list collapse in">
					<li onclick=""><a href="#"><span
							class="fa fa-caret-right"></span> 监控管理</a></li>
					<li><a href="<%=basePath%>zg/view/user_manage.jsp"><span
							class="fa fa-caret-right"></span> 用户管理</a></li>
				</ul>
			</li>
		</ul>
	</div>


	<%-- 	<script src="<%=basePath%>zg/js/jquery.min.js"></script>
	<script src="<%=basePath%>zg/js/bootstrap.min.js"></script>
	<script src="<%=basePath%>zg/js/metismenu.js"></script>
	<script>
		$(function() {
			$('.metisFolder').metisMenu({
				toggle : false
			});
		});
	</script> --%>


</body>
</html>