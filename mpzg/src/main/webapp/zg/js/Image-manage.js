
var stomp_client = null;

/**
 * 进入页面获取表格数据
 */

/**
 * 加载表格
 */
function changeImageInfoPage(data){
    pushDataIntoTemplate(data);
    var src = getBasePath() +"zgmp/getAvailableImageInfo";
    createNewPagination(data,"image-info-next-info",src,changeImageInfoPage,"image-info-left","image-info-right","image-file-info-pagination",{});
}
/**
 * 数据注入模板
 */
function pushDataIntoTemplate(data){
    updateImageInfoTableTemplate(data,"image-file-info-template","image-file-info");
    updateImageInfoTipsTemplate(data,"image-file-info-page-info");
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
    $("#"+tbody_id+">tr").remove();
    var table_data = $("#"+template_id).html();
    var image_info = data["image_info"];
    var content = "";
    for(var i = 0; i < image_info.length; i++){
        content += Mustache.render(table_data, image_info[i]);
    }
    $("#"+tbody_id).append(content);
}


$(function(){
//        var data={level_1:1};
    var src = getBasePath() + "zgmp/getImageFileDir";
    sendAjaxRequest(src,"",showImageFileDirLevel1,error);
})
function showImageFileDirLevel1(data){
    var content_head = "<p>道路列表</p>";
    var content_body = "";
    var imageFileDirLevel1 = data["level_1"];
    for(var i = 0;i < imageFileDirLevel1.length;i++){
        var _span = "<span style='margin: 20px;'><a href='##' onclick='getImageFileDirLevel2(this)'>"+imageFileDirLevel1[i]+"</a></span>";
        content_body += _span;
    }
    $("#image-file-dir-lvl-1").html(content_head);
    $("#image-file-dir-content").html(content_body);
}


function getImageFileDirLevel2(obj){
    var imageFileDirLevel1_name = $(obj).text();
    console.log(imageFileDirLevel1_name);
    var data={level_1:imageFileDirLevel1_name};
    var src = getBasePath() + "zgmp/getImageFileDir";
    sendAjaxRequest(src,data,function(data){
        showImageFileDirLevel2(data,imageFileDirLevel1_name);
    },error);

}
function showImageFileDirLevel2(data,pid){
    console.log(data);
    console.log(pid+"HAHA");
    var content_head = "<p>"+pid+"--卡口列表<a href='##' class='btn btn-primary' onclick='backToLevel1(this)' >返回</a></p>";
    var content_body = "";
    var imageFileDirLevel2 = data["level_2"];
    for(var i = 0;i < imageFileDirLevel2.length;i++){
        var _span = "<span style='margin: 20px;'><a href='##' onclick='getImageFileDirLevel3(this)' pid='"+pid+"'>"+imageFileDirLevel2[i]+"</a></span>";
        content_body += _span;
    }
    $("#image-file-dir-lvl-1").html(content_head);
    $("#image-file-dir-content").html(content_body);
}

function getImageFileDirLevel3(obj){
    var imageFileDirLevel1_name = $(obj).attr("pid");
    var imageFileDirLevel2_name = $(obj).text();
    console.log($(obj).text());
    var data={level_1:imageFileDirLevel1_name,level_2:imageFileDirLevel2_name};
    var src = getBasePath() + "zgmp/getImageFileDir";
    sendAjaxRequest(src,data,function(data){
        console.log(data);
        showImageFileDirLevel3(data,imageFileDirLevel1_name,imageFileDirLevel2_name);
    },error);

}
function showImageFileDirLevel3(data,pid,bid){
    var content_head = "<p>"+pid+"--"+bid+"--日期列表<a href='##' class='btn btn-primary' onclick='backToLevel2(this)' pid='"+pid+"'>返回</a></p>";
    var content_body = "";
    var imageFileDirLevel3 = data["level_3"];
    for(var i = 0;i < imageFileDirLevel3.length;i++){
        var _span = "<span style='margin: 20px;'><a href='##' onclick='getImageFiles(this)' pid='"+pid+"' bid='"+bid+"'>"+imageFileDirLevel3[i]+"</a></span>";
        content_body += _span;
    }
    $("#image-file-dir-lvl-1").html(content_head);
    $("#image-file-dir-content").html(content_body);
}

