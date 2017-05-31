<%--
  Created by IntelliJ IDEA.
  User: 95
  Date: 2016/11/15
  Time: 11:34
  To change this template use File | Settings | File Templates.
--%>

<%@ page language="java" pageEncoding="utf-8"%>
<!-- <div class="row" style="text-align:center;margin:40px auto;">
    <img src="/mybatis/knowledgebase/img/img_3.jpg" width="725px" height="80px">
</div>
<div class="row" style="margin:0px">
    <div class="col-xs-3" style="width:283px;margin-right:16px;">
        <img src="/mybatis/knowledgebase/img/zhishiku_2.png" style="margin-left:30px;">
    </div>
    <div class="col-xs-9" style="margin-top:16px;">
        <div style="float:left;margin-right:0px;">
            <select class="form-control" onchange="value" id="category_select" style="width: 100px;">
                <option value='类别' >类别</option>
            </select>
        </div>
        <div style="float:left;margin-right:0px;">
            <select class="form-control" onchange="value" id="key_select" style="width: 100px;">
                <option value='fullText' selected>全文</option>
                <option value='title'>标题</option>
                <option value='author'>作者</option>
                <option value='keyword'>关键字</option>
            </select>
        </div>
        <div class="input-group input-group-md" style="float:left;">
            <input type="text" class="form-control" aria-describedby="sizing-addon1"  style="width:521px;"
                   id="file_search_input" onfocus="myfunction()" oninput="myfunction()">
            <button class="btn btn-primary" style="width:78px;margin-left:5px;" id="file_search_sub"
                    onclick="send_search_info()">搜&nbsp;索
            </button>
        </div>
    </div>
</div> -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="/mybatis/knowledgebase/css/buttons.css"> 
<c:set value="0" var="is_manager"/>
<c:set value="0" var="is_worker"/>
<c:forEach items="${userrole}" var="role">
    <c:if test="${role == '管理员'}">
        <c:set value="1" var="is_manager"/>
    </c:if>
    <c:if test="${role == '普通用户'}">
        <c:set value="1" var="is_worker"/>
    </c:if>
</c:forEach>

<div class="pub_top">
<input type="hidden" id="userid" value="<%=session.getAttribute("userid")%>">
<input type="hidden" id="username" value="<%=session.getAttribute("username")%>">
<input type="hidden" id="admin" value="<%=session.getAttribute("adminid")%>">
	<div class="col-xs-2 top-img">
		<img src="/mybatis/knowledgebase/img/3.png">
	</div>
	<div class="col-xs-10" style="margin-top: 0.74%;padding:0 1% 0 1.94%;">
		<div style="float: left; margin-right: 0px;">
			<select class="form-control" onchange="get_child_category_selected_public(this)" id="category_select"
				style="width: 95px;padding:6px;">
				<option value='类别'>类别</option>
			</select>
		</div>
        <div style="float: left; margin-right: 0px;">
			<select class="form-control" onchange="value" id="second_category"
				style="width: 95px;padding:6px;">
				<option value=''>请选择</option>
			</select>
		</div>
		<div style="float: left; margin-right: 0px;">
			<select class="form-control" onchange="value" id="key_select"
				style="width: 80px;padding:6px;">
				<option value='fullText' selected>全文</option>
				<option value='title'>标题</option>
				<option value='author'>作者</option>
				<option value='keyword'>关键字</option>
			</select>
		</div>
		<div class="input-group input-group-md" style="float: left;">
			<input type="text" class="form-control"
				aria-describedby="sizing-addon1" style="width:350px;"
				id="file_search_input" onfocus="myfunction()" oninput="myfunction()" onkeydown="keysearch();">
			<!-- <button class="btn btn-default"
				style="width: 78px; margin-left: 5px; background: #808080; color: #FFF"
				id="file_search_sub" onclick="send_search_info()">搜&nbsp;索
			</button> -->
			<button class="button button-action button-pill button-small"
				style="width: 78px;height:33px; margin-left: 5px; background: #808080;padding:0; color: #FFF"
				id="file_search_sub" onclick="send_search_info()" >搜&nbsp;索
			</button>
			<a href="/mybatis/search.jsp" target="black">
			<button class="button button-primary button-pill button-small"
				style="width: 78px;height:33px; margin-left: 5px; padding:0; color: #fff">高级搜索</button>
			</a>
			<!-- <input type="button"><a href="/mybatis/search.jsp" target="black">高级搜索</a> -->
			<!-- id="file_search_sub" onclick="send_search_info()" -->
		</div>
		
		<div style="float: right;">
		<c:if test="${is_worker == 1}">
			<div class="upload">
				<a href="/mybatis/knowledgebase/adm-upload.jsp"><span
					class="glyphicon glyphicon-open"></span>上传</a>
			</div>
			</c:if>
			<div class="index">
				<a href="/mybatis/knowledgebase/adm-personal.jsp">主页</a>
			</div>
			<div class="index">
				<a href="#" onclick="logout()">退出</a>
			</div>
		</div>
		<div class="clear"></div>
	</div>
	<div style="float:right;padding:0.4% 1.1% 0 0;
	font-size:13px;font-weight:bold;">
		<span id="welcomeword">欢迎你，<%=session.getAttribute("username")%>！</span>
	</div>
	<div style="clear: both;"></div>
</div>
