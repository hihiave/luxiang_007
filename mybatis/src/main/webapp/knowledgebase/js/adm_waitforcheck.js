/**
 * Created by 95 on 2016/11/7.
 */
$(function(){
    get_all_wait_file();
})
function get_all_wait_file(){
    var src = "/mybatis/FileInfoController/waitforcheckfile.do";
    sendAjaxRequest(src,{"page_Now":1},cb_get_all);
}
function cb_get_all(obj){
    var _table_wait = $("#waitforcheck_file>tr");
    _table_wait.remove();
    var all_wait_file = obj["wait_file"];
    //console.log(all_wait_file[0].fileName);
    for(var each in all_wait_file){
        console.log(all_wait_file[each].fileName);
        //console.log(each.fileName);
        //上面这种写法不对
        var tr_begin = "<tr>";
        var tr_end = "</tr>";
        var td_1 = "<td style='padding-top:15px;'><input type='checkbox' name='checkAll' onclick='select_one(this)' value="+all_wait_file[each].fileId+"></td>";
        var td_2 = "<td style='padding-top:15px;width:180px;' value="+all_wait_file[each].fileId+"><a href='##'>"+all_wait_file[each].fileName+"</a></td>";
        var td_3 = "<td style='padding-top:15px;width:180px;' >"+get_file_check(all_wait_file[each].fileCheck)+"</td>";
        //var td_4 = "<td><button class='btn btn-primary' onclick='delete_one_file(this)' value="+all_wait_file[each].fileId+">删除</button></td>";
        var td_5 = "<td><button class='btn btn-primary' onclick='download(this)' path='"+all_wait_file[each].fileUrl+"'>下载</button></td>";
        //var td_6 = "";
        //if(all_wait_file[each].fileCheck == -1){
        //    td_6 = "<td><button class='btn btn-primary' onclick='' value="+all_wait_file[each].fileId+">提交</button></td>";
        //}else if(all_wait_file[each].fileCheck == 0){
        //    td_6 = "<td></td>"
        //}
        var content = tr_begin  + td_1 + td_2 + td_3 + td_5 + tr_end;
        $("#waitforcheck_file").append(content);
    }
    createNewPagination(obj,"file_waitforcheck","/mybatis/FileInfoController/waitforcheckfile.do",cb_get_all,"first_file_click","last_file_click","page-file-two",{})
}
function get_file_check(obj){
    if(obj == -1){
        return "未通过";
    }else if(obj == 0){
        return "待审核";
    }
}

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

                get_all_wait_file();
            }else{
                alert("网络故障,稍后重试");
            }

        }
    })
}

function delete_one_file(obj){
    var file_id = $(obj).attr("value");
    var delete_array = [];
    delete_array.push(file_id);
    deleteFile(delete_array);
}



function delete_all_file_modal(obj){
    var delete_count = 0;
    $("input[name=checkAll]").each(function(){
        if($(this).is(':checked')){
            delete_count += 1;
        }
    });
    console.log("选中的文件个数："+delete_count);
    if(delete_count == 0){
        $(obj).attr({"data-toggle":"modal","data-target":"#delete-modal"});
        $("#myModalLabel-delete_title").html("删除文件提示");
        $("#delete_p_info").html("请选择需要删除的文件");
    }else{
        $(obj).attr({"data-toggle":"modal","data-target":"#delete-modal"});
        $("#myModalLabel-delete_title").html("删除文件提示");

        $("#delete_p_info").html("确定删除选中文件？此次删除后不可恢复");
        $("#ok_btn").attr("onclick","delete_selected_file()");
    }
}

function delete_selected_file(){
    var delete_array = [];
    $("input[name=checkAll]").each(function(){
        if($(this).is(':checked')){
            var arr = $(this).attr("value");
            delete_array.push(arr);
        }
    });
    deleteFile(delete_array);
}

