
//审查页面出来，加载表格
$(function(){
    get_all_checkfile();

})

//获取所有未审核的文件
function get_all_checkfile(){
    $.ajax({
        type:'post',
        url:"/mybatis/FileInfoController/get_all_checkfile.do",
        dataType:"json",
        success:function get_all_checkfile(data){
            var _table = $("#file_result>tr");
            _table.remove();
            var checkfile = data["checkfile"];
            for(var i = 0;i < checkfile.length;i++){
                var tr_begin = "<tr>";
                var tr_end = "</tr>";
                var td_1 = "<td style='padding-top:15px;'><label><input type='checkbox' name='checkAll'></label></td>";
                var td_2 = "<td style='padding-top:15px;width:280px;'>"+checkfile[i].fileName+"</td>";
                var td_3 = "<td style='padding-top:15px;'>"+checkfile[i].fileAuthor+"</td>";
                var td_4 = "<td><button class='btn btn-primary' onclick='check_one_file(this)'>通过</button></td>";
            	var td_5 = "<td><button class='btn btn-primary'>拒绝</button></td>";
                var content = tr_begin + td_1 + td_2 + td_3 + td_4 + td_5 + tr_end;
                $("#file_result").append(content);
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
    

}

function check_one_file(obj){
	var check_one_file = $(obj).parent().siblings()[1].innerHTML;
    $(obj).attr({"data-toggle":"modal","data-target":"#pass"});
    $("#file_remind").html("确认通过  "+check_one_file+"  么？");
    $("#file_remind").removeClass();
    $("#file_click").removeAttr("onclick_name");
    $("#file_click").attr("onclick_name","pass_one");
}

function pass_one_file(obj){
	

}

function pass_info_ok(){
    var btn_info = $("#file_click").attr("onclick_name");
    if(btn_info == "pass_one"){
        pass_one_file();
    }else if(btn_info == "pass_all"){
        pass_all_file();
    }
}