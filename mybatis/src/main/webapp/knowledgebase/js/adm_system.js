//还原备份
function backup_ok(){
	$.ajax({
		type:'post',
		url:"/mybatis/SystemConfigController/backup.do",
		dataType:"json",
		  success:function(data){
			   if(data["flag"] == "chenggong"){
                   $("#add_result").html("系统备份成功");
                   $("#result").modal('show');
                   get_category();
               }else if(data["flag"] == "shibai"){
                   $("#add_result").html("系统备份失败");
                   $("#result").modal('show');
               }
          }
	})
}

function restore_ok(){
	$.ajax({
		type:'post',
		url:"/mybatis/SystemConfigController/restore.do",
		dataType:"json",
		  success:function(data){
			   if(data["flag"] == "chenggong"){
                   $("#add_result").html("系统还原成功");
                   $("#result").modal('show');
                   get_category();
               }else if(data["flag"] == "shibai"){
                   $("#add_result").html("系统还原失败");
                   $("#result").modal('show');
               }
          }
	})
}