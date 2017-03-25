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
    <title>卡口图片</title>
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
    <script src="<%=basePath%>zg/js/Image-manage.js"></script>

    <script src="<%=basePath%>zg/js/sockjs-1.0.0.min.js"></script>
    <script src="<%=basePath%>zg/js/stomp.min.js"></script>



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
<input type="hidden" id="user_id_session" value="${UserInfo.userId}"/>
<jsp:include page="../templates/header.jsp" flush="true"></jsp:include>
<div id="middle">
    <jsp:include page="../view/menu_monitoring.jsp" flush="true"></jsp:include>
    <div class="right" id="mainFrame">

        <div class="right_cont">
            <div class="title_right">
                <span style="cursor: pointer;" onclick="checkMonitoring()">实时监控</span>
                <strong>卡口图片</strong>
            </div>
            <div class="content">
                <nav class="navbar navbar-default" role="navigation">
                    <div class="form-inline center-block">
                        <button class="btn btn-primary" onclick="carBrandCheck()" type="button" style="margin-top: 10px;">车标品牌检测</button>
                        <button class="btn btn-primary" onclick="carColorCheck()" type="button" style="margin-top: 10px;">车身颜色检测</button>
                        <button class="btn btn-primary" onclick="carNumberCheck()" type="button" style="margin-top: 10px;">车牌检测</button>
                    </div>
                </nav>
                <div class="row-fluid">
                    <div class="col-lg-3">
                        <div id="image-file-dir-lvl-1">

                        </div>
                        <div id="image-file-dir-content" style="max-height: 600px;overflow-y: auto;">

                        </div>
                    </div>
                    <div class="col-lg-9">
                        <div id="image_select" style="display: none;">
                            <h3>选中图片</h3>
                        </div>
                        <div id="image_file" style="max-height: 600px;overflow-y: auto;">

                        </div>
                    </div>
                </div>




            </div>
        </div>
    </div>
</div>
</body>
<script type="text/template" id="image-file-info-template">
    <tr>
        <td>{{imageKakou}}</td>
        <td>{{imageDate}}</td>
        <td>
            <button class="btn btn-primary" btnid="{{imageId}}" onclick="getImageList(this)">检测</button>
        </td>
    </tr>
</script>

</html>
