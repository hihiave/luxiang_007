<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <%--<meta http-equiv="X-UA-Compatible" content="IE=8" />--%>
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <title><tiles:insertAttribute name="title" /></title>
</head>
<body>
<tiles:insertAttribute name="header"></tiles:insertAttribute>
<tiles:insertAttribute name="content"></tiles:insertAttribute>
<tiles:insertAttribute name="footer" />
</body>
</html>