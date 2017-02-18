
//审查页面出来，加载表格
$(function(){
	get_category();
})




function get_category(){
	var userid=document.getElementById("userid");
    $.ajax({
        type:'post',
        url:"/mybatis/CategoryController/get_category.do",
        data:{"userid":userid.value},
        dataType:"json",
        success:function get_category(data){
           var _table = $("#category_result>tr");
           var _ul = $("#category_result>ul");
            _table.remove();
            _ul.remove();
            var category = data["category"];
            for(var i = 0;i < category.length;i++){
                var tr_begin = "<tr id='"+category[i].categoryName+"th'>";
                var tr_end = "</tr>";
                var td_1 = "<td >"+category[i].categoryName+"</td>";
                var td_2 = "<th style='padding-bottom:3px;padding-top:3px;'>" +
        				"<button class='button button-royal button-rounded button-giant button-small'" +
        				" onclick='change_cate(this)'>修改</button></th>";
                var td_4 = "<th style='padding-bottom:3px;padding-top:3px;'>" +
			        		"<button class='button button-action button-rounded button-small' " +
			        		"onclick='add_chid_cate(this)'>添加子类</button></th>";
                var td_3 ="<th style='padding-bottom:3px;padding-top:3px;'>" +
                		"<button class='button button-caution button-rounded button-jumbo button-small' " +
                		"onclick='delete_cate(this)'>删除</button></th>";
                var td_5 ="<th><a href='#"+category[i].categoryName+"' data-toggle='collapse'" +
                		"onclick='get_child_cate(this)'><span class='glyphicon glyphicon-chevron-down'></span></a></th>"
                var content = tr_begin + td_1 + td_5 + td_2 + td_3 + td_4  +  tr_end ;
                $("#category_result").append(content);
                var ul_begin="<tr style='margin:0px' id='"+category[i].categoryName+"' class='panel-collapse collapse '>";
                var ul_end ="</tr>";
                var content2 =ul_begin + ul_end;
                $("#"+category[i].categoryName+"th").after(content2);
            }
        
            

        }
    })
}
function get_child_cate(obj){
	var userid=document.getElementById("userid");
	var btn_name = $(obj);
    var father_cate_name = btn_name.parent().siblings()[0].innerHTML;
  
        $.ajax({
            type:'post',																																																																																																																							
            url:"/mybatis/CategoryController/get_child_category.do",
            data:{"userid":userid.value,"categoryBelongTo":father_cate_name},
            dataType:"json",
            success:function get_category(data){
                var _table = $("#"+father_cate_name+">tr");
                _table.remove();
                var category = data["category"];
                for(var i = 0;i < category.length;i++){
                    var li_begin = "<tr>";
                    var li_end   = "</tr>";
                    var td_1 = "<td width='150px' style='padding-left:20px;'>"+category[i].categoryName+"</td>";
                    var td_2 = "<th style='padding-bottom:3px;padding-top:3px;width:100px;'>" +
            				"<button class='button button-royal button-rounded button-giant button-small'" +
            				" onclick='change_cate(this)'>修改</button></th>";
                   
                    var td_3 ="<th style='padding-bottom:3px;padding-top:3px;'>" +
                    		"<button class='button button-caution button-rounded button-jumbo button-small' " +
                    		"onclick='delete_cate(this)'>删除</button></th>";
                 
                 
                    var content = li_begin + td_1 +  td_2 + td_3 +  li_end ;
                    $("#"+father_cate_name+"").append(content);
                }
            
                

            }
  
    })

    
   
}

function add_chid_cate(obj){
	 	var btn_name = $(obj);
	    var father_cate_name = btn_name.parent().siblings()[0].innerHTML;
	    btn_name.attr({"data-toggle":"modal","data-target":"#add_child_cate"});
	    $("#father_cate").removeClass();
	    $("#father_cate").addClass(father_cate_name);
}
function add_childcate(obj){
	var cate_name=document.getElementById("child_cate_name");
	 var userid=document.getElementById("userid");
	 var attr_p = $("#father_cate").attr("class");
	 var btnobj=document.getElementById("btn_changemsg");
	 	btnobj.onclick=delete_ok;
	    if(cate_name != "") {
	         $.ajax(
	             {
	                 type:'post',
	                 url:"/mybatis/CategoryController/add_cate.do",
	                 data:{catename:cate_name.value,userid:userid.value,
	                	 categoryBelongTo:attr_p},
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

	     }else{
	    	 $("#add_cate_result").html("请输入类别名称！");
	         $("#tianjia_result").modal('show');
	     }
}

function add_cate(){
	var cate_name = $.trim($("#cate_name").val());
	var userid=document.getElementById("userid");
	 if(cate_name != "") {
         $.ajax(
             {
                 type:'post',
                 url:"/mybatis/CategoryController/add_cate.do",
                 data:{catename:cate_name,userid:userid.value},
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

     }else{
    	 $("#add_cate_result").html("请输入类别名称！");
         $("#tianjia_result").modal('show');
     }
}

function change_cate(obj){
	var btn_name = $(obj);
	btn_name.attr({"data-toggle":"modal","data-target":"#changemsg"});
    var cate_name = btn_name.parent().siblings()[0].innerHTML;
    $("#old_catename").val(cate_name);
    var btnobj=document.getElementById("btn_changemsg");
	btnobj.onclick=delete_ok;
	function delete_ok(){
		
		var userid=document.getElementById("userid");
		var username=document.getElementById("username");
		var oldname=document.getElementById("old_catename");
		var newname=document.getElementById("new_catename");
	if(newname.value==""){
		document.getElementById("checkmsg").innerHTML = "<font color='red' size='2px'>类名不能为空!</font>";
		return;
	}
	else{
	    $.ajax(
		        {
		            type:'post',
		            url:"/mybatis/CategoryController/change_cate.do",
		            data:{"old_catename":oldname.value,"username":username.value,
		            	"userid":userid.value,"new_catename":newname.value},
		            dataType:"json",
		            success:function(data){
		                if(data["flag"] == "chenggong"){
		                	$('#changemsg').modal('toggle');
	                         $("#add_cate_result").html("修改类别成功");
	                         $("#tianjia_result").modal('show');
	                         get_category();
	                     }else if(data["flag"] == "shibai"){
	                    	 $('#changemsg').modal('toggle');
	                         $("#add_cate_result").html("修改类别失败");
	                         $("#tianjia_result").modal('show');
	                         get_category();
	                     }else if(data["flag"] == "cunzai"){
	                    	 $('#changemsg').modal('toggle');
	                         $("#add_cate_result").html("该类别已存在");
	                         $("#tianjia_result").modal('show');
	                         get_category();
	                     }
		            }
		        }
		    )
	}
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
	 var userid=document.getElementById("userid");
	    $.ajax(
	        {
	            type:'post',
	            url:"/mybatis/CategoryController/del_cate.do",
	            data:{"cate_name":attr_p,"userid":userid.value},
	            dataType:"json",
	            success:function(data){
	                console.log(data["flag"]);
	                if(data["flag"]==true){
	                	 $("#add_cate_result").html("删除成功!");
                         $("#tianjia_result").modal('show');
	                	get_category()
	                }
	            }
	        }
	    )
}
