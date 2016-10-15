<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>文件下载</title>
</head>
<body>

	<form name="form2" method="post"
		action="/mybatis/FileDownloadController/fileDownload.do">
		<input type="text" name="filename" id="filename"> <input
			type="submit" name="submit" value="下载">
	</form>