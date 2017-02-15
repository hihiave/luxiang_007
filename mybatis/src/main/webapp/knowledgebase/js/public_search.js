/**
 * Created by 95 on 2016/11/3.
 */
$(function() {
	get_category_select();
	
	/*
	 * get_file_points(); $("#file_search_input").oninput(function(){ var
	 * filepoints=new Array();
	 * 
	 * $.ajax({ type:'post',
	 * url:"/mybatis/FileInfoController/get_file_points.do", dataType:"json",
	 * success:function(data){
	 * 
	 * var all_pass_file =data["FileInfo_check"];
	 * 
	 * for(var i = 0;i < all_pass_file.length;i++){
	 * filepoints.unshift(all_pass_file[i].fileName);
	 *  } }
	 *  }) $("#file_search-input").autocomplete({ source:filepoints }); })
	 */
})
function keysearch(){
	if (event.keyCode==13)
		document.getElementById("file_search_sub").click();
}

//全选



function myfunction() {
	var filepoints = new Array();
	var fileinput = $("#file_search_input").val();
	var filepropertyftype = $("#key_select").val();

	$.ajax({
		type : 'post',
		url : "/mybatis/FileInfoController/get_file_points.do",
		data : {
			"filePropertyType" : filepropertyftype,
			"value" : fileinput
		},
		dataType : "json",
		success : function(data) {

			var search_file = data["FileInfo_check"];

			for (var i = 0; i < search_file.length; i++) {
				filepoints.unshift(search_file[i]);

			}
		}

	})
	$("#file_search_input").autocomplete({
		source : filepoints
	});
}
// 获取文件提示
/*
 * function get_file_points(){ var file_input =
 * $.trim($("#file_search_input").val()); var key =
 * $.trim($("#key_select").val()); var category =
 * $.trim($("#category_select").val());
 * 
 * ("#file_search_input").autocomplete() }
 */
function get_child_category_selected(){
	var category=document.getElementById("category_select");
	var obj=document.getElementById('second_category');
	obj.options.length=0;
	var userid=1;
	$.ajax({
		type : 'post',
		url : "/mybatis/CategoryController/get_child_category.do",
		data:{"userid":userid,"categoryBelongTo":category.value},
		dataType : "json",
		success : function(data){	
		 
			// var _select = $("#category_select>option");
			// _select.remove();
			var category=data["category"];
			if(category.length==0){
				var op="<option value=''>请选择</option>";
				$("#second_category").append(op);
			}else{
			for(var i=0 ; i < category.length ;i++){
				 var op = "<option value='"
					+ category[i].categoryName + "'>"
					+ category[i].categoryName + "</option>";
			$("#second_category").append(op);
			}
			}
/*			if($("#category_select").val()=="专利"){
			// console.log(all_select_category.length);
				var obj=document.getElementById('second_category');
				obj.options.length=0;
			for (var i = 0; i < child1.length; i++) {
				var op = "<option value='"
						+ child1[i].categoryName + "'>"
						+ child1[i].categoryName + "</option>";
				$("#second_category").append(op);
				// console.log(all_select_category[i].categoryName);
			}
			}else if($("#category_select").val()=="论文"){
				var obj=document.getElementById('second_category');
				obj.options.length=0;
				for (var i = 0; i < child2.length; i++) {
					var op = "<option value='"
							+ child2[i].categoryName + "'>"
							+ child2[i].categoryName + "</option>";
					$("#second_category").append(op);
					// console.log(all_select_category[i].categoryName);
				}
			}
			else{
				var obj=document.getElementById('second_category');
				obj.options.length=0;
				var op="<option value=''>请选择</option>";
				$("#second_category").append(op);*/
			}
			
		
	})
}


function get_category_select() {
	//var userid=document.getElementById("userid");
	var userid=1;
	$.ajax({
		type : 'post',
		url : "/mybatis/CategoryController/get_category.do",
		data:{"userid":userid},
		dataType : "json",
		success : function(data) {
			// var _select = $("#category_select>option");
			// _select.remove();
			var all_select_category = data["category"];
			// console.log(all_select_category.length);
			for (var i = 0; i < all_select_category.length; i++) {
				var op = "<option value='"
						+ all_select_category[i].categoryName + "'>"
						+ all_select_category[i].categoryName + "</option>";
				$("#category_select").append(op);
				// console.log(all_select_category[i].categoryName);
			}
		}
	})
}

