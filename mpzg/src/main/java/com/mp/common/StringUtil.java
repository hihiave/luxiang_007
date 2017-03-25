package com.mp.common;


import com.mysql.jdbc.StringUtils;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * Created by CM on 2016/3/28.
 */
public class StringUtil {

    private static StringUtil instance_;
    private static Logger logger = Logger.getLogger(StringUtil.class);



    private StringUtil() {

    }



    public synchronized static StringUtil getInstance() {

        if(instance_ == null)
            instance_ = new StringUtil();
        return instance_;
    }



    public static boolean isStrNull(String str) {
        if(str == null || str.equals("")) {
            return true;
        } else {
            return false;
        }
    }


    public static String convertArrayToString(String[] strs) {

        String res = "";

        if(strs != null) {
            for (String temp : strs) {
                if (res == "") {
                    res += temp;
                } else {
                    res += "," + temp;
                }
            }
        }
        return  res;
    }

    public static String getDefaultImageFilePath() {
        return getPropertiesValue("file_dir.properties","image_file_save_path");
    }
    public static String getDefaultVideoFilePath() {
        return getPropertiesValue("file_dir.properties","video_file_save_path");
    }
    public static String getDefaultShowRowNum() {

        return getPropertiesValue("page-config.properties", "default_show_row_num");
    }
    
    public static String getDBUrl(){
    	 return getPropertiesValue("jdbc.properties", "url");
    }
    
    public static String getDBUserName(){
    	 return getPropertiesValue("jdbc.properties", "username");
    }
    
    public static String getDBPwd(){
    	return getPropertiesValue("jdbc.properties", "password");
    }


    public static String getDefaultOrderPersonShowNum() {
        return getPropertiesValue("page-config.properties", "order_person_show_num");
    }
    
    public static String getDefaultFileServerPort(){
    	return StringUtil.getPropertiesValue("fileupload-config.properties","default_file_server_port");
    }

    public static String getDefaultFileServerIp(){
    	return StringUtil.getPropertiesValue("fileupload-config.properties","default_file_upload_ip");
    }

    public static String getDefaultFileUploadUrl(){
    	return StringUtil.getPropertiesValue("fileupload-config.properties","default_file_upload_url");
    }
    
    public static String getDefaultRedisUrl(){
    	return StringUtil.getPropertiesValue("redis-config.properties","redis_server_ip");
    }
    
    public static int  getDefaultRedisPort(){
    	return Integer.parseInt(StringUtil.getPropertiesValue("redis-config.properties", "redis_server_port"));
    }
    
    public static int getRedisDataCollectionTerm(){
    	return Integer.parseInt(StringUtil.getPropertiesValue("redis-config.properties", "data_collection_term"));
    }
    
    public static int getDatabaseCollectionTerm(){
    	return Integer.parseInt(StringUtil.getPropertiesValue("redis-config.properties", "database_collection_term"));
    }
    
    public static String getDefaultRedisPwd(){
    	return StringUtil.getPropertiesValue("redis-config.properties", "redis_server_password");
    }
    
    public static String getTcpConnectionHost(){
    	return StringUtil.getPropertiesValue("test-collection-config.properties","tcp-connection-ip");
    }
    
    public static int getTcpConnectionPort(){
    	return Integer.parseInt(StringUtil.getPropertiesValue("test-collection-config.properties", "tcp-connection-port"));
    }

    private static String getPropertiesValue(String configFilePath, String Key)  {
        String result = null;
        Properties properties = new Properties();

        try {
            properties.load(StringUtil.class.getClassLoader().getResourceAsStream(configFilePath));
            result = properties.getProperty(Key);
        } catch(Exception e) {
            logger.error("属性文件为 "+configFilePath+" 读取属性文件失败,请检查原因");
        }
        return result;

    }


    /*
     * 创建工单号
     */
    public String createOrderNumber() {
        String time =  TimeTrans.DateToString(new Date(), "yyyyMMddHHmmss");
        return "INC"+time;
    }


    /*
     *  返回0表示str为空和非数字
     */
    public static int convertStringToInt(String str) {

        if(str != null) {
            str = str.trim();
            int num = 0;
            try {
                num = Integer.parseInt(str);
                // logger.info("num:" + num);
                return num;
            } catch (Exception e) {
                e.printStackTrace();
                logger.error(e);
                return 0;
            }
        } else {
            return 0;
        }
    }
    
    /*
     *  返回0表示str为空和非数字
     */
    public static float convertStringToFloat(String str) {

        if(str != null) {
            str = str.trim();
            float num = 0.00f;
            try {
                num = Float.parseFloat(str);
                // logger.info("num:" + num);
                return num;
            } catch (Exception e) {
                e.printStackTrace();
                logger.error(e);
                return 0.00f;
            }
        } else {
            return 0.00f;
        }
    }


