<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="../knowledgebase/css/bootstrap.min.css">
<script src="../knowledgebase/js/jquery.min.js"></script>
<script src="../knowledgebase/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
	.login{
		width: 100%;
		min-width: 1200px;
		background-color: white;
	}
	.login-top{
		height: 60px;
		margin-left: 50px;
		background-color: white;
	}
	.login-mid{
		background: url(../knowledgebase/img/img_4.jpg) center center no-repeat #fff;
		
		height: 480px;
		min-width: 1200px;
	}
	.biao2{
		width: 350px;
		height: 325px;
		background-image:url(../knowledgebase/img/white.png);
		border-radius: 5px;
		padding: 22px 30px;
		position: absolute;
		right: 15%;
		margin-top:80px;
	}
	.name2{
		font-size: 22px;
		line-height: 33px;
		color: #ffffff;
		padding-bottom: 22px;
		height: 35px;
		margin-bottom: 10px;
	}
    .footer {
        position: absolute;
        top: 94%;
        height: 6%;
        background-color: #e1e1e1;
        width: 100%;

        text-align: center;
        min-width: 1190px;
    }

    .footer span.footerText {
        font-size: 14px;
        margin-top: 0.5%;
        color: #606060;
        display: inline-block;
    }
.loginform{
line-height: 18px;
margin-bottom: 10px;
	font-size: 14px;
}
.loginform.loginusername input[type="text"], .loginform.loginpassword input[type="password"] {
	width: 75%;
	border: none;
	
	border: 1px solid #ffffff;
	padding: 8px 0px 8px 5px;
	
}
body {
  height: 100%;
  min-width: 1000px;
  cursor: default;
}
.container{
	
}
</style>

<%--<script type="text/javascript">--%>
 <%--window.onload=function(){--%>
 	<%--var usernameobj=document.getElementById("username1");--%>
 	<%--var passwordobj=document.getElementById("password1");--%>
 	<%--var repasswordobj=document.getElementById("password2");--%>
 	<%--var registerobj=document.getElementById("register");--%>
 	<%--passwordobj.onblur=checkpassword;--%>
 	<%--registerobj.onclick=check;--%>
  	<%--function checkpassword(){--%>
 		<%--if (passwordobj.value.length==0) {--%>
 			<%--var msg="<font color='red' size='2px'>密码不能为空!</font>";--%>
 		<%--}--%>
 		<%--else if (passwordobj.value.length<6&&passwordobj.value.length>0) {--%>
 			<%--var msg="<font color='red' size='2px'>密码应大于六位数！</font>";--%>
 		<%--}--%>
 		<%--else{--%>
 			<%--var msg="";--%>
 		<%--}--%>
 		<%--var span=document.getElementById("passwordspan");--%>
 		<%--span.innerHTML=msg;--%>
 		<%--return ;--%>
 	<%--}--%>
 	<%--function check(){--%>
 		<%--if (passwordobj.value!=repasswordobj.value) {--%>
 			<%--var msg="<font color='red' size='2px' >两次输入密码不一致！</font>";--%>
 			<%--var span=document.getElementById("repasswordspan");--%>
 			<%--span.innerHTML=msg;--%>
 		<%--}--%>
 		<%--else if (usernameobj.value.length==0) {--%>
 			<%--alert("用户名不能为空！");--%>
 		<%--}--%>
 		<%--else{--%>
 			<%--alert("信息已提交，等待管理员审核");--%>
 		<%--}--%>
 	<%--}--%>
 <%--}--%>
<%--</script>--%>
</head>
<body>
	
	<div class="login">
		<div class="login-top">
			<img src="img/zhishiku_2.png">
		</div>
		<div class="login-mid">
	
			<div class="biao2 container" >

				 <div class="name2">用户注册</div>
				 <div class="loginform loginusername" style="margin-bottom:5px;margin-top:5px;">
				 	<span style="margin-left:14px;color:#FFFFFF;">用户名：</span><input type="text"  id="username1" name="username1" placeholder="请输入用户名">
				 </div>
				 <div style="height:16px" id="username_span" ></div>
				 <div class="loginform loginpassword" style="margin-bottom:5px;margin-top:5px;">
				 	<span style="margin-left:28px;color:#FFFFFF;">密码：</span><input type="password"    id="password1" name="password1" placeholder="请输入密码">
				 </div>
				 <div id="passwor1_span" style="height:16px" ></div>
				 <div class="loginform loginpassword" style="margin-bottom:5px;margin-top:5px;">
				 	<span style="color:#FFFFFF;">重复密码：</span><input type="password"    id="password2" name="password2" placeholder="请再次输入密码">
				 </div>
				<div id="password2_span" style="height:16px"></div>
				<div class="button-group" style="margin-top:5px;">
				<input type="button" class="btn btn-primary" id="register" value="注册" >
				<a href="../knowledgebase/Login.jsp"><input type="button" class="btn btn-primary" value="取消" style="float:right;"></a>
				</div>

     		</div>
		</div>
	</div>
	<div class="footer"> <span class="footerText">Copyright © 1956-2016 电子科技大学</span> </div>
    <script type="text/javascript">
        $(function(){
            $("#register").click(function(){
                var username1 = $("#username1").val();
                var password1 = $("#password1").val();
                var password2 = $("#password2").val();
                if(username1 == ""){
                    $("#username_span").html("<font color='red' size='2px'>用户名不能为空!</font>");
                    return;
                }else{
                    $("#username_span").html("");
                }
                if(password1.length < 6 && password1.length > 0){
                    $("#password1_span").html("<font color='red' size='2px'>密码长度不能小于六位数!</font>");
                    return;
                }else{
                    $("#password1_span").html("");
                }
                if(password1 == ""){
                    $("#password1_span").html("<font color='red' size='2px'>密码不能为空!</font>");
                    return
                }else{
                    $("#password1_span").html("");
                }
                if(password1 != password2){
                    $("#password2_span").html("<font color='red' size='2px'>两次密码不一致!</font>");
                    return;
                }else{
                    $("#password2_span").html("");
                }
                $.ajax({
                    type:'post',
                    url:"/mybatis/UserInfoController/regist.do",
                    dataType:'json',
                    data:{"username1":username1,"password1":password1},
                    success:function(data){
                        if(!data["check"]){
                            alert("注册信息提交审核!");
                        }else{
                            alert("用户名已存在!");
                        }
                    }
                })
            })
        })
    </script>
</body>
</html>