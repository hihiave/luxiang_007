$(function() {
	// get_category_select_file();
	// sub.onclick = uploadFile;
})
function get_category(obj) {
	var val = $(obj).val();
	var num = $(obj).attr("num");
	var proto = "proto" + num;
	document.getElementById(proto).value = val;
	var cate = "cate" + num;
	var child_cate = "child_cate" + num;
	var obj1 = document.getElementById(cate);
	var obj2 = document.getElementById(child_cate);
	obj1.options.length = 0;
	obj2.options.length = 0;
	var op = "<option value=''>请选择</option>";
	$("#" + child_cate + "").append(op);
	$("#" + cate + "").append(op);
	var userid;
	if (val == "公有") {
		userid = 1;
	} else if (val == "私有") {
		userid = document.getElementById("userid").value;
	}
	if (val !== "") {
		$
				.ajax({
					type : 'post',
					url : "/mybatis/CategoryController/get_category.do",
					data : {
						"userid" : userid
					},
					dataType : "json",
					success : function(data) {
						var _select = $("#" + cate + ">option");
						_select.remove();
						$("#" + cate + "").prepend(
								"<option value=''>请选择</option>");
						var all_select_category = data["category"];
						// console.log(all_select_category.length);
						for (var i = 0; i < all_select_category.length; i++) {
							var op = "<option value='"
									+ all_select_category[i].categoryName
									+ "'>"
									+ all_select_category[i].categoryName
									+ "</option>";
							$("#" + cate + "").append(op);
							// console.log(all_select_category[i].categoryName);
						}
					}
				})
	}
}

function get_child_category_select(obj) {
	var num = $(obj).attr("count");
	var type = $("#proto" + num).val();
	var userid;
	if (type == "公有")
		userid = 1;
	else if (type == "私有")
		userid = document.getElementById("userid").value;
	var child_cate = "child_cate" + num;
	var father_cate = "cate" + num;
	var obj = document.getElementById(child_cate);
	obj.options.length = 0;
	var belongto = $("#" + father_cate + "").val();

	// obj.options.length=0;
	$.ajax({
		type : 'post',
		url : "/mybatis/CategoryController/get_child_category.do",
		data : {
			"userid" : userid,
			"categoryBelongTo" : belongto
		},
		dataType : "json",
		success : function(data) {
			var category = data["category"];
			if (category.length == 0) {
				var op = "<option value=''>请选择</option>";
				$("#" + child_cate + "").append(op);
			} else {
				for (var i = 0; i < category.length; i++) {
					var op = "<option value='" + category[i].categoryName
							+ "'>" + category[i].categoryName + "</option>";
					$("#" + child_cate + "").append(op);
				}
			}

		}

	})
}

function add_one_click(obj) {
	var input_id = $(obj).attr("bid");
	return $("#" + input_id).click();
}

function del_one_click(obj) {
	var input_id = $(obj).attr("bid");
	var file_name = $(obj).attr("pid");
	$("#" + input_id).val("");
	$("#" + file_name).val("");
	// if($("#"+input_id).val() == ""){//不能写$("#"+input_id).val() == null
	// alert("xx");
	// }
	// console.log($("#"+input_id).val());

}

function changeFile(obj) {
	var file = $(obj).val();
	var input_text = $(obj).attr("pid");
	var result_text = $(obj).attr("rid");
	var arr = file.split('\\');
	var filename = arr[arr.length - 1];
	var realname_arr = filename.split('.');
	var file_last = realname_arr[realname_arr.length - 1];
	if (file_last != "pdf" && file_last != "doc" && file_last != "xls"
			&& file_last != "ppt" && file_last != "txt") {
		alert("不支持选中的文件格式！！");
		$(obj).val("");
		return;
	}
	console.log("上传文件" + file_last);
	$("#" + input_text).val(filename);
	var fileid = $(obj).attr("id");
	console.log(file);
	$.ajaxFileUpload({
		url : "/mybatis/FileUploadController/fileUpload.do",
		secureuri : false,
		fileElementId : fileid,
		data : {},
		dataType : 'json',
		success : function(data) {
			$("#info-p").html(data["message"]);
			$("#info-modal").modal("show");
			$("#" + result_text).val(data["dirPath"]);
		},
		error : function(data) {
			$("#info-p").html("网络故障，请稍后重试！！！");
			$("#info-modal").modal("show");
		}
	})
}

