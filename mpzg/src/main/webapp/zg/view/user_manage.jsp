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
<title>用户管理</title>
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
    <script src="<%=basePath%>zg/js/user-manage.js"></script>




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
	<jsp:include page="../view/menu_backend_manage.jsp"></jsp:include>

	<div class="right" id="mainFrame">
        <script>
            $(function(){
                checkUserManageInfo();
            })
            function getKakouManage(){
                window.location.href = getBasePath()+"zg/view/kakou_manage.jsp"
            }
        </script>
		<div class="right_cont">
			<div class="title_right">
				<strong>用户管理</strong>
                <span style="cursor:pointer;" onclick="getKakouManage()">卡口管理</span>
			</div>
			<div class="main-content">

				<table class="table table-striped table-hover">
					<thead>
						<tr>
							<th>用户名</th>
							<th>真实姓名</th>
							<th>电子邮件</th>
							<th>性别</th>
							<th>电话号码</th>
							<th>用户权限</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody id="user-manage-info">
						<tr >
							<!-- <td>Tanmay</td>
							<td>Bangalore</td>
							<td>560001</td>
							<td>Sachin</td>
							<td>Mumbai</td>
							<td>400003</td>
							<td><a href="" data-toggle="modal"
								data-target="#userinfo_modify"><i class="fa fa-pencil"></i></a>
								<a href="#myModal" role="button" data-toggle="modal"><i
									class="fa fa-trash-o"></i></a></td> -->
						</tr>
					</tbody>
				</table>
				<div class="row-fluid">
					<div id="user-manage-info-page-info" class="col-sm-4" style="margin-top: 15px;">
						<p>显示第 1 到第 2 条记录，共 2 条记录</p>
					</div>
					<div class="col-sm-8">
						<div style="float: right;" >
							<ul class="pagination" id="user-manage-info-pagination">

							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
	<!-- 模态框（Modal） -->
	<div class="modal fade modal-style" id="userinfo_modify" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">修改用户信息</h4>
				</div>
				<div class="modal-body">

					<form class="form-horizontal" role="form">
						<div class="form-group form-group-sm form-group-style">
							<label class="col-sm-2 control-label">用户名</label>
							<div class="col-sm-10">
								<input class="form-control" type="text" id=""
									placeholder="Small input">
							</div>
						</div>
						<div class="form-group form-group-sm form-group-style">
							<label class="col-sm-2 control-label">真实姓名</label>
							<div class="col-sm-10">
								<input class="form-control" type="text" id=""
									placeholder="Small input">
							</div>
						</div>
						<div class="form-group form-group-sm form-group-style">
							<label class="col-sm-2 control-label">电子邮件</label>
							<div class="col-sm-10">
								<input class="form-control" type="text" id=""
									placeholder="Small input">
							</div>
						</div>
						<div class="form-group form-group-sm form-group-style">
							<label class="col-sm-2 control-label">性别</label>
							<div class="col-sm-10">
								<select class="form-control">
									<option>男</option>
									<option>女</option>
								</select>
							</div>
						</div>
						<div class="form-group form-group-sm form-group-style">
							<label class="col-sm-2 control-label">电话号码</label>
							<div class="col-sm-10">
								<input class="form-control" type="text" id=""
									placeholder="Small input">
							</div>
						</div>
						<div class="form-group form-group-sm form-group-style">
							<label class="col-sm-2 control-label">用户权限</label>
							<div class="col-sm-10">
								<select class="form-control">
									<option>普通管理员</option>
									<option>系统管理员</option>
								</select>
							</div>
						</div>
					</form>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" data-dismiss="modal">关闭
					</button>
					<button type="button" class="btn btn-primary">修改</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>

	<%--出库单查看模板--%>
	<script type="text/template" id="user-manage-info-template">

    <tr>
        <td>
           {{userName}}
        </td>
        <td>
            {{userRealname}}
        </td>
        <td>
            {{userEmail}}
        </td>
        <td>
            {{sex}}
        </td>
        <td>
            {{userTelephoneNumber}}
        </td>
        <td>
            {{userRole}}
        </td>
        <td>
            <a href="" data-toggle="modal" data-target="#userinfo_modify"><i class="fa fa-pencil"></i></a>
			<a href="#myModal" role="button" data-toggle="modal"><i class="fa fa-trash-o"></i></a>
        </td>    
    </tr>

</script>
</body>
</html>
