
//审查页面出来，加载表格
$(function(){
    get_all_checkfile();

})

//获取所有未审核的文件
function get_all_checkfile(){
    var src = "/mybatis/FileInfoController/get_all_checkfile.do";
    sendAjaxRequest(src,{"page_Now":1},get_all_checkfile_table);
}


function get_all_checkfile_table(data){
    var _table = $("#file_result>tr");
    _table.remove();
    var checkfile = data["checkfile"];
    for(var i = 0;i < checkfile.length;i++){
        var tr_begin = "<tr>";
        var tr_end = "</tr>";
        var td_1 = "<td style='padding-top:15px;'><input type='checkbox' name='checkAll' value="+checkfile[i].fileId+"></td>";
        var td_2 = "<td style='padding-top:15px;width:280px;' id="+checkfile[i].fileId+"><a href='##' onclick='ReadOnLine(this)' path='"+checkfile[i].fileUrl+"'>"+checkfile[i].fileName+"</a></td>";
        var td_3 = "<td style='padding-top:15px;text-align:center;'>"+checkfile[i].fileAuthor+"</td>";
        var td_4 = "<td style='text-align:center;'><button class='button button-action" +
        		" button-rounded button-small changefilemsg' onclick='changeFileInfo(this)'>查看信息</button></td>";
        //var td_4 = "<td><button class='btn btn-primary' onclick='check_one_file(this)'>通过</button></td>";
        //var td_5 = "<td><button class='btn btn-primary' onclick='notpass_one_file(this)' value="+checkfile[i].fileId+">拒绝</button></td>";
        var content = tr_begin + td_1 + td_2 + td_4 + td_3 + tr_end;
        $("#file_result").append(content);
    }
    createNewPagination(data,"file_checkfile","/mybatis/FileInfoController/get_all_checkfile.do",get_all_checkfile_table,"first_file_click","last_file_click","page-file-checkfile",{});
}
function changeFileInfo(obj){
	//var filename = $(obj).parent().siblings()[1].innerHTML;
	$(obj).attr({"data-toggle":"modal","data-target":"#changefileinfo"});
	var filename = $($(obj).parent().siblings()[1]).attr("id");
	var cate_select=document.getElementById("cate_select");
	cate_select.options.length=0;
	var op="<option value=''>请选择</option>";
	$("#cate_select").append(op);
	var userid=2;
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
				$("#cate_select").append(op);
				// console.log(all_select_category[i].categoryName);
			}
		}
	})
	
		$.ajax({
		type: 'post',
		url : "/mybatis/FileInfoController/get_file_msg.do",
		data:{"fileid":filename},
		dataType : "json",
		success : function(data){	
			var keyword=data["keyWord"]+"";
			var filedes=data["fileDes"]+"";
			var filecate=data["filecate"];
			if(filecate=="")
				document.getElementById("usercate").placeholder="用户没有对文件分类";
			else
				document.getElementById("usercate").value=filecate;
			document.getElementById("fileword").value=keyword;
			document.getElementById("filearea").value=filedes;
	
			}
	})
}
function updateFile(obj){
	var filecate;
	var fileid = $($(".changefilemsg").parent().siblings()[1]).attr("id");
	var cate=$("#cate_select").val();
	var second_cate=$("#second_cate").val();
	var usercate=$("#usercate").val();
	var keyword=$("#fileword").val();
	var filedesc=$("#filearea").val();
	if((cate=="")&&(second_cate==""))
		filecate=usercate;
	else if((cate!="")&&(second_cate!=""))
		filecate=second_cate;
	else
		filecate=cate;
	$.ajax({
		type: 'post',
		url : "/mybatis/FileInfoController/alter_file_msg.do",
		data:{"fileid":fileid,"filecate":filecate,
			"keyword":keyword,"filedesc":filedesc},
		dataType : "json",
		success : function(data){	
		     var result = data["flag"];
             if(result){
             	$("#result_msg").html("修改信息成功!");
                 $("#result").modal('show');
             }else{
             	$("#result_msg").html("修改信息失败!");
                 $("#result").modal('show');
             }
			}
		})
}
function get_child_category_select(obj){
	var child_cate=document.getElementById("second_cate");

	child_cate.options.length=0;
	var userid=2;
	var belongto=$(obj).val();
	$.ajax({
		type : 'post',
		url : "/mybatis/CategoryController/get_child_category.do",
		data:{"userid":userid,"categoryBelongTo":belongto},
		dataType : "json",
		success : function(data){	
			var category=data["category"];
			if(category.length==0){
				var op="<option value=''>请选择</option>";
				$("#second_cate").append(op);
			}else{
			for(var i=0 ; i < category.length ;i++){
				 var op = "<option value='"
					+ category[i].categoryName + "'>"
					+ category[i].categoryName + "</option>";
				 $("#second_cate").append(op);
			}
			}

			}
			
		
	})

}
function check_all_file(obj){
	   var check_num_1 = 0;
	    $("input[name=checkAll]").each(function(){
	        if($(this).is(':checked')){
	            check_num_1 += 1;
	        }
	    });
	    if(check_num_1 > 0){
	        $(obj).attr({"data-toggle":"modal","data-target":"#pass"});
	        $("#file_remind").html("确认全部通过所选的文件么？");
	        $("#file_remind").removeClass();
	       
	        $("#file_click").removeAttr("onclick_name");
	        $("#file_click").attr("onclick_name","pass_all");
	    }else{
	        $(obj).attr({"data-toggle":"modal","data-target":"#pass_remind"});
	        $("#remind_info").html("请选择要通过的文件");
	    }

}

