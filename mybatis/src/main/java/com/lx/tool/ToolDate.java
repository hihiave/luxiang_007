package com.lx.tool;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ToolDate {

	/**
	 * 日期格式 Date转 String
	 * @param date
	 * @param resDateFormat
	 * @return dateString
	 */
	public static String stringFromDate(Date date, String resDateFormat) {
		return new SimpleDateFormat(resDateFormat).format(date);
	}

	/**
	 * 获取当前时间的时间戳
	 * @return 时间戳:int (秒)
	 */
	public static int getCurrentTimestamp(){
		return (int) (System.currentTimeMillis() / 1000);
	}
	
	/**
	 * 通过给定时间戳(秒)，获取日期字符串
	 * @param timestamp
	 * @param resDateFormat
	 * @return 日期字符串:String 
	 */
	public static String getDateStringByTimestamp(int timestamp,String resDateFormat){
		Date date = new Date(timestamp * 1000l);
		return stringFromDate(date, resDateFormat);
	}	
}
