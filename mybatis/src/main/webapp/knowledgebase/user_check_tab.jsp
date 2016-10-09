<%--
  Created by IntelliJ IDEA.
  User: w
  Date: 2016/10/9
  Time: 14:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page  language="java" pageEncoding="UTF-8" %>
<div class="tab-pane fade" id="user-check-tab">
    <div class="panel panel-primary" style="height:625px;">
        <div class="panel-heading">
            <h3 class="panel-title">用户审核</h3>
        </div>
        <div class="panel-body" style="padding-top:0px;">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th style="padding-left: 28px;border-bottom-width: 2px;padding-bottom: 4px;width: 78px;"><label class="checkbox"><input type="checkbox" id="selAll" onclick="selectAll()">全选 </label></th>
                    <th style="padding-bottom:15px;">用户名</th>
                    <th style="padding-bottom:15px;">审核状态</th>
                    <th style="width:116px;"><button class="btn btn-primary" data-toggle="modal" data-target="#tongguo">通过</button></th>
                    <th style="width:116px;"><button class="btn btn-primary" data-toggle="modal" data-target="#jujue">拒绝</button></th>
                </tr>
                </thead>
                <tbody id="list">
                <tr>
                    <td style="padding-top:15px;"><label><input type="checkbox" name="checkAll"></label></td>
                    <td style="padding-top:15px;">哈利路亚</td>
                    <td style="padding-top:15px;padding-left:15px;">待审核</td>
                    <td><button class="btn btn-primary">通过</button></td>
                    <td><button class="btn btn-primary">拒绝</button></td>
                </tr>
                <tr>
                    <td style="padding-top:15px;"><label><input type="checkbox" name="checkAll"></label></td>
                    <td style="padding-top:15px;">哈利路亚</td>
                    <td style="padding-top:15px;padding-left:15px;">待审核</td>
                    <td><button class="btn btn-primary">通过</button></td>
                    <td><button class="btn btn-primary">拒绝</button></td>
                </tr>
                <tr>
                    <td style="padding-top:15px;"><label><input type="checkbox" name="checkAll"></label></td>
                    <td style="padding-top:15px;">哈利路亚</td>
                    <td style="padding-top:15px;padding-left:15px;">待审核</td>
                    <td><button class="btn btn-primary">通过</button></td>
                    <td><button class="btn btn-primary">拒绝</button></td>
                </tr>

                </tbody>
            </table>
        </div>
    </div>
</div>
<div class="modal fade" id="tongguo" tabindex="-1" role="dialog" aria-labelledby="myModalLabel-check-1">
    <div class="modal-dialog modal-sm" role="document">
        <div class="modal-content">
            <div class="modal-header">

                <h4 class="modal-title" id="myModalLabel-check-1">提示</h4>
            </div>
            <div class="modal-body">
                用户已通过审核！
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">返回</button>
            </div>
        </div>
    </div>
</div>




<div class="modal fade" id="jujue" tabindex="-1" role="dialog" aria-labelledby="myModalLabel-check-2">
    <div class="modal-dialog modal-sm" role="document">
        <div class="modal-content">
            <div class="modal-header">

                <h4 class="modal-title" id="myModalLabel-check-2">提示</h4>
            </div>
            <div class="modal-body">
                用户未通过审核!
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">返回</button>
            </div>
        </div>
    </div>
</div>
