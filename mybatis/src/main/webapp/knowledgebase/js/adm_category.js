
//审查页面出来，加载表格
$(function(){
	get_category();

})


function get_category(){
    $.ajax({
        type:'post',
        url:"/mybatis/CategoryController/get_category.do",
        dataType:"json",
        success:function get_category(data){
           var _table = $("#category_result>tr");
            _table.remove();
            var category = data["category"];
            for(var i = 0;i < category.length;i++){
                var tr_begin = "<tr>";
                var tr_end = "</tr>";
                var td_1 = "<td >"+category[i].categoryName+"</td>";
                var td_2 = "<th style='padding-bottom:3px;padding-top:3px;width:116px;'>" +
        				"<button class='btn btn-primary' onclick='change_cate(this)'>修改类别</button></th>";
                var td_3 ="<th style='padding-bottom:3px;padding-top:3px;width:116px;'>" +
                		"<button class='btn btn-primary' onclick='delete_cate(this)'>删除类别</button></th>";
                var content = tr_begin + td_1 + td_2 + td_3 + tr_end;
                $("#category_result").append(content);
            }

        }
    })
}
function add_cate(){
	var cate_name = $.trim($("#cate_name").val());
	 if(cate_name != null) {
         $.ajax(
             {
                 type:'post',
                 url:"/mybatis/CategoryController/add_cate.do",
                 data:{catename:cate_name},
                 dataType:'json',
                 success:function(data){
                     if(data["flag"] == "chenggong"){
                         $("#add_cate_result").html("添加类别成功");
                         $("#tianjia_result").modal('show');
                         get_category();
                     }else if(data["flag"] == "shibai"){
                         $("#add_cate_result").html("添加类别失败");
                         $("#tianjia_result").modal('show');
                     }else if(data["flag"] == "cunzai"){
                         $("#add_cate_result").html("该类别已存在");
                         $("#tianjia_result").modal('show');
                     }
                 }
             }
         )

     }
}

function delete_cate(obj){
	 var btn_name = $(obj);
	    var cate_name = btn_name.parent().siblings()[0].innerHTML;
	    btn_name.attr({"data-toggle":"modal","data-target":"#shanchu"});
	    $("#adm_delete").html("删除类别："+cate_name+"吗？");
	    $("#adm_delete").removeClass();
	    $("#adm_delete").addClass(cate_name);
}

function delete_ok(obj){
	 var attr_p = $("#adm_delete").attr("class");
	   
	    $.ajax(
	        {
	            type:'post',
	            url:"/mybatis/CategoryController/del_cate.do",
	            data:{"cate_name":attr_p},
	            dataType:"json",
	            success:function(data){
	                console.log(data["flag"]);
	                if(data["flag"]==true){

	                	get_category()
	                }
	            }
	        }
	    )
}