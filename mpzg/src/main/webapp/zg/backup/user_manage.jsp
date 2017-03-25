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
<title>用户管理</title>


</head>
<body>

	<jsp:include page="../templates/head.jsp"></jsp:include>

	<jsp:include page="../templates/left.jsp"></jsp:include>

	<div class="content">
		<div class="header">
			<h1 class="page-title">用户管理</h1>
		</div>
		<div class="main-content">

			<table class="table table-striped table-hover">
				<thead>
					<tr>
						<th>用户名</th>
						<th>真实姓名</th>
						<th>电子邮件</th>
						<th>性别</th>
						<th>电话号码</th>
						<th>用户权限</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>Tanmay</td>
						<td>Bangalore</td>
						<td>560001</td>
						<td>Sachin</td>
						<td>Mumbai</td>
						<td>400003</td>
						<td><a href="" data-toggle="modal"
							data-target="#userinfo_modify"><i class="fa fa-pencil"></i></a> <a
							href="#myModal" role="button" data-toggle="modal"><i
								class="fa fa-trash-o"></i></a></td>
					</tr>
				</tbody>
			</table>
			<div class="row-fluid">
				<div id="user-info-page-info" class="span4"
					style="margin-top: 15px;">
					<p>显示第 1 到第 2 条记录，共 2 条记录</p>
				</div>
				<div class="span8">
					<div class="pagination  pagination-right">
						<ul id="user-info-pagination">

						</ul>
					</div>
				</div>
			</div>
			<footer> </footer>
		</div>
	</div>
	<jsp:include page="../templates/footer.jsp"></jsp:include>

	<!-- 模态框（Modal） -->
	<div class="modal fade modal-style" id="userinfo_modify" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">修改用户信息</h4>
				</div>
				<div class="modal-body">

					<form class="form-horizontal" role="form">
						<div class="form-group form-group-sm form-group-style">
							<label class="col-sm-2 control-label">用户名</label>
							<div class="col-sm-10">
								<input class="form-control" type="text" id=""
									placeholder="Small input">
							</div>
						</div>
						<div class="form-group form-group-sm form-group-style">
							<label class="col-sm-2 control-label">真实姓名</label>
							<div class="col-sm-10">
								<input class="form-control" type="text" id=""
									placeholder="Small input">
							</div>
						</div>
						<div class="form-group form-group-sm form-group-style">
							<label class="col-sm-2 control-label">电子邮件</label>
							<div class="col-sm-10">
								<input class="form-control" type="text" id=""
									placeholder="Small input">
							</div>
						</div>
						<div class="form-group form-group-sm form-group-style">
							<label class="col-sm-2 control-label">性别</label>
							<div class="col-sm-10">
								<select class="form-control">
									<option>男</option>
									<option>女</option>
								</select>
							</div>
						</div>
						<div class="form-group form-group-sm form-group-style">
							<label class="col-sm-2 control-label">电话号码</label>
							<div class="col-sm-10">
								<input class="form-control" type="text" id=""
									placeholder="Small input">
							</div>
						</div>
						<div class="form-group form-group-sm form-group-style">
							<label class="col-sm-2 control-label">用户权限</label>
							<div class="col-sm-10">
								<select class="form-control">
									<option>普通管理员</option>
									<option>系统管理员</option>
								</select>
							</div>
						</div>
					</form>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" data-dismiss="modal">关闭
					</button>
					<button type="button" class="btn btn-primary">修改</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>

<%--出库单查看模板--%>
<script type="text/template" id="user-info-template">

    <tr>
        <td>
            <input type='checkbox' name="my-asset-out-info">
        </td>
        <td>
            <a data-toggle= 'modal' data-target='' pid ='{{asset.assetOutId}}' onclick='getAssetOutDetailValue(this)'>{{asset.assetModel}}
            </a>
        </td>
        <td>
            {{asset.assetOutName}}
        </td>
        <td>
            {{asset.assetCategory}}
        </td>

        <td>
            {{asset.assetNum}}
        </td>
        <td>
            {{asset.assetUnit}}
        </td>
        <td>
            {{asset.assetOutNum}}
        </td>
        <td>
            {{asset.assetOutReturnNum}}
        </td>
        <td>
            {{asset.assetOutReason}}
        </td>

        <td>
            {{asset.assetOutChangeTime}}
        </td>
        <td>
            {{asset.assetBorrowTime}}
        </td>
        <%--<td>--%>
            <%--{{asset.assetIsAcceptanceOrNot}}--%>
        <%--</td>--%>

    </tr>

</script>
</body>
</html>
