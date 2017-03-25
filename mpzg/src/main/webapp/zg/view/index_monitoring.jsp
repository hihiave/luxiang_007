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
<title>首页</title>
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
    <link href="//vjs.zencdn.net/4.10/video-js.css" rel="stylesheet">



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
    <script src="//vjs.zencdn.net/4.10/video.js"></script>


    <script src="<%=basePath%>zg/js/sockjs-1.0.0.min.js"></script>
    <script src="<%=basePath%>zg/js/stomp.min.js"></script>
    <script src="<%=basePath%>zg/js/websocket.js"></script>




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
					<strong>实时监控</strong>
					<span style="cursor: pointer;" onclick="getImagesPage()">卡口图片</span>
				</div>
                <div class="content" >
                    <div>
                        <button class="btn btn-primary">单视频播放</button>
                        <button class="btn btn-primary">4视频播放</button>
                        <button class="btn btn-primary">9视频播放</button>
                        <button class="btn btn-primary">视频处理</button>
                    </div>
                    <%--<div id="content-test">--%>
                        <%--<input type="text" id="test-file" />--%>
                        <%--<button class="btn btn-primary" onclick="btnclick(1,getfileinfoSuccess)">获取</button>--%>
                    <%--</div>--%>
                    <div style="margin-top: 50px;">
                        <div class="col-md-2" style="overflow: auto;max-height: 600px;">
                            <h6>选择视频</h6><span><button>返回</button></span>
                            <a href="##">xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx</a>
                        </div>
                        <div class="col-md-9 ">
                            <video controls="controls" style="height: 600px;width: 800px;" >
                            </video>
                            <div>
                                <button class="btn btn-primary">添加视频</button>
                            </div>
                        </div>


                        <video src="<%=basePath%>zg/video/test.mp4" controls="controls" style="height: 300px;width: 400px;" ></video>

                    </div>

                </div>

			</div>
		</div>
	</div>

</body>
<script>

    function btnclick(id,_fun){
        var src=getBasePath() + "zgmp/get_fileinfo";
        var _data = {fileid:id};
        sendAjaxRequest(src,_data,_fun,error,true);
    }
    function getfileinfoSuccess(data){
//        console.log("request success");
        var fileurl = data["file_url"];
//        var fileurl = "http://localhost:8080/zg/video/2016.12.19-09.24.32.mp4";

        console.log(fileurl);
        $("#test-file").val(fileurl);
        var _html = "<video controls='controls' style='width: 800px;height: 600px;' src='"+fileurl+"'></video>"
        $("#content-test").html(_html);
    }
    function getImageSuccess(data){
        var img_url = data["file_url"];
        $("#test-images").val(img_url);
        var _html = "<img style='height:320px;width:240px;' src='"+img_url+"'></img>";
        $("#image_show").html(_html);
    }

//    /**
//     * 跳转卡口图片页面
//     */
//    function getImagesPage(){
//        window.location.href=getBasePath()+"/zg/view/images_operate.jsp";
//    }
</script>
</html>
