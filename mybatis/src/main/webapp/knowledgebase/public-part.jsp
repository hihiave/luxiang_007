<%--
  Created by IntelliJ IDEA.
  User: 95
  Date: 2016/11/15
  Time: 11:34
  To change this template use File | Settings | File Templates.
--%>

<%@ page language="java" pageEncoding="utf-8" %>
<!-- <div class="row" style="text-align:center;margin:40px auto;">
    <img src="/mybatis/knowledgebase/img/img_3.jpg" width="725px" height="80px">
</div>
<div class="row" style="margin:0px">
    <div class="col-xs-3" style="width:283px;margin-right:16px;">
        <img src="/mybatis/knowledgebase/img/zhishiku_2.png" style="margin-left:30px;">
    </div>
    <div class="col-xs-9" style="margin-top:16px;">
        <div style="float:left;margin-right:0px;">
            <select class="form-control" onchange="value" id="category_select" style="width: 100px;">
                <option value='类别' >类别</option>
            </select>
        </div>
        <div style="float:left;margin-right:0px;">
            <select class="form-control" onchange="value" id="key_select" style="width: 100px;">
                <option value='fullText' selected>全文</option>
                <option value='title'>标题</option>
                <option value='author'>作者</option>
                <option value='keyword'>关键字</option>
            </select>
        </div>
        <div class="input-group input-group-md" style="float:left;">
            <input type="text" class="form-control" aria-describedby="sizing-addon1"  style="width:521px;"
                   id="file_search_input" onfocus="myfunction()" oninput="myfunction()">
            <button class="btn btn-primary" style="width:78px;margin-left:5px;" id="file_search_sub"
                    onclick="send_search_info()">搜&nbsp;索
            </button>
        </div>
    </div>
</div> -->

<!-- <link rel="stylesheet" type="text/css" href="/mybatis/knowledgebase/css/common.min.css"> -->
<div class="pub_top">

		<div class="col-xs-3 top-img">
			<img src="/mybatis/knowledgebase/img/3.png" >
		</div>
	    <div class="col-xs-9" style="margin-top:10px;">
        <div style="float:left;margin-right:0px;">
            <select class="form-control" onchange="value" id="category_select" style="width: 100px;">
                <option value='类别' >类别</option>
            </select>
        </div>
        <div style="float:left;margin-right:0px;">
            <select class="form-control" onchange="value" id="key_select" style="width: 100px;">
                <option value='fullText' selected>全文</option>
                <option value='title'>标题</option>
                <option value='author'>作者</option>
                <option value='keyword'>关键字</option>
            </select>
        </div>
        <div class="input-group input-group-md" style="float:left;">
            <input type="text" class="form-control" aria-describedby="sizing-addon1"  style="width:300px;"
                   id="file_search_input" onfocus="myfunction()" oninput="myfunction()">
            <button class="btn btn-default" style="width:78px;margin-left:5px;background: #808080;color: #FFF" id="file_search_sub"
                    onclick="send_search_info()">搜&nbsp;索
            </button>
        </div>

        		<div style="float: right;margin-right: 10px;">
        		<div class="upload">
					<a href="/mybatis/knowledgebase/adm-upload.jsp">上传</a>
				</div>	
				<div class="index">
					<a href="/mybatis/knowledgebase/adm-personal.jsp">主页</a>
	
				</div>
				<div class="index">
					<a href="#" onclick="logout()">退出</a>
				</div>
				</div>
				<div class="clear"></div>
    </div>

<div style="clear: both;"></div>
</div>
