/**
 * Created by 95 on 2016/10/12.
 */
//审查页面出来，加载表格
$(function(){
    get_all_user_is_check();

})

//请求全部待审核用户
function get_all_user_is_check(){
    var src = "/mybatis/UserInfoController/check.do";
    sendAjaxRequest(src,{"page_Now":1},get_all_check_user_one,function(data){});
}

function get_all_check_user_one(data){
    var _table = $("#check_result>tr");
    _table.remove();
    var all_check_user = data["UserInfo_check"];
    for(var i = 0;i < all_check_user.length;i++){
        var tr_begin = "<tr>";
        var tr_end = "</tr>";
        var td_1 = "<td ><input type='checkbox' name='checkAll'></td>";
        var td_2 = "<td class='Name' >" + all_check_user[i].userName +"</td>";
        var td_3 = "<td class='trueName' >"+all_check_user[i].userRealName+"</td>";      
        var td_4 = "<td padding-left:15px;' style='text-align:center;'>"+timeStampFormatDay(all_check_user[i].userRegisterTime*1000)+"</td>";
        var td_5 = "<td padding-left:15px;  style='text-align:center;'>待审核</td>";
        var td_6 = "<td style='padding-bottom:3px;padding-top:3px;'>" +
        		"<button class='button button-action button-rounded button-small' style='width:64px;' onclick='check_one_pick(this)'>通过</button></td>";
        var td_7 = "<td style='padding-bottom:3px;padding-top:3px;'>" +
        		"<button class='button button-caution button-rounded button-jumbo button-small' style='width:64px;' onclick='refuse_one_pick(this)'>拒绝</button></td>";
        var content = tr_begin + td_1 + td_2 + td_3 + td_4 + td_5 + td_6 + td_7 + tr_end;
        $("#check_result").append(content);

    }
    createNewPagination(data,"inquire_check","/mybatis/UserInfoController/check.do",get_all_check_user_one,"first_check_click","last_check_click","page-two","");
}

//拒绝选中的用户（多个用户）
function refuse_all_pick(obj){
    var check_num_1 = 0;
    $("input[name=checkAll]").each(function(){
        if($(this).is(':checked')){
            check_num_1 += 1;
        }
    });
    if(check_num_1 > 0){
        $(obj).attr({"data-toggle":"modal","data-target":"#refuse_check"});
        $("#refuse_info").html("确认拒绝所选的用户么？");
        $("#refuse_info").removeClass();
        //$("#check_info").addClass(check_one_user);
        $("#refuse_click").removeAttr("click_name");
        $("#refuse_click").attr("click_name","refuse_all");
    }else{
        $(obj).attr({"data-toggle":"modal","data-target":"#pass_remind"});
        $("#remind_info").html("请选择要拒绝的用户");
    }


}
//确认拒绝选中的用户
function refuse_all_selected(){
    var check_num = 0;
    $("input[name=checkAll]").each(function(){
        if($(this).is(':checked')){
            check_num += 1;
        }
    });
    //console.log(check_num);
    var check_array = new Array(check_num);
    $("input[name=checkAll]").each(function(index){
        if($(this).is(':checked')){
            check_array[index] = $(this).parent().siblings()[0].innerHTML;
            //console.log($(this).parent().next().innerHTML);
        }
    });
    //console.log(check_array);
    sendAjaxRequest("/mybatis/UserInfoController/check_refuse_user.do",{"check_user_array":check_array},get_all_user_is_check,function(data){});


}
//拒绝选中的用户（单个用户）
function refuse_one_pick(obj){
    var check_one_user = $(obj).parent().siblings()[1].innerHTML;
    $(obj).attr({"data-toggle":"modal","data-target":"#refuse_check"});
    $("#refuse_info").html("确认拒绝  "+check_one_user+"  么？");
    $("#refuse_info").removeClass();
    $("#refuse_info").addClass(check_one_user);
    $("#refuse_click").removeAttr("click_name");
    $("#refuse_click").attr("click_name","refuse_one");

}

//确认拒绝单个用户
function refuse_one_selected(){
    var check_user = $("#refuse_info").attr("class");
    sendAjaxRequest("/mybatis/UserInfoController/check_refuse_user.do",{"check_user_array":check_user},get_all_user_is_check,function(data){});

}

//确认通过按钮
function refuse_info_ok(){
    var btn_info = $("#refuse_click").attr("click_name");
    if(btn_info == "refuse_one"){
        refuse_one_selected();
    }else if(btn_info == "refuse_all"){
        refuse_all_selected();
    }
}



//通过选中的用户（多个用户）
function check_all_pick(obj){
    var check_num_1 = 0;
    $("input[name=checkAll]").each(function(){
        if($(this).is(':checked')){
            check_num_1 += 1;
        }
    });
    if(check_num_1 > 0){
        $(obj).attr({"data-toggle":"modal","data-target":"#pass_check"});
        $("#check_info").html("确认通过所选的用户么？");
        $("#check_info").removeClass();
        //$("#check_info").addClass(check_one_user);
        $("#pass_click").removeAttr("onclick_name");
        $("#pass_click").attr("onclick_name","pass_all");
    }else{
        $(obj).attr({"data-toggle":"modal","data-target":"#pass_remind"});
        $("#remind_info").html("请选择要通过的用户");
    }


}
//确认通过选中的用户
function pass_all_selected(){
    var check_num = 0;
    $("input[name=checkAll]").each(function(){
        if($(this).is(':checked')){
            check_num += 1;
        }
    });
    console.log(check_num);
    var check_array = new Array(check_num);
    $("input[name=checkAll]").each(function(index){
        if($(this).is(':checked')){
            check_array[index] = $(this).parent().siblings()[0].innerHTML;
            //console.log($(this).parent().next().innerHTML);
        }
    });
    console.log(check_array);
    sendAjaxRequest("/mybatis/UserInfoController/check_pass_user.do",{"check_user_array":check_array},get_all_user_is_check,function(data){});


}
//审核选中的用户（单个用户）
function check_one_pick(obj){
    var check_one_user = $(obj).parent().siblings()[1].innerHTML;
    $(obj).attr({"data-toggle":"modal","data-target":"#pass_check"});
    $("#check_info").html("确认通过  "+check_one_user+"  么？");
    $("#check_info").removeClass();
    $("#check_info").addClass(check_one_user);
    $("#pass_click").removeAttr("onclick_name");
    $("#pass_click").attr("onclick_name","pass_one");

}

//确认通过单个用户
function pass_one_selected(){
    var check_user = $("#check_info").attr("class");
    sendAjaxRequest("/mybatis/UserInfoController/check_pass_user.do",{"check_user_array":check_user},get_all_user_is_check,function(data){});

}

//确认通过按钮
function pass_info_ok(){
    var btn_info = $("#pass_click").attr("onclick_name");
    if(btn_info == "pass_one"){
        pass_one_selected();
    }else if(btn_info == "pass_all"){
        pass_all_selected();
    }
}
