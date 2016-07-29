package com.lyr.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;

public class StringUtil {
	private static Logger logger =  Logger.getLogger(StringUtil.class);
	
	private static StringUtil stringUtil;
	
	public StringUtil(){
		
	}
	
	public synchronized static StringUtil getStringUtil(){
		if(stringUtil == null)
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
	
	// MD5加码。32位  
	public String 	MD5(String str){
		String stringTomd5 = null;
		try{
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			char[] charArray = str.toCharArray();
			byte[] byteArray = new byte[charArray.length];
			
			for (int i = 0; i < charArray.length; i++)  {
				byteArray[i] = (byte) charArray[i]; 
			}
				   
			byte[] bytes = md5.digest(byteArray);
			StringBuffer stringBuffer = new StringBuffer();
			for (int i = 0; i < bytes.length; i++){
				int happy =((int) bytes[i] ) & 0xff;
				if (happy < 16){
					stringBuffer.append(0);
				}
				stringBuffer.append(Integer.toHexString(happy));
			}
			stringTomd5 = stringBuffer.toString();
		}catch(Exception e){
			 System.out.println(e.toString());   
			   e.printStackTrace();   
			   return "";
		}
		return stringTomd5;
	}
	
	
}
