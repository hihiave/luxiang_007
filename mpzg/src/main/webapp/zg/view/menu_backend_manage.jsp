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



</head>
<body>

<div class="left">

			<div class="side sucaihuo-container">
				<div class="sucaihuo-content ">
					<div class="container">
						<div class="row">
							<div class="col-sm-4">
								<nav class="nav">
								<ul class="metisFolder">
									<li><a href="#"> <span class="fa fa-users"></span>后台管理</a>
										<ul>
											<li onclick=""><a href="#"> <span class="fa fa-user"></span>用户管理</a></li>
											<li onclick=""><a href="#"> <span class="fa fa-user"></span>卡口管理</a></li>
								         </ul>
								    </li>
								</ul>
								</nav>
							</div>

						</div>
					</div>
				</div>
			</div>


		</div>
	
</body>
</html>
