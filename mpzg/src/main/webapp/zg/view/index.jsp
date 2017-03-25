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
<!-- 分屏样式 -->
<style type="text/css">
.footerCon {
	width: 320px;
	margin: 0 auto;
}

.footerCon .dropDiv {
	background: #fff;
	margin: 10px 0 0 0;
	float: left;
}

.footerCon .fpmodel {
	display: none;
	float: right;
	width: 160px;
}

.footerCon .saveBtn {
	margin: 10px 0 0 10px;
	padding: 2px 10px;
	border: 1px solid #CCC;
	cursor: pointer;
}

.clearFix:after {
	content: ’’;
	display: block;
	height: 0;
	overflow: hidden;
	clear: both;
}

.footer { /* 	height: 40px; */
	/* 	background: #ABABAB; */
	position: fixed;
	bottom: 0px;
	width: 100%;
}

.footer .ulTab {
	list-style-type: none;
	width: 200px;
	overflow: hidden;
	float: left;
}

.footer .ulTab li {
	float: left;
/* 	height: 16px; */
	padding: 12px 15px;
	cursor: pointer;
}

.footer .ulTab li.currentLi {
	background: #fff;
}

.tabImg {
	width: 18px;
	height: 14px;
	border: 1px solid #707070;
	background: #fff;
}

.tabImg td {
	width: 7px;
	height: 4px;
	border: 1px solid #707070;
}

.divImg {
	border-width: 2px;
	height: 12px;
}

.td3Img td {
	height: 2px;
}

.currentLi .tabImg,.currentLi .tabImg td {
	border-color: #1e7be4;
}

.topbarDiv {
	position: absolute;
	left: 0;
	top: 0;
	right: 0;
	border: 1px solid #dedede;
	z-index: 1;
	height: 25px;
	padding: 3px;
	background: #61C0FA;
	display: none;
}

.changeBtn {
	cursor: pointer;
	padding: 2px 10px;
	float: left;
}

.dropDiv,.footer .dropDiv {
	position: relative;
	width: 100px;
	z-index: 100;
}

.dropDiv .curSrc,.footer .dropDiv .curSrc {
	display: inline-block;
	height: 20px;
	line-height: 20px;
	padding: 0 2px;
}

.dropDiv ul,.footer .dropDiv ul {
	position: absolute;
	left: -1px;
	top: 20px;
	background: #fff;
	width: 100px;
	border: 1px solid #1E7BE4;
	display: none;
}

.dropDiv ul li,.footer .dropDiv ul li {
	line-height: 20px;
	padding: 0 2px;
}

.dropDiv ul li.currentSrc,.footer .dropDiv ul li.currentSrc {
	background: #1E7BE4;
	color: #fff;
	cursor: pointer;
}

.dropDiv ul li:hover,.footer .dropDiv ul li:hover {
	background: #AEC9F3;
	color: #fff;
	cursor: pointer;
}

.optionsWrap {
	float: right;
}

.optionsWrap a {
	font-family: 'MicroSoft YaHei';
	color: #fff;
	text-decoration: none;
	float: left;
}

.optionsWrap a:hover {
	color: #fdd;
	cursor: pointer;
}

/*分屏模块布局*/
.container {
	position: relative;
}

.fp-box {
	position: absolute;
	border: 1px solid #000;
	background: #fff;
}

.fp-1-1 {
	height: 80%;
	width: 80%;
	left: 10%;
}

.fp-4-1 {
	margin-left: 50px;
	height: 280px;
	width: 500px;
}

.fp-4-2 {
	height: 280px;
	width: 500px;
	margin-left: 600px;
}

.fp-4-3 {
	margin-left: 50px;
	height: 280px;
	width: 500px;
	margin-top: 290px;
}

.fp-4-4 {
	height: 280px;
	width: 500px;
	margin-top: 290px;
	margin-left: 600px;
}

.fp-9-1 {
	margin-left: 60px;
	height: 180px;
	width: 300px;
}

.fp-9-2 {
	height: 180px;
	width: 300px;
	margin-left: 410px;
}

.fp-9-3 {
	height: 180px;
	width: 300px;
	margin-left: 760px;
}

.fp-9-4 {
	margin-left: 60px;
	height: 180px;
	width: 300px;
	margin-top: 190px;
}

.fp-9-5 {
	height: 180px;
	width: 300px;
	margin-top: 190px;
	margin-left: 410px;
}

.fp-9-6 {
	height: 180px;
	width: 300px;
	margin-top: 190px;
	margin-left: 760px;
}

