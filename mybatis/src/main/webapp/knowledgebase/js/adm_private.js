
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
        success:function get_aal_private_file(data){
            var _table = $("#pri_file>tr");
            _table.remove();
            var all_pri_file = data["pri_file"];
            for(var i = 0;i < all_pri_file.length;i++){
                var tr_begin = "<tr>";
                var tr_end = "</tr>";
                var td_1 = "<td style='padding-top:15px;'>"+all_pri_file[i].fileName+"</td>";
                var td_2 = "<td style='padding-top:15px;'>"+all_pri_file[i].fileUploadTime+"</td>";
                var td_3 = "<td><button class='btn btn-primary' data-toggle='modal' data-target='#download' onclick='down_file(this)'>下载</button></td>";
                var td_4 = "<td><button class='btn btn-primary' data-toggle='modal' data-target='#preview' onclick='pre_file(this)'>预览</button></td>";
                var content = tr_begin + td_1 + td_2 + td_3 + td_4  + tr_end;
                $("#pri_file").append(content);
            }
        }
    })
}

function down_file(obj){
    var btn_name = $(obj);
    var file_name = btn_name.parent().siblings()[0].innerHTML;

    btn_name.attr("data-target","#download");
    $("#download_file").html("是否下载："+file_name+"？");
    $("#download_file").removeClass();
    $("#download_file").addClass(file_name);
}

function pre_file(obj){
    var btn_name = $(obj);
    var file_name = btn_name.parent().siblings()[0].innerHTML;

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
}