function getImageFiles(obj){
    var imageFileDirLevel1_name = $(obj).attr("pid");
    var imageFileDirLevel2_name = $(obj).attr("bid");
    var imageFileDirLevel3_name = $(obj).text();
//        var content = "<h3>"+imageFileDirLevel1_name+"--"+imageFileDirLevel2_name+"--"+imageFileDirLevel3_name+"--图片列表</h3>";
    console.log(imageFileDirLevel1_name+imageFileDirLevel2_name+imageFileDirLevel3_name);
    var data={level_1:imageFileDirLevel1_name,level_2:imageFileDirLevel2_name,level_3:imageFileDirLevel3_name};
    var src = getBasePath() + "zgmp/getImageFileDir";
    sendAjaxRequest(src,data,function(data){
        console.log(data);
        showImageFileDirLevel4(data,imageFileDirLevel1_name,imageFileDirLevel2_name,imageFileDirLevel3_name);
    },error);
}
function showImageFileDirLevel4(data,pid,bid,mid){
    var content_head = "<p>"+pid+"--"+bid+"--"+mid+"--图片列表<a href='##' class='btn btn-primary' onclick='backToLevel3(this)' pid='"+pid+"' bid='"+bid+"' >返回</a></p>";
    var content_body = "";
    var imageFileDirLevel4 = data["level_image"];
    for(var i = 0;i < imageFileDirLevel4.length;i++){
        var _span = "<p style='margin:20px;'><a href='##' onclick='imageSelectShow(this)' pid='"+pid+"' bid='"+bid+"' mid='"+mid+"'  >"+imageFileDirLevel4[i]+"</a></p>";
        content_body += _span;
    }
    $("#image-file-dir-lvl-1").html(content_head);
    $("#image-file-dir-content").html(content_body);
}

function backToLevel1(obj){
    var src = getBasePath() + "zgmp/getImageFileDir";
    sendAjaxRequest(src,"",showImageFileDirLevel1,error);
}
function backToLevel2(obj){
    var src = getBasePath() + "zgmp/getImageFileDir";
    var level_1 = $(obj).attr("pid");
    var data = {level_1:level_1};
    sendAjaxRequest(src,data,function(data){
        showImageFileDirLevel2(data,level_1);
    },error);
}
function backToLevel3(obj){
    var src = getBasePath() + "zgmp/getImageFileDir";
    var level_1 = $(obj).attr("pid");
    var level_2 = $(obj).attr("bid");
    var data = {level_1:level_1,level_2:level_2};
    sendAjaxRequest(src,data,function(data){
        showImageFileDirLevel3(data,level_1,level_2);
    },error);
}

function imageSelectShow(obj){

    var pid = $(obj).attr("pid");
    var bid = $(obj).attr("bid");
    var mid = $(obj).attr("mid");
    var select_name = $(obj).text();
    var child_span_a = $("#image_file").children("span");
    var child_span_a_1 = $("#image_file span>a");
    console.log("hahah"+child_span_a_1.length);
    console.log("当前选中"+child_span_a.length);
    if(child_span_a.length == 0){
        var select_content = "<span style='margin:20px;'><a href='##' onclick='disSelected(this)' pid='"+pid+"' bid='"+bid+"' mid='"+mid+"'  >"+select_name+"</a></span>";
        $("#image_select").css("display","block");
        $("#image_file").append(select_content);
    }else{
        var _same = 0;
        for(var i = 0;i < child_span_a.length;i++){
            console.log(child_span_a.eq(i).text());
            console.log(child_span_a_1.eq(i).attr("pid"));
            if(child_span_a.eq(i).text() == select_name){
                _same = 1;
                console.log("已经选择！！！！");
                return;
            }
        }
        if(_same == 0){
            var select_content = "<span style='margin:20px;'><a href='##' onclick='disSelected(this)' pid='"+pid+"' bid='"+bid+"' mid='"+mid+"'  >"+select_name+"</a></span>";
            $("#image_select").css("display","block");
            $("#image_file").append(select_content);
        }
    }


}

function disSelected(obj){
    $(obj).parent().remove();
    var _child_nums = $("#image_file").children().length;
    console.log(_child_nums+"=========");
    if(_child_nums == 0){
        $("#image_select").css("display","none");
    }
}

function carBrandCheck(){
    var select_img = $("#image_file span>a");
    if(select_img.length == 0){
        return;
    }else{
        var images = new Array();
        for(var i = 0;i < select_img.length;i++){
            var pid = select_img.eq(i).attr("pid");
            var bid = select_img.eq(i).attr("bid");
            var mid = select_img.eq(i).attr("mid");
            var image_name = select_img.eq(i).text();
            var str = pid + "/" + bid + "/" + mid + "/" + image_name;
            images[i] = str;
        }
        var socket = null;
        if('WebSocket' in window){
            socket = new WebSocket("ws://localhost/web_socket");
        }else if('MozWebSocket' in window){
            socket = new MozWebSocket("ws://localhost/web_socket");
        }else{
            socket = new SocketJS("http://localhost/new_event");
        }
        function connectWebSocket(){
            var user_id = $("#user_id_session").val();
            console.log(user_id+"================");
            stomp_client = Stomp.over(socket);
            stomp_client.connect({},function(){

                stomp_client.subscribe("/user/"+user_id+"/get_result",function(data){
                    var msg = JSON.parse(data.body);
                    //console.log(msg.length);
                    for(var i = 0;i < msg.length;i++){
                        console.log(msg[i]+"!!!!!!!!!!!!!!!!!!");
                    }

                })
                stomp_client.send("/app/image-handle-test",{},JSON.stringify({"userId":user_id,"imagePath":images}))
            },function(){
                console.log("connect failed");
            })
        }
        connectWebSocket();
    }
}
function carColorCheck(){

}
function carNumberCheck(){

}

