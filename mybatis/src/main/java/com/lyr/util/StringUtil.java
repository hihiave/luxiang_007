package com.lyr.util;

import org.apache.log4j.*;

public class StringUtil {
	private static Logger logger =  Logger.getLogger(StringUtil.class);
	
	private static StringUtil stringUtil;
	
	public StringUtil(){
		
	}
	
	public synchronized static StringUtil getStringUtil(){
		if(stringUtil!=null)
			stringUtil = new StringUtil();
		return stringUtil;
	}
	
	public static boolean isEmpty(String str){
		if(str != null && !str.equals("")){
			return false;
		}else{
			return true;
		}
	}
	
	
}
