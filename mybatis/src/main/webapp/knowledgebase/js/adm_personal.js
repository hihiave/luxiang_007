/**
 * Created by 95 on 2016/10/17.
 */
//window.onload = function(){
//    var oldpswobj = document.getElementById("oldpsw");
//    var psw1obj = document.getElementById("password1");
//    var psw2obj = document.getElementById("password2");
//    var usernameobj = document.getElementById("show_user_name");
//    console.log(usernameobj.innerText);
//}
//function change_msg(){
//	var usertruename=document.getElementById("usertruename");
//	var useremail=document.getElementById("usermail");
//	var btnobj=document.getElementById("bbtn_changemsg");
//	btnobj.onclick=changemsg;
//	function changemsg(){
//		alert(usertruename.value());
//	}
//}
function changemsg(){
	var usertruename=document.getElementById("usertruename");
	var useremail=document.getElementById("useremail");
	var id=document.getElementById("userid");
	if((usertruename.value!="")&&(useremail.value!="")){
        $.ajax({
            type:'post',
            url:"/mybatis/UserInfoController/change_msg.do",
            data:{"truename":usertruename.value,"email":useremail.value,"userid":id.value},
            dataType:"json",
            success:function(data){
                var result = data["flag"];
                if(result){
                	$('#changemsg').modal('toggle');
                	$("#result_msg").html("修改信息成功!");
                    $("#result").modal('show');
                   
                }else{
                    $('#changemsg').modal('toggle');
                	$("#result_msg").html("修改信息失败!");
                    $("#result").modal('show');
                }
                $("#show_user_truename").html(usertruename.value);
        		$("#show_register_email").html(useremail.value);
            }
        })
	}
	else{
		document.getElementById("checkmsg").innerHTML = "<font color='red' size='2px'>请检查填写是否为空!</font>";
		
	}
}

function change_password(){
    var oldpswobj=document.getElementById("oldpsw");
    var psw1obj=document.getElementById("password1");
    var psw2obj=document.getElementById("password2");
    var btnobj=document.getElementById("btnclick");
    var username = document.getElementById("show_user_name").innerText;
    console.log(username);

    oldpswobj.onblur=checkoldpsw;
    psw1obj.onblur=checkpsw;
    btnobj.onclick=checkrepsw;
    function checkoldpsw(){
//                    if ((oldpswobj.value)!=oldpsw){
//                        var msg="<font color='red' size='2px'>原密码错误!</font>";
//                    }
//                    else{
//                        var msg="";
//                    }
        if(oldpswobj.value != ""){
//                        console.log(oldpswobj.value);
            $.ajax({
                type:'post',
                url:"/mybatis/UserInfoController/get_psw.do",
                data:{"username":username,"oldpassword":oldpswobj.value},
                dataType:"json",
                success:function(data){
                    if(data["flag"] == false){
                        document.getElementById("spanusername").innerHTML = "<font color='red' size='2px'>原密码错误!</font>";
                    }else{
                        document.getElementById("spanusername").innerHTML = "<font color='red' size='2px'>原密码正确!</font>";
                    }
                }
            })
        }else{
            document.getElementById("spanusername").innerHTML = "<font color='red' size='2px'>请输入密码!</font>";
        }
        var span=document.getElementById("spanusername");
//                    span.innerHTML=msg;
        return;
    }
    function checkpsw(){

        if (psw1obj.value.length<6) {
            var msg="<font color='red' size='2px'>密码应大于六位数!</font>";
        }
        else{
            var msg="";
        }
        var span=document.getElementById("spanpsw");
        span.innerHTML=msg;
        return;
    }
    function checkrepsw(){
        if(psw1obj.value!=psw2obj.value ){
        	 document.getElementById("spanrepsw").innerHTML = "<font color='red' size='2px'>密码不一致!</font>";
            return;
        }else if(oldpswobj.value == ""){
            document.getElementById("spanusername").innerHTML = "<font color='red' size='2px'>原密码不能为空!</font>";
            return;
        }else if(psw1obj.value.length < 6){
            document.getElementById("spanpsw").innerHTML = "<font color='red' size='2px'>密码应大于六位数!</font>";
        }
        else{
            var msg="";
            $.ajax(
                {
                    type:'post',
                    url:"/mybatis/UserInfoController/alterpsw.do",
                    data:{"username":username,"oldpassword":oldpswobj.value,"newpassword":psw1obj.value},
                    dataType:"json",
                    success:function(data){
                        var result = data["flag"];
                        if(result){
                        	$('#changepsw').modal('toggle');
                        	$("#result_msg").html("修改信息成功!");
                            $("#result").modal('show');
                        }else{
                        	$('#changepsw').modal('toggle');
                        	$("#result_msg").html("修改信息失败!");
                            $("#result").modal('show');
                        }
                    }
                }
            )
        }
        var span=document.getElementById("spanrepsw");
        span.innerHTML=msg;
        return;
    }
}