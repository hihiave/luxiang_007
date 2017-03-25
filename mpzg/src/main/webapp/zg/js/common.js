/**
 * 获取路径
 * @returns
 */
function getBasePath() {
	var base = $("#base").val();
	return base;
}

/**
 * ajax后台交互
 * @param src
 * @param data
 * @param success_cb
 * @param err_cb
 * @param is_async
 */

function sendAjaxRequest(src, data, success_cb, err_cb, is_async) {

	if (arguments.length != 5) {
		is_async = true;
	}

	$.ajax({
		'type' : "POST",
		'url' : src,
		'data' : data,
		// 'time': 1000,
		async : is_async,
		'success' : success_cb,
		'error' : err_cb,
		'traditional' : true

	});

}

/**
 * 
 * @param checkBoxName
 */
function initCheckBox(checkBoxName) {
	$("input[name=" + checkBoxName + "]:checkbox").prop("checked", false);
}

function initCheckbox(boxname, checkboxName) {
	$("input[" + boxname + "=" + checkboxName + "]:checkbox").prop("checked",
			false);
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
    //console.log("创建分页+++++");
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

/**
 * 创建分页
 * @param data
 * @param class_name
 * @param src
 * @param deal_cb
 * @param left_arrow_id
 * @param right_arrow_id
 * @param ul_id
 * @param addition_data
 */
function createNewPagination(data, class_name, src, deal_cb, left_arrow_id,
		right_arrow_id, ul_id, addition_data) {

	var page_now = data["page_now"];
	var page_total_num = data["page_num"];

	var page_begin = Math.max(1, page_now - 4);
	var page_end = Math.min(page_total_num, page_now + 4);

	var elem = createPaginationHtml(page_begin, page_end, page_now,
			page_total_num, class_name, left_arrow_id, right_arrow_id);

	// $("#"+ul_id+">li").remove();
	// $("#"+ul_id).append(elem);
	$("#" + ul_id).html(elem);

	// addition_data = Object.create(addition_data);

	var send_left_data = $.extend(true, {}, {
		"page_now" : 1
	}, addition_data);
	var send_right_data = $.extend(true, {}, {
		"page_now" : page_total_num
	}, addition_data);

	$("#" + left_arrow_id).click(function() {
		sendAjaxRequest(src, send_left_data, deal_cb, function(data) {

		});
	});

	// ���click �¼�
	$("." + class_name).each(function() {

		$(this).click(function() {
			var text = $(this).text();
			// log(text);
			text = trimValue(text);
			// log("text are %s", text);
			var send_data = $.extend(true, {}, {
				"page_now" : text
			}, addition_data);
			sendAjaxRequest(src, send_data, deal_cb, function(data) {

			});

		});
	});

	$("#" + right_arrow_id).click(function() {
		sendAjaxRequest(src, send_right_data, deal_cb, function(data) {
		});
	});

}
/**
 * 提示内容
 */
var setAlertContent = function(alertId, alertText) {
	/*var text = document.getElementById(alertId);
	text.innerText = alertText;
	log(document.getElementById(alertId).innerText);*/
	
	$("#" + alertId).text(alertText);
	
	
};

function trimValue(val) {

    if(val != undefined) {
        val = $.trim(val);
    } else {
        val = "";
    }
    return val;
}

function showSuccessBox  (content) {
    setAlertContent("success-content", content);
	$("#success-box").modal("show");
}

function showAlertBox (content) {
	
    setAlertContent("alert-content", content);
    $("#alert-box").modal("show");
}

function valIsNull(val) {
    if(val == undefined || val == "" || val == null || val == "0") {
        return true;
    } else {
        return false;
    }

}
