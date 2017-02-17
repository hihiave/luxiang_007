//审查页面出来，加载表格
$(function(){
    get_all_public_file();

})

//获取公有文件
function get_all_public_file(){
    var src = "/mybatis/FileInfoController/publicfile.do";
    sendAjaxRequest(src,{"page_Now":1,"fileProperty":"fullText"},get_all_public_file_table);
}

function get_all_public_file_table(data){
    var _table = $("#pub_file>tr");
    _table.remove();
    var all_pub_file = data["pub_file"];
    for(var i = 0;i < all_pub_file.length;i++){
        var tr_begin = "<tr>";
        var tr_end = "</tr>";
       // var td_1 = "<td style='padding-top:15px;'><a href='##' onclick='ReadOnLine(this)' path='"+all_pub_file[i].fileUrl+"'>"+all_pub_file[i].fileName+"</a></td>";
        var td_1 = "<td style='padding-top:15px;'><a href='../"+all_pub_file[i].fileUrl+"' >"+all_pub_file[i].fileName+"</a></td>";
        var td_2 = "<td style='padding-top:15px;text-align:center'>"+all_pub_file[i].fileAuthor+"</td>";
        var td_3 = "<td style='padding-top:15px;text-align:center'>"+timeStampFormatDay(all_pub_file[i].fileUploadTime*1000)+"</td>";
        var td_4 = "<td style='text-align:center'><button class='button button-primary button-rounded button-small' did='public' bid='"+all_pub_file[i].fileId+"'  onclick='download(this)' path='"+all_pub_file[i].fileUrl+"'>下载</button></td>";
        //var td_5 = "<td><button class='btn btn-primary' data-toggle='modal' data-target='#preview' onclick='pre_file(this)'>预览</button></td>";
        var td_5 = "<td style='padding-top:15px;text-align:center'>"+ all_pub_file[i].fileKeywords + "</td>";
        var td_6="<td style='padding-top:15px;text-align:center'>"+all_pub_file[i].fileDownloadCount+"</td>"
        var content = tr_begin + td_1 + td_5 + td_2 + td_3 + td_4 + td_6 + tr_end;
        $("#pub_file").append(content);
    }
    createNewPagination(data,"file_public","/mybatis/FileInfoController/publicfile.do",get_all_public_file_table,"first_file_click","last_file_click","page-file-three",{"fileProperty":"fullText"});
}

//function down_file(obj){
//    var btn_name = $(obj);
//    var file_name = btn_name.parent().siblings()[0].innerHTML;
//
//    btn_name.attr("data-target","#download");
//    $("#download_file").html("是否下载："+file_name+"？");
//    $("#download_file").removeClass();
//    $("#download_file").addClass(file_name);
//}

//function pre_file(obj){
//    var btn_name = $(obj);
//    var file_name = btn_name.parent().siblings()[0].innerHTML;
//
//    btn_name.attr("data-target","#preview");
//    $("#preview_file").html("是否预览："+file_name+"？");
//    $("#preview_file").removeClass();
//    $("#preview_file").addClass(file_name);
//}
////下载
//function downloadFile(obj){
//	var attr_d = $("#download_file").attr("class");
//	/*$.ajax(
//			{
//		type:'post',
//        url:"/mybatis/FileDownloadController/fileDownload.do",
//        data:{"filename":attr_d},
//        dataType:"json",
//
//	}
//			)*/
//}
//预览
//function previewFile(obj){
//	var attr_p=$("preview_file").attr("class");
//	$.ajax()
//}