    public static String convertEventSatatusReadable(String str) {
        if(!StringUtil.isStrNull(str)) {

            if(str.equals(Constants.ADD)) {
                return "新增";
            } else if(str.equals(Constants.ON_GOING)) {
                return "正在进行";
            } else if(str.equals(Constants.DRAFT)) {
                return "草稿箱";
            } else if(str.equals(Constants.COMPLETED)) {
                return "已完成";
            } else if(str.equals(Constants.CLOSED)) {
                return "已关闭";
            } else if(str.equals(Constants.Garbage)) {
                return "已作废";
            } else if(str.equals(Constants.FIRST_DISPATCHED)) {
                return "一级分派";
            } else if(str.equals(Constants.SECOND_DISPATCHED)) {
                return "二级分派";
            } else if(str.equals(Constants.SUSPEND)) {
                return "已暂缓";
            } else if(str.equals(Constants.HangeUp)) {
                return "已挂起";
            }

        }
        return "";
    }

    public static String convertEventPriorityReadable(String str) {

        if(str == null) {
            return "";
        }

        if(str.equals("low")) {
            return "低";
        } else if(str.equals("mid")) {
            return "中";
        } else if(str.equals("high")) {
            return "高";
        } else {
            return "";
        }
    }

    /*
     * 转换事件来源由human转成人工报账
     */
    public static String convertEventSource(String str) {
        if(str == null) {
            return "";
        } else {
            if(str.equals("human")) {
                return "人工报障";
            } else {
                return "人工报障";
            }

        }
    }
    
    /*
     * 根据项目组名称匹配相应的项目组id
     */
    public static String convertUserStatus(String str) {
    	if(str.equals("1")) {
            return "有效";
        } else if(str.equals("0")) {
            return "无效";
        } else {
			return "有效";
		}
    }

    public static List<Integer> convertStringArrToIntList(String[] ids) {
        List<Integer> int_ids = new ArrayList<Integer>();

        for(String id : ids) {
            int_ids.add(Integer.parseInt(id));
        }

        assert(ids.length == int_ids.size());
        return int_ids;
    }


    public static String[] splitString(String str,  String ch) {
        return str.split(ch);
    }

	public static String[] splitAConStr(String param) {
		// TODO Auto-generated method stub
		String [] strs= {};
    	strs = param.split(":");
    	strs[0] = "set" + Character.toUpperCase(strs[0].charAt(0)) + strs[0].substring(1);
    	return strs;
	}
	/**
	 * @param str
	 * @return
	 * @Description: 32位小写MD5
	 */
	public String parseStrToMd5L32(String str) {
		String reStr = null;
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			byte[] bytes = md5.digest(str.getBytes());
			StringBuffer stringBuffer = new StringBuffer();
			for (byte b : bytes) {
				int bt = b & 0xff;
				if (bt < 16) {
					stringBuffer.append(0);
				}
				stringBuffer.append(Integer.toHexString(bt));
			}
			reStr = stringBuffer.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return reStr;
	}
	
	public String getDefaultPwd()
	{
		return parseStrToMd5L32("123456");
	}

    /**
     *生成记录号时间戳
     * @return
     */
    public static String randomRecordNum() {

        return "record_num_" + TimeTrans.getCurrentTimestamp();

    }


//
//	/**
//	 *  获取ip地址
//	 * @param request
//	 * @return
//	 */
//	public  String getIp(HttpServletRequest request) {
//        String ip = request.getHeader("X-Forwarded-For");
//        //logger.info("ip1 = " + ip + ",!StringUtils.isNullOrEmpty(ip) = " + !StringUtils.isNullOrEmpty(ip));
//        if(!StringUtils.isNullOrEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
//            //多次反向代理后会有多个ip值，第一个ip才是真实ip
//            int index = ip.indexOf(",");
//            if(index != -1){
//                return ip.substring(0,index);
//            }else{
//                return ip;
//            }
//        }
//        
//        ip = request.getHeader("X-Real-IP");
//        //logger.info("ip2 = " +ip + ",!StringUtils.isNullOrEmpty(ip)" + !StringUtils.isNullOrEmpty(ip));
//        if(!StringUtils.isNullOrEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
//            return ip;
//        }
//        return request.getRemoteAddr();
//    }
	
	public static int judgeIE8(HttpServletRequest request){
		String userInfo=request.getHeader("User-Agent");
		if(userInfo.contains("IE 8")||userInfo.contains("IE 7")){
			return 1;
		}
		else{
			return 0;
		}
	}



}
