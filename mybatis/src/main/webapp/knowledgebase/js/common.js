/**
 * Created by wsz on 2016/10/12.
 */
/**
 * 分页
 * page_begin 显示从第几页开始
 * page_end 显示到第几页结束
 * page_now 现在是第几页
 * page_total_num：总共显示多少页
 *
 */

//时间戳转换
// 格式化时间对象
$(function() {
	get_user_check_num();
	get_file_check_num();
})

function get_user_check_num(){
	$.ajax(
            {
                type:'post',
                url:"/mybatis/UserInfoController/get_unchecked_num.do",
                dataType:'json',
                success:function(data){
                	var num=data["num"];
                    $("#badge1").html(num);
                }
            }
        )

}
function get_file_check_num(){
	$.ajax(
            {
                type:'post',
                url:"/mybatis/FileInfoController/get_unchecked_num.do",
                dataType:'json',
                success:function(data){
                	var num=data["num"];
                    $("#badge2").html(num);
                }
            }
        )

}
Date.prototype.Format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "h+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
};




/*
 * 将时间戳转成日期格式 比如2016-04-26 08:00:00
 */


function timeStampFormat(time_stamp) {

    var add0 = function(m){
        return m<10?'0'+m:m
    };


    var time = new Date(time_stamp);
    var y = time.getFullYear();
    var m = time.getMonth()+1;
    var d = time.getDate();
    var h = time.getHours();
    var mm = time.getMinutes();
    var s = time.getSeconds();
    return y+'-'+add0(m)+'-'+add0(d)+' '+add0(h)+':'+add0(mm)+':'+add0(s);
}

function timeStampFormatMinute(time_stamp) {

    var time = new Date(time_stamp);
    return getFormatTime(time, "yyyy-MM-dd hh:mm");


}

/**
 * 将时间戳转成日期格式yyyy-mm-dd
 * @param time_stamp
 * @returns {String}
 */
function timeStampFormatDay(time_stamp) {

    var add0 = function(m){
        return m<10?'0'+m:m
    };

    var time = new Date(time_stamp);
    var y = time.getFullYear();
    var m = time.getMonth()+1;
    var d = time.getDate();
    return y+'-'+add0(m)+'-'+add0(d);
}

/*
 * 获取指定格式的时间
 * date: 是Date时间对象
 * format: 格式字符串，yyyy表示年, MM表示月，dd表示天，hh表示小时，mm表示分钟，ss表示秒钟
 *
 * 返回事件字符串
 *
 */
function getFormatTime (date, format) {

    var time = date.Format(format);
    return time;
}
function getBasePath() {
    var base = $("#base").val();
    return base;
}
//退出登录
function logout(){
    var logout_src = getBasePath()+"/UserInfoController/logout.do";
    console.log(logout_src);
    $.ajax({
        type:'post',
        url:"/mybatis/UserInfoController/logout.do",
        dataType:'json',
        success:function(data){
            var username = data["username"];
            //console.log(data["result"]);
            $("#logout-username").text(username+"再见！");
            $("#logout-modal").modal('show');
            $("#logout-dismiss").click(function(){
                top.location='Login.jsp';
            })

        },
        error:function(){
            $("#logout-username").text("登录已过期，请重新登录！");
            $("#logout-modal").modal('show');
            $("#logout-dismiss").click(function(){
                top.location='Login.jsp';
            })
        }
    })
}
function sendAjaxRequest(src,data,success_cb,err_cb,is_async){
    if(arguments.length != 5){
        is_async = true;
    }
    $.ajax({
        'type':"POST",
        'url':src,
        'data':data,
        'dataType':"json",
        async:is_async,
        'success':success_cb,
        'error':err_cb,
        'traditional':true
    });
}

/**
 * 分页
 * page_begin
 * page_end
 * page_now
 * page_total
 * class_name
 */
function createPaginationHtml(page_begin, page_end, page_now, page_total_num, class_name, left_arrow_id, right_arrow_id) {
    console.log("创建分页+++++");
    var li_active_begin_elem = "<li class='active'>" + "<a href='#' class='" + class_name + "' >";
    var li_non_active_begin =  "<li><a href='#' class = '" + class_name + "' >";
    var li_end =  " </a> </li>";

    var elem = "";

    if(page_now != 1 && page_total_num != 0) {

        elem += "<li> <a id = '" + left_arrow_id  + "' href='#' aria-label='Previous'> <span aria-hidden='true'>&laquo;</span> </a>";
    }

    for(var i = page_begin ; i <= page_end ; i++) {
        if(i != page_now) {
            var whole_li = li_non_active_begin + i + li_end;
            elem += whole_li;
        } else {
            whole_li = li_active_begin_elem + i + li_end;
            elem += whole_li;
        }
    }

    if(page_now != page_total_num && page_total_num != 0) {
        elem += "<li> <a href='#' id ='" + right_arrow_id + "' aria-label='Next'> <span aria-hidden='true'>&raquo;</span> </a> </li>"

    }

    return elem;
}

