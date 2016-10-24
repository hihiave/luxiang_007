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

//退出登录
function logout(){
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



