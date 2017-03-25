package com.mp.common;

import java.util.List;

public class Constants {

	/*
	 * 工单的状态
	 */
	public static final String NONE = "None";
	public static final String DRAFT = "Draft";
	public static final String ON_GOING = "OnGoing";
	public static final String SUSPEND = "Suspend";
	public static final String ADD = "Added";
	public static final String NEED_TO_DO = "NeedToDo";
	public static final String FIRST_DISPATCHED = "FirstDispatched";
	public static final String SECOND_DISPATCHED = "SecondDispatched";
	public static final String CLOSED = "Closed";
	public static final String COMPLETED = "Completed";
	public static final String Garbage = "Garbage";
	public static final String ALL = "ALL";
	public static final String CONVERTTOREQUEST = "ConvertToRequest";
	// 挂起
	public static final String HangeUp = "HangUp";
	public static final String TEMPLATE = "Template";

	/*
	 * 权限
	 */
	public static final String WORK_ORDER_CREATE = "工单创建";
	public static final String WORK_ORDER_ASSIGN = "工单分派";
	public static final String WORK_ORDER_DEAL = "工单处理";
	public static final String REQUEST_CREATE = "需求创建";
	public static final String REQUEST_ASSIGN = "需求分派";
	public static final String QUESTION_CREATE = "问题创建";
	public static final String QUESTION_ASSIAN = "问题分派";

	public static final String EVENT_CHECK = "事件审查";
	public static final String REQUEST_CHECK = "需求审查";
	public static final String PROBLEM_CHECK = "问题审查";

	/*
	 * 两票步骤
	 */
	public static final String WORK_TICKET_WAIT_CHARGE_USER_SIGN = "待负责人签字";
	public static final String WORK_TICKET_WAIT_PUBLISH_USER_SIGN = "待签发人签字";
	public static final String RETURNED_WORK_TICKET = "工作票退回待修改";
	public static final String FILL_HANDLE_TICKET = "填写操作票";
	public static final String HANDLE_TICKET_WAIT_CHECK_USER_SIGN = "待安全审核人签字";
	public static final String HANDLE_TICKET_WAIT_PERMIT_USER_SIGN = "待许可人签字";
	public static final String RETURNED_HANDLE_TICKET = "操作票退回待修改";
	public static final String COMPLETE_WORK_TICKET_REMAIN = "完善工作票";
	public static final String FILE_TICKETS = "两票待归档";
	public static final String QUALIFIED_TICKET = "合格票";
	public static final String UNQUALIFIED_TICKET = "问题票";

	/**
	 * 主机监控指标
	 */
	public static final String cpuUsageRate = "cpu_usage_percent";
	public static final String memorySize = "memory_size";
	public static final String usedMemorySize = "used_memory_size";
	public static final String freeMemorySize = "free_memory_size";
	public static final String totalDiskSize = "total_disk_size";
	public static final String diskUsageInfo = "disk_partition_info";
	public static final String hostDataInfo = "HOST_DATA_INFO";
	public static final String hostCollectList = "COLL_HOST_INFO";

	/**
	 * 数据库监控
	 */
	public static final String MySql = "mysql";
	public static final String Oracle = "oracle";
	public static final String SqlServer = "sqlserver";
	public static final String DBDataInfo = "DATABASE_INDEX_HASH_DATA";
	public static final String CollDBList = "COLL_DB_INFO";

	/*
	 * 两票权限
	 */
	public static final String TWO_TICKETS_CREATE_PERSON = "两票创建";
	public static final String FILE_PERSON = "归档人";

	/*
	 * 值班
	 */
	public static final String UNHANDOVER = "未交接";
	public static final String HANDOVER = "已交接";

	public static boolean isEventCheck(List<String> priority) {
		for (String pri : priority) {
			if (pri.equals(EVENT_CHECK)) {
				return true;
			}
		}
		return false;
	}

