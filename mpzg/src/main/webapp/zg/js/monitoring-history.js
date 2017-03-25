/**
 * 跳转监控历史界面
 */
function monitoringHistory(){
	var src=getBasePath()+"zgmp/check_monitoring_history";
	  sendAjaxRequest(src,"",checkMonitoringHistory,error,true);
}
function checkMonitoringHistory(data){
//	alert("login out");
	var username=data["username"];
	if(username!="none"){
		window.location.href=getBasePath()+"zgmp/check_monitoring_history";
	}
	else{
	}
}