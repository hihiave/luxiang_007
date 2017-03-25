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


</head>
<body>

<div class="left">

    <div class="side sucaihuo-container">
        <div class="sucaihuo-content ">
            <div class="container">
                <div class="row">
                    <div class="col-sm-4">
                        <nav class="nav">
                            <ul class="metisFolder">
                                <li><a href="#"> <span class="fa fa-folder-o"></span>
                                    实时监控
                                </a>
                                    <ul>

                                        <li><a href="#"> <span class="fa fa-folder-o"></span>
                                            XX路
                                        </a>
                                            <ul>

                                                <li><a href="#"> <span class="fa fa-folder-o"></span>
                                                    白天
                                                </a>
                                                    <ul>
                                                        <li><a href="#"> <span class="fa fa-video-camera"></span>
                                                            a点
                                                        </a></li>
                                                        <li><a href="#"> <span class="fa fa-video-camera"></span>
                                                            b点
                                                        </a></li>
                                                        <li><a href="#"> <span class="fa fa-video-camera"></span>
                                                            c点
                                                        </a></li>
                                                    </ul>
                                                </li>

                                                <li><a href="#"> <span class="fa fa-folder-o"></span>
                                                    晚上
                                                </a>
                                                    <ul>
                                                        <li><a href="#"> <span class="fa fa-video-camera"></span>
                                                            a点
                                                        </a></li>

                                                        <li><a href="#"> <span class="fa fa-video-camera"></span>
                                                            b点
                                                        </a></li>
                                                        <li><a href="#"> <span class="fa fa-video-camera"></span>
                                                            c点
                                                        </a></li>
                                                    </ul>
                                                </li>

                                            </ul>
                                        </li>

                                    </ul>
                                </li>

                                <li><a href="#"> <span class="fa fa-folder-o"></span>
                                    历史记录
                                </a>
                                    <ul>
                                        <li onclick="monitoringHistory();"><a href="#"> <span
                                                class="fa fa-folder-o"></span>
                                            监控历史
                                        </a>
                                        </li>
                                    </ul>
                                </li>
                            </ul>
                        </nav>
                    </div>

                </div>
            </div>
        </div>
    </div>


</div>

</body>
</html>
