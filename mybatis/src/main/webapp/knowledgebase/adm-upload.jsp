<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    if (session.getAttribute("username") != null) {
%>

<%
} else {
%>
<script language="javascript">

    alert("请先登录!");

    top.location = 'Login.jsp';

</script>
<%
    }
%>
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
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <link rel="stylesheet" type="text/css" href="/mybatis/knowledgebase/css/bootstrap.min.css">
    <script src="/mybatis/knowledgebase/js/jquery.min.js"></script>
    <script src="/mybatis/knowledgebase/js/bootstrap.min.js"></script>
    <script src="/mybatis/knowledgebase/js/html5shiv.min.js"></script>
    <script src="/mybatis/knowledgebase/js/respond.min.js"></script>
    <script src="/mybatis/knowledgebase/js/public_search.js"></script>

    <style type="text/css">
        .nav {
            margin-bottom: 5px;
            text-align: center;
        }

        .hr {
            margin: 0 auto;
        }

        .container {
            width: 1320px !important;
        }

    </style>


    <%
        java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        java.util.Date currentTime = new java.util.Date();
        String time = simpleDateFormat.format(currentTime).toString();
    %>
    <script type="text/javascript">
        window.onload = function () {
            var file1 = $('#file1'),
                    aim1 = $('#aim1');
            file1.on('change', function (e) {
                var name = e.currentTarget.files[0].name;
                aim1.val(name);
            });
            var file2 = $('#file2'),
                    aim2 = $('#aim2');
            file2.on('change', function (e) {
                var name = e.currentTarget.files[0].name;
                aim2.val(name);
            });
            var file3 = $('#file3'),
                    aim3 = $('#aim3');
            file3.on('change', function (e) {
                var name = e.currentTarget.files[0].name;
                aim3.val(name);
            });
            sub.onclick = check;
            function check() {

                var aim1 = document.getElementById("aim1"),
                        aim2 = document.getElementById("aim2"), aim3 = document.getElementById("aim3");
                var word1 = document.getElementById("word1"),
                        word2 = document.getElementById("word2"), word3 = document.getElementById("word3");
                var area1 = document.getElementById("area1"),
                        area2 = document.getElementById("area2"), area3 = document.getElementById("area3");

                if (aim1.value == "" & aim2.value == "" & aim3.value == "") {
                    alert("请选择上传的文件");
                    return false;
                }
                else {
                    if (aim2.value != "") {
                        if ((area2.value == "") || (word2.value == "")) {
                            alert("请完善文件信息");
                            return false;
                        }
                    }
                    else if (aim1.value != "") {
                        if ((area1.value == "") || (word1.value == "")) {
                            alert("请完善文件信息");
                            return false;
                        }
                    }
                    else if (aim3.value != "") {
                        if ((area3.value == "") || (word3.value == "")) {
                            alert("请完善文件信息");
                            return false;
                        }
                    }
                }
            }
        }
    </script>
</head>
<body>

<div style="margin:10px 20px;">
    <button type="button" class="btn btn-md btn-default" style="border:0px;float:right;" data-toggle="tooltip"
            data-placement="bottom" title="退出登录">
        <span class="glyphicon glyphicon-off"></span>&nbsp;退出
    </button>
    <button type="button" class="btn btn-md btn-default" style="border:0px;float:right;" data-toggle="tooltip"
            data-placement="bottom" title="个人中心">
        <a href="/mybatis/knowledgebase/adm-personal.jsp"><span class="glyphicon glyphicon-user"></span>&nbsp;首页</a>
    </button>
