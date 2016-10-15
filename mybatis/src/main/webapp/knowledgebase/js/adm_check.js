/**
 * Created by 95 on 2016/10/12.
 */
//审查页面出来，加载表格
$(function(){
    get_all_user_is_check();

})

//请求全部待审核用户
function get_all_user_is_check(){
    $.ajax({
        type:'post',
        url:"/mybatis/UserInfoController/check.do",
        dataType:"json",
        success:function get_all_check_user(data){
            var _table = $("#check_result>tr");
            _table.remove();
            var all_check_user = data["UserInfo_check"];
            for(var i = 0;i < all_check_user.length;i++){
                var tr_begin = "<tr>";
                var tr_end = "</tr>";
                var td_1 = "<td style='padding-top:15px;'><input type='checkbox' name='checkAll'></td>";
                var td_2 = "<td class='Name' style='padding-top:15px;'>" + all_check_user[i].userName +"</td>";
                var td_3 = "<td style='padding-top:15px;padding-left:15px;'>待审核</td>";
                var td_4 = "<td><button class='btn btn-primary' onclick='check_one_pick(this)'>通过</button></td>";
                var td_5 = "<td><button class='btn btn-primary'>拒绝</button></td>";
                var content = tr_begin + td_1 + td_2 + td_3 + td_4 + td_5 + tr_end;
                $("#check_result").append(content);
            }
        }
    })
}


//全选

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
            ckbs[i].checked = true;
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
//审核选中的用户（多个用户）
function check_all_pick(obj){
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
    $.ajax({
        type:'post',
        url:"/mybatis/UserInfoController/check_pass_user.do",
        dataType:"json",
        data:{"check_user_array":check_array.toString()},
        success:function(data){
            get_all_user_is_check();
        }
    })
}
//审核选中的用户（单个用户）
function check_one_pick(obj){
    var check_one_user = $(obj).parent().siblings()[1].innerHTML;
    //console.log(check_one_user);
    $.ajax({
        type:'post',
        url:"/mybatis/UserInfoController/check_pass_user.do",
        dataType:"json",
        data:{"check_user_array":check_one_user},
        success:function(data){
            if(data){
                get_all_user_is_check();
            }
        }
    })
}

