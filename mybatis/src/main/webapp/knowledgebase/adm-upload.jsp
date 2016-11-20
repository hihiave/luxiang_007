<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	if (session.getAttribute("username") != null) {
%>

<%
	} else {
%>
<script language="javascript">
	alert("请先登录!");

	top.location = 'Login.jsp';
</script>
<%
	}
%>
<c:set value="0" var="is_manager" />
<c:set value="0" var="is_worker" />
<c:forEach items="${userrole}" var="role">
	<c:if test="${role == '管理员'}">
		<c:set value="1" var="is_manager" />
	</c:if>
	<c:if test="${role == '普通用户'}">
		<c:set value="1" var="is_worker" />
	</c:if>
</c:forEach>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="/mybatis/knowledgebase/css/bootstrap.min.css">
<script src="/mybatis/knowledgebase/js/jquery.min.js"></script>
<script src="/mybatis/knowledgebase/js/bootstrap.min.js"></script>
<script src="/mybatis/knowledgebase/js/html5shiv.min.js"></script>
<script src="/mybatis/knowledgebase/js/ajaxfileupload.js"></script>
<script src="/mybatis/knowledgebase/js/respond.min.js"></script>
<script src="/mybatis/knowledgebase/js/common.js"></script>
<script src="/mybatis/knowledgebase/js/public_search.js"></script>
<script src="/mybatis/knowledgebase/js/adm_upload.js"></script>

<style type="text/css">
.nav {
	margin-bottom: 5px;
	text-align: center;
}

.hr {
	margin: 0 auto;
}

.container {
	width: 1320px !important;
}
</style>


<%
	java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	java.util.Date currentTime = new java.util.Date();
	String time = simpleDateFormat.format(currentTime).toString();
%>
<%--<script type="text/javascript">--%>
<%--window.onload = function () {--%>
<%--$("#file1").val("");--%>
<%--$("#file2").val("");--%>
<%--$("#file3").val("");--%>
<%--$("#aim1").val("");--%>
<%--$("#aim2").val("");--%>
<%--$("#aim3").val("");--%>
<%--var file1 = $('#file1'),--%>
<%--aim1 = $('#aim1');--%>
<%--//            console.log(file1.val());--%>
<%--file1.on('change', function (e) {--%>

<%--if(file1.val == null){--%>
<%--aim1.val("");--%>
<%--}else{--%>
<%--var name = e.currentTarget.files[0].name;--%>
<%--aim1.val(name);--%>
<%--}--%>

<%--});--%>
<%--var file2 = $('#file2'),--%>
<%--aim2 = $('#aim2');--%>
<%--file2.on('change', function (e) {--%>
<%--var name = e.currentTarget.files[0].name;--%>
<%--aim2.val(name);--%>
<%--});--%>
<%--var file3 = $('#file3'),--%>
<%--aim3 = $('#aim3');--%>
<%--file3.on('change', function (e) {--%>
<%--var name = e.currentTarget.files[0].name;--%>
<%--aim3.val(name);--%>
<%--});--%>
<%--sub.onclick = check;--%>
<%--function check() {--%>

<%--var aim1 = document.getElementById("aim1"),--%>
<%--aim2 = document.getElementById("aim2"), aim3 = document.getElementById("aim3");--%>
<%--var word1 = document.getElementById("word1"),--%>
<%--word2 = document.getElementById("word2"), word3 = document.getElementById("word3");--%>
<%--var area1 = document.getElementById("area1"),--%>
<%--area2 = document.getElementById("area2"), area3 = document.getElementById("area3");--%>

<%--if (aim1.value == "" & aim2.value == "" & aim3.value == "") {--%>
<%--alert("请选择上传的文件");--%>
<%--return false;--%>
<%--}--%>
<%--else {--%>
<%--if (aim2.value != "") {--%>
<%--if ((area2.value == "") || (word2.value == "")) {--%>
<%--alert("请完善文件信息");--%>
<%--return false;--%>
<%--}--%>
<%--}--%>
<%--else if (aim1.value != "") {--%>
<%--if ((area1.value == "") || (word1.value == "")) {--%>
<%--alert("请完善文件信息");--%>
<%--return false;--%>
<%--}--%>
<%--}--%>
<%--else if (aim3.value != "") {--%>
<%--if ((area3.value == "") || (word3.value == "")) {--%>
<%--alert("请完善文件信息");--%>
<%--return false;--%>
<%--}--%>
<%--}--%>
<%--}--%>
<%--}--%>
<%--}--%>
<%--</script>--%>

