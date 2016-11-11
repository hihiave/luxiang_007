/**
 * Created by 95 on 2016/11/3.
 */
$(function(){
    get_category_select();
})
function get_category_select(){
    $.ajax({
        type:'post',
        url:"/mybatis/CategoryController/get_category.do",
        dataType:"json",
        success:function(data){
            var _select = $("#category_select>option");
            _select.remove();
            var all_select_category = data["category"];
            //console.log(all_select_category.length);
            for(var i = 0;i < all_select_category.length;i++){
                var op = "<option value='"+ all_select_category[i].categoryName +"'>" +all_select_category[i].categoryName+"</option>";
                $("#category_select").append(op);
                //console.log(all_select_category[i].categoryName);
            }
        }
    })
}

function send_search_info(){
    var _category = $.trim($("#category_select").val());
    var _key = $.trim($("#key_select").val());
    var _search_input = $.trim($("#file_search_input").val());
    var src = "/mybatis/FileInfoController/search_file.do"
    if(_search_input != ""){
        var dataPost = {"fileCategory":_category,"fileProperty":_key,"fileIn":_search_input};
        //console.log(dataPost);
        sendAjaxRequest(src,dataPost,function(data){},function(data){});
        $("#default_panel").css("display","none");
        $("#search_panel").css("display","block");
    }
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