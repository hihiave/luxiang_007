<%--
  Created by IntelliJ IDEA.
  User: w
  Date: 2016/10/9
  Time: 14:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page  language="java" pageEncoding="UTF-8" %>
<div class="tab-pane fade" id="user-query-tab">
    <div class="panel panel-primary" style="height:625px;">
        <div class="panel-heading" >
            <h3 class="panel-title">用户查询</h3>
        </div>
        <div class="panel-body">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th style="font-size:16px;"></th>
                    <th style="font-size:16px;">用户名</th>
                    <th style="padding-bottom:5px;padding-top:0px;width:285px;"><input type="text" name="" style="height:34px;width:273px;"></th>
                    <th style="padding-bottom:5px;padding-top:0px;width:116px;"><button class="btn btn-info">搜索用户</button></th>
                    <th style="padding-bottom:5px;padding-top:0px;width:116px;"><button class="btn btn-info" data-toggle="modal" data-target="#tianjia">添加用户</button></th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>1</td>
                    <td>张三</td>
                    <td></td>
                    <th style="padding-bottom:3px;padding-top:3px;width:116px;"><button class="btn btn-primary" data-toggle="modal" data-target="#shanchu">删除用户</button></th>
                    <th style="padding-bottom:3px;padding-top:3px;width:116px;"><button class="btn btn-primary" data-toggle="modal" data-target="#chongzhi">重置密码</button></th>
                </tr>
                <tr>
                    <td>2</td>
                    <td>李四</td>
                    <td></td>
                    <th style="padding-bottom:3px;padding-top:3px;width:116px;"><button class="btn btn-primary">删除用户</button></th>
                    <th style="padding-bottom:3px;padding-top:3px;width:116px;"><button class="btn btn-primary">重置密码</button></th>
                </tr>
                </tr>
                <tr>
                    <td>3</td>
                    <td>王五</td>
                    <td></td>
                    <th style="padding-bottom:3px;padding-top:3px;width:116px;"><button class="btn btn-primary">删除用户</button></th>
                    <th style="padding-bottom:3px;padding-top:3px;width:116px;"><button class="btn btn-primary">重置密码</button></th>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>
<div class="modal fade" id="shanchu" tabindex="-1" role="dialog" aria-labelledby="myModalLabel-query-1">
    <div class="modal-dialog modal-sm" role="document">
        <div class="modal-content">
            <div class="modal-header">

                <h4 class="modal-title" id="myModalLabel-query-1">提示</h4>
            </div>
            <div class="modal-body">
                确认删除该用户吗?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" data-dismiss="modal">确认</button>
            </div>
        </div>
    </div>
</div>



<div class="modal fade" id="chongzhi" tabindex="-1" role="dialog" aria-labelledby="myModalLabel-query-2">
    <div class="modal-dialog modal-sm" role="document">
        <div class="modal-content">
            <div class="modal-header">

                <h4 class="modal-title" id="myModalLabel-query-2">提示</h4>
            </div>
            <div class="modal-body">
                是否重置该用户密码?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">否</button>
                <button type="button" class="btn btn-primary" data-dismiss="modal">是</button>
            </div>
        </div>
    </div>
</div>




<div class="modal fade" id="tianjia" tabindex="-1" role="dialog" aria-labelledby="myModalLabel-query-3">
    <div class="modal-dialog modal-sm" role="document">
        <div class="modal-content">
            <div class="modal-header">

                <h4 class="modal-title" id="myModalLabel-query-3">输入用户信息</h4>
            </div>
            <div class="modal-body">
                <ul style="padding-left:0px;">
                    <li><input type="text" placeholder="请输入用户名" class="form-control" id="username1"></li>
                    <div id="spanusername" style="margin-left:6px;margin-bottom:10px;height:6px;"></div>
                    <li><input type="password"  id="password1" class="form-control" value="" placeholder="初始密码为123456" readonly="true"></li>
                </ul>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary " id="btnclick" >确认</button>
            </div>
        </div>
    </div>
</div>
