package com.mp.common;

import java.sql.Connection;
import java.sql.DriverManager;
import org.apache.log4j.*;

/**
 * Created by CM on 2016/8/11.
 */
public class MysqlConnectionUtil {

    private static MysqlConnectionUtil instance_;
    private static String prefix_url_ = "jdbc:mysql://";
    private static String suffix_url = "?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true";
    private static Logger logger = Logger.getLogger(MysqlConnectionUtil.class);

    public synchronized static MysqlConnectionUtil getInstance() {
        if(instance_ == null) {
            return new MysqlConnectionUtil();
        } else {
            return instance_;
        }
    }



    public static Connection  getConnection(String url,String db_name, String user_name, String pass) {
        Connection connection = null;
        try {

            Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动
            logger.info("成功加载MySQL驱动程序");


            // 一个Connection代表一个数据库连接
            connection = DriverManager.getConnection(url, user_name, pass);
            return connection;
        } catch (Exception e) {
            logger.error("get connection error ", e);
        }
        return connection;
    }

}
