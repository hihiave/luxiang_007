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
<%
if(session.getAttribute("message")=="noexist"){
	%>
	<%
	request.getSession().removeAttribute("message");
	%>
	<script language="javascript">
	alert("资源不存在！");
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
<script src="/mybatis/knowledgebase/js/adm_private.js"></script>
<script src="/mybatis/knowledgebase/js/jquery-ui.min.js"></script>


<style type="text/css">
		td .button-rounded{
	padding-left:20px;
	padding-right:20px;
	}

</style>

</head>
<body>


<jsp:include page="public-part.jsp" flush="true"/>
<input type="hidden" id="type" value="">
<input type="hidden" id="cate" value="">
<div class="pub_bottom">

		<div class="col-xs-2 left_nav">
		<ul class="nav nav-menu">
					<li><a href="#user-center" data-toggle="collapse">个人中心<span class="glyphicon glyphicon-menu-down" ></span></a></li>
						<ul id="user-center" class="panel-collapse collapse in">
							<li><a href="/mybatis/knowledgebase/adm-personal.jsp">个人信息</a></li>
							<c:if test="${is_worker == 1}">
							<li class="active"><a href="/mybatis/knowledgebase/adm-private.jsp">我的文件</a></li>					
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
							<li><a href="/mybatis/knowledgebase/adm-check.jsp">用户审核<span id="badge1" class="badge"></span></a></li>
						</ul>
						</c:if>
						<c:if test="${is_worker == 1}">
						<li><a href="#source-center" data-toggle="collapse">资源中心<span class="glyphicon glyphicon-menu-down" ></span></a></li>
						<ul id="source-center" class="panel-collapse collapse in ">
						
							<li><a href="/mybatis/knowledgebase/adm-public.jsp">公有文件</a></li>
							
							<li><a href="/mybatis/knowledgebase/adm-upload.jsp">文件上传</a></li>
						
						</ul>
						</c:if>
					
						<c:if test="${is_manager == 1}">
						<li><a href="#source-manager" data-toggle="collapse">资源管理<span class="glyphicon glyphicon-menu-down" ></span></a></li>
						<ul id="source-manager" class="panel-collapse collapse in ">
							<li><a href="/mybatis/knowledgebase/adm-category.jsp">类别管理</a></li>
							<li><a href="/mybatis/knowledgebase/adm-checkfile.jsp">资源审核<span id="badge2" class="badge"></span></a></li>
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
						<h3 class="panel-title">我的文件</h3>
					</div>
					<div class="panel-body" style="padding-top: 0px;">
						<div style="padding-top: 10px;">
							<button class="button button-royal button-rounded button-giant button-small" 
							 style="height:34px;float:left;padding:0 15px; margin-right:10px;
							 background: #808080;color: #FFF"
								onclick="delete_all_pick(this)">删除</button>
							<!-- <select class="form-control" onchange="get_category(this)" id="filetype"
								style="width: 10%;padding:6px;display:inline;">
							<option value='' selected>全部</option>
							<option value='私有'>私有</option>
							<option value='公有'>公有</option>
							</select> -->
							<input type="hidden" id="filetype" value="">
							<input type="radio" name="typeradio" onclick="get_category(this)" value="" checked style="display:inline;">全部&nbsp;
							<input type="radio" name="typeradio" onclick="get_category(this)" value="公有" style="display:inline;">公有&nbsp;
							<input type="radio" name="typeradio" onclick="get_category(this)" value="私有" style="display:inline;">私有&nbsp;
							<select class="form-control" onchange="get_child_category_select(this)" id="filecate"
								style="width: 10%;padding:6px;display:inline;">
							<option value=''>请选择</option>
							
							</select>
								<select class="form-control" onchange="" id="filechildcate"
								style="width: 10%;padding:6px;display:inline;">
							<option value=''>请选择</option>
							</select>
							<button class="button button-royal button-rounded button-giant button-small"
							 onclick="fileinfo_search(this)" 
							 style="float:right;height:33px; margin-right:2.5%; background: #808080;color: #FFF">查询</button>
						</div>
						<table class="table table-striped">
							<thead>
								<tr>
									<th style="width: 5%"><input type="checkbox" id="selAll"
										onclick="selectAll()"></th>
									<th style="width: 50%;">文件名</th>
									<th style="width: 10%">属性</th>
									<th style="width: 15%">上传时间</th>
									<th style="width: 10%;">&nbsp;&nbsp;分类</th>
									<th style="width: 10%;">&nbsp;&nbsp;下载</th>

									<%--<th style="padding-bottom:15px;">&nbsp;&nbsp;预览</th>--%>
								</tr>
							</thead>
							<tbody id="pri_file">
						
							</tbody>
						</table>
						<div style="margin-top: -20px; float: right;">
							<ul class="pagination" id="page-file-one">
								
							</ul>
						</div>
					</div>
				</div>
				<jsp:include page="search-result.jsp" flush="true" />
				<div style="clear:both;"></div>
			</div>
		</div>
	 
	 <div class="modal fade" id="changefileinfo" tabindex="-1" role="dialog" aria-labelledby="myModalLabel-changefileino">
    <div class="modal-dialog modal-sm" role="document">
        <div class="modal-content" style="width:400px;">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel-changefileinfo">提示</h4>
            </div>
            <div class="modal-body">
       		<ul style="padding-left: 0px;">
       			<li>
       			<span>用户分类：</span>
       			<input type="text" value="" style="width:70%;display:inline;"
							class="form-control" id="usercate"  readonly="true">
       			</li>
				<li><span>重新分类：</span>
				<select class="form-control" onchange="get_my_category_select(this)" id="cate_select"
					style="width: 34%;padding:6px;display:inline;">
				<option value='类别'>类别</option>
				</select>
				<select class="form-control" onchange="value" id="second_cate"
				style="width: 35%;padding:6px;display:inline;">
				<option value=''>请选择</option>
				</select>	
				</li>
					</ul>
					</div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" data-dismiss="modal" id="change_click"
                        onclick="updateFile(this)">修改
                </button>
            </div>
        </div>
    </div>
</div>
	 	<div class="modal fade" id="result" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog modal-sm" role="document">
        <div class="modal-content">
            <div class="modal-header">

                <h4 class="modal-title" id="myModalLabel-add-result">提示信息</h4>
            </div>
            <div class="modal-body">
                <p id="result_msg"></p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary " data-dismiss="modal">确认</button>
            </div>
        </div>
    </div>
	</div>

	<div class="modal fade" id="download" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog modal-sm" role="document">
			<div class="modal-content">
				<div class="modal-header">

					<h4 class="modal-title" id="myModalLabel">提示</h4>
				</div>
				<div class="modal-body">
					<p id="down_file"></p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">否</button>
					<button type="button" class="btn btn-primary" id="down_click"
						data-dismiss="modal" onclick="down_ok()">是</button>
				</div>
			</div>
		</div>
	</div>


	<div class="modal fade" id="delete" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog modal-sm" role="document">
			<div class="modal-content">
				<div class="modal-header">

					<h4 class="modal-title" id="myModalLabel">提示</h4>
				</div>
				<div class="modal-body">
					<p id="delete_file"></p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">否</button>
					<button type="button" class="btn btn-primary" id="delete_click"
						data-dismiss="modal" onclick="delete_file_ok()">是</button>
				</div>
			</div>
		</div>
	</div>


	<div class="modal fade" id="logout-modal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel-logout">
		<div class="modal-dialog modal-sm" role="document">
			<div class="modal-content">
				<div class="modal-header">

					<h4 class="modal-title" id="myModalLabel-logout">退出提示</h4>
				</div>
				<div class="modal-body">
					<p id="logout-username"></p>
				</div>
				<div class="modal-footer">

					<button type="button" class="btn btn-primary " data-dismiss="modal"
						id="logout-dismiss">确认</button>
				</div>
			</div>
		</div>
	</div>

	<div class="modal fade" id="preview" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog modal-sm" role="document">
			<div class="modal-content">
				<div class="modal-header">

					<h4 class="modal-title" id="myModalLabel">提示</h4>
				</div>
				<div class="modal-body">
					<p id="preview_file"></p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">否</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal"
						onclick="previewFile(this)">是</button>
				</div>
			</div>
		</div>
	</div>

	<div class="modal fade" id="click_remind" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel-person_remind">
		<div class="modal-dialog modal-sm" role="document">
			<div class="modal-content">
				<div class="modal-header">

					<h4 class="modal-title" id="myModalLabel-person_remind">提示</h4>
				</div>
				<div class="modal-body">
					<p id="remind_file"></p>
				</div>
				<div class="modal-footer">

					<button type="button" class="btn btn-primary " data-dismiss="modal">确认</button>
				</div>
			</div>
		</div>
	</div>

	<style type="text/css">
th.btn-primary {
	width: 116px;
}
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

#username1, #password1 {
	font-size: 15px;
}

.footer {
	position: absolute;
	top: 94%;
	height: 6%;
	background-color: #e1e1e1;
	width: 100%;
	text-align: center;
	min-width: 1190px;
}

.footer span.footerText {
	font-size: 14px;
	margin-top: 0.5%;
	color: #606060;
	display: inline-block;
}
</style>
	<%--<div class="footer"> <span class="footerText">Copyright © 1956-2016 电子科技大学</span> </div>--%>
</body>
</html>
