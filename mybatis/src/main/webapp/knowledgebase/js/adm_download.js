/**
 * Created by 95 on 2016/11/15.
 */
$(function(){
    get_my_download();
})

function get_my_download(){
    var src = "/mybatis/MyDownloadController/get_my_download.do";
    sendAjaxRequest(src,{"page_Now":1},get_my_download_table);
}

function get_my_download_table(obj){
    var _download_table = $("#down-file>tr");
    _download_table.remove();
    var all_download_file = obj["download_file"];
    console.log(all_download_file.length);
    for(var i = 0; i < all_download_file.length;i++ ){
        //console.log(all_download_file[i].fileName);
        //console.log(all_download_file[i].fileAuthor);
        //console.log(all_download_file[i].myDownloadTime);
        var tr_begin = "<tr>";
        var tr_end = "</tr>";
        var td_0 = "<td><input type='checkbox' name='checkAll' value="+all_download_file[i].myDownloadId+"></td>";
        var td_1 = "<td><a href='../"+all_download_file[i].fileUrl+"' >"+all_download_file[i].fileName+"</a></td>";
        var td_2 = "<td style='text-align:center;'>"+all_download_file[i].fileAuthor+"</td>";
        var td_3 = "<td style='text-align:center;'>"+timeStampFormatDay(all_download_file[i].myDownloadTime*1000)+"</td>";
        var content = tr_begin  + td_0 + td_1 + td_2 + td_3 + tr_end;
        $("#down-file").append(content);
    }
    createNewPagination(obj,"file_my_download","/mybatis/MyDownloadController/get_my_download.do",get_my_download_table,"first_file_click","last_file_click","page-file-mydownload",{});
}


function deleteDownloadFile(delete_array){
    $.ajax({
        type:'post',
        url:"/mybatis/MyDownloadController/del_my_download.do",
        dataType:"json",
        traditional:true,
        data:{"myDownloadIds":delete_array},
        success:function(data)
        {
            if(data["flag"]){

            	get_my_download();
            }else{
                alert("网络故障,稍后重试");
            }

        }
    })
}

//function delete_one_file(obj){
//    var file_id = $(obj).attr("value");
//    var delete_array = [];
//    delete_array.push(file_id);
//    deleteFile(delete_array);
//}



function delete_mydownload(obj){
    var delete_count = 0;
    $("input[name=checkAll]").each(function(){
        if($(this).is(':checked')){
            delete_count += 1;
        }
    });
    console.log("选中的文件个数："+delete_count);
    if(delete_count == 0){
        $(obj).attr({"data-toggle":"modal","data-target":"#delete_download"});
        $("#myModalLabel-delete_title").html("删除文件提示");
        $("#delete_download_info").html("请选择需要删除的文件");
    }else{
        $(obj).attr({"data-toggle":"modal","data-target":"#delete_download"});
        $("#myModalLabel-delete_title").html("删除文件提示");

        $("#delete_download_info").html("确定删除选中文件？此次删除后不可恢复");
        $("#delete_btn").attr("onclick","delete_download_file()");
    }
}

function delete_download_file(){
    var delete_array = [];
    $("input[name=checkAll]").each(function(){
        if($(this).is(':checked')){
            var arr = $(this).attr("value");
            delete_array.push(arr);
        }
    });
    deleteDownloadFile(delete_array);
}
