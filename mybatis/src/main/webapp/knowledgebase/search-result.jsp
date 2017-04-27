<%--
  Created by IntelliJ IDEA.
  User: 95
  Date: 2016/11/10
  Time: 10:06
  To change this template use File | Settings | File Templates.
--%>
<%
if(session.getAttribute("message")=="noexist"){
	%>
	<script language="javascript">
	alert("资源不存在！");
	</script>
<%
request.getSession().removeAttribute("message");
}
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<style type="text/css">
	
	td .button-rounded{
	padding-left:20px;
	padding-right:20px;
	}
</style>

<div class="panel panel-info" style="display: none;" id="search_panel" >
    <div class="panel-heading">
        <h3 class="panel-title">搜索结果</h3>
    </div>
    <div class="panel-body" style="padding-top:0px;">
    	<c:if test="${is_manager == 1}">
    				<div style="padding-top: 10px;">
                        <button class="button button-royal button-rounded button-giant button-small" 
						 style="height:34px;float:left;padding:0 15px; margin-right:10px;
							 background: #808080;color: #FFF" onclick="delete_pub_file_modal(this)">删除
                        </button>
                    </div>
         </c:if>
        <table class="table table-striped" style="font-size:14px;">
            <thead>
            <tr>
            		<c:choose>
            			<c:when test="${is_worker == 1}">
                            <th style="width: 37%;">文件名</th>
                            <th style="width: 20%;text-align:center;">关键词</th>
                            <th style="width: 10%;text-align:center">上传者</th>
                            <th style="width: 13%;text-align:center">上传时间</th>
                            <th style="width: 10%;text-align:center">下载</th>
                            <th style="width: 10%;text-align:center">下载量</th>
                        </c:when>
                        <c:otherwise>
                        	<th style="width: 5%">
                        	<input type="checkbox" id="selAllfile" onclick="selectAllfile()"></th>
                        	<th style="width: 19%;">文件名</th>
                        	<th style="width: 20%;text-align:center;">关键词</th>
                            <th style="width: 10%;text-align:center">上传者</th>
                            <th style="width: 13%;text-align:center">上传时间</th>
                            <th style="width: 13%;text-align:center">修改信息</th>
                            <th style="width: 10%;text-align:center">下载</th>
                            <th style="width: 10%;text-align:center">下载量</th>
                        </c:otherwise>
                    </c:choose>
            </tr>
            </thead>
            <tbody id="search_file" style="height: 560px; overflow-y: auto;">

            </tbody>
        </table>
    </div>
</div>
<div class="modal fade" id="deletepubfile-modal" tabindex="-1" role="dialog">
    <div class="modal-dialog modal-sm" role="document">
        <div class="modal-content">
            <div class="modal-header">

                <h4 class="modal-title" id="myModalLabel-delete_pub_title"></h4>
            </div>
            <div class="modal-body">
                <p id="delete_pub_info"></p>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" data-dismiss="modal" id="adm_ok_btn" >确认</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="alterfileinfo" tabindex="-1" role="dialog" aria-labelledby="myModalLabel-changefileino">
    <div class="modal-dialog modal-sm" role="document">
        <div class="modal-content" style="width:400px;">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel-changefileinfo">提示</h4>
            </div>
            <div class="modal-body">
       		<ul style="padding-left: 0px;">
       			<li>
       			<span>用户分类：</span>
       			<input type="text" value="" style="width:70%;display:inline;"
							class="form-control" id="usercate_checked"  readonly="true">
       			</li>
				<li><span>重新分类：</span>
				<select class="form-control" onchange="get_child_category_selected(this)" id="cate_select_checked"
					style="width: 34%;padding:6px;display:inline;">
				<option value='类别'>类别</option>
				</select>
				<select class="form-control" onchange="value" id="second_cate_checked"
				style="width: 35%;padding:6px;display:inline;">
				<option value=''>请选择</option>
				</select>	
				</li>
				<li>
				<span style="margin-left:4%;">关键词：</span>
				<input type="text" value="" style="width:70%;display:inline;"
					placeholder="多个关键词以空格分开"		class="form-control" id="fileword_checked" >
				</li>
				<li>
				<p type="hidden" id="fileid_checked"></p>
				<span>资料描述：</span>
				<textarea type="textarea" id="filearea_checked" rows="3" 
						style="width:70%;vertical-align:top;display:inline;"
						placeholder="请对文件进行描述"	class="form-control" value=""></textarea>
				</li>	
					</ul>
					</div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" data-dismiss="modal" id="change_click_checked"
                        onclick="updateFileChecked(this)">修改
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="result_checked" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog modal-sm" role="document">
        <div class="modal-content">
            <div class="modal-header">

                <h4 class="modal-title" id="myModalLabel-add-result">提示信息</h4>
            </div>
            <div class="modal-body">
                <p id="result_msg_checked"></p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary " data-dismiss="modal">确认</button>
            </div>
        </div>
    </div>
	</div>