/**
 * Created by 95 on 2017/1/6.
 */
var stomp_client = null;

$(function(){
    //startWebSocket();
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
            console.log("connect success");
            //stomp_client.send("/app/web-socket-test",{},JSON.stringify({"userId":5}));
            //stomp_client.send();
            stomp_client.subscribe("/user/"+user_id+"/get_info",function(data){
                var msg = JSON.parse(data.body);
                console.log(msg.userId+"!!!!!!!!!!!!!!!");
                //alert(msg.userName);
            })
            var desc = "/app/web-socket-test";
            stomp_client.send(desc,{},JSON.stringify({"userId":user_id}));
            var images = new Array();
            images[0] = "西源大道/卡口二/20160711";
            images[1] = "西源大道/卡口二/20160712";
            images[2] = "西源大道/卡口二/20160713";
            images[3] = "西源大道/卡口二/20160714";
            stomp_client.send("/app/image-handle-test",{},JSON.stringify({"userId":user_id,"imagePath":images}))
        },function(){
            console.log("connect failed");
        })
    }
    connectWebSocket();
})

function startWebSocket(){
    var ws = new WebSocket("ws://localhost/web_socket");
    ws.onopen = function(){
        console.log("connect");
    }
    ws.onmessage = function(){
        ws.send("hahaha");
    }
    ws.onclose = function(){
        console.log("close");
    }
    ws.onerror = function(){
        console.log("error");
    }
}