function changeFile_test(obj) {
	var file = $(obj).val();
	console.log(file);
	$.ajaxFileUpload({
		url : "/mybatis/FileUploadController/fileUpload.do",
		secureuri : false,
		data : {
			"filename" : "hahah",
			"age" : "12",
			"sex" : "man"
		},
		fileElementId : 'file-test',
		dataType : 'json',
		success : function(data) {
			alert(data["message"]);
		}
	})
}

// 上传文件信息
function uploadFile() {
	// var src = "/mybatis/FileUploadController/fileUpload.do";
	var aim1 = $("#aim1"), aim2 = $("#aim2"), aim3 = $("#aim3"); // 文件名
	var word1 = $("#word1"), word2 = $("#word2"), word3 = $("#word3"); // 关键词
	var area1 = $("#area1"), area2 = $("#area2"), area3 = $("#area3"); // 描述
	var cate1 = $("#cate1"), cate2 = $("#cate2"), cate3 = $("#cate3"); // 类别
	var child_cate1 = $("#child_cate1"), child_cate2 = $("#child_cate2"), child_cate3 = $("#child_cate3"); // 子类别
	var proto1 = $("#proto1"), proto2 = $("#proto2"), proto3 = $("#proto3"); // 属性
	var file1 = $("#file-test1").val();
	var file2 = $("#file-test2").val();
	var file3 = $("#file-test3").val();
	var path1 = $("#file_result1").val();
	var path2 = $("#file_result2").val();
	var path3 = $("#file_result3").val();
	if (aim1.val() == "" && aim2.val() == "" && aim3.val() == "") {
		alert("请选择上传的文件");
		return false;
	}
	if (aim1.val() != "") {
		if (word1.val() == "") {
			alert("请完善文件1关键词");
			return false;
		}
		if (proto1.val() == "") {
			alert("请完善文件1资料属性");
			return false;
		}
		if (proto1.val() == "公有" && cate1.val() == ""
				&& child_cate1.val() == "") {
			alert("请选择文件1类别");
			return false;
		}
		if ((area1.val() == "")) {
			alert("请完善文件1描述");
			return false;
		}
	}

	if (aim2.val() != "") {
		if (word2.val() == "") {
			alert("请完善文件2关键词");
			return false;
		}
		if (proto2.val() == "") {
			alert("请完善文件2资料属性");
			return false;
		}
		if (proto2.val() == "公有" && cate2.val() == ""
				&& child_cate2.val() == "") {
			alert("请选择文件2类别");
			return false;
		}
		if ((area2.val() == "")) {
			alert("请完善文件2描述");
			return false;
		}

	}

	if (aim3.val() != "") {
		if (word3.val() == "") {
			alert("请完善文件3关键词");
			return false;
		}
		if (proto3.val() == "") {
			alert("请完善文件3资料属性");
			return false;
		}
		if (proto3.val() == "公有" && cate3.val() == ""
				&& child_cate3.val() == "") {
			alert("请选择文件3类别");
			return false;
		}
		if ((area3.val() == "")) {
			alert("请完善文件3描述");
			return false;
		}
	}

	var data = {};
	if (aim1.val() != "") {
		data.filepath1 = path1;
		var name1 = aim1.val().split(".");
		// console.log(name1[0]);
		data.filename1 = name1[0];
		data.author1 = $("#author1").attr("placeholder");
		// console.log($("#author1").val()+"!!!!!!!");
		// data.time1 = $("#time1").attr("placeholder");
		data.word1 = word1.val();
		data.pro1 = proto1.val();
		data.cate1 = cate1.val();
		data.child_cate1 = child_cate1.val();
		data.area1 = area1.val();
	}
	if (aim2.val() != "") {
		data.filepath2 = path2;
		var name2 = aim2.val().split(".");
		// console.log(name1[0]);
		data.filename2 = name2[0];
		data.author2 = $("#author2").attr("placeholder");
		// data.time2 = $("#time2").attr("placeholder");
		data.word2 = word2.val();
		data.pro2 = proto2.val();
		data.cate2 = cate2.val();
		data.child_cate2 = child_cate2.val();
		data.area2 = area2.val();
	}
	if (aim3.val() != "") {
		data.filepath3 = path3;
		var name3 = aim3.val().split(".");
		// console.log(name1[0]);
		data.filename3 = name3[0];
		data.author3 = $("#author3").attr("placeholder");
		// data.time3 = $("#time3").attr("placeholder");
		data.word3 = word3.val();
		data.pro3 = proto3.val();
		data.cate3 = cate3.val();
		data.child_cate3 = child_cate3.val();
		data.area3 = area3.val();
	}
	console.log(data);
	sendAjaxRequest("/mybatis/FileInfoController/add_file_info.do", data,
			upload_success_cb, function() {
				alert("网络故障，请稍后重试！！！")
			});

}

