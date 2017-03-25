/**
 * Created by 95 on 2016/12/30.
 */
/**
 * 加载表格
 */
function changeKakouManagePage(data){
    pushDataIntoTemplate(data);
    var src = getBasePath() +"zgmp/getAllImageInfo";
    createNewPagination(data,"Kakou-Manage-next-info",src,changeKakouManagePage,"Kakou-Manage-left","Kakou-Manage-right","kakou_pagination",{});
}
/**
 * 数据注入模板
 */
function pushDataIntoTemplate(data){
    updateImageInfoTableTemplate(data,"kakou-template","kakou_tbody");
    updateImageInfoTipsTemplate(data,"kakou_nums_tips");
}
/**
 * 更新数据条数提示模板
 */
function updateImageInfoTipsTemplate(data,tips_id){
    var tips_text = "显示第{{from}}到第{{end}}条记录，共{{total}}条记录";
    var output = Mustache.render(tips_text,data);
    $("#"+tips_id).text(output);
}
/**
 * 跟新表格模板
 */
function updateImageInfoTableTemplate(data,template_id,tbody_id){
    if(valIsNull(data)){
        return;
    }
    //console.log(data);
    $("#"+tbody_id+">tr").remove();
    var table_data = $("#"+template_id).html();
    var image_info = data["image_info"];
    var content = "";
    for(var i = 0; i < image_info.length; i++){
        if(image_info[i].imageStatus == 1){
            image_info[i].imageStatus = "启用";
            console.log(image_info[i].imageStatus);
        }else{
            image_info[i].imageStatus = "禁用";
            console.log(image_info[i].imageStatus);
        }
        content += Mustache.render(table_data,image_info[i]);
    }
    $("#"+tbody_id).append(content);
}

//function intTransString(obj,str1,str2){
//    if(obj == 1){
//        return str1;
//    }else if(obj == 2){
//        return str2;
//    }
//}