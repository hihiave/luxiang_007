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

</head>
<body>

	<jsp:include page="../templates/header.jsp"></jsp:include>
	<div id="middle">
		<jsp:include page="../view/menu_monitoring.jsp"></jsp:include>
		<div class="right" id="mainFrame">

			<div class="right_cont">
				<div class="title_right">
					<strong>实时监控</strong>
				</div>
				<ul class="nav nav-pills">
					<li class="active"><a href="#one" data-toggle="tab"> 一屏 </a></li>
					<li><a href="#four" data-toggle="tab">四屏</a></li>
					<li><a href="#nine" data-toggle="tab">九屏</a></li>
				</ul>
				<div id="myTabContent" class="tab-content">
					<div class="tab-pane fade in active" id="one">
						<div class="panel panel-primary" style="margin-top: 10px;">
							<div class="panel-heading">
								<h3 class="panel-title">
									监控点名称<span class="fa fa-folder-o" style="float: right;"></span><span
										class="fa fa-folder-o" style="float: right;"></span><span
										class="fa fa-folder-o" style="float: right;"></span>
								</h3>
							</div>
							<div class="panel-body video-1">
								<video controls="controls" autoplay="autoplay"> <source
									src="../video/in.mp4"></video>
							</div>
						</div>
					</div>
					<div class="tab-pane fade" id="four">
						<div class="container">
							<div class="row">
								<div class="col-md-6">
									<div class="panel panel-primary" style="margin-top: 10px;">
										<div class="panel-heading">
											<h3 class="panel-title">
												监控点名称<span class="fa fa-folder-o" style="float: right;"></span><span
													class="fa fa-folder-o" style="float: right;"></span><span
													class="fa fa-folder-o" style="float: right;"></span>
											</h3>
										</div>
										<div class="panel-body video-4">
											<video controls="controls" autoplay="autoplay"> <source
												src="http://www.100sucai.com/img/video/happyfit2.mp4"></video>
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="panel panel-primary" style="margin-top: 10px;">
										<div class="panel-heading">
											<h3 class="panel-title">
												监控点名称<span class="fa fa-folder-o" style="float: right;"></span><span
													class="fa fa-folder-o" style="float: right;"></span><span
													class="fa fa-folder-o" style="float: right;"></span>
											</h3>
										</div>
										<div class="panel-body video-4">
											<video controls="controls" autoplay="autoplay"> <source
												src="http://www.100sucai.com/img/video/happyfit2.mp4"></video>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="container">
							<div class="row">
								<div class="col-md-6">
									<div class="panel panel-primary" style="margin-top: 10px;">
										<div class="panel-heading">
											<h3 class="panel-title">
												监控点名称<span class="fa fa-folder-o" style="float: right;"></span><span
													class="fa fa-folder-o" style="float: right;"></span><span
													class="fa fa-folder-o" style="float: right;"></span>
											</h3>
										</div>
										<div class="panel-body video-4">
											<video controls="controls" autoplay="autoplay"> <source
												src="http://www.100sucai.com/img/video/happyfit2.mp4"></video>
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="panel panel-primary" style="margin-top: 10px;">
										<div class="panel-heading">
											<h3 class="panel-title">
												监控点名称<span class="fa fa-folder-o" style="float: right;"></span><span
													class="fa fa-folder-o" style="float: right;"></span><span
													class="fa fa-folder-o" style="float: right;"></span>
											</h3>
										</div>
										<div class="panel-body video-4">
											<video controls="controls" autoplay="autoplay"> <source
												src="http://www.100sucai.com/img/video/happyfit2.mp4"></video>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>

					<div class="tab-pane fade" id="nine">
						<div class="container">
							<div class="row">
								<div class="col-md-4">
									<div class="panel panel-primary" style="margin-top: 10px;">
										<div class="panel-heading">
											<h3 class="panel-title">
												监控点名称<span class="fa fa-folder-o" style="float: right;"></span><span
													class="fa fa-folder-o" style="float: right;"></span><span
													class="fa fa-folder-o" style="float: right;"></span>
											</h3>
										</div>
										<div class="panel-body video-9">
											<video controls="controls" autoplay="autoplay"> <source
												src="http://www.100sucai.com/img/video/happyfit2.mp4"></video>
										</div>
									</div>
								</div>
								<div class="col-md-4">
									<div class="panel panel-primary" style="margin-top: 10px;">
										<div class="panel-heading">
											<h3 class="panel-title">
												监控点名称<span class="fa fa-folder-o" style="float: right;"></span><span
													class="fa fa-folder-o" style="float: right;"></span><span
													class="fa fa-folder-o" style="float: right;"></span>
											</h3>
										</div>
										<div class="panel-body video-9">
											<video controls="controls" autoplay="autoplay"> <source
												src="http://www.100sucai.com/img/video/happyfit2.mp4"></video>
										</div>
									</div>
								</div>
								<div class="col-md-4">
									<div class="panel panel-primary" style="margin-top: 10px;">
										<div class="panel-heading">
											<h3 class="panel-title">
												监控点名称<span class="fa fa-folder-o" style="float: right;"></span><span
													class="fa fa-folder-o" style="float: right;"></span><span
													class="fa fa-folder-o" style="float: right;"></span>
											</h3>
										</div>
										<div class="panel-body video-9">
											<video controls="controls" autoplay="autoplay"> <source
												src="http://www.100sucai.com/img/video/happyfit2.mp4"></video>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="container">
							<div class="row">
								<div class="col-md-4">
									<div class="panel panel-primary" style="margin-top: 10px;">
										<div class="panel-heading">
											<h3 class="panel-title">
												监控点名称<span class="fa fa-folder-o" style="float: right;"></span><span
													class="fa fa-folder-o" style="float: right;"></span><span
													class="fa fa-folder-o" style="float: right;"></span>
											</h3>
										</div>
										<div class="panel-body video-9">
											<video controls="controls" autoplay="autoplay"> <source
												src="http://www.100sucai.com/img/video/happyfit2.mp4"></video>
										</div>
									</div>
								</div>
								<div class="col-md-4">
									<div class="panel panel-primary" style="margin-top: 10px;">
										<div class="panel-heading">
											<h3 class="panel-title">
												监控点名称<span class="fa fa-folder-o" style="float: right;"></span><span
													class="fa fa-folder-o" style="float: right;"></span><span
													class="fa fa-folder-o" style="float: right;"></span>
											</h3>
										</div>
										<div class="panel-body video-9">
											<video controls="controls" autoplay="autoplay"> <source
												src="http://www.100sucai.com/img/video/happyfit2.mp4"></video>
										</div>
									</div>
								</div>
								<div class="col-md-4">
									<div class="panel panel-primary" style="margin-top: 10px;">
										<div class="panel-heading">
											<h3 class="panel-title">
												监控点名称<span class="fa fa-folder-o" style="float: right;"></span><span
													class="fa fa-folder-o" style="float: right;"></span><span
													class="fa fa-folder-o" style="float: right;"></span>
											</h3>
										</div>
										<div class="panel-body video-9">
											<video controls="controls" autoplay="autoplay"> <source
												src="http://www.100sucai.com/img/video/happyfit2.mp4"></video>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="container">
							<div class="row">
								<div class="col-md-4">
									<div class="panel panel-primary" style="margin-top: 10px;">
										<div class="panel-heading">
											<h3 class="panel-title">
												监控点名称<span class="fa fa-folder-o" style="float: right;"></span><span
													class="fa fa-folder-o" style="float: right;"></span><span
													class="fa fa-folder-o" style="float: right;"></span>
											</h3>
										</div>
										<div class="panel-body video-9">
											<video controls="controls" autoplay="autoplay"> <source
												src="http://www.100sucai.com/img/video/happyfit2.mp4"></video>
										</div>
									</div>
								</div>
								<div class="col-md-4">
									<div class="panel panel-primary" style="margin-top: 10px;">
										<div class="panel-heading">
											<h3 class="panel-title">
												监控点名称<span class="fa fa-folder-o" style="float: right;"></span><span
													class="fa fa-folder-o" style="float: right;"></span><span
													class="fa fa-folder-o" style="float: right;"></span>
											</h3>
										</div>
										<div class="panel-body video-9">
											<video controls="controls" autoplay="autoplay"> <source
												src="http://www.100sucai.com/img/video/happyfit2.mp4"></video>
										</div>
									</div>
								</div>
								<div class="col-md-4">
									<div class="panel panel-primary" style="margin-top: 10px;">
										<div class="panel-heading">
											<h3 class="panel-title">
												监控点名称<span class="fa fa-folder-o" style="float: right;"></span><span
													class="fa fa-folder-o" style="float: right;"></span><span
													class="fa fa-folder-o" style="float: right;"></span>
											</h3>
										</div>
										<div class="panel-body video-9">
											<video controls="controls" autoplay="autoplay"> <source
												src="http://www.100sucai.com/img/video/happyfit2.mp4"></video>
										</div>
									</div>
								</div>
							</div>
						</div>

					</div>
				</div>

			</div>
		</div>
	</div>

</body>
</html>