// 文件上传成功，清空页面内容
function upload_success_cb(data) {
	var message1 = "";
	var message2 = "";
	var message3 = "";
	var cate = $("#cate1>option:eq(0)").val();
	var info = "";
	if (data["message1"] != null) {
		// console.log(data["result1"]);
		if (data["result1"] == true) {
			message1 = "文件1上传成功!";
			$("#file-test1").val("");
			$("#file-result1").val("");
			$("#aim1").val("");
			$("#proto1").val("私有");
			$("#word1").val("");
			$("#area1").val("");
			$("#cate1").val(cate);
		} else {
			message1 = "文件1上传失败!";
		}
	}
	if (data["message2"] != null) {
		// console.log(data["result2"]);
		if (data["result2"] == true) {
			message2 = "文件2上传成功!";
			$("#file-test2").val("");
			$("#file-result2").val("");
			$("#aim2").val("");
			$("#proto2").val("私有");
			$("#word2").val("");
			$("#area2").val("");
			$("#cate2").val(cate);
		} else {
			message2 = "文件2上传失败!";
		}
	}
	if (data["message3"] != null) {
		// console.log(data["result3"]);
		if (data["result3"] == true) {
			message3 = "文件3上传成功!";
			$("#file-test3").val("");
			$("#file-result3").val("");
			$("#aim3").val("");
			$("#proto3").val("私有");
			$("#word3").val("");
			$("#area3").val("");
			$("#cate3").val(cate);
		} else {
			message3 = "文件3上传失败!";
		}
	}
	info = message1 + message2 + message3;
	if (info != "") {
		$("#info-p").html(info);
		$("#info-modal").modal("show");
	}
}

/*
 * 获取自己的一级目录
 * 
 * function get_my_category(obj){ var userid=document.getElementById("userid");
 * $.ajax({ type : 'post', url : "/mybatis/CategoryController/get_category.do",
 * data:{"userid":userid}, dataType : "json", success : function(data) { var
 * _select = $("#"+obj+">option"); _select.remove(); var all_select_category =
 * data["category"]; // console.log(all_select_category.length); for (var i = 0;
 * i < all_select_category.length; i++) { var op = "<option value='" +
 * all_select_category[i].categoryName + "'>" +
 * all_select_category[i].categoryName + "</option>"; $("#"+obj+"").append(op); //
 * console.log(all_select_category[i].categoryName); } } }) } 获取公有 一级目录
 * 
 * function get_category_select_file(obj) { var userid=2; $.ajax({ type :
 * 'post', url : "/mybatis/CategoryController/get_category.do",
 * data:{"userid":userid}, dataType : "json", success : function(data) { var
 * _select = $("#"+obj+">option"); _select.remove(); var all_select_category =
 * data["category"]; // console.log(all_select_category.length); for (var i = 0;
 * i < all_select_category.length; i++) { var op = "<option value='" +
 * all_select_category[i].categoryName + "'>" +
 * all_select_category[i].categoryName + "</option>"; $("#"+obj+"").append(op); //
 * console.log(all_select_category[i].categoryName); } } }) }
 */