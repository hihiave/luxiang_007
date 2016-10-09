<%--
  Created by IntelliJ IDEA.
  User: w
  Date: 2016/10/9
  Time: 14:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java"  pageEncoding="utf-8" %>

<div class="tab-pane fade" id="my-download-tab">
    <div class="panel panel-primary" style="height:625px;">
        <div class="panel-heading">
            <h3 class="panel-title">我的下载</h3>
        </div>
        <div class="panel-body" style="padding-top:0px;">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>文件名</th>
                    <th style="padding-left:0px;">上传者</th>
                    <th>下载时间</th>
                    <th style="padding-left:20px;">下载</th>
                    <th style="padding-left:20px;">预览</th>
                </tr>
                </thead>
                <tbody >
                <tr>
                    <td style="padding-top:15px;">第八次人民代表大会</td>
                    <td style="padding-top:15px;">张三</td>
                    <td style="padding-top:15px;">2016-8-1</td>
                    <td><button class="btn btn-primary" data-toggle="modal" data-target="#download">下载</button></td>
                    <td><a href="preview.html"><button class="btn btn-primary">预览</button></a></td>
                </tr>
                <tr>
                    <td style="padding-top:15px;">第N次人民代表大会</td>
                    <td style="padding-top:15px;">李四</td>
                    <td style="padding-top:15px;">2016-8-1</td>
                    <td><button class="btn btn-primary" data-toggle="modal" data-target="#download">下载</button></td>
                    <td><button class="btn btn-primary">预览</button></td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>
<div class="modal fade" id="download" tabindex="-1" role="dialog" aria-labelledby="myModalLabel-download">
    <div class="modal-dialog modal-sm" role="document">
        <div class="modal-content">
            <div class="modal-header">

                <h4 class="modal-title" id="myModalLabel-download">提示</h4>
            </div>
            <div class="modal-body">
                是否下载该文件？
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">否</button>
                <button type="button" class="btn btn-primary" data-dismiss="modal">是</button>
            </div>
        </div>
    </div>
</div>