.fp-9-7 {
	margin-left: 60px;
	height: 180px;
	width: 300px;
	margin-top: 380px;
}

.fp-9-8 {
	height: 180px;
	width: 300px;
	margin-top: 380px;
	margin-left: 410px;
}

.fp-9-9 {
	height: 180px;
	width: 300px;
	margin-top: 380px;
	margin-left: 760px;
}
</style>
</head>
<body>

	<jsp:include page="../templates/header.jsp"></jsp:include>
	<div id="middle">
		<jsp:include page="../view/menu_monitoring.jsp"></jsp:include>
		<div class="right" id="mainFrame">

			<div class="right_cont">
				<div class="title_right">
					<strong>实时监控</strong> 
					<span style="float: right;">
						<button onclick="" type="button" class="btn btn-xs btn-primary">修改</button>
						<button onclick="" type="button" class="btn btn-xs btn-primary">修改</button>
						<button onclick="" type="button" class="btn btn-xs btn-primary">修改</button>
						<button onclick="" type="button" class="btn btn-xs btn-primary">修改</button>
						<button onclick="" type="button" class="btn btn-xs btn-primary">修改</button>
					</span>
				</div>

				<div class="container" id="displayArea">
					<!-- 分屏内容区 -->
				</div>
				<div class="footer">
					<div class="footerCon clearFix">
						<ul class="ulTab">
							<li class="currentLi" data-fp="1" onclick="changeModel(this)">
								<div class="tabImg divImg"></div>
							</li>
							<li data-fp="4" onclick="changeModel(this)">
								<table class="tabImg" cellpadding="0" cellspacing="0">
									<tr>
										<td></td>
										<td></td>
									</tr>
									<tr>
										<td></td>
										<td></td>
									</tr>
								</table>
							</li>
							<li data-fp="9" onclick="changeModel(this)">
								<table class="tabImg td3Img" cellpadding="0" cellspacing="0">
									<tr>
										<td></td>
										<td></td>
										<td></td>
									</tr>
									<tr>
										<td></td>
										<td></td>
										<td></td>
									</tr>
									<tr>
										<td></td>
										<td></td>
										<td></td>
									</tr>
								</table>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	
	<script type="text/javascript" src="<%=basePath%>zg/js/splitScreen.js"></script>
	<script type="text/javascript">
		//分屏数据
		var iframes = [ {
			id : 'frames_1',
			frameName : '百度一下',
			src : getBasePath()+'zg/video/in.mp4'
		}, {
			id : 'frames_1',
			frameName : '百度地图',
			src : 'http://www.100sucai.com/img/video/happyfit2.mp4'
		}, {
			id : 'frames_1',
			frameName : '百度下',
			src : 'http://www.100sucai.com/img/video/happyfit2.mp4'
		}, {
			id : 'frames_1',
			frameName : '百度视频',
			src : ''
		}, {
			id : 'frames_1',
			frameName : '百度新闻2',
			src : ''
		}, {
			id : 'frames_1',
			frameName : 'test6',
			src : 'http://www.100sucai.com/img/video/happyfit2.mp4'
		}, {
			id : 'frames_1',
			frameName : '百度新闻',
			src : 'http://www.100sucai.com/img/video/happyfit2.mp4'
		}, {
			id : 'frames_1',
			frameName : '88888',
			src : 'http://www.100sucai.com/img/video/happyfit2.mp4'
		}, {
			id : 'frames_1',
			frameName : '百度更多',
			src : 'http://www.100sucai.com/img/video/happyfit2.mp4'
		},

		];
		window.onload = function() {
			Panel.resize();
		}
		window.onresize = function() {
			Panel.resize();
		}

		//初始化分屏
		var splitScreen = new splitScreen($('#displayArea'), iframes);

		//监听removeSettingCls自定义事件
		splitScreen._on('removeSettingCls', function() {
			splitScreen.toggleTopbar(function() {
				$('.ulTab li[data-fp="setting"]').removeClass('currentLi');
			});
		});
		//分屏切换
		function changeModel(obj) {
			var fpmodel = $(obj).attr('data-fp');
			if (fpmodel != "setting") {
				splitScreen.screenMode(fpmodel, function() {
					$(obj).addClass('currentLi').siblings().removeClass(
							'currentLi');
				});
			} else {
				splitScreen.toggleTopbar(function() {
					$(obj).toggleClass('currentLi');
				});
			}
		}
	</script>
</body>
</html>
