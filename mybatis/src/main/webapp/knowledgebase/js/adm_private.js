
//审查页面出来，加载表格
$(function(){
    get_all_private_file();
    
})



//获取全部文件
function get_all_private_file(){
    var src = "/mybatis/FileInfoController/myuploadfile.do";
    sendAjaxRequest(src,{"page_Now":1},get_all_private_file_table);

}

function get_all_my_file(){
	 var src = "/mybatis/FileInfoController/myuploadfile.do";
	    sendAjaxRequest(src,{"page_Now":1},get_my_file);
}

function get_all_private_file_table(data){
    var _table = $("#pri_file>tr");
    _table.remove();
    var all_pri_file = data["pri_file"];
    for(var i = 0;i < all_pri_file.length;i++){
        var tr_begin = "<tr>";
        var tr_end = "</tr>";
        var td_1 = "<td style='padding-top:15px;'><input type='checkbox' name='checkAll' value="+all_pri_file[i].fileId+"></td>"
        var td_2 = "<td style='padding-top:15px;width:180px;' id="+all_pri_file[i].fileId+"><a href='##'  onclick='ReadOnLine(this)' path='"+all_pri_file[i].fileUrl+"' >"+all_pri_file[i].fileName+"</a></td>";
        var td_3 = "<td style='padding-top:15px;'>"+all_pri_file[i].fileIsVisible+"</td>";
        var td_4 = "<td style='padding-top:15px;'>"+timeStampFormatDay(all_pri_file[i].fileUploadTime*1000)+"</td>";
        var td_5 = "<td><button class='button button-primary button-rounded button-small'" +
        		" bid='"+all_pri_file[i].fileId+"'  onclick='download(this)' path='"+all_pri_file[i].fileUrl+"'>下载</button></td>";
        if(all_pri_file[i].fileIsVisible=="私有")
        	var td_6="<td><button class='button button-action button-rounded button-small changefilemsg' " +
        			"  onclick='file_classify(this)' >分类</button></td>";
        else
        	var td_6 = "<td style='padding-top:15px;'></td>"
        //var td_5 = "<td><button class='btn btn-primary' data-toggle='modal' data-target='' onclick='delete_one_pick(this)'>删除</button></td>";
        //var td_6 = "<td ></td>";
        //var td_7 = "<td><button class='btn btn-primary' data-toggle='modal' data-target='' onclick='pre_file(this)'>预览</button></td>";
        var content = tr_begin  + td_1 + td_2 + td_3 + td_4  + td_6 + td_5 + tr_end;
        $("#pri_file").append(content);
    }
    createNewPagination(data,"file_private","/mybatis/FileInfoController/myuploadfile.do",get_all_private_file_table,"first_file_click","last_file_click","page-file-one",{})
}
/*
 * 条件查询文件
 * */

function fileinfo_search(obj){
	var filetype=$("#filetype").val();
	var filecate=$("#filecate").val();
	var filechildcate=$("#filechildcate").val();
	//alert("type="+filetype+"..filecate="+filecate+"..filechildcate="+filechildcate)
	if(filetype==''){
		get_all_private_file();
	}
	else if((filetype!='')&&(filecate=='')&&(filechildcate=='')){
		$("#type").val(filetype);
		$("#cate").val(filecate);
		get_all_my_file();
	}
	else if((filetype!='')&&(filecate!='')&&(filechildcate=='')){
		$("#type").val(filetype);
		$("#cate").val(filecate);
		get_all_my_file();
	}else if((filetype!='')&&(filecate!='')&&(filechildcate!='')){
		$("#type").val(filetype);
		$("#cate").val(filechildcate);
		get_all_my_file();
	}
}
/*
 * 通过条件查询
 */
