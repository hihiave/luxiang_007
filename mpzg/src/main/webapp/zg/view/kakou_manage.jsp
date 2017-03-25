<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>卡口管理</title>
    <link rel="stylesheet" type="text/css"
          href="<%=basePath%>zg/css/bootstrap.css">
    <link rel="stylesheet" href="<%=basePath%>zg/css/css.css" />
    <link rel="stylesheet" type="text/css"
          href="<%=basePath%>zg/css/font-awesome.min.css">
    <%-- <link rel="stylesheet" type="text/css"
        href="<%=basePath%>zg/css/theme.css"> --%>
    <link rel="stylesheet" type="text/css"
          href="<%=basePath%>zg/css/premium.css">
    <link rel="stylesheet" href="<%=basePath%>zg/css/metisfolder.css">
    <link rel="stylesheet" type="text/css"
          href="<%=basePath%>zg/css/default.css">



    <script type="text/javascript"
            src="<%=basePath%>zg/js/jquery-1.11.1.min.js"></script>
    <%-- <script type="text/javascript"
        src="<%=basePath%>zg/js/laydate/laydate.js"></script> --%>
    <%-- <script src="<%=basePath%>zg/js/jquery-1.11.1.min.js"type="text/javascript"></script>
    <script src="<%=basePath%>zg/js/jquery.knob.js" type="text/javascript"></script> --%>
    <%-- <script src="<%=basePath %>zg/js/bootstrap.js"></script> --%>
    <script src="<%=basePath%>zg/js/bootstrap.v3.5.min.js"></script>
    <script src="<%=basePath%>zg/js/jquery-form.js"></script>
    <script src="<%=basePath%>zg/js/mustache.min.js"></script>
    <script src="<%=basePath%>zg/js/common.js"></script>
    <script src="<%=basePath%>zg/js/login.js"></script>
    <script src="<%=basePath%>zg/js/index.js"></script>
    <script src="<%=basePath%>zg/js/user-info.js"></script>
    <script src="<%=basePath%>zg/js/monitoring-history.js"></script>
    <script src="<%=basePath%>zg/js/user-manage.js"></script>

    <script src="<%=basePath%>zg/js/kakou-manage.js"></script>



    <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

    <!-- Le fav and touch icons -->

    <!--[if lt IE 7 ]> <body class="ie ie6"> <![endif]-->
    <!--[if IE 7 ]> <body class="ie ie7 "> <![endif]-->
    <!--[if IE 8 ]> <body class="ie ie8 "> <![endif]-->
    <!--[if IE 9 ]> <body class="ie ie9 "> <![endif]-->
    <!--[if (gt IE 9)|!(IE)]><!-->

    <!--<![endif]-->

</head>
<body>

<jsp:include page="../templates/header.jsp"></jsp:include>
<div id="middle">
    <jsp:include page="../view/menu_backend_manage.jsp"></jsp:include>

    <div class="right" id="mainFrame">
        <script>
            $(function(){
                var data={page_now:1};
                var src = getBasePath() + "zgmp/getAllImageInfo";
                sendAjaxRequest(src,data,changeKakouManagePage,error)
            })
            function getUserManage() {
                window.location.href = getBasePath() + "zg/view/user_manage.jsp"
            }
        </script>
        <div class="right_cont">
            <div class="title_right">
                <span style="cursor:pointer;" onclick="getUserManage()">用户管理</span>
                <strong>卡口管理</strong>
            </div>
            <div class="main-content">
                <div>
                    <table class="table table-striped table-hover">
                        <thead>
                        <tr>
                            <th>卡口名称</th>
                            <th>监控日期</th>
                            <th>卡口状态</th>
                            <th>卡口描述</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody id="kakou_tbody">
                        <tr >

                        </tr>
                        </tbody>
                    </table>
                    <div class="row-fluid">
                        <div id="kakou_nums_tips" class="col-sm-4" style="margin-top: 15px;">
                            <p>显示第 0 到第0条记录，共 2 条记录</p>
                        </div>
                        <div class="col-sm-8">
                            <div style="float: right;" >
                                <ul class="pagination" id="kakou_pagination">

                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/template" id="kakou-template">
    <tr>
        <td>{{imageKakou}}</td>
        <td>{{imageDate}}</td>
        <td>{{imageStatus}}</td>
        <td>{{imageDisc}}</td>
        <td>
            <button class="btn btn-primary" btnid="{{imageId}}" onclick="">启用</button>
            <button class="btn btn-primary" btnid="{{imageId}}" onclick="">禁用</button>
        </td>
    </tr>
</script>
</html>