</div>
<div class="container">
    <div class="row" style="text-align:center;margin:40px auto;">
        <img src="/mybatis/knowledgebase/img/img_3.jpg" width="725px" height="80px">
    </div>
    <div class="row" style="margin:0px">
        <div class="col-md-3" style="width:283px;margin-right:16px;">
            <img src="/mybatis/knowledgebase/img/zhishiku_2.png" style="margin-left:30px;">
        </div>
        <div class="col-md-9" style="margin-top:16px;">
            <div style="float:left;margin-right:0px;">
                <select class="form-control" onchange="value" id="category_select">
                    <option value='' selected>类别&nbsp;&nbsp;&nbsp;</option>
                    <option value=''>专利</option>
                    <option value=''>论文</option>
                    <option value=''>报告</option>
                </select>
            </div>
            <div style="float:left;margin-right:0px;">
                <select class="form-control" onchange="value" id="key_select">
                    <option value='' selected>全文</option>
                    <option value=''>标题</option>
                    <option value=''>作者</option>
                    <option value=''>关键字</option>
                </select>
            </div>
            <div class="input-group input-group-md" style="float:left;">
                <input type="text" class="form-control" aria-describedby="sizing-addon1" style="width:521px;">
                <button class="btn btn-primary" style="width:78px;margin-left:5px;">搜&nbsp;索</button>
            </div>
        </div>
    </div>
    <hr>
    <div class="row" style="margin-left:80px;">
        <div class="col-md-2" style=" border-radius:10px;width:170px;font-size:16px;border-right:1px solid #eee">
            <div class="panel-group" id="accordion">
                <div class="panel panel-info" style="border-color: #eeeeee;background-color: #f9f9f9;">
                    <div class="panel-heading">
                        <h4 class="panel-title"><a href="#person-center" data-toggle="collapse"
                                                   data-parent="accordion">个人中心<span class="caret"
                                                                                     style="float:right;margin-top: 7px;margin-right: -11px;"></span></a>
                        </h4>
                    </div>
                    <div id="person-center" class="panel-collapse collapse in">
                        <div class="panel-body" style="padding: 6px 15px;">
                            <ul class="nav nav-pills nav-stacked"
                                style="margin-left: -15px;margin-right: -15px;margin-bottom: 0px;font-size: inherit;">
                                <li><a href="/mybatis/knowledgebase/adm-personal.jsp">个人信息</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
                <c:if test="${is_manager == 1}">
                    <div class="panel panel-info" style="border-color: #eeeeee;background-color: #f9f9f9;">
                        <div class="panel-heading">
                            <h4 class="panel-title"><a href="#user-manage" data-toggle="collapse"
                                                       data-parent="accordion">用户管理<span class="caret"
                                                                                         style="float:right;margin-top: 7px;margin-right: -11px;"></span></a>
                            </h4>
                        </div>
                        <div id="user-manage" class="panel-collapse collapse in">
                            <div class="panel-body" style="padding: 6px 15px;">
                                <ul class="nav nav-pills nav-stacked"
                                    style="margin-left: -15px;margin-right: -15px;margin-bottom: 0px;font-size: inherit;">
                                    <li><a href="/mybatis/knowledgebase/adm-inquire.jsp">用户查询</a></li>
                                    <li><a href="/mybatis/knowledgebase/adm-check.jsp">用户审核</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </c:if>
                <div class="panel panel-info" style="border-color: #eeeeee;background-color: #f9f9f9;">
                    <div class="panel-heading">
                        <h4 class="panel-title"><a href="#source-manage" data-toggle="collapse"
                                                   data-parent="accordion">资源管理<span class="caret"
                                                                                     style="float:right;margin-top: 7px;margin-right: -11px;"></span></a>
                        </h4>
                    </div>
                    <div id="source-manage" class="panel-collapse collapse in">
                        <div class="panel-body" style="padding: 6px 15px;">
                            <ul class="nav nav-pills nav-stacked"
                                style="margin-left: -15px;margin-right: -15px;margin-bottom: 0px;font-size: inherit;">
                                <li><a href="/mybatis/knowledgebase/adm-private.jsp">我的上传</a></li>
                                <li><a href="/mybatis/knowledgebase/adm-public.jsp">共有文件</a></li>
                                <li><a href="/mybatis/knowledgebase/adm-download.jsp">我的下载</a></li>
                                <li class="active"><a href="/mybatis/knowledgebase/adm-upload.jsp">文件上传</a></li>
                                <c:if test="${is_manager == 1}">
                                    <li><a href="/mybatis/knowledgebase/adm-category.jsp">类别管理</a></li>
                                    <li><a href="/mybatis/knowledgebase/adm-checkfile.jsp">资源审核</a></li>
                                </c:if>
                            </ul>
                        </div>
                    </div>
                </div>
                <c:if test="${is_manager == 1}">
                    <div class="panel panel-info" style="border-color: #eeeeee;background-color: #f9f9f9;">
                        <div class="panel-heading">
                            <h4 class="panel-title"><a href="#system-manage" data-toggle="collapse"
                                                       data-parent="accordion">系统管理<span class="caret"
                                                                                         style="float:right;margin-top: 7px;margin-right: -11px;"></span></a>
                            </h4>
                        </div>
                        <div id="system-manage" class="panel-collapse collapse in">
                            <div class="panel-body" style="padding: 6px 15px;">
                                <ul class="nav nav-pills nav-stacked"
                                    style="margin-left: -15px;margin-right: -15px;margin-bottom: 0px;font-size: inherit;">
                                    <li><a href="##">数据还原</a></li>
                                    <li><a href="##">数据备份</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </c:if>

            </div>
        </div>
        <div class="col-md-8" style="margin-left:50px;">

            <div class="panel panel-primary" style="height:625px;">
                <div class="panel-heading">
                    <h3 class="panel-title">文件上传</h3>
                </div>
                <div class="panel-body" style="height:580px;overflow-y:auto;">
                    <form action="/mybatis/Uploadservlet" name="name1" enctype="multipart/form-data" method="post">

                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h4 class="panel-title">
                                    <a data-toggle="collapse" href="#upload1">
                                        选择文件1
                                    </a>
                                </h4>
                            </div>
                            <div id="upload1" class="panel-collapse collapse">
                                <div class="panel-body form-inline">
                                    <input type="file" class="upload" name="file" id="file1"><br>
                                    <span>资料名称：</span><input class="form-control" style="width:40%;" type="text"
                                                             id="aim1" readonly="true">&nbsp;&nbsp;

                                    <span>上传者：</span><input class="form-control" style="width:40%;" type="text"
                                                            placeholder="<%=session.getAttribute("username")%>"
                                                            readonly="true"><br><br>
                                    <span>上传时间：</span><input class="form-control" style="width:40%;" type="text"
                                                             placeholder="<%=time%>" readonly="true">&nbsp;&nbsp;
                                    <span>关键词：</span><input class="form-control" style="width:40%;"
                                                            placeholder="多个关键词以空格分开" type="text" id="word1"><br><br>
                                    <span>资料属性：</span><input type="radio" name="type1" checked>公有&nbsp;&nbsp;<input
                                        type="radio" name="type1">私有<br><br>
                                    <span>资料描述：</span><textarea type="textarea" class="form-control"
                                                                style="width:89.5%;" rows="3" id="area1"></textarea>

                                </div>
                            </div>
                        </div>
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h4 class="panel-title">
                                    <a data-toggle="collapse" href="#upload2">
                                        选择文件2
                                    </a>
                                </h4>
                            </div>
                            <div id="upload2" class="panel-collapse collapse">
                                <div class="panel-body form-inline">
                                    <input type="file" class="upload" name="file" id="file2"><br>
                                    <span>资料名称：</span><input class="form-control" style="width:40%;" type="text"
                                                             id="aim2" readonly="true">&nbsp;&nbsp;

                                    <span>上传者：</span><input class="form-control" style="width:40%;" type="text"
                                                            placeholder="<%=session.getAttribute("username")%>"
                                                            readonly="true"><br><br>
                                    <span>上传时间：</span><input class="form-control" style="width:40%;" type="text"
                                                             placeholder="<%=time%>" readonly="true">&nbsp;&nbsp;
                                    <span>关键词：</span><input class="form-control" style="width:40%;"
                                                            placeholder="多个关键词以空格分开" type="text" id="word2"><br><br>
                                    <span>资料属性：</span><input type="radio" name="type2" checked>公有&nbsp;&nbsp;<input
                                        type="radio" name="type2">私有<br><br>
                                    <span>资料描述：</span><textarea type="textarea" class="form-control"
                                                                style="width:89.5%;" rows="3" id="area2"></textarea>

                                </div>
                            </div>
                        </div>
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h4 class="panel-title">
                                    <a data-toggle="collapse" href="#upload3">
                                        选择文件3
                                    </a>
                                </h4>
                            </div>
                            <div id="upload3" class="panel-collapse collapse">
                                <div class="panel-body form-inline">
                                    <input type="file" class="upload" name="file" id="file3"><br>
                                    <span>资料名称：</span><input class="form-control" style="width:40%;" type="text"
                                                             id="aim3" readonly="true">&nbsp;&nbsp;

                                    <span>上传者：</span><input class="form-control" style="width:40%;" type="text"
                                                            placeholder="<%=session.getAttribute("username")%>"
                                                            readonly="true"><br><br>
                                    <span>上传时间：</span><input class="form-control" style="width:40%;" type="text"
                                                             placeholder="<%=time%>" readonly="true">&nbsp;&nbsp;
                                    <span>关键词：</span><input class="form-control" style="width:40%;"
                                                            placeholder="多个关键词以空格分开" type="text" id="word3"><br><br>
                                    <span>资料属性：</span><input type="radio" name="type3" checked>公有&nbsp;&nbsp;<input
                                        type="radio" name="type3">私有<br><br>
                                    <span>资料描述：</span><textarea type="textarea" class="form-control"
                                                                style="width:89.5%;" rows="3" id="area3"></textarea>

                                </div>
                            </div>
                        </div>


                        <div align="center">
                            <input type="submit" value="上传文件" id="sub" class="btn btn-primary">
                        </div>
                    </form>


                </div>
            </div>


        </div>
    </div>
</div>


<style type="text/css">
    th.btn-primary {
        width: 116px;
    }
</style>


<style type="text/css">
    .modal {
        margin-top: 20%;
    }

    .modal-body li {
        list-style-type: none;
        padding: 0px 5px;
        border: 1px solid #ffffff;
        padding: 5px 0px;
    }

    #username1, #password1 {
        font-size: 15px;

    }

    .footer {
        position: absolute;
        top: 94%;
        height: 6%;
        background-color: #e1e1e1;
        width: 100%;

        text-align: center;
        min-width: 1190px;
    }

    .footer span.footerText {
        font-size: 14px;
        margin-top: 0.5%;
        color: #606060;
        display: inline-block;
    }
</style>
<%--<div class="footer"> <span class="footerText">Copyright © 1956-2016 电子科技大学</span> </div>--%>
</body>
</html>
