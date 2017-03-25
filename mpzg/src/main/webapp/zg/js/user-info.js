/**
 * 跳转用户信息界面
 */
function checkUserInfo(){
	var src=getBasePath()+"zgmp/check_userinfo";
	  sendAjaxRequest(src,"",checkUserInfoSuccess,error,true);
}
function checkUserInfoSuccess(data){
//	alert("login out");
	var username=data["username"];
	if(username!="none"){
		window.location.href=getBasePath()+"zgmp/check_userinfo";
	}
	else{
	}
}

/**
 * 得到提交的数据
 * @returns {{}}
 */
function getUserinfoData() {
    var data = {};
    //id
    var user_id = trimValue($("#user_id").val());
    //用户名
    var user_name = trimValue($("#my_user_name").val());
    //真实姓名
    var user_realname = trimValue($("#user_realname").val());
    //电子邮件
    var user_email = trimValue($("#user_email").val());
    //用户性别
    var user_sex = $("#user_sex").find("option:selected").attr("value");
    //用户电话
    var user_phone_number = trimValue($("#user_phone_number").val());
    //用户角色
    var user_role = $("#user_role").find("option:selected").attr("value");
    /*console.log("user_id = " + user_id);
    console.log("user_name = " + user_name);
    console.log("user_realname = " + user_realname);
    console.log("user_email = " + user_email);
    console.log("user_sex = " + user_sex);
    console.log("user_phone_number = " + user_phone_number);
    console.log("user_role = " + user_role);*/
   

    data.user_id = user_id;
    data.user_name = user_name;
    data.user_realname = user_realname;
    data.user_email = user_email;
    data.user_sex = user_sex;
    data.user_phone_number = user_phone_number;
    data.user_role = user_role;
    
    return data;

}
/**
 * 判断必填内容是否为空
 * @param datas
 * @returns {boolean}
 */
function checkUserInfoValue(datas) {

    if(valIsNull(datas.user_name)) {
        showAlertBox("用户名不能为空");
        return false;
    }
    if(valIsNull(datas.user_realname)) {
        showAlertBox("真实姓名不能为空");
        return false;
    }
    if(valIsNull(datas.user_email)) {
        showAlertBox("电子邮件不能为空");
        return false;
    }

    if(valIsNull(datas.user_sex)) {
        showAlertBox("性别不能为空");
        return false;
    }
    if(valIsNull(datas.user_phone_number)) {
        showAlertBox("电话号码不能为空");
        return false;
    }
    if(valIsNull(datas.user_role)) {
        showAlertBox("用户角色不能为空");
        return false;
    }
    
    return true;

}

/**
 * 修改个人信息
 */
function modifyUserInfo(){
	var datas = getUserinfoData();
	var src=getBasePath()+"modifyUserInfo";
	if(checkUserInfoValue(datas)){
		sendAjaxRequest(src,datas,modifyUserInfoSuccess,error,true);
	}
	  
}
function modifyUserInfoSuccess(){
	showSuccessBox  ("修改成功！");
	/*checkUserInfo();*/
}

/**
 * 得到修改密码数据
 */
function getUserPswData(){
	var data ={};
	var user_id = trimValue($("#user_id").val());
	//旧密码
	var user_psw = trimValue($("#user_psw").val());
	//新密码
	var user_psw_new = trimValue($("#user_psw_new").val());
	//确认密码
	var user_psw_confirm = trimValue($("#user_psw_confirm").val());
	
	data.user_id=user_id;
	data.user_psw = user_psw;
	data.user_psw_new = user_psw_new;
	data.user_psw_confirm = user_psw_confirm;
	
	console.log("user_id = " + user_id);
	console.log("user_psw = " + user_psw);
	console.log("user_psw_new = " + user_psw_new);
	console.log("user_psw_confirm = " + user_psw_confirm);
	return data;
	
}

/**
 * 判断密码输入是否正确
 * @param datas
 * @returns {Boolean}
 */
function checkUserPswValue(datas){
	if(valIsNull(datas.user_psw)){
		showAlertBox("旧密码不能为空！");
        return false;
	}
	/*var psw = ${UserInfo.userPassword};
	if(datas.user_psw != psw){
		showAlertBox("原始密码不正确！");
        return false;
	}*/
	if(valIsNull(datas.user_psw_new)){
		showAlertBox("新密码不能为空！");
        return false;
	}
	if(valIsNull(datas.user_psw_confirm)){
		showAlertBox("确认密码不能为空！");
        return false;
	}
	if(datas.user_psw_new != datas.user_psw_confirm){
		showAlertBox("两次密码输入不同！");
        return false;
	}
	return true;
}
/**
 * 判断旧密码是否正确,若正确进入修改密码
 */
function getPsw(){
	var data = getUserinfoData();
	var id = data.user_id;
	var user_psw = trimValue($("#user_psw").val());
	var src =  getBasePath() + "getUserPsw";
	sendAjaxRequest(src,{"id":id,"user_psw":user_psw},function (result) {
		if(result == 1) {
			var datas = getUserPswData();
			var src = getBasePath() + "modifyUserPsw";
			sendAjaxRequest(src,datas,modifyUserPswSuccess,error,true);
		}else{
	        showAlertBox("密码错误！");
	    }     
	    }, function (data) {
	    	console.log("error");
	    });
}

/**
 * 修改密码
 */
function modifyUserPsw(){
	var datas = getUserPswData();
	if(checkUserPswValue(datas)){
		//判断密码是否正确，输入正确才能修改
		getPsw();
	}
}
function modifyUserPswSuccess(){
	setAlertContent("success-content","修改成功，请重新登录！");
	$("#success-box").modal('show');
    $("#success-box").on('hidden.bs.modal',function(){
		window.location.href=getBasePath()+"login_check";
	});
}