function pass_all_file(){
    
	 var check_num = 0;
	    $("input[name=checkAll]").each(function(){
	        if($(this).is(':checked')){
	            check_num += 1;
	        }
	    });
	 
	    var pass_array = new Array(check_num);
	    $("input[name=checkAll]").each(function(index){
	        if($(this).is(':checked')){
	        	var selected_id = $(this).parent().siblings()[0];
	            var file_id=$(selected_id).attr("id");
	            pass_array[index] = file_id;
	           
	        }
	    });
	    console.log(pass_array);
	    $.ajax({
	        type:'post',
	        url:"/mybatis/FileInfoController/pass_file.do",
	        dataType:"json",
	        traditional:true,
	        data:{"pass_array":pass_array},
	        success:function(data)
	        {
	        	if(data["flag"]){
                    get_all_checkfile();
                }else{
                    alert("网络故障，请稍后重试");
                }
	        }
	    })
}

function check_one_file(obj){
	var check_one_file = $(obj).parent().siblings()[1].innerHTML;
    var check_one_id = $($(obj).parent().siblings()[1]).attr("id");
    console.log(check_one_id);
    $(obj).attr({"data-toggle":"modal","data-target":"#pass"});
    $("#file_remind").html("确认通过  "+"<span id='one_selected_file' checkid='"+check_one_id+"'>"+check_one_file+"</span>"+"  么？");
    $("#file_remind").removeClass();
    $("#file_click").removeAttr("onclick_name");
    $("#file_click").attr("onclick_name","pass_one");
}

function pass_one_file(obj){
    var pass_arr = [];
    pass_arr.push($("#one_selected_file").attr("checkid"));
    //console.log(pass_arr[0]);
    $.ajax({
        type:'post',
        url:"/mybatis/FileInfoController/pass_file.do",
        dataType:"json",
        traditional:true,
        data:{"pass_array":pass_arr},
        success:function(data)
        {
            get_all_checkfile();
        }
    })
}

function pass_file_ok(){
    var btn_info = $("#file_click").attr("onclick_name");
    if(btn_info == "pass_one"){
        pass_one_file();
    }else if(btn_info == "pass_all"){
        pass_all_file();
    }
}
function notpass_file(notpass_array){
    $.ajax({
        type:'post',
        url:"/mybatis/FileInfoController/notpass_file.do",
        dataType:"json",
        traditional:true,
        data:{"notpass_array":notpass_array},
        success:function(data)
        {
            if(data["flag"]){
                get_all_checkfile();
            }else{
                alert("网络故障，请稍后重试");
            }

        }
    })
}

function notpass_one_file(obj){
    var notpass_array = [];
    var file_id = $(obj).attr("value");
    notpass_array.push(file_id);
    notpass_file(notpass_array);

}

function notpass_all_selected_file_modal(obj){
    var notpass_count = 0;
    $("input[name=checkAll]").each(function(){
        if($(this).is(":checked")){
            notpass_count += 1;
        }
    })
    if(notpass_count > 0){
        $(obj).attr({"data-toggle":"modal","data-target":"#jujue"});
        $("#jujue_remind_info").html("确认全部拒绝所选的文件么？");
        $("#jujue_ok_btn").attr("onclick","notpass_all_selected_file()");


    }else{
        $(obj).attr({"data-toggle":"modal","data-target":"#pass_remind"});
        $("#remind_info").html("请选择要拒绝的文件");
    }
}

function notpass_all_selected_file(){
    var notpass_array = [];
    $("input[name=checkAll]").each(function(){
        if($(this).is(":checked")){
            var file_id = $(this).attr("value");
            notpass_array.push(file_id);
        }
    })
    notpass_file(notpass_array);
}