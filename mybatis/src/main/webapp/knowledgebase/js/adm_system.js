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

function build_ok(){
	$.ajax({
		type:'post',
		url:"/mybatis/SystemConfigController/build.do",
		dataType:"json",
		  success:function(data){
			   if(data["flag"] == "chenggong"){
                   $("#add_result").html("初始化索引成功");
                   $("#result").modal('show');
                   get_category();
               }else if(data["flag"] == "shibai"){
                   $("#add_result").html("初始化索引失败");
                   $("#result").modal('show');
               }
          }
	})
}

function build_word_ok(){
	$.ajax({
		type:'post',
		url:"/mybatis/SystemConfigController/build_word.do",
		dataType:"json",
		  success:function(data){
			   if(data["flag"] == "chenggong"){
                   $("#add_result").html("建立Word索引成功");
                   $("#result").modal('show');
                   get_category();
               }else if(data["flag"] == "shibai"){
                   $("#add_result").html("建立Word索引失败");
                   $("#result").modal('show');
               }
          }
	})
}

function build_pdf_ok(){
	$.ajax({
		type:'post',
		url:"/mybatis/SystemConfigController/build_pdf.do",
		dataType:"json",
		  success:function(data){
			   if(data["flag"] == "chenggong"){
                   $("#add_result").html("建立PDF索引成功");
                   $("#result").modal('show');
                   get_category();
               }else if(data["flag"] == "shibai"){
                   $("#add_result").html("建立PDF索引失败");
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