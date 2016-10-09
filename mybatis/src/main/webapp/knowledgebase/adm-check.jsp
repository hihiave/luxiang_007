<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<%--<%--%>
        <%--if(session.getAttribute("username")!=null){--%>
<%--%>--%>
  <%----%>
<%--<%--%>
	<%--}--%>
	<%--else--%>
	<%--{--%>
<%--%>--%>
	<%--<script language="javascript">         --%>

		<%--alert("请先登录!");         --%>

		<%--top.location='Login.jsp';  --%>

	<%--</script>--%>
<%--<%--%>
	<%--}--%>
<%--%>--%>

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
$(function(){
    $(".onLiClick").each(function(){
        $(this).click(function(){
            //alert("1");
            var ulid = $(this).attr("ulid");
            //alert("ulid=" + ulid);
            if(ulid != "user-info-ul"){
                //alert("!=");
                $("#user-info-ul").find("li").each(function () {
                    //alert("2");
                    //alert($(this).attr('class'));
                    $(this).removeClass("active");
                });
            }
            if(ulid != "user-manage-ul"){
                //alert("!=");
                $("#user-manage-ul").find("li").each(function () {
                    //alert("3");
                    //alert($(this).attr('class'));
                    $(this).removeClass("active");
                });
            }
            if(ulid != "source-manag-ul"){
                //alert("!=");
                $("#source-manag-ul").find("li").each(function () {
                    //alert("4");
                    //alert($(this).attr('class'));
                    $(this).removeClass("active");
                });
            }
            if(ulid != "system-manage-ul"){
                //alert("!=");
                $("#system-manage-ul").find("li").each(function () {
                    //alert("5");
                    //alert($(this).attr('class'));
                    $(this).removeClass("active");
                });
            }


        });
    });
})

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
				<img src="/mybatis/knowledgebase/img/img_3.jpg" width="725px" height="220px">
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
                    <div class="panel panel-info" >
                        <div class="panel-heading">
                            <h4 class="panel-title"> <a data-toggle="collapse"  data-parent="#accordion" href="#user-info">个人中心</a> </h4>
                        </div>
                        <div id="user-info" class="panel-collapse collapse in ">
                            <div class="panel-body" style="padding: 6px 15px;">
                                <ul id="user-info-ul" class="nav nav-pills nav-stacked"
                                    style="margin-left: -15px;margin-right: -15px;margin-bottom: 0px;font-size: inherit;">
                                    <li class="onLiClick" ulid="user-info-ul"><a href="#person-info-tab" data-toggle="tab" >个人信息</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="panel panel-info" >
                        <div class="panel-heading">
                            <h4 class="panel-title"> <a data-toggle="collapse"  data-parent="#accordion" href="#user-manage">用户管理</a> </h4>
                        </div>
                        <div id="user-manage" class="panel-collapse collapse in ">
                            <div class="panel-body" style="padding: 6px 15px;">
                                <ul id="user-manage-ul" class="nav nav-pills nav-stacked"
                                    style="margin-left: -15px;margin-right: -15px;margin-bottom: 0px;font-size: inherit;">
                                    <li class="onLiClick" ulid="user-manage-ul"><a href="#user-query-tab" data-toggle="tab" >用户查询</a></li>
                                    <li class="onLiClick" ulid="user-manage-ul"><a href="#user-check-tab" data-toggle="tab" >用户审核</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="panel panel-info" >
                        <div class="panel-heading">
                            <h4 class="panel-title"> <a data-toggle="collapse"  data-parent="#accordion" href="#source-manage">资源管理</a> </h4>
                        </div>
                        <div id="source-manage" class="panel-collapse collapse in ">
                            <div class="panel-body" style="padding: 6px 15px;">
                                <ul id="source-manag-ul" class="nav nav-pills nav-stacked"
                                    style="margin-left: -15px;margin-right: -15px;margin-bottom: 0px;font-size: inherit;">
                                    <li class="onLiClick" ulid="source-manag-ul"><a href="#private-file-tab" data-toggle="tab" >私有文件</a></li>
                                    <li class="onLiClick" ulid="source-manag-ul"><a href="#public-file-tab" data-toggle="tab" >公有文件</a></li>
                                    <li class="onLiClick" ulid="source-manag-ul"><a href="#my-download-tab" data-toggle="tab" >我的下载</a></li>
                                    <li class="onLiClick" ulid="source-manag-ul"><a href="#file-update-tab" data-toggle="tab" >文件上传</a></li>
                                    <li class="onLiClick" ulid="source-manag-ul"><a href="#sort-manage-tab" data-toggle="tab" >类别管理</a></li>
                                    <li class="onLiClick" ulid="source-manag-ul"><a href="#source-check-tab" data-toggle="tab" >资源审核</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="panel panel-info" >
                        <div class="panel-heading">
                            <h4 class="panel-title"> <a data-toggle="collapse"  data-parent="#accordion" href="#system-manage">系统管理</a> </h4>
                        </div>
                        <div id="system-manage" class="panel-collapse collapse in ">
                            <div class="panel-body" style="padding: 6px 15px;">
                                <ul id="system-manage-ul" class="nav nav-pills nav-stacked"
                                    style="margin-left: -15px;margin-right: -15px;margin-bottom: 0px;font-size: inherit;">
                                    <li class="onLiClick" ulid="system-manage-ul"><a href="#data-return-tab" data-toggle="tab" >数据还原</a></li>
                                    <li class="onLiClick" ulid="system-manage-ul"><a href="#data-backup-tab" data-toggle="tab" >数据备份</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>

 				</div>
			 	<div class="col-md-8" style="margin-left:50px;">
                    <div id="myTab"  class="tab-content">

                        <%@include file="person_info_tab.jsp"%>
                        <%@include file="user_query_tab.jsp"%>
                        <%@include file="user_check_tab.jsp"%>
                        <%@include file="private_file_tab.jsp"%>
                        <%@include file="public_file_tab.jsp"%>
                        <%@include file="download_tab.jsp"%>
                        <%@include file="upload_tab.jsp"%>
                        <div class="tab-pane fade" id="sort-manage-tab">
                            <h1>XXXbbb</h1>
                        </div>
                        <div class="tab-pane fade" id="source-check-tab">
                            <h1>XXXddd</h1>
                        </div>
                        <div class="tab-pane fade" id="data-return-tab">
                            <h1>XXXnnn</h1>
                        </div>
                        <div class="tab-pane fade" id="data-backup-tab">
                            <h1>XXXqqq</h1>
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