function send_search_info() {
	var _category = $.trim($("#category_select").val());
	var _subcategory = $.trim($("#second_category").val());
	var _key = $.trim($("#key_select").val());
	var _search_input = $.trim($("#file_search_input").val());

	var src = "/mybatis/FileInfoController/publicfile.do"

	var dataPost = {
		"fileCategory" : _category,
		"subFileCategory" : _subcategory,
		"fileProperty" : _key,
		"fileIn" : _search_input
	};
	// console.log(dataPost);
	$("#default_panel").css("display", "none");
	$("#search_panel").css("display", "block");
	sendAjaxRequest(src, dataPost, get_all_search_file_table);

}
function get_all_search_file_table(data) {
	var _table = $("#search_file>tr");
	_table.remove();
	var all_pub_file = data["pub_file"];
	for (var i = 0; i < all_pub_file.length; i++) {
		var tr_begin = "<tr>";
		var tr_end = "</tr>";
		var td_0 = "<td style='padding-top:15px;'><input type='checkbox' name='checkAllfile' " +
				" value="+all_pub_file[i].fileId+"></td>";
		var td_1 = "<td style='padding-top:15px;'><a href='../"+all_pub_file[i].fileUrl+"' >"
				+ all_pub_file[i].fileName
				+ "</a></td>";
		var td_2 = "<td style='padding-top:15px;'>"+ all_pub_file[i].fileAuthor + "</td>";
		var td_3 = "<td style='padding-top:15px;'>"
				+ timeStampFormatDay(all_pub_file[i].fileUploadTime * 1000)
				+ "</td>";
		var td_4 = "<td ><button class='button button-primary button-rounded button-small'  did='public' bid='"
				+ all_pub_file[i].fileId + "'  onclick='download_search(this)' path='"
				+ all_pub_file[i].fileUrl + "'>下载</button></td>";
		var td_5="<td style='padding-top:15px;'>"+ all_pub_file[i].fileDesc + "</td>";
		var td_6 = "<td style='padding-top:15px;'>"
				+ all_pub_file[i].fileDownloadCount + "</td>"
		var userid = document.getElementById("userid").value;
		if (userid==1){
			var content = tr_begin + td_0 + td_1 + td_5 + td_2 + td_3 + td_4 + td_6 + tr_end;
		}else{
			var content = tr_begin + td_1 + td_5 + td_2 + td_3 + td_4 + td_6 + tr_end;
		}
		
		$("#search_file").append(content);
	}
	// createNewPagination(data,"file_public","/mybatis/FileInfoController/publicfile.do",get_all_public_file_table,"first_file_click","last_file_click","page-file-three",{"fileProperty":"fullText"});
}

//全选
function selectAllfile() {
	var ckbs = document.getElementsByName("checkAllfile");
	var cka = document.getElementById("selAllfile");
	if (cka.checked == true) {
		for (var i = 0; i < ckbs.length; i++) {
			ckbs[i].checked = true;
		}
	} else {
		for (var i = 0; i < ckbs.length; i++) {
			ckbs[i].checked = false;
		}
	}
};

function deleteFile(delete_array){
    $.ajax({
        type:'post',
        url:"/mybatis/FileInfoController/delete_file.do",
        dataType:"json",
        traditional:true,
        data:{"delete_array":delete_array},
        success:function(data)
        {
            if(data["flag"]){

            	send_search_info();
            }else{
                alert("网络故障,稍后重试");
            }

        }
    })
}



function delete_pub_file_modal(obj){
    var delete_count = 0;
    $("input[name=checkAllfile]").each(function(){
        if($(this).is(':checked')){
            delete_count += 1;
        }
    });
    console.log("选中的文件个数："+delete_count);
    if(delete_count == 0){
        $(obj).attr({"data-toggle":"modal","data-target":"#deletepubfile-modal"});
        $("#myModalLabel-delete_pub_title").html("删除文件提示");
        $("#delete_pub_info").html("请选择需要删除的文件");
    }else{
        $(obj).attr({"data-toggle":"modal","data-target":"#deletepubfile-modal"});
        $("#myModalLabel-delete_pub_title").html("删除文件提示");

        $("#delete_pub_info").html("确定删除选中文件？此次删除后不可恢复");
        $("#adm_ok_btn").attr("onclick","delete_selected_pubfile()");
    }
}

function delete_selected_pubfile(){
    var delete_array = [];
    $("input[name=checkAllfile]").each(function(){
        if($(this).is(':checked')){
            var arr = $(this).attr("value");
            delete_array.push(arr);
        }
    });
    deleteFile(delete_array);
}

