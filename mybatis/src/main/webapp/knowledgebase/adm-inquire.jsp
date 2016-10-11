<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%-- <%
        if(session.getAttribute("username")!=null){
%>
  
<%
	}
	else
	{
%>
	<script language="javascript">         

		alert("请先登录!");         

		top.location='/mybatis/knowledgebase/Login.jsp';  

	</script>
<%
	}
%> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 
<html> 
<head>
<link rel="stylesheet" type="text/css" href="/mybatis/knowledgebase/css/bootstrap.min.css">
<script src="/mybatis/knowledgebase/js/jquery.min.js"></script>
<script src="/mybatis/knowledgebase/js/bootstrap.min.js"></script>
<script src="/mybatis/knowledgebase/js/html5shiv.min.js"></script>
<script src="/mybatis/knowledgebase/js/respond.min.js"></script>

<style type="text/css">
.nav{
		margin-bottom: 5px;
		text-align:center;
	}
.hr{
	margin: 0 auto;
}
.container{
width: 1320px !important;
}
.tbody th{
padding-bottom:3px;
padding-top:3px;
width:116px;
}
.table thead tr { 
display:block; 
} 
.table tbody { 
display: block; 
height: 500px; 
overflow: auto; 
} 
.table th { 
width:104px; 
} 
.table td { 
width:104px; 
}
</style>
<!--  <script type="text/javascript">
function deleteuser(num){
	if(confirm("确认删除该用户吗?")){
		return true;
	}
	else
		return false;
}

function resetpsw(num){
	if(confirm("确认重置用户密码吗?")){
		return true;
	}
	else
		return false;
}
</script>-->
<script type="text/javascript">
	window.onload=function(){
		
		var btnobj=document.getElementById("btnclick");
		var username1obj=document.getElementById("username1");
		var spanusernameobj=document.getElementById("spanusername");
		btnobj.onclick=checkname;
		spanusernameobj.innerHTML="";
		function checkname(){
			if (username1obj.value.length==0) {
				var msg="<font color='red' size='1px'>用户名不能为空!</font>";
				spanusernameobj.innerHTML=msg;
				return false;
			}
			else{
				return true;
			}
			
		}
	
	}
</script>
<!-- <script type="text/javascript">
var queren=document.getElementById("queren");
queren.addEventListener(type, listener, useCapture)

</script>
-->
</head>
<body>

<div style="margin:10px 20px;">
	<button type="button" class="btn btn-md btn-default" onclick="return confirm('确认退出吗?');"
	style="border:0px;float:right;" data-toggle="tooltip" data-placement="bottom" title="退出登录">
 		<a href="/mybatis/Quitservlet"><span class="glyphicon glyphicon-off"></span>&nbsp;退出</a>
	</button>
	<button type="button" class="btn btn-md btn-default" style="border:0px;float:right;" data-toggle="tooltip" data-placement="bottom" title="个人中心">
 		<a href="/mybatis/knowledgebase/adm-personal.jsp"><span class="glyphicon glyphicon-user"></span>&nbsp;首页</a>
	</button>
