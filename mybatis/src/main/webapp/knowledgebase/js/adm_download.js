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
        var td_1 = "<td><a href='../"+all_download_file[i].fileUrl+"' >"+all_download_file[i].fileName+"</a></td>";
        var td_2 = "<td>"+all_download_file[i].fileAuthor+"</td>";
        var td_3 = "<td>"+timeStampFormatDay(all_download_file[i].myDownloadTime*1000)+"</td>";
        var content = tr_begin + td_1 + td_2 + td_3 + tr_end;
        $("#down-file").append(content);
    }
    createNewPagination(obj,"file_my_download","/mybatis/MyDownloadController/get_my_download.do",get_my_download_table,"first_file_click","last_file_click","page-file-mydownload",{});
}