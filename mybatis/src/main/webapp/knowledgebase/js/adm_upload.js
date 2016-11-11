/**
 * Created by 95 on 2016/11/8.
 */
$(function(){
    get_category_select_file();
    sub.onclick = uploadFile;
})
function get_category_select_file(){
    $.ajax({
        type:'post',
        url:"/mybatis/CategoryController/get_category.do",
        dataType:"json",
        success:function(data){
            var _select = $(".select-class-name>option");
            _select.remove();
            var all_select_category = data["category"];
            //console.log(all_select_category.length);
            for(var i = 0;i < all_select_category.length;i++){
                var op = "<option value='"+ all_select_category[i].categoryName +"'>" +all_select_category[i].categoryName+"</option>";
                $(".select-class-name").append(op);
                //console.log(all_select_category[i].categoryName);
            }
        }
    })
}
function add_one_click(obj){
    var input_id = $(obj).attr("bid");
    return $("#"+input_id).click();
}

function del_one_click(obj){
    var input_id = $(obj).attr("bid");
    var file_name = $(obj).attr("pid");
    $("#"+input_id).val("");
    $("#"+file_name).val("");
    //if($("#"+input_id).val() == ""){//不能写$("#"+input_id).val() == null
    //    alert("xx");
    //}
    //console.log($("#"+input_id).val());

}

function changeFile(obj){
    var file = $(obj).val();
    var input_text = $(obj).attr("pid");
    var arr = file.split('\\');
    $("#"+input_text).val(arr[arr.length - 1]);
}


function uploadFile(){
    var src = "/mybatis/FileUploadController/fileUpload.do";
    var aim1 = $("#aim1"),
        aim2 = $("#aim2"), aim3 = $("#aim3");
    var word1 = $("#word1"),
        word2 = $("#word2"), word3 = $("#word3");
    var area1 = $("#area1"),
        area2 = $("#area2"), area3 = $("#area3");
    var file1 = $("#file1").val();
    var file2 = $("#file2").val();
    var file3 = $("#file3").val();

    if (aim1.val() == "" && aim2.val() == "" && aim3.val() == "") {
        alert("请选择上传的文件");
        return false;
    }
    else if ((aim2.val() != "") && ((area2.val() == "") || (word2.val() == ""))) {

        alert("请完善文件2信息");
        return false;

    }
    else if ((aim1.val() != "") && ((area1.val() == "") || (word1.val() == ""))) {

        alert("请完善文件1信息");
        return false;

    }
    else if ((aim3.val() != "") && ((area3.val() == "") || (word3.val() == ""))) {

        alert("请完善文件3信息");
        return false;
    }
//    else{
//        var data = new FormData();
//        if(file1 != "" && aim1.val() != "" && area1.val() != ""){
//            data.append("filepath1",file1);
//            //data.filepath1 = file1;
//            data.filename1 = aim1.val();
//            data.author1 = $("#author1").attr("placeholder");
//            data.time1 = $("#time1").attr("placeholder");
//            data.word1 = word1.val();
//            data.pro1 = $("#proto1").val();
//            data.cate1 = $("#cate1").val();
//            data.area1 = area1.val();
//
//        }
//        if(file2 != "" && aim2.val() != "" && area2.val() != ""){
//            data.filepath2 = file2;
//            data.filename2 = aim2.val();
//            data.author2 = $("#author2").attr("placeholder");
//            data.time2 = $("#time2").attr("placeholder");
//            data.word2 = word2.val();
//            data.pro2 = $("#proto2").val();
//            data.cate2 = $("#cate2").val();
//            data.area2 = area2.val();
//        }
//        if(file3 != "" && aim3.val() != "" && area3.val() != ""){
//            data.filepath3 = file3;
//            data.filename3 = aim3.val();
//            data.author3 = $("#author3").attr("placeholder");
//            data.time3 = $("#time3").attr("placeholder");
//            data.word3 = word3.val();
//            data.pro3 = $("#proto3").val();
//            data.cate3 = $("#cate3").val();
//            data.area3 = area3.val();
//        }
//        console.log(data);
//        data.append("HHH","nihao");
//
//        $.ajax({
//            url:src,
//            type:'post',
//            data:data,
//            processData : false,
//            contentType : false,
//            dataType:"json",
//            success:function(data){
//                console.log(data["message"]);
//            }
//
//        })
//
//    }
//
//
//
//
}