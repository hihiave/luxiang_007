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

<link rel="stylesheet" type="text/css" href="/mybatis/knowledgebase/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="/mybatis/knowledgebase/css/jquery-ui.min.css">
<link rel="stylesheet" type="text/css" href="/mybatis/knowledgebase/css/common.css">
<script src="/mybatis/knowledgebase/js/jquery.min.js"></script>
<script src="/mybatis/knowledgebase/js/bootstrap.min.js"></script>
<script src="/mybatis/knowledgebase/js/html5shiv.min.js"></script>
<script src="/mybatis/knowledgebase/js/respond.min.js"></script>
<script src="/mybatis/knowledgebase/js/common.js"></script>
<script src="/mybatis/knowledgebase/js/public_search.js"></script>
<script src="/mybatis/knowledgebase/js/ajaxfileupload.js"></script>
<script src="/mybatis/knowledgebase/js/adm_upload.js"></script>
<script src="/mybatis/knowledgebase/js/jquery-ui.min.js"></script>

<style type="text/css">
.up_panel{
min-height:10px !important;
}
.up_panel a{
text-decoration:none;
}
</style>
<%
	java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	java.util.Date currentTime = new java.util.Date();
	String time = simpleDateFormat.format(currentTime).toString();
%>
</head>
<body>


