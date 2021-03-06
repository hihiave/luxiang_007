<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <link rel="stylesheet" href="../knowledgebase/css/bootstrap.min.css">
    <script src="../knowledgebase/js/jquery.min.js"></script>
    <script src="../knowledgebase/js/jquery.cookie.min.js"></script>
    <script src="../knowledgebase/js/bootstrap.min.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <style type="text/css">
        .login {
            width: 100%;
            min-width: 1200px;
            background-color: white;
        }

        .login-top {
            height: 60px;
            margin-left: 50px;
            background-color: white;
        }

        .login-mid {
            background: url(../knowledgebase/img/img_4.jpg) top center no-repeat #fff;
            margin-top: 28px;
            height: 480px;
            min-width: 1200px;
        }

        .biao1 {
            width: 280px;
            height: auto;
            background-image: url(../knowledgebase/img/white.png);
            border-radius: 5px;
            padding: 10px 20px;
            position: absolute;
            right: 15%;
            margin-top: 100px;
        }

        .name1 {
            font-size: 22px;
            line-height: 33px;
            color: #FFFFFF;
            padding-bottom: 22px;
            height: 35px;
            margin-bottom: 10px;
        }

        .footer {
            position: fixed;
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

        .loginform {
            line-height: 18px;
            margin-bottom: 10px;
            font-size: 14px;
        }

        .loginform.loginusername input[type="text"], .loginform.loginpassword input[type="password"] {
            width: 80%;
            border: none;

            border: 1px solid #ffffff;
            padding: 8px 0px 8px 5px;

        }

        body {
            height: 100%;
            min-width: 1000px;
        }
    </style>

  
   <script type="text/javascript">
   function keylogin(){
	   if(event.keyCode==13){
		   document.getElementById("denglu").click();
	   }
   }
   </script>
</head>
<body onkeydown="keylogin();">
<div class="login">
    <div class="login-top">
    	<br/>
        <img src="img/3.png" width="260px">
    </div>
    <div class="login-mid">

        <div class="biao1">
            <%--<form action="/mybatis/UserInfoController/login.do" method="post" >--%>
                <div class="name1">用户平台登录</div>
                <div class="loginform loginusername" style="margin-top:5px;margin-bottom:5px;">
                  <span style="color:#FFFFFF;">账号：</span> <input type="text" id="username" name="username" placeholder="请输入用户名">
                </div>
                <div id="spanusername" style="height:16px"></div>
                <div class="loginform loginpassword" style="margin-top:5px;margin-bottom:5px;">
           		 <span style="color:#FFFFFF;">密码：</span> <input type="password" name="password" id="password" placeholder="请输入密码">
                </div>
                
                <div id="spanpassword" style="height:16px"></div>
				<div style="float:right"><a style="color:#ffffff;text-decoration:none;" id="forget_psw" href="#">忘记密码？</a></div>
				<div style="clear:both;"></div>
                <div class="button-group" style="margin-top:5px;margin-bottom:5px;">
                    <input type="button" class="btn btn-primary" id="denglu" value="登录">
                    <input type="button" class="btn btn-primary" value="注册" style="float:right;"
                           onclick="window.open('../knowledgebase/Register.jsp','_self')">
                </div>
            <%--</form>--%>
        </div>
    </div>
</div>
<div class="footer"><span class="footerText">Copyright © 1956-2016 电子科技大学</span></div>
<script type="text/javascript">
    $(function(){
    	$("#forget_psw").click(function(){
    		$.ajax(
    	            {
    	                type:'post',
    	                url:"/mybatis/UserInfoController/get_admin_email.do",
    	                dataType:'json',
    	                success:function(data){
    	                	var email=data["email"];
    	                	alert("请联系管理员，管理员邮箱"+email);
    	                }
    	            }
    	        )
    		
    	})
        $("#denglu").click(function(){
            var username = $("#username").val();
            var password = $("#password").val();
            if(username == ""){
                $("#spanusername").html("<font color='red' size='2px'>用户名不能为空!</font>");
                return;
            }else{
                $("#spanusername").html("");
            }
            if(password.length < 6 && password.length > 0){
                $("#spanpassword").html("<font color='red' size='2px'>密码长度不能小于六位数!</font>");
                return;
            }else{
                $("#spanpassword").html("");
            }
            if(password == ""){
                $("#spanpassword").html("<font color='red' size='2px'>密码不能为空!</font>");
                return
            }else{
                $("#spanpassword").html("");
            }
            $.ajax({
                type:'post',
                url:"/mybatis/UserInfoController/login.do",
                dataType:'json',
                data:{"username":username,"password":password},
                success:function(data){
                    if(data["data"] == "LoginSuccess"){
                        window.open('../knowledgebase/adm-personal.jsp','_self');
                    }else if(data["data"] == "CheckNotPass"){
                        console.log("用户未通过审核!");
                        $("#spanusername").html("");
                        $("#spanpassword").html("<font color='red' size='2px'>用户审核未通过!</font>");
                    }else{
                        console.log("登录失败!");
                        $("#spanusername").html("");
                        $("#spanpassword").html("<font color='red' size='2px'>用户名或密码错误!</font>");
                    }
                }
            })
        })
    })
</script>

</body>
</html>