/**
 * 跳转用户信息界面
 */
function checkUserManage(){
	var src=getBasePath()+"zgmp/check_user_manage";
	  sendAjaxRequest(src,"",function(){
          window.location.href=getBasePath()+"zg/view/user_manage.jsp";
          //checkUserManageInfo();
      },error,true);
}
function checkUserManageSuccess(data){
	//alert("login out");
	var username=data["username"];
    //console.log(username);
	if(username!="none"){
		//window.location.href=getBasePath()+"zgmp/check_user_manage";
        //console.log(username);
		checkUserManageInfo();
	}
	else{
	}
}
//================================分页查看用户信息列表============================
/**
 * 获取用户信息列表
 */
function checkUserManageInfo(){
    var data =  {
        page_now: 1

    };
    var src = getBasePath() + "zgmp/get_user_manage_info";
    //console.log("sssss" + src);
    sendAjaxRequest(src, data, changeUserManageInfoPage, function(data) {

    });
    //console.log("sssss" + src);

}
/**
 * 根据收到的数据，改变整个个页面
 * @param data
 */
function changeUserManageInfoPage(data) {
    //console.log("nihao");
    changeUserManageInfoStableReligion(data);
    var src = getBasePath() + "zgmp/get_user_manage_info";
    createNewPagination(data, "user-manage-info-next-next-info", src, changeUserManageInfoPage,
        "first-user-manage-info", "lastuser-manage-info", "user-manage-info-pagination", {});
}
/**
 * 
 * @param data
 */
function changeUserManageInfoStableReligion(data) {

    //console.log("========data==========");
    //console.log(data);
    // 注入新的工单数据
    createNewUserManageInfoTableData(data, "user-manage-info-template", "user-manage-info");
    //console.log("hahahahah");

    // 更新P中的数据
    changePInfo(data, "user-manage-info-page-info");

}
/**
 * 用户管理信息列表中显示每页的信息条数
 * @param data
 * @param p_id
 */
function changePInfo (data, p_id) {
    // log("form are %d", from);
    var p_text = "显示第{{from}}到第{{end}}条记录，共{{total}}条记录";
    var output = Mustache.render(p_text, data);
    $("#"+p_id).text(output);

}

/**
 *   创建新的table数据
 *  tr为tbody下子元素所有tr
 *  order_key，是从后台返回给前天工单数据的键值
 *  tbody_id 是table的 tbody id 
 */
var createNewUserManageInfoTableData = function(data, template_id, tbody_id) {

    if(valIsNull(data)) {
        return;
    }

    // 删除所有的表格
    // #need-to-do-id>tr
    $("#"+tbody_id+">tr").remove();

    var table_data = $("#"+template_id).html();
    //console.log(table_data);

    var userinfo = data["userinfo"];
    //console.log(userinfo);
    var content = "";
    var obj = new Object();
    //console.log(userinfo[2].userName+"hahahahah");

    for (var i = 0; i < userinfo.length; i++) {
        if(userinfo[i].sex == 1){
            userinfo[i].sex = "男";
        }else{
            userinfo[i].sex = "女";
        }
        content += Mustache.render(table_data, userinfo[i]);
    }

    $("#" + tbody_id).append(content);


};
