//页面加载完毕加载表格
$(function(){
    get_all_is_check();
})
//获取全部通过用户
function get_all_is_check(){
    var src = "/mybatis/UserInfoController/Is_pass.do";
    sendAjaxRequest(src,{"page_Now":1,"username":null},function(data){
        get_all_check_user(data)
    },function(data){});
}




function get_all_check_user(obj){
    var name = $.trim($("#search-in").val());
    var _table = $("#search_result>tr");
    _table.remove();
    var all_pass_user = obj["UserInfo_check"];
    //console.log(all_pass_user[0].userName);
    for(var i = 0;i < all_pass_user.length;i++){
        var tr_begin = "<tr>";
        var tr_end = "</tr>";
        var td_1 = "<td>"+(i+1)+"</td>";
        var td_2 = "<td class='Name'>"+all_pass_user[i].userName+"</td>"
        var td_3 = "<td></td>";
        var td_4 = "<td style='padding-bottom:3px;padding-top:3px;width:116px;'>"+"<button class='btn btn-primary' data-toggle='modal' data-target='' onclick='deleteUser(this)'>删除用户</button>"+"</td>";
        var td_5 = "<td style='padding-bottom:3px;padding-top:3px;width:116px;'>"+"<button class='btn btn-primary' data-toggle='modal' data-target='' onclick='user_selected(this)' >重置密码</button>"+"</td>";
        var content = tr_begin + td_1 + td_2 + td_3 + td_4 + td_5 + tr_end;
        $("#search_result").append(content);

    }
    createNewPagination(obj,"inquire_query","/mybatis/UserInfoController/Is_pass.do",get_all_check_user,"first_inquire_click","last_inquire_click","page-one",{"username":name});
}
/**
 * wsz
 * 管理员添加用户
 */
function admadduser(){
    var username = $.trim($("#username1").val());

        if(username != null) {
            $.ajax(
                {
                    type:'post',
                    url:"/mybatis/UserInfoController/adm-adduser.do",
                    data:{username:username},
                    dataType:'json',
                    success:function(data){
                        if(data["flag"] == "chenggong"){
                            $("#add_user_result").html("添加用户成功");
                            $("#tianjia_result").modal('show');
                            get_all_is_check();
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


}

/**
 * wsz
 * 搜索用户
 */
function searchuser(){
    var name = $.trim($("#search-in").val());
    var src = "/mybatis/UserInfoController/Is_pass.do";
    sendAjaxRequest(src,{"page_Now":1,"username":name},function(data){
        get_all_check_user(data)
    },function(data){});
}
/**
 * wsz
 * 点击重置密码，
 */
function user_selected(obj){
    var btn_name = $(obj);
    var selected_name = btn_name.parent().siblings()[1].innerHTML;
    var selected_id = btn_name.parent().siblings()[0].innerHTML;
    btn_name.attr("pid",selected_id);
    btn_name.attr("data-target","#chongzhi");
    $("#reset_user_password").html("重置用户："+selected_name+"的密码？");
    $("#reset_user_password").removeClass();
    $("#reset_user_password").addClass(selected_name);
    //resetUserPassword(selected_id);
    //console.log(selected_name.innerHTML);
    //if($(".Name").hasClass("selected").length > 1){
    //    for(var i = 0;i < $(".Name").hasClass("selected").length;i++){
    //        $(".Name").hasClass("selected")[i].removeClass("selected");
    //    }
    //}


}

/**
 * wsz
 * 管理员重置密码
 */
function resetUserPassword(obj){
    var attr_p = $("#reset_user_password").attr("class");
    console.log(attr_p+"!!!!!!");
        $.ajax(
            {
                type:'post',
                url:"/mybatis/UserInfoController/resetpsw.do",
                data:{"select_username":attr_p},
                dataType:"json",
                success:function(data){
                    console.log(data["flag"]);
                    if(data){

                        alert("修改密码成功！");
                    }
                }
            }
        )
}
/**
 * wsz
 * 管理员点击删除用户
 */
function deleteUser(obj){
    var btn_name = $(obj);
    var selected_name = btn_name.parent().siblings()[1].innerHTML;
    var selected_id = btn_name.parent().siblings()[0].innerHTML;
    btn_name.attr("pid","del"+selected_id);
    btn_name.attr("data-target","#shanchu");
    $("#adm_delete").html("删除用户："+selected_name+"？");
    $("#adm_delete").removeClass();
    $("#adm_delete").addClass(selected_name);
}
function delete_ok(obj){
    var attr_p = $("#adm_delete").attr("class");
    console.log(attr_p);
    $.ajax(
        {
            type:'post',
            url:"/mybatis/UserInfoController/del_user.do",
            data:{"select_username":attr_p},
            dataType:"json",
            success:function(data){
                console.log(data["flag"]);
                if(data["flag"]==true){

                    get_all_is_check();
                }
            }
        }
    )
}

