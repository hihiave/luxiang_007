/**
 * Created by 95 on 2016/11/3.
 */
$(function(){
    get_category_select();
   
   /* get_file_points();
    $("#file_search_input").oninput(function(){
    	var filepoints=new Array();
    	
  	  $.ajax({
            type:'post',
            url:"/mybatis/FileInfoController/get_file_points.do",          
            dataType:"json",
            success:function(data){
            
          	   var all_pass_file =data["FileInfo_check"];
          	  
          	   for(var i = 0;i < all_pass_file.length;i++){
          	    	filepoints.unshift(all_pass_file[i].fileName);
          	    	
          	   }
             }
            
        })
        $("#file_search-input").autocomplete({
            source:filepoints
          });
    })*/
})
function myfunction(){
   var filepoints =new Array();
   var fileinput=$("#file_search_input").val();
   var filepropertyftype=$("#key_select").val();
   
   $.ajax({
       type:'post',
       url:"/mybatis/FileInfoController/get_file_points.do",    
       data:{"filePropertyType":filepropertyftype,"value":fileinput},
       dataType:"json",
       success:function(data){
       
     	   var search_file =data["FileInfo_check"];
     	  
     	   for(var i = 0;i < search_file.length;i++){
     	    	filepoints.unshift(search_file[i]);
     	    	
     	   }
        }
       
   })
   $("#file_search_input").autocomplete({
       source:filepoints
     });
    }
//获取文件提示
/*function get_file_points(){
	 var file_input = $.trim($("#file_search_input").val());
	 var key = $.trim($("#key_select").val());
	 var category = $.trim($("#category_select").val());
	 
	("#file_search_input").autocomplete()
}
*/

function get_category_select(){
    $.ajax({
        type:'post',
        url:"/mybatis/CategoryController/get_category.do",
        dataType:"json",
        success:function(data){
            //var _select = $("#category_select>option");
            //_select.remove();
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