	public static boolean isEventDispatch(String pri) {
		if (pri.equals(WORK_ORDER_ASSIGN)) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isEventDeal(String pri) {
		if (pri.equals(WORK_ORDER_DEAL)) {
			return true;
		} else {
			return false;
		}

	}

	/*
	 * 巡检项类型
	 */
	public static final int MEDICINE_PATROL = 0; // 药房
	public static final int SELFHELP_PRINTER_PATROL = 1; // 自助打印机
	public static final int MACHINEROOM_PATROL = 2; // 机房
	public static final int ONDUTY_RECORD = 3; // 值班记录
	public static final int HEALTH_DEPARTMENT_PATROL = 4; // 健康管理部
	public static final int QUEUING_UP_PATROL = 5; // 排队叫号

	/*
	 * 消息的类型
	 */
	public static String EVENT_MESSAGE_TYPE = "0";
	public static String REQUEST_MESSAGE_TYPE = "1";
	public static String PROBLEM_MESSAGE_TYPE = "2";
	public static String ONDUTY_MESSAGE_TYPE = "3";
	/*
	 * 工单的操作
	 */
	public static String OrderOperationCancel = "EventCancel";
	public static String OrderOperationDelete = "EventDelete";

	/**
	 * 需求字段信息
	 */
	public static final int[] REQUEST_FILED_INDEX = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
	public static final String[] REQUEST_FILED_NAME = { "信通部负责人", "提出人", "标题", "信息描述", "来源系统", "集成商负责人", "厂商负责人",
			"来源科室", "业务部门预期时间", "解决描述", "解决方法", "预计开始处理时间", "预计完成时间", "实际开始处理时间", "实际解决时间", "需求/问题 类型" };
	public static final String[] REQUEST_FILED_DATABASE = { "request_charge_user_id", "request_user_name",
			"request_order_name", "request_order_description", "request_order_projectid", "request_integrator",
			"request_manufacturer", "request_order_department", "request_order_goal_time",
			"request_order_complete_description", "request_order_complete_confirm", "request_plan_start_time",
			"request_plan_complete_time", "request_actual_start_time", "request_actual_complete_time",
			"request_question_tag" };
	public static final String[] REQUEST_FILED_EXPROT = { "request_order_id", "request_order_num",
			"request_complete_status", "request_order_project", "request_order_name", "request_order_description",
			"request_user_name", "request_order_department", "request_charge_user", "request_order_type",
			"request_order_goaltime", "request_order_trace", "request_order_integrator", "request_order_manufacturer",
			"request_order_record_user", "request_record_time", "request_plan_start_time", "request_plan_complete_time",
			"request_actual_start_time", "request_actual_complete_time", "request_complete_method",
			"request_complete_description", "request_close_userinfo", "request_close_time" };

	/**
	 * 需求字段信息转换操作
	 */
	public static int RequestDatabaseToIndex(String field_database) {
		int i = 0;
		for (String s : Constants.REQUEST_FILED_DATABASE) {
			if (s.equals(field_database)) {
				break;
			}
			i++;
		}
		return i;
	}

	public static String IndexToName(String Index) {
		String[] field_index_list = Index.split(",");
		String stance_show = "本次修改了：";
		int index;
		for (String s : field_index_list) {
			index = Integer.parseInt(s);
			stance_show = stance_show + Constants.REQUEST_FILED_NAME[index] + "、";
		}
		return stance_show;
	}

	public static String DatabaseToName(String database) {
		int i = 0;
		for (String s : Constants.REQUEST_FILED_DATABASE) {
			if (s.equals(database)) {
				break;
			}
			i++;
		}
		return Constants.REQUEST_FILED_NAME[i];
	}

	public static String ValuelistToIndex(String valuelist[]) {
		String index_string = "";
		int i = 0;
		for (String s : valuelist) {
			i = 0;
			for (String t : Constants.REQUEST_FILED_EXPROT) {
				if (t.equals(s)) {
					break;
				}
				i++;
			}
			index_string = index_string + i + ",";
		}
		index_string = index_string.substring(0, index_string.lastIndexOf(","));
		return index_string;
	}

	public static String[] IndextoValuelist(String index) {

		String index_list[] = index.split(",");
		String[] value_list = new String[index_list.length];
		int i = 0;
		for (String s : index_list) {
			value_list[i] = Constants.REQUEST_FILED_EXPROT[Integer.parseInt(s)];
			i++;
		}
		return value_list;
	}

	public static int getOrderOperationType(String type) {
		if (type != null) {
			if (type.equals(OrderOperationCancel)) {
				return 1;
			} else if (type.equals(OrderOperationDelete)) {
				return 2;
			} else {
				return 3;
			}
		} else {
			return 0;
		}
	}

}
