
//审查页面出来，加载表格
$(function(){
	get_category();

})


function get_category(){
    $.ajax({
        type:'post',
        url:"/mybatis/qmd/get_category.do",
        dataType:"json",
        success:function get_category(data){
           var _table = $("#category_result>tr");
            _table.remove();
            var category = data["category"];
            for(var i = 0;i < category.length;i++){
                var tr_begin = "<tr>";
                var tr_end = "</tr>";
                var td_1 = "<td >"+category[i].category_name+"</td>";
                
                var content = tr_begin + td_1 +  tr_end;
                $("#category_result").append(content);
            }

        }
    })
}
/*function add_cate(){
	var cate_name = $.trim($("#cate_name").val());
	 if(cate_name != null) {
         $.ajax(
             {
                 type:'post',
                 url:"/mybatis/qmd/add_cate.do",
                 data:{catename:cate_name},
                 dataType:'json',
                 success:function(data){
                     if(data["flag"] == "chenggong"){
                         $("#add_user_result").html("添加用户成功");
                         $("#tianjia_result").modal('show');
                         get_all_is_ckeck();
                     }else if(data["flag"] == "shibai"){
                         $("#add_user_result").html("添加用户失败");
                         $("#tianjia_result").modal('show');
                     }else if(data["flag"] == "cunzai"){
                         $("#add_user_result").html("该用户名已存在");
                         $("#tianjia_result").modal('show');
                     }
                 }
             }
         )

     }
}*/