</head>
<body>
	<%--<input type="hidden" id="username_file" value="<%=session.getAttribute("username") %>"/>--%>
	<%--<input type="hidden" id="time_file" value="<%=time%>"/>--%>
	<%--<script type="text/javascript">--%>
	<%--window.onload = function () {--%>
	<%--&lt;%&ndash;var register_time = "${time}";&ndash;%&gt;--%>
	<%--//            var time = timeStampFormatDay(register_time * 1000);--%>
	<%--//            console.log(time);--%>
	<%--$("#author1").html("${username}");--%>
	<%--$("#author2").html("${username}");--%>
	<%--$("#author3").html("${username}");--%>
	<%--&lt;%&ndash;$("#show_user_name").html("${username}");&ndash;%&gt;--%>
	<%--$("#time2").html("<%=time%>>");--%>
	<%--$("#time3").html("<%=time%>>");--%>
	<%--$("#time1").html("<%=time%>>");--%>
	<%--//            $("#show_register_time").html(time);--%>

	<%--}--%>
	<%--</script>--%>
	<div style="margin: 10px 20px;">
		<button type="button" class="btn btn-md btn-default"
			style="border: 0px; float: right;" data-toggle="tooltip"
			data-placement="bottom" title="退出登录" onclick="logout()">
			<span class="glyphicon glyphicon-off"></span>&nbsp;退出
		</button>
		<button type="button" class="btn btn-md btn-default"
			style="border: 0px; float: right;" data-toggle="tooltip"
			data-placement="bottom" title="个人中心">
			<a href="/mybatis/knowledgebase/adm-personal.jsp"><span
				class="glyphicon glyphicon-user"></span>&nbsp;首页</a>
		</button>
	</div>
	<div class="container">
		<jsp:include page="public-part.jsp" flush="true" />
		<hr>
		<div class="row" style="margin-left: 80px;">
			<div class="col-md-2"
				style="border-radius: 10px; width: 170px; font-size: 16px; border-right: 1px solid #eee">
				<div class="panel-group" id="accordion">
					<div class="panel panel-info"
						style="border-color: #eeeeee; background-color: #f9f9f9;">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a href="#person-center" data-toggle="collapse"
									data-parent="accordion">个人中心<span class="caret"
									style="float: right; margin-top: 7px; margin-right: -11px;"></span></a>
							</h4>
						</div>
						<div id="person-center" class="panel-collapse collapse in">
							<div class="panel-body" style="padding: 6px 15px;">
								<ul class="nav nav-pills nav-stacked"
									style="margin-left: -15px; margin-right: -15px; margin-bottom: 0px; font-size: inherit;">
									<li><a href="/mybatis/knowledgebase/adm-personal.jsp">个人信息</a></li>
								</ul>
							</div>
						</div>
					</div>
					<c:if test="${is_manager == 1}">
						<div class="panel panel-info"
							style="border-color: #eeeeee; background-color: #f9f9f9;">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a href="#user-manage" data-toggle="collapse"
										data-parent="accordion">用户管理<span class="caret"
										style="float: right; margin-top: 7px; margin-right: -11px;"></span></a>
								</h4>
							</div>
							<div id="user-manage" class="panel-collapse collapse in">
								<div class="panel-body" style="padding: 6px 15px;">
									<ul class="nav nav-pills nav-stacked"
										style="margin-left: -15px; margin-right: -15px; margin-bottom: 0px; font-size: inherit;">
										<li><a href="/mybatis/knowledgebase/adm-inquire.jsp">用户查询</a></li>
										<li><a href="/mybatis/knowledgebase/adm-check.jsp">用户审核</a></li>
									</ul>
								</div>
							</div>
						</div>
					</c:if>
					<div class="panel panel-info"
						style="border-color: #eeeeee; background-color: #f9f9f9;">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a href="#source-manage" data-toggle="collapse"
									data-parent="accordion">资源管理<span class="caret"
									style="float: right; margin-top: 7px; margin-right: -11px;"></span></a>
							</h4>
						</div>
						<div id="source-manage" class="panel-collapse collapse in">
							<div class="panel-body" style="padding: 6px 15px;">
								<ul class="nav nav-pills nav-stacked"
									style="margin-left: -15px; margin-right: -15px; margin-bottom: 0px; font-size: inherit;">
									<li><a href="/mybatis/knowledgebase/adm-private.jsp">我的文件</a></li>
									<li><a href="/mybatis/knowledgebase/adm-waitforcheck.jsp">待审文件</a></li>
									<li><a href="/mybatis/knowledgebase/adm-public.jsp">公有文件</a></li>
									<li><a href="/mybatis/knowledgebase/adm-download.jsp">我的下载</a></li>
									<li class="active"><a
										href="/mybatis/knowledgebase/adm-upload.jsp">文件上传</a></li>
									<li><a href="/mybatis/knowledgebase/adm-draft.jsp">草稿箱</a></li>
								</ul>
							</div>
						</div>
					</div>
					<c:if test="${is_manager == 1}">
						<div class="panel panel-info"
							style="border-color: #eeeeee; background-color: #f9f9f9;">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a href="#system-manage" data-toggle="collapse"
										data-parent="accordion">系统管理<span class="caret"
										style="float: right; margin-top: 7px; margin-right: -11px;"></span></a>
								</h4>
							</div>
							<div id="system-manage" class="panel-collapse collapse in">
								<div class="panel-body" style="padding: 6px 15px;">
									<ul class="nav nav-pills nav-stacked"
										style="margin-left: -15px; margin-right: -15px; margin-bottom: 0px; font-size: inherit;">
										<li><a href="/mybatis/knowledgebase/adm-category.jsp">类别管理</a></li>
										<li><a href="/mybatis/knowledgebase/adm-checkfile.jsp">资源审核</a></li>
										<li><a href="##">数据还原</a></li>
										<li><a href="##">数据备份</a></li>
									</ul>
								</div>
							</div>
						</div>
					</c:if>

				</div>
			</div>
			<div class="col-md-8" style="margin-left: 50px;">
				<div class="panel panel-primary" style="min-height: 625px;">
					<div class="panel-heading">
						<h3 class="panel-title">文件上传</h3>
					</div>
					<div class="panel-body" style="height: 580px; overflow-y: auto;">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a data-toggle="collapse" href="#upload1"> 选择文件1 </a>
								</h4>
							</div>
							<div id="upload1" class="panel-collapse collapse">
								<div class="panel-body form-inline">
									<div class="row" style="display: none;">
										<div class="col-md-12" style="margin-bottom: 25px;">
											<input type="file" name="file-test1" id="file-test1"
												pid="aim1" rid="file_result1" onchange="changeFile(this)">
										</div>
									</div>
									
									<div class="row" style="display: none;">
										<div class="col-md-12" style="margin-bottom: 25px;">
											<input type="input" name="file_result" id="file_result1">
										</div>
									</div>
									
									<div class="row">
										<div class="col-md-1" style="margin-bottom: 25px;">
											<button type="button" class="btn btn-default"
												bid="file-test1" pid="aim1" onclick="add_one_click(this)">添加
											</button>
										</div>
										<div class="col-md-1" style="margin-bottom: 25px;">
											<button type="button" class="btn btn-default"
												bid="file-test1" pid="aim1" onclick="del_one_click(this)">移除
											</button>
										</div>
									</div>

									<div class="row">
										<div class="col-md-6" style="margin-bottom: 25px;">
											<span>资料名称：</span><input class="form-control"
												style="width: 75%;" type="text" id="aim1" readonly="true">&nbsp;&nbsp;
										</div>
										<div class="col-md-6" style="margin-bottom: 25px;">
											<span>上传者：</span><input class="form-control"
												style="width: 75%;" type="text" id="author1"
												placeholder="<%=session.getAttribute("username")%>"
												readonly="true">
										</div>
									</div>

									<div class="row">
										<div class="col-md-6" style="margin-bottom: 25px;">
											<span>上传时间：</span><input class="form-control"
												style="width: 75%;" type="text" id="time1"
												placeholder="<%=time%>" readonly="true">&nbsp;&nbsp;
										</div>
										<div class="col-md-6" style="margin-bottom: 25px;">
											<span>关键词：</span><input class="form-control"
												style="width: 75%;" placeholder="多个关键词以空格分开" type="text"
												id="word1">
										</div>
									</div>

									<div class="row">
										<div class="col-md-6" style="margin-bottom: 25px;">
											<span>资料属性：</span> <select class="form-control " id="proto1"
												onchange="value" style="width: 75%; margin-left: -3px;">
												<option value='私有' selected>私有</option>
												<option value='公有'>公有</option>
											</select>
										</div>
										<div class="col-md-6" style="margin-bottom: 25px;">
											<span>类&nbsp;&nbsp;&nbsp;&nbsp;别：</span><select
												class="form-control select-class-name" id="cate1"
												onchange="value" style="width: 75%; margin-left: -2px;"></select>
										</div>
									</div>

									<div class="row">
										<div class="col-md-12" style="margin-bottom: 25px;">
											<span>资料描述：</span>
											<textarea type="textarea" class="form-control"
												style="width: 95%;" rows="3" id="area1"></textarea>
										</div>

									</div>
								</div>
							</div>
						</div>
						<div class="panel panel-default">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a data-toggle="collapse" href="#upload2"> 选择文件2 </a>
								</h4>
							</div>
							<div id="upload2" class="panel-collapse collapse">
								<div class="panel-body form-inline">
									<div class="row" style="display: none;">
										<div class="col-md-12" style="margin-bottom: 25px;">
											<input type="file" name="file-test2" id="file-test2"
												pid="aim2" rid="file_result2" onchange="changeFile(this)">
										</div>
									</div>
									
									<div class="row" style="display: none;">
										<div class="col-md-12" style="margin-bottom: 25px;">
											<input type="input" name="file_result" id="file_result2">
										</div>
									</div>
									
									<div class="row">
										<div class="col-md-1" style="margin-bottom: 25px;">
											<button type="button" class="btn btn-default"
												bid="file-test2" pid="aim2" onclick="add_one_click(this)">添加
											</button>
										</div>
										<div class="col-md-1" style="margin-bottom: 25px;">
											<button type="button" class="btn btn-default"
												bid="file-test2" pid="aim2" onclick="del_one_click(this)">移除
											</button>
										</div>
									</div>

									<div class="row">
										<div class="col-md-6" style="margin-bottom: 25px;">
											<span>资料名称：</span><input class="form-control"
												style="width: 75%;" type="text" id="aim2" readonly="true">&nbsp;&nbsp;
										</div>
										<div class="col-md-6" style="margin-bottom: 25px;">
											<span>上传者：</span><input class="form-control"
												style="width: 75%;" type="text" id="author2"
												placeholder="<%=session.getAttribute("username")%>"
												readonly="true">
										</div>
									</div>

									<div class="row">
										<div class="col-md-6" style="margin-bottom: 25px;">
											<span>上传时间：</span><input class="form-control"
												style="width: 75%;" type="text" id="time2"
												placeholder="<%=time%>" readonly="true">&nbsp;&nbsp;
										</div>
										<div class="col-md-6" style="margin-bottom: 25px;">
											<span>关键词：</span><input class="form-control"
												style="width: 75%;" placeholder="多个关键词以空格分开" type="text"
												id="word2">
										</div>
									</div>

									<div class="row">
										<div class="col-md-6" style="margin-bottom: 25px;">
											<span>资料属性：</span> <select class="form-control " id="proto2"
												onchange="value" style="width: 75%; margin-left: -3px;">
												<option value='私有' selected>私有</option>
												<option value='公有'>公有</option>
											</select>
										</div>
										<div class="col-md-6" style="margin-bottom: 25px;">
											<span>类&nbsp;&nbsp;&nbsp;&nbsp;别：</span><select
												class="form-control select-class-name" id="cate2"
												onchange="value" style="width: 75%; margin-left: -2px;"></select>
										</div>
									</div>

									<div class="row">
										<div class="col-md-12" style="margin-bottom: 25px;">
											<span>资料描述：</span>
											<textarea type="textarea" class="form-control"
												style="width: 95%;" rows="3" id="area2"></textarea>
										</div>

									</div>
								</div>
							</div>
						</div>
						<div class="panel panel-default">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a data-toggle="collapse" href="#upload3"> 选择文件3 </a>
								</h4>
							</div>
							<div id="upload3" class="panel-collapse collapse">
								<div class="panel-body form-inline">
									<div class="row" style="display: none;">
										<div class="col-md-12" style="margin-bottom: 25px;">
											<input type="file" name="file-test3" id="file-test3"
												pid="aim3" rid="file_result3" onchange="changeFile(this)">
										</div>
									</div>
									
									<div class="row" style="display: none;">
										<div class="col-md-12" style="margin-bottom: 25px;">
											<input type="input" name="file_result" id="file_result3">
										</div>
									</div>
									
									<div class="row">
										<div class="col-md-1" style="margin-bottom: 25px;">
											<button type="button" class="btn btn-default"
												bid="file-test3" pid="aim3" onclick="add_one_click(this)">添加
											</button>
										</div>
										<div class="col-md-1" style="margin-bottom: 25px;">
											<button type="button" class="btn btn-default"
												bid="file-test3" pid="aim3" onclick="del_one_click(this)">移除
											</button>
										</div>
									</div>
									<div class="row">
										<div class="col-md-6" style="margin-bottom: 25px;">
											<span>资料名称：</span><input class="form-control"
												style="width: 75%;" type="text" id="aim3" readonly="true">&nbsp;&nbsp;
										</div>
										<div class="col-md-6" style="margin-bottom: 25px;">
											<span>上传者：</span><input class="form-control"
												style="width: 75%;" type="text" id="author3"
												placeholder="<%=session.getAttribute("username")%>"
												readonly="true">
										</div>
									</div>

									<div class="row">
										<div class="col-md-6" style="margin-bottom: 25px;">
											<span>上传时间：</span><input class="form-control"
												style="width: 75%;" type="text" id="time3"
												placeholder="<%=time%>" readonly="true">&nbsp;&nbsp;
										</div>
										<div class="col-md-6" style="margin-bottom: 25px;">
											<span>关键词：</span><input class="form-control"
												style="width: 75%;" placeholder="多个关键词以空格分开" type="text"
												id="word3">
										</div>
									</div>

									<div class="row">
										<div class="col-md-6" style="margin-bottom: 25px;">
											<span>资料属性：</span> <select class="form-control " id="proto3"
												onchange="value" style="width: 75%; margin-left: -3px;">
												<option value='私有' selected>私有</option>
												<option value='公有'>公有</option>
											</select>
										</div>
										<div class="col-md-6" style="margin-bottom: 25px;">
											<span>类&nbsp;&nbsp;&nbsp;&nbsp;别：</span><select
												class="form-control select-class-name" id="cate3"
												onchange="value" style="width: 75%; margin-left: -2px;"></select>
										</div>
									</div>

									<div class="row">
										<div class="col-md-12" style="margin-bottom: 25px;">
											<span>资料描述：</span>
											<textarea type="textarea" class="form-control"
												style="width: 95%;" rows="3" id="area3"></textarea>
										</div>

									</div>
								</div>
							</div>
						</div>


						<div align="center">
							<%--<input type="submit" value="上传文件" id="sub"   class="btn btn-primary">--%>
							<button class="btn btn-primary" onclick="uploadFile()">上传</button>
						</div>


						<%--<div style="padding-top: 20px;">--%>
						<%--<input type="file" name="file-test" id="file-test" onchange="changeFile_test(this)">--%>
						<%--<button class="btn btn-primary">提交</button>--%>
						<%--</div>--%>
					</div>
				</div>

			</div>
		</div>
	</div>
	<div class="modal fade" id="info-modal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel-info">
		<div class="modal-dialog modal-sm" role="document">
			<div class="modal-content">
				<div class="modal-header">

					<h4 class="modal-title" id="myModalLabel-info">提示</h4>
				</div>
				<div class="modal-body">
					<p id="info-p"></p>
				</div>
				<div class="modal-footer">

					<button type="button" class="btn btn-primary" data-dismiss="modal">
						是</button>
				</div>
			</div>
		</div>
	</div>

	<style type="text/css">
/*th.btn-primary {*/
/*width: 116px;*/
/*}*/
</style>


	<style type="text/css">
.modal {
	margin-top: 20%;
}

.modal-body li {
	list-style-type: none;
	padding: 0px 5px;
	border: 1px solid #ffffff;
	padding: 5px 0px;
}

/*#username1, #password1 {*/
/*font-size: 15px;*/

/*}*/

/*.footer {*/
/*position: absolute;*/
/*top: 94%;*/
/*height: 6%;*/
/*background-color: #e1e1e1;*/
/*width: 100%;*/

/*text-align: center;*/
/*min-width: 1190px;*/
/*}*/

/*.footer span.footerText {*/
/*font-size: 14px;*/
/*margin-top: 0.5%;*/
/*color: #606060;*/
/*display: inline-block;*/
/*}*/
</style>
	<%--<div class="footer"> <span class="footerText">Copyright © 1956-2016 电子科技大学</span> </div>--%>
</body>
</html>
