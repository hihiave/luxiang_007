<%--
  Created by IntelliJ IDEA.
  User: w
  Date: 2016/10/9
  Time: 14:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page  language="java" pageEncoding="UTF-8" %>
<div class="tab-pane fade " id="person-info-tab">
    <div class="panel panel-primary" style="height:625px;">
        <div class="panel-heading" >
            <h3 class="panel-title">个人信息</h3>
        </div>
        <div class="panel-body">


            <div style="float:left;margin-top:50px;margin-left:100px;">
                <img src="/mybatis/knowledgebase/img/1.png" >
            </div>
            <div  style="font-size:15px;float:right;margin-top:120px;margin-right:150px;">
                用户名:<%=session.getAttribute("username")%><br>
                用户类型:<%=session.getAttribute("type")%><br>
                注册时间:<%=session.getAttribute("time")%>
            </div>
            <div align="center" style="margin-top:400px;">
                <button class="btn btn-info" data-toggle="modal" data-target="#changepsw">修改密码</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="changepsw" tabindex="-1" role="dialog" aria-labelledby="myModalLabel-person">
    <div class="modal-dialog modal-sm" role="document">
        <div class="modal-content">
            <div class="modal-header">

                <h4 class="modal-title" id="myModalLabel-person">修改密码</h4>
            </div>
            <div class="modal-body">
                <ul style="padding-left:0px;">
                    <li><input type="password" placeholder="请输入原密码" class="form-control" id="oldpsw"></li>
                    <div id="spanusername" style="margin-left:6px;margin-bottom:10px;height:6px;"></div>
                    <li><input type="password"  id="password1" class="form-control" placeholder="请输入新密码"></li>
                    <div id="spanpsw" style="margin-left:6px;margin-bottom:10px;height:6px;"></div>
                    <li><input type="password"  id="password2" class="form-control"  placeholder="请再次输入新密码"></li>
                    <div id="spanrepsw" style="margin-left:6px;margin-bottom:10px;height:6px;"></div>
                </ul>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary " id="btnclick" data-dismiss="">确认</button>
            </div>
        </div>
    </div>
</div>
