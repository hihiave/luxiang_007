<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>首页</title>

</head>
<body>

<jsp:include page="zg/templates/head.jsp"></jsp:include>

<jsp:include page="zg/templates/left.jsp"></jsp:include>

<div class="content">
        <div class="header">
            <h1 class="page-title">首页</h1>
        </div>
        <div class="main-content">
            <div class="panel panel-default">
                <a href="#page-stats" class="panel-heading" data-toggle="collapse">欢迎使用</a>
                <div id="page-stats" class="panel-collapse panel-body collapse in">

                    <div class="row">
                        <img src="<%=basePath %>zg/images/map.jpg" style="width:100%; height: 450px;">
                    </div>
                </div>
            </div>
            
            <footer>
            </footer>
        </div>
</div>
<jsp:include page="zg/templates/footer.jsp"></jsp:include>
</body>
</html>
