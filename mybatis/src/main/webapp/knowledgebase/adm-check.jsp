<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<%
        if(session.getAttribute("username")!=null){
            System.out.println("ssssssssssssssssss"+session.getAttribute("username"));
%>

<%
	}
	else
	{
%>
	<script language="javascript">

		alert("请先登录!");

		top.location='Login.jsp';

	</script>
<%
	}
%>

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

</style>
<script type="text/javascript">
function selectAll() { 
var ckbs=document.getElementsByName("checkAll");
var cka=document.getElementById("selAll");
if (cka.checked==true) {
	for (var i = 0; i < ckbs.length; i++) {
		ckbs[i].checked=true;
	}
}
else{
	for (var i = 0; i < ckbs.length; i++) {
		ckbs[i].checked=false;
	}
}
}


</script>
</head>
<body>

<div style="margin:10px 20px;">
	<button type="button" class="btn btn-md btn-default" style="border:0px;float:right;" data-toggle="tooltip" data-placement="bottom" title="退出登录">
 		<span class="glyphicon glyphicon-off"></span>&nbsp;退出
	</button>
	<button type="button" class="btn btn-md btn-default" style="border:0px;float:right;" data-toggle="tooltip" data-placement="bottom" title="个人中心">
 		<span class="glyphicon glyphicon-user"></span>&nbsp;首页
	</button>
</div>
<div class="container">
			<div class="row" style="text-align:center;margin:40px auto;">
				<img src="/mybatis/knowledgebase/img/img_3.jpg" width="725px" height="80px">
			</div>
			<div class="row" style="margin:0px">
				<div class="col-md-3" style="width:283px;margin-right:16px;"> 
				<img src="/mybatis/knowledgebase/img/zhishiku_2.png" style="margin-left:30px;">
				</div>
				<div class="col-md-9" style="margin-top:16px;">
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
				<div class="col-md-2" style=" border-radius:10px;width:170px;font-size:16px;border-right:1px solid #eee">
                    <ul class="nav nav-pills nav-stacked" >
                        <li class="active"><a href="/mybatis/knowledgebase/adm-personal.jsp">个人中心</a></li>

                    </ul>
                    <ul class="nav nav-pills nav-stacked">
                        <li class="active"><a href="##">用户管理</a></li>
                        <li><a href="/mybatis/knowledgebase/adm-inquire.jsp">用户查询</a></li>
                        <li><a href="/mybatis/knowledgebase/adm-check.jsp">用户审核</a></li>
                    </ul>
                    <ul class="nav nav-pills nav-stacked">
                        <li class="active"><a href="##">资源管理</a></li>
                        <li><a href="/mybatis/knowledgebase/adm-private.jsp">私有文件</a></li>
                        <li><a href="/mybatis/knowledgebase/adm-public.jsp">公有文件</a></li>
                        <li><a href="/mybatis/knowledgebase/adm-download.jsp">我的下载</a></li>
                        <li><a href="/mybatis/knowledgebase/adm-upload.jsp">文件上传</a></li>
                        <li><a href="##">类别管理</a></li>
                        <li><a href="##">资源审核</a></li>
                    </ul>
                    <ul class="nav nav-pills nav-stacked">
                        <li class="active"><a href="##">系统管理</a></li>
                        <li><a href="##">数据还原</a></li>
                        <li><a href="##">数据备份</a></li>
                    </ul>

 				</div>
			 	<div class="col-md-8" style="margin-left:50px;">
                    <div class="panel panel-primary" style="height:625px;">
                        <div class="panel-heading">
                            <h3 class="panel-title">用户审核</h3>
                        </div>
                        <div class="panel-body" style="padding-top:0px;">
                            <table class="table table-striped">
                                <thead>
                                <tr>
                                    <th style="padding-left: 28px;border-bottom-width: 2px;padding-bottom: 4px;width: 78px;"><label class="checkbox"><input type="checkbox" id="selAll" onclick="selectAll()">全选 </label></th>
                                    <th style="padding-bottom:15px;">用户名</th>
                                    <th style="padding-bottom:15px;">审核状态</th>
                                    <th style="width:116px;"><button class="btn btn-primary" data-toggle="modal" data-target="#tongguo">通过</button></th>
                                    <th style="width:116px;"><button class="btn btn-primary" data-toggle="modal" data-target="#jujue">拒绝</button></th>
                                </tr>
                                </thead>
                                <tbody id="list">
                                <tr>
                                    <td style="padding-top:15px;"><label><input type="checkbox" name="checkAll"></label></td>
                                    <td style="padding-top:15px;">哈利路亚</td>
                                    <td style="padding-top:15px;padding-left:15px;">待审核</td>
                                    <td><button class="btn btn-primary">通过</button></td>
                                    <td><button class="btn btn-primary">拒绝</button></td>
                                </tr>
                                <tr>
                                    <td style="padding-top:15px;"><label><input type="checkbox" name="checkAll"></label></td>
                                    <td style="padding-top:15px;">哈利路亚</td>
                                    <td style="padding-top:15px;padding-left:15px;">待审核</td>
                                    <td><button class="btn btn-primary">通过</button></td>
                                    <td><button class="btn btn-primary">拒绝</button></td>
                                </tr>
                                <tr>
                                    <td style="padding-top:15px;"><label><input type="checkbox" name="checkAll"></label></td>
                                    <td style="padding-top:15px;">哈利路亚</td>
                                    <td style="padding-top:15px;padding-left:15px;">待审核</td>
                                    <td><button class="btn btn-primary">通过</button></td>
                                    <td><button class="btn btn-primary">拒绝</button></td>
                                </tr>

                                </tbody>
                            </table>
                        </div>
                    </div>
				</div>
                <div class="modal fade" id="changepsw" tabindex="-1" role="dialog" aria-labelledby="myModalLabel-person">
                    <div class="modal-dialog modal-sm" role="document">
                        <div class="modal-content">
                            <div class="modal-header">

                                <h4 class="modal-title" id="myModalLabel-person">修改密码</h4>
                            </div>
                            <div class="modal-body">
                                <ul style="padding-left:0px;">
                                    <li><input type="password" placeholder="请输入原密码" class="form-control" id="oldpsw"></li>
                                    <div id="spanusername" style="margin-left:6px;margin-bottom:10px;height:6px;"></div>
                                    <li><input type="password"  id="password1" class="form-control" placeholder="请输入新密码"></li>
                                    <div id="spanpsw" style="margin-left:6px;margin-bottom:10px;height:6px;"></div>
                                    <li><input type="password"  id="password2" class="form-control"  placeholder="请再次输入新密码"></li>
                                    <div id="spanrepsw" style="margin-left:6px;margin-bottom:10px;height:6px;"></div>
                                </ul>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                                <button type="button" class="btn btn-primary " id="btnclick" data-dismiss="">确认</button>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
    </div>
<style type="text/css">
th.btn-primary{
	width: 116px;
}
</style>



<style type="text/css">
	.modal{margin-top: 20%;}
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