</div>
<div class="container">
			<div class="row" style="text-align:center;margin:40px auto;">
				<img src="/mybatis/knowledgebase/img/img_3.jpg" width="725px" height="220px">
			</div>
			<div class="row" style="margin:0px ">
				<div class="col-md-3" style="width:283px;margin-right:16px;"> 
				<img src="/mybatis/knowledgebase/img/zhishiku_2.png" style="margin-left:30px;">
				</div>
				<div class="col-md-9 " style="margin-top:16px;">
				<div  style="float:left;margin-right:0px;">
		  			<select  class="form-control"  onchange="value">
					<option value='' selected>类别&nbsp;&nbsp;&nbsp;</option>
					<option value=''>专利</option>
					<option value=''>论文</option>
					<option value=''>报告</option>
					</select>
				</div> 
				<div  style="float:left;margin-right:0px;">
		  			<select  class="form-control" onchange="value">
					<option value='' selected>全文</option>
					<option value=''>标题</option>
					<option value=''>作者</option>
					<option value=''>关键字</option>
					</select>
				</div> 
				<div class="input-group input-group-md" style="float:left;">
				<input type="text" class="form-control"  aria-describedby="sizing-addon1" style="width:521px;">
				<button class="btn btn-primary" style="width:78px;margin-left:5px;">搜&nbsp;索</button>
				</div>
				</div>
			</div>
			<hr>
			<div class="row" style="margin-left:80px;">
				<div class="col-xs-2" style=" border-radius:10px;width:170px;font-size:16px;border-right:1px solid #eee">
					<ul class="nav nav-pills nav-stacked" >
				     <li class="active"><a href="/mybatis/knowledgebase/adm-personal.jsp">个人中心</a></li>
		
		 			</ul>
		 			<ul class="nav nav-pills nav-stacked">
				     <li class="active"><a href="##">用户管理</a></li>
				 	<li><a href="/mybatis/Listservlet">用户查询</a></li>
				 	<li><a href="/mybatis/knowledgebase/adm-check.jsp">用户审核</a></li>
		 			</ul>
		 			<ul class="nav nav-pills nav-stacked">
				     <li class="active"><a href="##">资源管理</a></li>
				     <li><a href="/mybatis/knowledgebase/adm-private.jsp">私有文件</a></li>
				     <li><a href="/mybatis/knowledgebase/adm-public.jsp">公有文件</a></li>
				 	<li><a href="/mybatis/knowledgebase/adm-download.jsp">我的下载</a></li>
				 	<li><a href="/mybatis/knowledgebase/adm-upload.jsp">文件上传</a></li>
				 	<li><a href="/mybatis/knowledgebase/adm-category.jsp">类别管理</a></li>
				 	<li><a href="/mybatis/knowledgebase/adm-checkr.jsp">资源审核</a></li>
		 			</ul>
		 			<ul class="nav nav-pills nav-stacked">
				     <li class="active"><a href="##">系统管理</a></li>
				 	<li><a href="##">数据还原</a></li>
				 	<li><a href="##">数据备份</a></li>
		 			</ul>
 				</div>
			 	<div class="col-xs-8" style="margin-left:50px;">
			 		
			 		<div class="panel panel-primary" style="height:625px;">
						<div class="panel-heading" >
							<h3 class="panel-title">用户查询</h3>
						</div>
						<div class="panel-body" >
							<table class="table table-striped">
							<thead>
							<tr>
							<th style="font-size:16px;padding-bottom:10px;">编号</th>
							<th style="font-size:16px;padding-bottom:10px;">用户名</th>
							<th><input type="text" style="height:34px;width:273px;"></th>
							<th><button class="btn btn-info">搜索用户</button></th>
							<th><button class="btn btn-info" data-toggle="modal" 
							data-target="#tianjia">添加用户</button></th>
							</tr>
							</thead>
							
							<tbody>
							<c:forEach var="user" items="${UserInfo}">
							<tr>
						
							
							<td>${user.userName}</td>
							<td style="width:289px;"></td>
							<th><a href="/mybatis/Deleteuser?username=${user.userName}">
							<button class="btn btn-primary"
							 onclick="return confirm('确认删除该用户吗?');">删除用户</button></a></th>
							<th><a href="/mybatis/Resetpsw?username=${user.userName}">
							<button class="btn btn-primary"  id="${user.userName}"
							onclick="return confirm('确认重置用户密码吗?');">重置密码</button></a></th>
							
							
							</tr>
							</c:forEach>
							
							
							</tbody>
						
							</table>
						</div>	
				</div>
			
</div>





<!-- 
data-toggle="modal" data-target="#shanchu"
data-toggle="modal" data-target="#reset"
 <div class="modal fade" id="shanchu" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog modal-sm" role="document">
    <div class="modal-content">
      <div class="modal-header">
       
        <h4 class="modal-title" id="myModalLabel">提示</h4>
      </div>
      <div class="modal-body">
        	确认删除该用户吗?
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
        <input type="submit" class="btn btn-primary " id="" value="确认">
      </div>
    </div>
  </div>
</div>	
</div>


<div class="modal fade" id="reset" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog modal-sm" role="document">
    <div class="modal-content">
      <div class="modal-header">
       
        <h4 class="modal-title" id="myModalLabel">提示</h4>
      </div>
      <div class="modal-body">
        是否重置该用户密码?
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">否</button>
        <input type="submit" class="btn btn-primary " id="" value="是">
      </div>
    </div>
  </div>
</div>	
</div>


-->
<div class="modal fade" id="tianjia" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog modal-sm" role="document">
    <div class="modal-content">
      <div class="modal-header">
       
        <h4 class="modal-title" id="myModalLabel">输入用户信息</h4>
      </div>
      <div class="modal-body">
        <ul style="padding-left:0px;">
        	<li><input type="text" placeholder="请输入用户名" class="form-control" id="username1"></li>
        	<div id="spanusername" style="margin-left:6px;margin-bottom:10px;height:6px;"></div>
        	<li><input type="password"  id="password1" class="form-control" value="" placeholder="初始密码为123456" readonly="true"></li>
        </ul>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
        <input type="submit" class="btn btn-primary" id="btnclick" value="确认">
      </div>
    </div>
  </div>
</div>	
</div>

<style type="text/css">
	.modal{margin-top:20%;}
	.modal-body li{
		list-style-type:none;
		padding:0px 5px;
		border: 1px solid #ffffff;
		padding: 5px 0px;
	}
	#username1,#password1{
		font-size: 15px;

	}
.footer {
	height: 60px;
	background-color: #e1e1e1;
	width:100%;
	text-align: center;
	margin-top: 20px;
	line-height: 60px;

	}
	.footer span.footerText {
		font-size: 14px;
		color: #606060;
		display: inline-block;
	}
</style>
<div class="footer"> <span class="footerText">Copyright © 1956-2016 电子科技大学</span> </div>
</body>
</html>
