// 数据备份
function backup_ok() {
	$.ajax({
		type : 'post',
		url : "/mybatis/SystemConfigController/backup.do",
		dataType : "json",
		success : function(data) {
			if (data["flag"] == "chenggong") {
				$("#add_result").html("系统备份成功");
				$("#result").modal('show');
				get_category();
			} else if (data["flag"] == "shibai") {
				$("#add_result").html("系统备份失败");
				$("#result").modal('show');
			}
		}
	})
}

// 数据还原
function restore_ok() {
	$.ajax({
		type : 'post',
		url : "/mybatis/SystemConfigController/restore.do",
		dataType : "json",
		success : function(data) {
			if (data["flag"] == "chenggong") {
				$("#add_result").html("系统还原成功");
				$("#result").modal('show');
				get_category();
			} else if (data["flag"] == "shibai") {
				$("#add_result").html("系统还原失败");
				$("#result").modal('show');
			}
		}
	})
}

// 初始化索引
function build_ok() {
	$.ajax({
		type : 'post',
		url : "/mybatis/SystemConfigController/build.do",
		dataType : "json",
		success : function(data) {
			if (data["flag"] == "chenggong") {
				$("#add_result").html("初始化索引成功");
				$("#result").modal('show');
				get_category();
			} else if (data["flag"] == "shibai") {
				$("#add_result").html("初始化索引失败");
				$("#result").modal('show');
			} else if (data["flag"] == "waitforcheck") {
				$("#add_result").html("初始化失败，存在待审核文件");
				$("#result").modal('show');
			}
		}
	})
}

// 创建Word索引
function build_word_ok() {
	$.ajax({
		type : 'post',
		url : "/mybatis/SystemConfigController/build_word.do",
		dataType : "json",
		success : function(data) {
			if (data["flag"] == "chenggong") {
				$("#add_result").html("建立Word索引成功");
				$("#result").modal('show');
				get_category();
			} else if (data["flag"] == "shibai") {
				$("#add_result").html("建立Word索引失败");
				$("#result").modal('show');
			} else if (data["flag"] == "waitforcheck") {
				$("#add_result").html("建立索引失败，存在待审核文件");
				$("#result").modal('show');
			}
		}
	})
}

// 创建PDF索引
function build_pdf_ok() {
	$.ajax({
		type : 'post',
		url : "/mybatis/SystemConfigController/build_pdf.do",
		dataType : "json",
		success : function(data) {
			if (data["flag"] == "chenggong") {
				$("#add_result").html("建立PDF索引成功");
				$("#result").modal('show');
				get_category();
			} else if (data["flag"] == "shibai") {
				$("#add_result").html("建立PDF索引失败");
				$("#result").modal('show');
			} else if (data["flag"] == "waitforcheck") {
				$("#add_result").html("建立索引失败，存在待审核文件");
				$("#result").modal('show');
			}
		}
	})
}
