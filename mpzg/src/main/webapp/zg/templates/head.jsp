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

<link rel="stylesheet" type="text/css"
	href="<%=basePath%>zg/css/bootstrap.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>zg/lib/font-awesome/css/font-awesome.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>zg/css/theme.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>zg/css/premium.css">

<script src="<%=basePath%>zg/js/jquery-1.11.1.min.js"
	type="text/javascript"></script>
<script src="<%=basePath%>zg/js/jquery.knob.js" type="text/javascript"></script>
<%-- <script src="<%=basePath %>zg/js/bootstrap.js"></script> --%>
<script src="<%=basePath%>zg/js/bootstrap.v3.5.min.js"></script>
<script src="<%=basePath%>zg/js/jquery-form.js"></script>
<script src="<%=basePath%>zg/js/mustache.min.js"></script>
<script src="<%=basePath%>zg/js/common.js"></script>
<script src="<%=basePath%>zg/js/login.js"></script>
<script src="<%=basePath%>zg/js/index.js"></script>
<script src="<%=basePath%>zg/js/user-info.js"></script>

<!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

<!-- Le fav and touch icons -->

<!--[if lt IE 7 ]> <body class="ie ie6"> <![endif]-->
<!--[if IE 7 ]> <body class="ie ie7 "> <![endif]-->
<!--[if IE 8 ]> <body class="ie ie8 "> <![endif]-->
<!--[if IE 9 ]> <body class="ie ie9 "> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!-->

<!--<![endif]-->
</head>
<body>
	<input type="hidden" id='base' value="<%=basePath%>" />
	<div class="navbar navbar-default" role="navigation">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target=".navbar-collapse">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="" href="index.jsp"><span class="navbar-brand"><span
					class="fa fa-video-camera"></span> 自贡公安监控系统管理平台</span></a>
		</div>

		<div class="navbar-collapse collapse" style="height: 1px;">
			<!-- <ul id="main-menu" class="nav navbar-nav">
            <li class="dropdown hidden-xs">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" id="user_name">
                   	 首页
                </a>
            </li>
          </ul>
          <ul id="main-menu" class="nav navbar-nav">
            <li class="dropdown hidden-xs">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" id="user_name">
                   	监控
                </a>
            </li>
          </ul>
          <ul id="main-menu" class="nav navbar-nav">
            <li class="dropdown hidden-xs">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" id="user_name">
                   	事故处理
                </a>
            </li>
          </ul>
          <ul id="main-menu" class="nav navbar-nav">
            <li class="dropdown hidden-xs">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" id="user_name">
                   	用户管理
                </a>
            </li>
          </ul> -->

			<ul id="main-menu" class="nav navbar-nav navbar-right">
				<li class="dropdown hidden-xs"><a href="#"
					class="dropdown-toggle" data-toggle="dropdown" id="user_name">
						<span class="fa fa-user"></span> ${UserInfo.userName } <i
						class="fa fa-caret-down"></i>
				</a>

					<ul class="dropdown-menu">
						<li><a tabindex="-1" href="#" id="logout">退出</a></li>
					</ul></li>
			</ul>

		</div>

	</div>


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
</body>
</html>