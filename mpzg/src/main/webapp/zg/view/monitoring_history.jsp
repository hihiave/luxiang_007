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
<title>监控历史</title>
    <link rel="stylesheet" type="text/css"
          href="<%=basePath%>zg/css/bootstrap.css">
    <link rel="stylesheet" href="<%=basePath%>zg/css/css.css" />
    <link rel="stylesheet" type="text/css"
          href="<%=basePath%>zg/css/font-awesome.min.css">
    <%-- <link rel="stylesheet" type="text/css"
        href="<%=basePath%>zg/css/theme.css"> --%>
    <link rel="stylesheet" type="text/css"
          href="<%=basePath%>zg/css/premium.css">
    <link rel="stylesheet" href="<%=basePath%>zg/css/metisfolder.css">
    <link rel="stylesheet" type="text/css"
          href="<%=basePath%>zg/css/default.css">



    <script type="text/javascript"
            src="<%=basePath%>zg/js/jquery-1.11.1.min.js"></script>
    <%-- <script type="text/javascript"
        src="<%=basePath%>zg/js/laydate/laydate.js"></script> --%>
    <%-- <script src="<%=basePath%>zg/js/jquery-1.11.1.min.js"type="text/javascript"></script>
    <script src="<%=basePath%>zg/js/jquery.knob.js" type="text/javascript"></script> --%>
    <%-- <script src="<%=basePath %>zg/js/bootstrap.js"></script> --%>
    <script src="<%=basePath%>zg/js/bootstrap.v3.5.min.js"></script>
    <script src="<%=basePath%>zg/js/jquery-form.js"></script>
    <script src="<%=basePath%>zg/js/mustache.min.js"></script>
    <script src="<%=basePath%>zg/js/common.js"></script>
    <script src="<%=basePath%>zg/js/login.js"></script>
    <script src="<%=basePath%>zg/js/index.js"></script>
    <script src="<%=basePath%>zg/js/user-info.js"></script>
    <script src="<%=basePath%>zg/js/monitoring-history.js"></script>




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

	<jsp:include page="../templates/header.jsp"></jsp:include>

	<div id="middle">
		<jsp:include page="../view/menu_monitoring.jsp"></jsp:include>
		<div class="right" id="mainFrame">
			<div class="right_cont">
				<div class="title_right">
					<strong>监控历史</strong>
				</div>
				<div class="main-content">

					<table class="table table-striped table-hover">
						<thead>
							<tr>
								<th>xx</th>
								<th>xx</th>
								<th>x</th>
								<th>x</th>
								<th>x</th>
								<th>xx</th>
								<th>xx</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>Tanmay</td>
								<td>Bangalore</td>
								<td>560001</td>
								<td>Sachin</td>
								<td>Mumbai</td>
								<td>400003</td>
								<td><a href="" data-toggle="modal"
									data-target="#monitoring_history_info"><i
										class="fa fa-pencil"></i></a> <a href="#myModal" role="button"
									data-toggle="modal"><i class="fa fa-trash-o"></i></a></td>
							</tr>
						</tbody>
					</table>
					<div class="row-fluid">
						<div id="user-info-page-info" class="span4"
							style="margin-top: 15px;">
							<p>显示第 1 到第 2 条记录，共 2 条记录</p>
						</div>
						<div class="span8">
							<div class="pagination  pagination-right">
								<ul id="user-info-pagination">

								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 模态框（Modal） -->


	<div class="modal fade modal-style" id="monitoring_history_info">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title">监控历史信息</h4>
				</div>
				<div class="modal-body">
					<video controls="controls" autoplay="autoplay"> 
					<source id="" src="http://www.100sucai.com/img/video/happyfit2.mp4">
					</video>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<!-- <button type="button" class="btn btn-primary">Save changes</button> -->
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->



</body>
</html>