/*
 * class_name：     给分页链接命名的样式类名
 * deal_func_name:  是给后台的名称
 * deal_cb:         是用来处理后台给前台数据的回调
 * left_arrow_id:   是给最左箭头取的新id
 * right_arrow_id:  是给最右键头取的新id
 */
function createNewPagination(data, class_name,src,deal_cb,left_arrow_id, right_arrow_id, ul_id,addition_data) {

    var page_now = data["pageNow"];
    var page_total_num = data["pageCount"];
    console.log(page_now);
    console.log(page_total_num);

    var page_begin = Math.max(1, page_now - 3);
    var page_end = Math.min(page_total_num, page_now + 3);

    var elem = createPaginationHtml(page_begin, page_end, page_now, page_total_num, class_name,
        left_arrow_id, right_arrow_id);


    $("#"+ul_id+">li").remove();
    $("#"+ul_id).append(elem);

    var send_left_data = $.extend({"page_Now": 1},addition_data);
    var send_right_data = $.extend({"page_Now": page_total_num},addition_data);

    $("#"+left_arrow_id).click(function() {
        sendAjaxRequest(src, send_left_data, deal_cb, function (data) {

        });
    });

    // 添加click 事件
    $("."+ class_name).each(function () {
        $(this).click(function () {
            var text = $(this).text();
            text = $.trim(text);
            var send_data = $.extend({"page_Now": text},addition_data);
            sendAjaxRequest(src, send_data, deal_cb, function (data) {

            });

        });
    });

    $("#"+right_arrow_id).click(function() {
        sendAjaxRequest(src, send_right_data, deal_cb, function(data) {
        });
    });

}


//在线阅读
function ReadOnLine(obj){
    //var filename = $(obj).attr("path");
    //var src = "/mybatis/ReadOnlineController/readOnline.do";
    //console.log(filename);
    //sendAjaxRequest(src,{"filename":filename},function(data){
    //});

    var form = $("<form>");   //定义一个form表单
    form.attr('style', 'display:none');   //在form表单中添加查询参数

    form.attr('method', 'post');
    form.attr('id', 'form-readonline');
    form.attr('action', "/mybatis/ReadOnlineController/readOnline.do");

    var input1 = $('<input>');
    input1.attr('type', 'hidden');
    input1.attr('name', 'filename');
    input1.attr('value', $(obj).attr("path"));
    $('.container').append(form);  //将表单放置在web中
    form.append(input1);   //将查询参数控件提交到表单上
    form.submit();
    $("#form-readonline").remove();
}


function download(obj){

        var form = $("<form>");   //定义一个form表单
        form.attr('style', 'display:none');   //在form表单中添加查询参数

        form.attr('method', 'post');
        form.attr('id', 'form-add');
        form.attr('action', "/mybatis/FileDownloadController/fileDownload.do");

        var input1 = $('<input>');
        input1.attr('type', 'hidden');
        input1.attr('name', 'filename');
        input1.attr('value', $(obj).attr("path"));
        var input2 = $('<input>');
        input2.attr('type', 'hidden');
        input2.attr('name', 'fileid');
        input2.attr('value', $(obj).attr("bid"));
        $('.container').append(form);  //将表单放置在web中
        form.append(input1);   //将查询参数控件提交到表单上
        form.append(input2);   //将查询参数控件提交到表单上
        form.submit();
        $("#form-add").remove();

        var fileid = $(obj).attr("bid");
        sendAjaxRequest("/mybatis/MyDownloadController/add_my_download.do",{"fileid":fileid},function(){console.log("下载成功")});
        sendAjaxRequest("/mybatis/FileInfoController/download_count_add.do",{"fileid":fileid},function(data){
            console.log("下载次数"+data["count"]);
            if($(obj).attr("did") == "public"){
                var src = "/mybatis/FileInfoController/publicfile.do";
                var page = $("ul.pagination li.active>a").text();
                console.log(page);
                sendAjaxRequest(src,{"page_Now":page,"fileProperty":"fullText"},get_all_public_file_table);
            }
        });
}