function get_my_file(data){

    var _table = $("#pri_file>tr");
    _table.remove();
    var all_pri_file = data["pri_file"];
    var type=$("#type").val();
    var cate=$("#cate").val();
	//alert(type+"''"+cate);
    for(var i = 0;i < all_pri_file.length;i++){
    	if(cate!=""){
    	if((all_pri_file[i].fileIsVisible==type)&&(all_pri_file[i].fileCategory==cate)){
        var tr_begin = "<tr>";
        var tr_end = "</tr>";
        var td_1 = "<td style='padding-top:15px;'><input type='checkbox' name='checkAll' value="+all_pri_file[i].fileId+"></td>"
        var td_2 = "<td style='padding-top:15px;width:180px;' id="+all_pri_file[i].fileId+"><a href='##'  onclick='ReadOnLine(this)' path='"+all_pri_file[i].fileUrl+"' >"+all_pri_file[i].fileName+"</a></td>";
        var td_3 = "<td style='padding-top:15px;'>"+all_pri_file[i].fileIsVisible+"</td>";
        var td_4 = "<td style='padding-top:15px;'>"+timeStampFormatDay(all_pri_file[i].fileUploadTime*1000)+"</td>";
        var td_5 = "<td><button class='button button-primary button-rounded button-small'" +
        		" bid='"+all_pri_file[i].fileId+"'  onclick='download(this)' path='"+all_pri_file[i].fileUrl+"'>下载</button></td>";
        if(all_pri_file[i].fileIsVisible=="私有")
        	var td_6="<td><button class='button button-action button-rounded button-small changefilemsg' " +
        			"  onclick='file_classify(this)' >分类</button></td>";
        else
        	var td_6 = "<td style='padding-top:15px;'></td>";
        var content = tr_begin  + td_1 + td_2 + td_3 + td_4  + td_6 + td_5 + tr_end;
        $("#pri_file").append(content);
    	}
    }else if(cate==""){
    	if(all_pri_file[i].fileIsVisible==type){
            var tr_begin = "<tr>";
            var tr_end = "</tr>";
            var td_1 = "<td style='padding-top:15px;'><input type='checkbox' name='checkAll' value="+all_pri_file[i].fileId+"></td>"
            var td_2 = "<td style='padding-top:15px;width:180px;' id="+all_pri_file[i].fileId+"><a href='##'  onclick='ReadOnLine(this)' path='"+all_pri_file[i].fileUrl+"' >"+all_pri_file[i].fileName+"</a></td>";
            var td_3 = "<td style='padding-top:15px;'>"+all_pri_file[i].fileIsVisible+"</td>";
            var td_4 = "<td style='padding-top:15px;'>"+timeStampFormatDay(all_pri_file[i].fileUploadTime*1000)+"</td>";
            var td_5 = "<td><button class='button button-primary button-rounded button-small'" +
            		" bid='"+all_pri_file[i].fileId+"'  onclick='download(this)' path='"+all_pri_file[i].fileUrl+"'>下载</button></td>";
            if(all_pri_file[i].fileIsVisible=="私有")
            	var td_6="<td><button class='button button-action button-rounded button-small changefilemsg' " +
            			"  onclick='file_classify(this)' >分类</button></td>";
            else
            	var td_6 = "<td style='padding-top:15px;'></td>";
            var content = tr_begin  + td_1 + td_2 + td_3 + td_4  + td_6 + td_5 + tr_end;
            $("#pri_file").append(content);
        	}
    }
    }
    createNewPagination(data,"file_private","/mybatis/FileInfoController/myuploadfile.do",get_my_file,"first_file_click","last_file_click","page-file-one",{})
}

function file_classify(obj){
	$(obj).attr({"data-toggle":"modal","data-target":"#changefileinfo"});
	var fileid = $($(obj).parent().siblings()[1]).attr("id");
	var cate_select=document.getElementById("cate_select");
	var second_cate=document.getElementById("second_cate");
	cate_select.options.length=0;
	second_cate.options.length=0;
	var op="<option value=''>请选择</option>";
	$("#cate_select").append(op);
	$("#second_cate").append(op);
	var userid=document.getElementById("userid").value;;
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
		data:{"fileid":fileid},
		dataType : "json",
		success : function(data){	
			var filecate=data["filecate"];
			if(filecate=="")
				document.getElementById("usercate").placeholder="用户没有对文件分类";
			else
				document.getElementById("usercate").value=filecate;
	
			}
	})
}

function get_category(obj){
	var val=$(obj).val();
	document.getElementById("filetype").value=val;
	var obj1=document.getElementById("filecate");
	var obj2=document.getElementById("filechildcate");
	obj1.options.length=0;
	obj2.options.length=0;
	var op="<option value=''>请选择</option>";
	$("#filecate").append(op);
	$("#filechildcate").append(op);
	var userid;
	if(val=="公有"){
		userid=1;
	}
	else if(val=="私有"){
		userid=document.getElementById("userid").value;
	}
	if(val!==""){$.ajax({
		type : 'post',
		url : "/mybatis/CategoryController/get_category.do",
		data:{"userid":userid},
		dataType : "json",
		success : function(data) {
			var _select = $("#filecate>option");
			_select.remove();
			$("#filecate").prepend("<option value=''>请选择</option>");
			var all_select_category = data["category"];
			// console.log(all_select_category.length);
			for (var i = 0; i < all_select_category.length; i++) {
				var op = "<option value='"
						+ all_select_category[i].categoryName + "'>"
						+ all_select_category[i].categoryName + "</option>";
				$("#filecate").append(op);
				// console.log(all_select_category[i].categoryName);
			}
		}
	})
	}
}

