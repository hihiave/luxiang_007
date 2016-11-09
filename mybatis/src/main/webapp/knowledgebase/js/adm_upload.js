/**
 * Created by 95 on 2016/11/8.
 */
$(function(){
    get_category_select_file();
})
function get_category_select_file(){
    $.ajax({
        type:'post',
        url:"/mybatis/CategoryController/get_category.do",
        dataType:"json",
        success:function(data){
            var _select = $(".select-class-name>option");
            _select.remove();
            var all_select_category = data["category"];
            //console.log(all_select_category.length);
            for(var i = 0;i < all_select_category.length;i++){
                var op = "<option value='"+ all_select_category[i].categoryName +"'>" +all_select_category[i].categoryName+"</option>";
                $(".select-class-name").append(op);
                //console.log(all_select_category[i].categoryName);
            }
        }
    })
}