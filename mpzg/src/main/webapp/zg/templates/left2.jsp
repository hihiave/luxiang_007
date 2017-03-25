<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>导航菜单</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<script language="JavaScript" src="<%=basePath %>zg/js/menu.js"></script>
	<link type="text/css" rel="stylesheet" href="<%=basePath %>zg/css/menu.css" />

</head>
<body style="margin:0">
<div id="Menu">
	<ul id="MenuUl">
		
		
		<li class="level1">
			<div onClick="menuClick(this);" id="MEMU_FUNC20070" class="level1Style">综合行政 </div>
			<ul style="display: none;" id="MEMU_FUNC20070d" class="MenuLevel2">
				<li class="level2">
					<div  id="MEMU_FUNC20021" class="level2Style"> 考勤管理 </div>
				</li>
				<li class="level2">
					<div onClick="subMenuClick(this);" id="MEMU_FUNC20071" class="level2Style">会议管理 </div>
					<ul style="display: none;" id="MEMU_FUNC20071d" class="MenuLevel2">
						<li class="level3Head" >会议申请</li>
						<li class="level33" >暂存会议查询</li>
						<li class="level33" >待我参加会议</li>
						<li class="level33" >我已参加会议</li>
						<li class="level33" >新建会议纪要</li>
						<li class="level33" >会议纪要查询</li>
						<li class="level33" >待开会议查询</li>
						<li class="level33" >已开会议查询</li>
						<li class="level33" >会议室设置</li>
						<li class="level32" >会议类型设置</li>
					</ul>
				</li>
				<li class="level2">
					<div onClick="subMenuClick(this);" id="MEMU_FUNC20072" class="level2Style">车辆管理 </div>
					<ul style="display: none;" id="MEMU_FUNC20072d" class="MenuLevel2">
                <li class="level3Head" >用车申请</li>
                <li class="level33" >派车管理</li>
                <li class="level33" >我的申请记录</li>
                <li class="level33" >车辆状态</li>
                <li class="level33" >车辆档案</li>
                <li class="level32" >私车公用</li>
            </ul>
				</li>
			</ul>
		</li>
		
		
	</ul>
</div>
</body>
</html>