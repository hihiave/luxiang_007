/**
 * 跳转监控界面（主界面）
 */
function checkMonitoring(){
	var src=getBasePath()+"zgmp/check_monitoring";
	  sendAjaxRequest(src,"",checkMonitoringSuccess,error,true);
}
function checkMonitoringSuccess(data){
//	alert("login out");
	var username=data["username"];
	if(username!="none"){
		window.location.href=getBasePath()+"zg/view/index_monitoring.jsp";
	}
	else{
	}
}


/**
 * 退出
 */
$(function () {
    $("#logout").click(function(){
	  var src=getBasePath()+"logout";
	  sendAjaxRequest(src,"",logOutSuccess,error,true);
    });
    
});
/**
 * 退出成功操作
 */
function logOutSuccess(data){
//	alert("login out");
	var username=data["username"];
	if(username!="none"){
		/*window.location.href=getBasePath()+"login_check";*/
		setAlertContent("success-content",username+"再见");
		$("#success-box").modal('show');
	    $("#success-box").on('hidden.bs.modal',function(){
			window.location.href=getBasePath()+"login_check";
		});
	}
	else{
		setAlertContent("alert-content","登录已过期请重新登录！");
		$("#alert-box").modal('show');
	    $("#alert-box").on('hidden.bs.modal',function(){
			window.location.href=getBasePath()+"login_check";
		});
	}
}

function error() {
	setAlertContent("alert-content", "请求异常，请稍后重试!");
	$("#alert-box").modal('show');
}

/**
 * 跳转卡口图片页面
 */
function getImagesPage(){
    window.location.href=getBasePath()+"zg/view/images_operate.jsp";
}