<jsp:include page="public-part.jsp" flush="true"/>
<div class="container">

		<div class="col-xs-2 left_nav">
		<ul class="nav nav-menu">
					<li><a href="#user-center" data-toggle="collapse">个人中心<span class="glyphicon glyphicon-menu-down" ></span></a></li>
						<ul id="user-center" class="panel-collapse collapse in">
							<li><a href="/mybatis/knowledgebase/adm-personal.jsp">个人信息</a></li>
							<c:if test="${is_worker == 1}">
							<li><a href="/mybatis/knowledgebase/adm-private.jsp">我的文件</a></li>					
							<li><a href="/mybatis/knowledgebase/adm-waitforcheck.jsp">待审文件</a></li>
							<li><a href="/mybatis/knowledgebase/adm-download.jsp">我的下载</a></li>
							<li><a href="/mybatis/knowledgebase/user-category.jsp">我的分类</a></li>
							<li><a href="/mybatis/knowledgebase/adm-draft.jsp">草稿箱</a></li>
							</c:if>
						</ul>
					<c:if test="${is_manager == 1}">
					<li><a href="#user-manage" data-toggle="collapse">用户管理<span class="glyphicon glyphicon-menu-down" ></span></a></li>
						<ul id="user-manage" class="panel-collapse collapse in">
							<li ><a href="/mybatis/knowledgebase/adm-inquire.jsp">用户查询</a></li>
							<li><a href="/mybatis/knowledgebase/adm-check.jsp">用户审核</a></li>
						</ul>
						</c:if>
						<c:if test="${is_worker == 1}">
						<li><a href="#source-center" data-toggle="collapse">资源中心<span class="glyphicon glyphicon-menu-down" ></span></a></li>
						<ul id="source-center" class="panel-collapse collapse in ">
							<li><a href="/mybatis/knowledgebase/adm-public.jsp">公有文件</a></li>
							<li class="active"><a href="/mybatis/knowledgebase/adm-upload.jsp">文件上传</a></li>
						</ul>
						</c:if>
					
						<c:if test="${is_manager == 1}">
						<li><a href="#source-manager" data-toggle="collapse">资源管理<span class="glyphicon glyphicon-menu-down" ></span></a></li>
						<ul id="source-manager" class="panel-collapse collapse in ">
							<li><a href="/mybatis/knowledgebase/adm-category.jsp">类别管理</a></li>
							<li><a href="/mybatis/knowledgebase/adm-checkfile.jsp">资源审核</a></li>
						</ul>
						</c:if>
					
					
					 <c:if test="${is_manager == 1}">
					<li><a href="#system-manage" data-toggle="collapse">系统管理<span class="glyphicon glyphicon-menu-down" ></span></a></li>
						<ul id="system-manage" class="panel-collapse collapse in">
							<li><a href="#">数据还原</a></li>
							<li><a href="#">数据备份</a></li>
							<li><a href="#">系统配置</a></li>
						</ul>
					</c:if>
					</ul>
		<div style="clear: both;"></div>
		</div>
			<div class="col-xs-10 r_body" >	
            <div class="panel panel-info"  id="default_panel">
					<div class="panel-heading">
						<h3 class="panel-title">文件上传</h3>
					</div>
					<div class="panel-body" style="height: 560px; overflow-y: auto;">
						<div class="panel panel-default up_panel">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a data-toggle="collapse" href="#upload1"> 选择文件1 </a>
								</h4>
							</div>
							<div id="upload1" class="panel-collapse collapse">
								<div class="panel-body form-inline">
									<div class="row" style="display: none;">
										<div class="col-xs-12" style="margin-bottom: 25px;">
											<input type="file" name="file-test1" id="file-test1"
												pid="aim1" rid="file_result1" onchange="changeFile(this)">
										</div>
									</div>
									
									<div class="row" style="display: none;">
										<div class="col-xs-12" style="margin-bottom: 25px;">
											<input type="input" name="file_result" id="file_result1">
										</div>
									</div>
									
									<div class="row">
										<div class="col-xs-1" style="margin-bottom: 25px;">
											<button type="button" class="btn btn-default"
												bid="file-test1" pid="aim1" onclick="add_one_click(this)">添加
											</button>
										</div>
										<div class="col-xs-1" style="margin-bottom: 25px;">
											<button type="button" class="btn btn-default"
												bid="file-test1" pid="aim1" onclick="del_one_click(this)">移除
											</button>
										</div>
									</div>

									<div class="row">
										<div class="col-xs-6" style="margin-bottom: 25px;">
											<span>资料名称：</span><input class="form-control"
												style="width: 75%;" type="text" id="aim1" readonly="true">&nbsp;&nbsp;
										</div>
										<div class="col-xs-6" style="margin-bottom: 25px;">
											<span>上传者：</span><input class="form-control"
												style="width: 75%;" type="text" id="author1"
												placeholder="<%=session.getAttribute("username")%>"
												readonly="true">
										</div>
									</div>

									<div class="row">
										<%-- <div class="col-xs-6" style="margin-bottom: 25px;">
											<span>上传时间：</span><input class="form-control"
												style="width: 75%;" type="text" id="time1"
												placeholder="<%=time%>" readonly="true">&nbsp;&nbsp;
										</div> --%>
										<div class="col-xs-6" style="margin-bottom: 25px;">
											<span>关键词：</span><input class="form-control"
												style="width: 75%;" placeholder="多个关键词以空格分开" type="text"
												id="word1">
										</div>
									</div>

									<div class="row">
										<div class="col-xs-6" style="margin-bottom: 25px;">
											<span>资料属性：</span> <select class="form-control " id="proto1"  num="1"
												onchange="get_category(this)" style="width: 75%; margin-left: -3px;">
												<option value='' selected>请选择</option>
												<option value='私有' >私有</option>
												<option value='公有' >公有</option>
											</select>
										</div>
										<div class="col-xs-6" style="margin-bottom: 25px;">
											<span>类&nbsp;&nbsp;&nbsp;&nbsp;别：</span><select
												class="form-control select-class-name" id="cate1" count="1"
												onchange="get_child_category_select(this)" style="width: 25%; margin-left: -2px;">	
												<option value=''>请选择</option>									
												</select>
												<select
												class="form-control select-name" id="child_cate1"
												onchange="value" style="width: 25%; margin-left: -2px;">
												<option value=''>请选择</option></select>
										</div>
									</div>

									<div class="row">
										<div class="col-xs-12" style="margin-bottom: 25px;">
											<span>资料描述：</span>
											<textarea type="textarea" class="form-control"
												style="width: 95%;" rows="3" id="area1"></textarea>
										</div>

									</div>
								</div>
							</div>
						</div>
						<div class="panel panel-default up_panel">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a data-toggle="collapse" href="#upload2"> 选择文件2 </a>
								</h4>
							</div>
							<div id="upload2" class="panel-collapse collapse">
								<div class="panel-body form-inline">
									<div class="row" style="display: none;">
										<div class="col-xs-12" style="margin-bottom: 25px;">
											<input type="file" name="file-test2" id="file-test2"
												pid="aim2" rid="file_result2" onchange="changeFile(this)">
										</div>
									</div>
									
									<div class="row" style="display: none;">
										<div class="col-xs-12" style="margin-bottom: 25px;">
											<input type="input" name="file_result" id="file_result2">
										</div>
									</div>
									
									<div class="row">
										<div class="col-xs-1" style="margin-bottom: 25px;">
											<button type="button" class="btn btn-default"
												bid="file-test2" pid="aim2" onclick="add_one_click(this)">添加
											</button>
										</div>
										<div class="col-xs-1" style="margin-bottom: 25px;">
											<button type="button" class="btn btn-default"
												bid="file-test2" pid="aim2" onclick="del_one_click(this)">移除
											</button>
										</div>
									</div>

									<div class="row">
										<div class="col-xs-6" style="margin-bottom: 25px;">
											<span>资料名称：</span><input class="form-control"
												style="width: 75%;" type="text" id="aim2" readonly="true">&nbsp;&nbsp;
										</div>
										<div class="col-xs-6" style="margin-bottom: 25px;">
											<span>上传者：</span><input class="form-control"
												style="width: 75%;" type="text" id="author2"
												placeholder="<%=session.getAttribute("username")%>"
												readonly="true">
										</div>
									</div>

									<div class="row">
										<div class="col-xs-6" style="margin-bottom: 25px;">
											<span>上传时间：</span><input class="form-control"
												style="width: 75%;" type="text" id="time2"
												placeholder="<%=time%>" readonly="true">&nbsp;&nbsp;
										</div>
										<div class="col-xs-6" style="margin-bottom: 25px;">
											<span>关键词：</span><input class="form-control"
												style="width: 75%;" placeholder="多个关键词以空格分开" type="text"
												id="word2">
										</div>
									</div>

									<div class="row">
										<div class="col-xs-6" style="margin-bottom: 25px;">
											<span>资料属性：</span> <select class="form-control " id="proto2"  num="2"
												onchange="get_category(this)" style="width: 75%; margin-left: -3px;">
												<option value='' selected>请选择</option>
												<option value='私有' >私有</option>
												<option value='公有' >公有</option>
											</select>
										</div>
										<div class="col-xs-6" style="margin-bottom: 25px;">
											<span>类&nbsp;&nbsp;&nbsp;&nbsp;别：</span><select
												class="form-control select-class-name" id="cate2" count="2"
												onchange="get_child_category_select(this)" style="width: 25%; margin-left: -2px;">										
												</select>
												<select
												class="form-control select-name" id="child_cate2"
												onchange="value" style="width: 25%; margin-left: -2px;"></select>
										</div>
									</div>

									<div class="row">
										<div class="col-xs-12" style="margin-bottom: 25px;">
											<span>资料描述：</span>
											<textarea type="textarea" class="form-control"
												style="width: 95%;" rows="3" id="area2"></textarea>
										</div>

									</div>
								</div>
							</div>
						</div>
						<div class="panel panel-default up_panel">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a data-toggle="collapse" href="#upload3"> 选择文件3 </a>
								</h4>
							</div>
							<div id="upload3" class="panel-collapse collapse">
								<div class="panel-body form-inline">
									<div class="row" style="display: none;">
										<div class="col-xs-12" style="margin-bottom: 25px;">
											<input type="file" name="file-test3" id="file-test3"
												pid="aim3" rid="file_result3" onchange="changeFile(this)">
										</div>
									</div>
									
									<div class="row" style="display: none;">
										<div class="col-xs-12" style="margin-bottom: 25px;">
											<input type="input" name="file_result" id="file_result3">
										</div>
									</div>
									
									<div class="row">
										<div class="col-xs-1" style="margin-bottom: 25px;">
											<button type="button" class="btn btn-default"
												bid="file-test3" pid="aim3" onclick="add_one_click(this)">添加
											</button>
										</div>
										<div class="col-xs-1" style="margin-bottom: 25px;">
											<button type="button" class="btn btn-default"
												bid="file-test3" pid="aim3" onclick="del_one_click(this)">移除
											</button>
										</div>
									</div>
									<div class="row">
										<div class="col-xs-6" style="margin-bottom: 25px;">
											<span>资料名称：</span><input class="form-control"
												style="width: 75%;" type="text" id="aim3" readonly="true">&nbsp;&nbsp;
										</div>
										<div class="col-xs-6" style="margin-bottom: 25px;">
											<span>上传者：</span><input class="form-control"
												style="width: 75%;" type="text" id="author3"
												placeholder="<%=session.getAttribute("username")%>"
												readonly="true">
										</div>
									</div>

									<div class="row">
										<div class="col-xs-6" style="margin-bottom: 25px;">
											<span>上传时间：</span><input class="form-control"
												style="width: 75%;" type="text" id="time3"
												placeholder="<%=time%>" readonly="true">&nbsp;&nbsp;
										</div>
										<div class="col-xs-6" style="margin-bottom: 25px;">
											<span>关键词：</span><input class="form-control"
												style="width: 75%;" placeholder="多个关键词以空格分开" type="text"
												id="word3">
										</div>
									</div>

									<div class="row">
										<div class="col-xs-6" style="margin-bottom: 25px;">
											<span>资料属性：</span> <select class="form-control " id="proto3"  num="3"
												onchange="get_category(this)" style="width: 75%; margin-left: -3px;">
												<option value='' selected>请选择</option>
												<option value='私有' >私有</option>
												<option value='公有' >公有</option>
											</select>
										</div>
										<div class="col-xs-6" style="margin-bottom: 25px;">
											<span>类&nbsp;&nbsp;&nbsp;&nbsp;别：</span><select
												class="form-control select-class-name" id="cate3" count="3"
												onchange="get_child_category_select(this)" style="width: 25%; margin-left: -2px;">										
												</select>
												<select
												class="form-control select-name" id="child_cate3"
												onchange="value" style="width: 25%; margin-left: -2px;"></select>
										</div>
									</div>

									<div class="row">
										<div class="col-xs-12" style="margin-bottom: 25px;">
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
							<button class="button button-primary button-rounded button-small" onclick="uploadFile()">上传</button>
						</div>


						<%--<div style="padding-top: 20px;">--%>
						<%--<input type="file" name="file-test" id="file-test" onchange="changeFile_test(this)">--%>
						<%--<button class="btn btn-primary">提交</button>--%>
						<%--</div>--%>
					</div>
				</div>
			  <jsp:include page="search-result.jsp" flush="true"/>
            <div style="clear:both;"></div>
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
<div class="modal fade" id="logout-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel-logout">
    <div class="modal-dialog modal-sm" role="document">
        <div class="modal-content">
            <div class="modal-header">

                <h4 class="modal-title" id="myModalLabel-logout">退出提示</h4>
            </div>
            <div class="modal-body">
                <p id="logout-username"></p>
            </div>
            <div class="modal-footer">

                <button type="button" class="btn btn-primary " data-dismiss="modal" id="logout-dismiss">确认</button>
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
