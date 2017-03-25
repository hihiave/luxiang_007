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
<title>个人信息</title>


</head>
<body>

	<jsp:include page="../templates/head.jsp"></jsp:include>

	<jsp:include page="../templates/left.jsp"></jsp:include>

	<input id="user_id" type="hidden" value='${UserInfo.userId }'>
	<div class="content">
		<div class="header">
			<h1 class="page-title">个人信息</h1>
		</div>
		<div class="main-content">
			<ul id="userinfo-tab" class="nav nav-tabs">
				<li class="active"><a href="#user-info" data-toggle="tab">详细信息
				</a></li>
				<li><a href="#psw-modify" data-toggle="tab">修改密码</a></li>
			</ul>
			<div id="userinfo-tab-content" class="tab-content">
				<div class="tab-pane fade in active" id="user-info">
					<form class="form-horizontal" role="form">
						<div class="form-group form-group-sm form-group-style">
							<label class="col-sm-4 control-label">用户名</label>
							<div class="col-sm-5">
								<input id="my_user_name" class="form-control" type="text"
									value='${UserInfo.userName }'  placeholder="用户名">
							</div>
						</div>
						<div class="form-group form-group-sm form-group-style">
							<label class="col-sm-4 control-label">真实姓名</label>
							<div class="col-sm-5">
								<input id="user_realname" class="form-control" type="text"
									value='${UserInfo.userRealname }' 
									placeholder="真实姓名">
							</div>
						</div>
						<div class="form-group form-group-sm form-group-style">
							<label class="col-sm-4 control-label">电子邮件</label>
							<div class="col-sm-5">
								<input id="user_email" class="form-control" type="text"
									value='${UserInfo.userEmail }'  placeholder="Small input">
							</div>
						</div>
						<div class="form-group form-group-sm form-group-style">
							<label class="col-sm-4 control-label">性别</label>
							<div class="col-sm-5">
								<select id="user_sex" class="form-control">
									<option value="1"
										<c:if test="${UserInfo.sex=='1'}">selected</c:if>>男</option>
									<option value="2"
										<c:if test="${UserInfo.sex=='2'}">selected</c:if>>女</option>
								</select>
							</div>
						</div>
						<div class="form-group form-group-sm form-group-style">
							<label class="col-sm-4 control-label">电话号码</label>
							<div class="col-sm-5">
								<input id="user_phone_number" class="form-control" type="text"
									value='${UserInfo.userTelephoneNumber }' 
									placeholder="Small input">
							</div>
						</div>
						<div class="form-group form-group-sm form-group-style">
							<label class="col-sm-4 control-label">用户权限</label>
							<div class="col-sm-5">
								<select id="user_role" class="form-control">

									<option value="普通管理员"
										<c:if test="${UserInfo.userRole=='普通管理员'}">selected</c:if>>普通管理员</option>
									<option value="系统管理员"
										<c:if test="${UserInfo.userRole=='系统管理员'}">selected</c:if>>系统管理员</option>
								</select>
							</div>
						</div>
						<div class="form-group form-group-sm form-group-style">
							<div class="col-sm-4"></div>
							<div class="col-sm-5">
								<button onclick="modifyUserInfo();" type="button" class="btn btn-primary">修改</button>
							</div>
						</div>
					</form>
				</div>
				<div class="tab-pane fade" id="psw-modify">
					<form class="form-horizontal" role="form">
						<div class="form-group form-group-sm form-group-style">
							<label class="col-sm-4 control-label">旧密码</label>
							<div class="col-sm-5">
								<input class="form-control" type="password" id="user_psw"
									placeholder="Small input">
							</div>
						</div>
						<div class="form-group form-group-sm form-group-style">
							<label class="col-sm-4 control-label">新密码</label>
							<div class="col-sm-5">
								<input class="form-control" type="password" id="user_psw_new"
									placeholder="Small input">
							</div>
						</div>
						<div class="form-group form-group-sm form-group-style">
							<label class="col-sm-4 control-label">确认密码</label>
							<div class="col-sm-5">
								<input class="form-control" type="password" id="user_psw_confirm"
									placeholder="Small input">
							</div>
						</div>
						<div class="form-group form-group-sm form-group-style">
							<div class="col-sm-4"></div>
							<div class="col-sm-5">
								<button onclick="modifyUserPsw();" type="button" class="btn btn-primary">修改</button>
							</div>
						</div>

					</form>
				</div>
			</div>
			<footer> </footer>
		</div>
	</div>
	<jsp:include page="../templates/footer.jsp"></jsp:include>
</body>
</html>
