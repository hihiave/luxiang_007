<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	if (session.getAttribute("username") != null) {
		System.out.println("==============登录==");
%>

<%
	} else {
%>
<script language="javascript">
	System.out.println("==============未登录==");
	alert("请先登录!");

	top.location = 'Login.jsp';
</script>
<%
	}
%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ "/";
	System.out.println("ContextPath = " + path);
	System.out.println("basePath    = " + basePath);
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
<script type="text/javascript">
	window.onload = function() {
		var register_time = "${time}";
		var id = "${userid}";
		var time = timeStampFormatDay(register_time * 1000);
		console.log(time);
		//$("#welcomeword").html("${username}");
		$("#show_user_name").html("${username}");
		$("#show_user_role").html("${userrole}");
		$("#show_user_truename").html("${usertruename}");
		$("#show_register_email").html("${email}");
		$("#show_register_time").html(time);

	}
</script>
<link rel="stylesheet" type="text/css"
	href="/mybatis/knowledgebase/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="/mybatis/knowledgebase/css/jquery-ui.min.css">
<link rel="stylesheet" type="text/css"
	href="/mybatis/knowledgebase/css/common.css">
<script src="/mybatis/knowledgebase/js/jquery.min.js"></script>
<script src="/mybatis/knowledgebase/js/bootstrap.min.js"></script>
<script src="/mybatis/knowledgebase/js/html5shiv.min.js"></script>
<script src="/mybatis/knowledgebase/js/respond.min.js"></script>
<script src="/mybatis/knowledgebase/js/common.js"></script>
<script src="/mybatis/knowledgebase/js/public_search.js"></script>
<script src="/mybatis/knowledgebase/js/adm_system.js"></script>
<script src="/mybatis/knowledgebase/js/jquery-ui.min.js"></script>


<style type="text/css">
</style>

</head>
<body>

	<jsp:include page="public-part.jsp" flush="true" />
	<input type="hidden" id="userid" value="<%=session.getAttribute("userid")%>">
	<div class="pub_bottom">
		<div class="col-xs-2 left_nav">
			<ul class="nav nav-menu">
				<li><a href="#user-center" data-toggle="collapse">个人中心<span
						class="glyphicon glyphicon-menu-down"></span></a></li>
				<ul id="user-center" class="panel-collapse collapse in">
					<li><a
						href="/mybatis/knowledgebase/adm-personal.jsp">个人信息</a></li>
					<c:if test="${is_worker == 1}">
						<li><a href="/mybatis/knowledgebase/adm-private.jsp">我的文件</a></li>
						<li><a href="/mybatis/knowledgebase/adm-waitforcheck.jsp">待审文件</a></li>
						<li><a href="/mybatis/knowledgebase/adm-download.jsp">我的下载</a></li>
						<li><a href="/mybatis/knowledgebase/user-category.jsp">我的分类</a></li>
						<li><a href="/mybatis/knowledgebase/adm-draft.jsp">草稿箱<span id="badge3" class="badge"></span></a></li>
					</c:if>
				</ul>
				<c:if test="${is_manager == 1}">
					<li><a href="#user-manage" data-toggle="collapse">用户管理<span
							class="glyphicon glyphicon-menu-down"></span></a></li>
					<ul id="user-manage" class="panel-collapse collapse in">
						<li><a href="/mybatis/knowledgebase/adm-inquire.jsp">用户查询</a></li>
						<li><a href="/mybatis/knowledgebase/adm-check.jsp">用户审核<span id="badge1" class="badge"></span></a></li>
					</ul>
				</c:if>
				<c:if test="${is_worker == 1}">
					<li><a href="#source-center" data-toggle="collapse">资源中心<span
							class="glyphicon glyphicon-menu-down"></span></a></li>
					<ul id="source-center" class="panel-collapse collapse in ">
						<li><a href="/mybatis/knowledgebase/adm-public.jsp">公有文件</a></li>
						<li><a href="/mybatis/knowledgebase/adm-upload.jsp">文件上传</a></li>
					</ul>
				</c:if>

				<c:if test="${is_manager == 1}">
					<li><a href="#source-manager" data-toggle="collapse">资源管理<span
							class="glyphicon glyphicon-menu-down"></span></a></li>
					<ul id="source-manager" class="panel-collapse collapse in ">
						<li><a href="/mybatis/knowledgebase/adm-category.jsp">类别管理</a></li>
						<li><a href="/mybatis/knowledgebase/adm-checkfile.jsp">资源审核<span id="badge2" class="badge"></span></a></li>
					</ul>
				</c:if>

				<c:if test="${is_manager == 1}">
					<li><a href="#system-manage" data-toggle="collapse">系统管理<span
							class="glyphicon glyphicon-menu-down"></span></a></li>
					<ul id="system-manage" class="panel-collapse collapse in">

						<!-- <li><a href="#">数据还原</a></li>
						<li><a href="#">数据备份</a></li> -->
						<li  class="active"><a href="/mybatis/knowledgebase/adm-system.jsp">系统配置</a></li>
					</ul>
				</c:if>
			</ul>
			<div style="clear: both;"></div>
		</div>
		<div class="col-xs-10 r_body">
			<div class="panel panel-info" id="default_panel">
				<div class="panel-heading">
					<h3 class="panel-title">系统配置</h3>
				</div>
				<div class="panel-body">
					<!-- <div >
						<button class="btn btn-info" data-toggle="modal" 					
							data-target="#backup" >数据备份
						</button>
						<span style="display:inline;font-size:16px">&nbsp;按钮功能：备份当前时刻的数据，在数据丢失或损坏时，可以及时恢复。</span>
					</div>
					
					<div style="margin-top:10px;">
						<button class="btn btn-info" data-toggle="modal" 
							data-target="#restore">数据还原
						</button>
						<span style="display:inline;font-size:16px">&nbsp;按钮功能：在数据丢失或损坏时，将数据还原到上一备份的时刻，保证数据的安全性。</span>
					</div> -->
					
					<div style="margin-top:10px;">
						<button class="btn btn-info" data-toggle="modal" 
								data-target="#build">初始化文档索引
						</button>
						<span style="display:inline;font-size:16px">&nbsp;注：创建时，请确保“资源审核”中的文件数量为0。&nbsp;
						按钮功能：删除之前创建的所有索引，并会重新创建当前所有文档索引。此过程较为缓慢，请耐心等待！</span>
					</div>
					
					<div style="margin-top:10px;">
						<button class="btn btn-info" data-toggle="modal" 
							data-target="#build_pdf">创建新文档索引
						</button>
						<span style="display:inline;font-size:16px">&nbsp;注：创建时，请确保“资源审核”中的文件数量为0。&nbsp;
						按钮功能：创建新上传的文档索引，以方便在“高级搜索”中做全文检索。
						此按钮为增量创建文档索引，上一时刻被创建过的索引不会被重复创建。此过程较为缓慢，请耐心等待！</span>
					</div>
					   
					<!-- <div style="margin-top:10px;">
						<button class="btn btn-info" data-toggle="modal" 
							data-target="#build_word">创建Word索引
						</button>
						<span style="display:inline;font-size:16px">&nbsp;注：创建时，请确保“资源审核”中的文件数量为0。&nbsp;
						创建新上传的Word文档索引，以方便在"高级检索"中做全文检索。此按钮为增量创建Word索引，上一时刻被创建过的索引不会被重复创建。</span>
					</div>  -->
				</div>
			</div>
			<jsp:include page="search-result.jsp" flush="true" />
			<div style="clear: both;"></div>
		</div>
	</div>
	
