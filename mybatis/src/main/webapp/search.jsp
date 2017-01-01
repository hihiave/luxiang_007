<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv=Content-Type content="text/html;charset=UTF-8">
<title>政府知识库高级检索搜索</title>
<link href="css/souba.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
	function check() {
		var val = document.forms[0].queryStr.value.trim();
		if (val == "") {
			return false;
		} else {
			document.forms[0].submit();
		}
	}
</script>
</head>
<body>
	<br><br><br><br><br><br>
	<center>
		<img src="images/3.png" width=180 height=84 usemap="#mp" id=lg>
		<br> <br> <br> <br>
		<form action="searchIndex.do">
			<table>
				<tr>
					<td><font><b>选择检索的类型</b></font></td>
					<td>
					<select name="searchType">
							<option value="accurate" selected="selected">精确
							<option value="fuzzy">模糊
							<option value="Prefix">前缀
					</select>
					<input type="text" name="queryStr" size="42" maxlength="100" />
					<input type="button" value="搜索" onclick="check()" />
					</td>
				</tr>
				<tr>
				</tr>
				<tr>
					<td><font><b>选择每页显示的文档数</b></font></td>
					<td>
					<select name="pageSize">
							<option value="5" selected="selected">每页显示5条
							<option value="3">每页显示3条
							<option value="10">每页显示10条
					</select>
					</td>
				</tr>
				<tr>
				</tr>
				<tr>
					<td><font><b>检索结果数目设置</b></font></td>
					<td>
					<select name="topNum">
							<option value="20" selected="selected">处理前20条
							<option value="50">处理前50条
							<option value="100">处理前100条
					</select>
					</td>
				</tr>
				<tr>
				</tr>
				<tr>
					<td><font><b>选择检索的文档类型</b></font></td>
					<td>
					<select name="filetype">
							<option value="all" selected="selected">全部文档
							<option value="doc">Word文档
							<option value="pdf">PDF文档
					</select>
					</td>
				</tr>
			</table>
		</form>

		<p id="km"></p>
		<p style="height: 60px">
		<table cellpadding=0 cellspacing=0 id=lk>
			<tr>
				<td></td>
			</tr>
		</table>
		<p style="height: 30px">
			<a href="#"><br> </a>
		</p>
		<p style="height: 14px"></p>
		<br> <br> <br> <br> <br> <br> <br>
		<br>
		<p id="b">
			<span>Copyright &copy; 1956 - 2016&nbsp; All Rights Reserved
				电子科技大学</span>
		</p>
	</center>
</body>
</html>
