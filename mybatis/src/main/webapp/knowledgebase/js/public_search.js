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
    var _category = $("#category_select").val();
    var _key = $("#key_select").val();
    var _search_input = $.trim($("#file_search_input").val());
    console.log(_category);
    console.log(_key);
    console.log(_search_input);
}