<!-- 创建新文档索引？的提示信息 -->
<div class="modal fade" id="build" tabindex="-1" role="dialog" aria-labelledby="myModalLabel-build">
    <div class="modal-dialog modal-sm" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel-build">提示</h4>
            </div>
            <div class="modal-body">
                <p>是否初始化索引？</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" id="queren" class="btn btn-primary" data-dismiss="modal"
                        onclick="build_ok(this)">确认
                </button>
            </div>
        </div>
    </div>
</div>

<!-- 是否初始化索引？的提示信息 -->
<div class="modal fade" id="build_pdf" tabindex="-1" role="dialog" aria-labelledby="myModalLabel-build_pdf">
    <div class="modal-dialog modal-sm" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel-build_pdf">提示</h4>
            </div>
            <div class="modal-body">
                <p>是否创建新文档索引？</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" id="queren" class="btn btn-primary" data-dismiss="modal"
                        onclick="build_pdf_ok(this)">确认
                </button>
            </div>
        </div>
    </div>
</div>

<!-- 是否建立WORD索引？的提示信息 -->
<!-- <div class="modal fade" id="build_word" tabindex="-1" role="dialog" aria-labelledby="myModalLabel-build_word">
    <div class="modal-dialog modal-sm" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel-build_word">提示</h4>
            </div>
            <div class="modal-body">
                <p>是否建立WORD索引？</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" id="queren" class="btn btn-primary" data-dismiss="modal"
                        onclick="build_word_ok(this)">确认
                </button>
            </div>
        </div>
    </div>
</div>	 -->

<!-- 备份的提示信息 -->
<div class="modal fade" id="backup" tabindex="-1" role="dialog" aria-labelledby="myModalLabel-backup">
    <div class="modal-dialog modal-sm" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel-backup">提示</h4>
            </div>
            <div class="modal-body">
                <p>确定要对系统进行备份吗？</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" id="queren" class="btn btn-primary" data-dismiss="modal"
                        onclick="backup_ok(this)">确认
                </button>
            </div>
        </div>
    </div>
</div>

<!-- 还原的提示信息 -->
<div class="modal fade" id="restore" tabindex="-1" role="dialog" aria-labelledby="myModalLabel-restore">
    <div class="modal-dialog modal-sm" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel-restore">提示</h4>
            </div>
            <div class="modal-body">
                <p>是否还原到上一次的备份记录？</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" id="queren" class="btn btn-primary" data-dismiss="modal"
                        onclick="restore_ok(this)">确认
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="result" tabindex="-1" role="dialog" aria-labelledby="myModalLabel-result">
    <div class="modal-dialog modal-sm" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel-result">提示信息</h4>
            </div>
            <div class="modal-body">
                <p id="add_result"></p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary " data-dismiss="modal">确认</button>
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
