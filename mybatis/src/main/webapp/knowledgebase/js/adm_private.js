
//审查页面出来，加载表格
$(function(){
    get_all_private_file();

})



//获取公有文件
function get_all_private_file(){
    $.ajax({
        type:'post',
        url:"/mybatis/qmd/privatefile.do",
        dataType:"json",
        success:function get_all_private_file(data){
            var _table = $("#pri_file>tr");
            _table.remove();
            var all_pri_file = data["pri_file"];
            for(var i = 0;i < all_pri_file.length;i++){
                var tr_begin = "<tr>";
                var tr_end = "</tr>";
                var td_1 = "<td style='padding-top:15px;'><input type='checkbox' name='checkAll' value="+all_pri_file[i].fileId+"></td>"
                var td_2 = "<td style='padding-top:15px;width:180px;' id="+all_pri_file[i].fileId+">"+all_pri_file[i].fileName+"</td>";
                var td_3 = "<td style='padding-top:15px;'>"+all_pri_file[i].fileIsVisible+"</td>";
                var td_4 = "<td style='padding-top:15px;'>"+all_pri_file[i].fileUploadTime+"</td>";
                var td_6 = "<td><button class='btn btn-primary' data-toggle='modal'data-target='' onclick='down_file(this)'>下载</button></td>";
                var td_5 = "<td><button class='btn btn-primary' data-toggle='modal' data-target='' onclick='delete_one_pick(this)'>删除</button></td>";
                var td_7 = "<td><button class='btn btn-primary' data-toggle='modal' data-target='' onclick='pre_file(this)'>预览</button></td>";
                var content = tr_begin  + td_1 + td_2 + td_3 + td_4  + td_5 + td_6 + td_7 + tr_end;
                $("#pri_file").append(content);
            }
        }
    })
}

function selectAll() {
    var ckbs=document.getElementsByName("checkAll");
    var cka=document.getElementById("selAll");
    if (cka.checked == true) {
        for (var i = 0; i < ckbs.length; i++) {
            ckbs[i].checked = true;
        }
    }
    else{
        for (var i = 0; i < ckbs.length; i++) {
            ckbs[i].checked = false;
        }
    }
};


function select_one(obj) {
    var s = $(this).checked;
    if(s){
        $(this).attr("checked",false);
    }else{
        $(this).attr("checked",true);
    }
}





//下载文件
function down_file(obj){
	var btn_id=$(obj);
	var selected_name = btn_id.parent().siblings()[1].innerHTML;
    var selected_id = btn_id.parent().siblings()[1];
    var file_id=$(selected_id).attr("id");
    btn_id.attr("data-target","#download");
    $("#down_file").html("下载文件："+selected_name+"？");
    $("#down_file").removeClass();
    $("#down_file").addClass(file_id);
}
function down_ok(obj){
    var attr_p = $("#down_file").attr("class");
   
    $.ajax(
        {
            type:'post',
            url:"/mybatis/qmd/down_check_file.do",
            data:{"select_filename":attr_p},
            dataType:"json",
            success:function(data){
            	get_all_private_file();
            }
        }
    )
}


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
        url:"/mybatis/qmd/delete_file.do",
        dataType:"json",
        traditional:true,
        data:{"delete_array":delete_array},
        success:function(data){
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
	        url:"/mybatis/qmd/delete_file.do",
	        dataType:"json",
	        data:{"delete_array":file_id},
	        success:function(data){
	        		
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
        url:"/mybatis/qmd/down_check_file.do",
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
        url:"/mybatis/qmd/down_check_file.do",
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