function get_child_category_select(obj){
	var type=$("#filetype").val();
	var userid;
	if(type=="公有")
		userid=1;
	else if(type=="私有")
		userid=document.getElementById("userid").value;
	var obj=document.getElementById("filechildcate");
	obj.options.length=0;
	var belongto=$("#filecate").val();
	
	//obj.options.length=0;
	$.ajax({
		type : 'post',
		url : "/mybatis/CategoryController/get_child_category.do",
		data:{"userid":userid,"categoryBelongTo":belongto},
		dataType : "json",
		success : function(data){	
			var category=data["category"];
			if(category.length==0){
				var op="<option value=''>请选择</option>";
				$("#filechildcate").append(op);
			}else{
			for(var i=0 ; i < category.length ;i++){
				 var op = "<option value='"
					+ category[i].categoryName + "'>"
					+ category[i].categoryName + "</option>";
				 $("#filechildcate").append(op);
			}
			}

			}
			
		
	})
}

function get_my_category_select(obj){
	var child_cate=document.getElementById("second_cate");

	child_cate.options.length=0;
	var userid=document.getElementById("userid").value;
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
function updateFile(obj){
	var filecate;
	var fileid = $($(".changefilemsg").parent().siblings()[1]).attr("id");
	var cate=$("#cate_select").val();
	var second_cate=$("#second_cate").val();
	var usercate=$("#usercate").val();
	if((cate=="")&&(second_cate==""))
		filecate=usercate;
	else if((cate!="")&&(second_cate!=""))
		filecate=second_cate;
	else
		filecate=cate;
	$.ajax({
		type: 'post',
		url : "/mybatis/FileInfoController/alter_file_msg.do",
		data:{"fileid":fileid,"filecate":filecate},
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
////下载文件
//function down_file(obj){
//	var btn_id=$(obj);
//	var selected_name = btn_id.parent().siblings()[1].innerHTML;
//    var selected_id = btn_id.parent().siblings()[1];
//    var file_id=$(selected_id).attr("id");
//    btn_id.attr("data-target","#download");
//    $("#down_file").html("下载文件："+selected_name+"？");
//    $("#down_file").removeClass();
//    $("#down_file").addClass(file_id);
//}
//function down_ok(obj){
//    var attr_p = $("#down_file").attr("class");
//
//    $.ajax(
//        {
//            type:'post',
//            url:"/mybatis/FileInfoController/down_check_file.do",
//            data:{"select_filename":attr_p},
//            dataType:"json",
//            success:function(data){
//            	get_all_private_file();
//            }
//        }
//    )
//}


//删除文件

function delete_all_pick(obj){
	var check_num_1 = 0;
    $("input[name=checkAll]").each(function(){
        if($(this).is(':checked')){
            check_num_1 += 1;
        }
    });
    if(check_num_1 > 0){
        $(obj).attr({"data-toggle":"modal","data-target":"#delete"});
        $("#delete_file").html("确认删除通过所选的文件么？");
        $("#delete_file").removeClass();
       
        $("#delete_click").removeAttr("onclick_id");
        $("#delete_click").attr("onclick_id","delete_all");
    }else{
        $(obj).attr({"data-toggle":"modal","data-target":"#click_remind"});
        $("#remind_file").html("请选择要删除的文件");
    }
}


function delete_all(){
    var check_num = 0;
    $("input[name=checkAll]").each(function(){
        if($(this).is(':checked')){
            check_num += 1;
        }
    });
 
    var delete_array = new Array(check_num);
    $("input[name=checkAll]").each(function(index){
        if($(this).is(':checked')){
        	var selected_id = $(this).parent().siblings()[0];
            var file_id=$(selected_id).attr("id");
            delete_array[index] = file_id;
           
        }
    });
    console.log(delete_array);
    $.ajax({
        type:'post',
        url:"/mybatis/FileInfoController/delete_file_to_draft.do",
        dataType:"json",
        traditional:true,
        data:{"delete_array":delete_array},
        success:function(data){
            console.log(data["flag"]);
        	get_all_private_file();
        }
    })
}


function delete_one_pick(obj){
	var delete_file_name = $(obj).parent().siblings()[1].innerHTML;
    $(obj).attr({"data-toggle":"modal","data-target":"#delete"});
    
    var btn_id=$(obj);
    var selected_id = btn_id.parent().siblings()[1];
    var file_id=$(selected_id).attr("id");
    $("#delete_file").removeClass();
    $("#delete_file").addClass(file_id);
    $("#delete_file").html("确认删除  "+delete_file_name+"  么？");
    $("#delete_click").removeAttr("onclick_id");
    $("#delete_click").attr("onclick_id","delete_one");
    
}

function delete_one(obj){
	 var file_id = $("#delete_file").attr("class");
	    $.ajax({
	        type:'post',
	        url:"/mybatis/FileInfoController/delete_file_to_draft.do",
	        dataType:"json",
	        data:{"delete_array":file_id},
	        success:function(data){
                console.log(data["flag"]);
	        	get_all_private_file();
	                
	        }
	    })
}










function delete_file_ok(){
	   var btn_info = $("#delete_click").attr("onclick_id");
	    if(btn_info == "delete_one"){
	       delete_one();
	    }else if(btn_info == "delete_all"){
	        delete_all();
	    }
}







/*//下载选中的
function down_all_pick(obj){
    var check_num_1 = 0;
    $("input[name=checkAll]").each(function(){
        if($(this).is(':checked')){
            check_num_1 += 1;
        }
    });
    if(check_num_1 > 0){
        $(obj).attr({"data-toggle":"modal","data-target":"#download"});
        $("#check_file").html("确认下载 所选的文件么？");
        $("#check_file").removeClass();
   
        $("#down_click").removeAttr("onclick_name");
        $("#down_click").attr("onclick_name","down_all");
    }else{
        $(obj).attr({"data-toggle":"modal","data-target":"#click_remind"});
        $("#remind_file").html("请选择要下载的文件");
    }


}*/

/*function down_all_selected(){
    var down_num = 0;
    $("input[name=checkAll]").each(function(){
        if($(this).is(':checked')){
            down_num += 1;
        }
    });
   // console.log(check_num);
    var down_array = new Array(down_num);
    $("input[name=checkAll]").each(function(index){
        if($(this).is(':checked')){
            down_array[index] = $(this).parent().siblings()[0].value;
            //console.log($(this).parent().next().innerHTML);
        }
    });
   // console.log(check_array);
    $.ajax({
        type:'post',
        url:"/mybatis/FileInfoController/down_check_file.do",
        dataType:"json",
        traditional:true,
        data:{"check_file_arry":down_array},
        success:function(data){
            get_all_private_file();
        }
    })
}

function down_one_pick(obj){
    var down_one_file = $(obj).parent().siblings()[1].innerHTML;
    $(obj).attr({"data-toggle":"modal","data-target":"#download"});
    $("#check_file").html("确认下载  "+down_one_file+"  么？");
    $("#check_file").removeClass();
    $("#check_file").addClass(down_one_file);
    $("down_click").removeAttr("onclick_name");
    $("#down_click").attr("onclick_name","down_one");

}

//确认下载单个文件
function down_one_selected(){
    var check_file = $("#check_file").attr("class");
    $.ajax({
        type:'post',
        url:"/mybatis/FileInfoController/down_check_file.do",
        dataType:"json",
        data:{"check_file_array":check_file},
        success:function(data){
            if(data){
                get_all_user_is_check();
            }
        }
    })
}

function down_file_ok(){
    var btn_info = $("#down_click").attr("onclick_name");
    if(btn_info == "down_one"){
       down_one_selected();
    }else if(btn_info == "down_all"){
        down_all_selected();
    }
}*/
/*function down_file(obj){
    var btn_name = $(obj);
    var file_name = btn_name.parent().siblings()[1].innerHTML;

    btn_name.attr("data-target","#download");
    $("#download_file").html("是否下载："+file_name+"？");
    $("#download_file").removeClass();
    $("#download_file").addClass(file_name);
}

function pre_file(obj){
    var btn_name = $(obj);
    var file_name = btn_name.parent().siblings()[1].innerHTML;

    btn_name.attr("data-target","#preview");
    $("#preview_file").html("是否预览："+file_name+"？");
    $("#preview_file").removeClass();
    $("#preview_file").addClass(file_name);
}
//下载
function downloadFile(obj){
	var attr_d = $("#download_file").attr("class");

}
//预览
function previewFile(obj){
	var attr_p=$("preview_file").attr("class");
	$.ajax()
}*/