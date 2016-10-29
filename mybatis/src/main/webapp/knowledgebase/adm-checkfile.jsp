<%--<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>--%>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%
        if(session.getAttribute("username")!=null){
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
<script src="/mybatis/knowledgebase/js/adm_checkfile.js"></script>

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

</head>
<body>

<%--<sql:setDataSource var="snapshot" driver="com.mysql.jdbc.Driver"--%>
                   <%--url="jdbc:mysql://123.207.84.55:3306/knowledge?useUnicode=true&characterEncoding=utf-8"--%>
                   <%--user="root"  password="luxiang"/>--%>
<%--<sql:query dataSource="${snapshot}" var="result">--%>
    <%--SELECT * from UserInfo where user_check=1;--%>
<%--</sql:query>--%>

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
					<div class="col-md-2" style=" border-radius:10px;width:170px;font-size:16px;border-right:1px solid #eee">
                    <div class="panel-group" id="accordion">
                        <div class="panel panel-info" style="border-color: #eeeeee;background-color: #f9f9f9;">
                            <div class="panel-heading">
                                <h4 class="panel-title"><a href="#person-center" data-toggle="collapse"
                                                           data-parent="accordion">个人中心<span class="caret" style="float:right;margin-top: 7px;margin-right: -11px;"></span></a></h4>
                            </div>
                            <div id="person-center" class="panel-collapse collapse in">
                                <div class="panel-body" style="padding: 6px 15px;">
                                    <ul class="nav nav-pills nav-stacked" style="margin-left: -15px;margin-right: -15px;margin-bottom: 0px;font-size: inherit;">
                                        <li ><a href="/mybatis/knowledgebase/adm-personal.jsp">个人信息</a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <div class="panel panel-info" style="border-color: #eeeeee;background-color: #f9f9f9;">
                            <div class="panel-heading">
                                <h4 class="panel-title"><a href="#user-manage" data-toggle="collapse"
                                                           data-parent="accordion">用户管理<span class="caret" style="float:right;margin-top: 7px;margin-right: -11px;"></span></a></h4>
                            </div>
                            <div id="user-manage" class="panel-collapse collapse in">
                                <div class="panel-body" style="padding: 6px 15px;">
                                    <ul class="nav nav-pills nav-stacked" style="margin-left: -15px;margin-right: -15px;margin-bottom: 0px;font-size: inherit;">
                                        <li ><a href="/mybatis/knowledgebase/adm-inquire.jsp">用户查询</a></li>
                                        <li ><a href="/mybatis/knowledgebase/adm-check.jsp">用户审核</a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <div class="panel panel-info" style="border-color: #eeeeee;background-color: #f9f9f9;">
                            <div class="panel-heading">
                                <h4 class="panel-title"><a href="#source-manage" data-toggle="collapse"
                                                           data-parent="accordion">资源管理<span class="caret" style="float:right;margin-top: 7px;margin-right: -11px;"></span></a></h4>
                            </div>
                            <div id="source-manage" class="panel-collapse collapse in">
                                <div class="panel-body" style="padding: 6px 15px;">
                                    <ul class="nav nav-pills nav-stacked" style="margin-left: -15px;margin-right: -15px;margin-bottom: 0px;font-size: inherit;">
                                        <li ><a href="/mybatis/knowledgebase/adm-private.jsp">我的上传</a></li>
                                        <li ><a href="/mybatis/knowledgebase/adm-public.jsp">共有文件</a></li>
                                        <li ><a href="/mybatis/knowledgebase/adm-download.jsp">我的下载</a></li>
                                        <li ><a href="/mybatis/knowledgebase/adm-upload.jsp">文件上传</a></li>
                                        <li ><a href="/mybatis/knowledgebase/adm-category.jsp">类别管理</a></li>
                                        <li class="active"><a href="/mybatis/knowledgebase/adm-checkfile.jsp">资源审核</a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <div class="panel panel-info" style="border-color: #eeeeee;background-color: #f9f9f9;">
                            <div class="panel-heading">
                                <h4 class="panel-title"><a href="#system-manage" data-toggle="collapse"
                                                           data-parent="accordion">系统管理<span class="caret" style="float:right;margin-top: 7px;margin-right: -11px;"></span></a></h4>
                            </div>
                            <div id="system-manage" class="panel-collapse collapse in">
                                <div class="panel-body" style="padding: 6px 15px;">
                                    <ul class="nav nav-pills nav-stacked" style="margin-left: -15px;margin-right: -15px;margin-bottom: 0px;font-size: inherit;">
                                        <li ><a href="##">数据还原</a></li>
                                        <li ><a href="##">数据备份</a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>

                    </div>

 				</div>
			 	<div class="col-xs-8" style="margin-left:50px;">
			 		
			 		
						<div class="panel panel-primary" style="height:625px;">
			 			<div class="panel-heading">
			 				<h3 class="panel-title">资源审核</h3>	
			 			</div>
			 			<div class="panel-body" style="padding-top:0px;">
			 				<table class="table table-striped">
			 					<thead>
			 						<tr>
			 							<th style="padding-left: 28px;border-bottom-width: 2px;padding-bottom: 4px;width: 78px;"><label class="checkbox"><input type="checkbox" id="selAll" onclick="selectAll()">全选 </label></th>
			 							<th style="padding-bottom:15px;">资源名</th>
			 							<th style="padding-bottom:15px;">上传者</th>
			 							<th style="width:116px;"><button class="btn btn-primary" data-toggle="modal" onclick="check_all_pick(this)">通过</button></th>
			 							<th style="width:116px;"><button class="btn btn-primary" data-toggle="modal" data-target="#jujue">拒绝</button></th>
			 						</tr>
			 					</thead>
			 					<tbody id="file_result">
			 						<!-- <tr>
			 							<td style="padding-top:15px;"><label><input type="checkbox" name="checkAll"></label></td>
			 							<td style="padding-top:15px;">第八次人名代表大会</td>
			 							<td style="padding-top:15px;padding-left:15px;">张三</td>
			 							<td><button class="btn btn-primary">通过</button></td>
			 							<td><button class="btn btn-primary">拒绝</button></td>
			 						</tr>
			 						<tr>
			 							<td style="padding-top:15px;"><label><input type="checkbox" name="checkAll"></label></td>
			 							<td style="padding-top:15px;">G20峰会</td>
			 							<td style="padding-top:15px;padding-left:15px;">李四</td>
			 							<td><button class="btn btn-primary">通过</button></td>
			 							<td><button class="btn btn-primary">拒绝</button></td>
			 						</tr>
			 						<tr>
			 							<td style="padding-top:15px;"><label><input type="checkbox" name="checkAll"></label></td>
			 							<td style="padding-top:15px;">第二十八届全国人民代表大会</td>
			 							<td style="padding-top:15px;padding-left:15px;">王五</td>
			 							<td><button class="btn btn-primary">通过</button></td>
			 							<td><button class="btn btn-primary">拒绝</button></td>
			 						</tr> -->

			 					</tbody>
			 				</table>
			 			</div>
					</div>
				</div>
			
</div>
            </div>
</div>


<div class="modal fade" id="pass" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog modal-sm" role="document">
    <div class="modal-content">
      <div class="modal-header">
       
        <h4 class="modal-title" id="myModalLabel">提示</h4>
      </div>
      <div class="modal-body">
		<p id="file_remind"></p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
        <button type="button" class="btn btn-primary" data-dismiss="modal" id="file_click" onclick="pass_file_ok()">确认</button>
      </div>
    </div>
  </div>
</div>	
</div>



<div class="modal fade" id="jujue" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog modal-sm" role="document">
    <div class="modal-content">
      <div class="modal-header">
       
        <h4 class="modal-title" id="myModalLabel">提示</h4>
      </div>
      <div class="modal-body">
			
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">返回</button>
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
