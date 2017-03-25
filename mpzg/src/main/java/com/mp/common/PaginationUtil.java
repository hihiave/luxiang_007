package com.mp.common;

import org.apache.log4j.Logger;

/**
 * Created by CM on 2016/4/20.
 */
public class PaginationUtil {


    private static Logger logger = Logger.getLogger(PaginationUtil.class);


    /*
     * 显示从结果集中第几个开始
     *
     * row num 是每一页显示的行数.
     * page_now 是指现在是第几页
     */
    public static int getFrom(int row_num, int page_now) {
        page_now = (page_now > 0) ? page_now: 1;

        int from =  row_num * (page_now - 1) + 1;
        return from;
    }


    public static int getTo(int from, int size) {

        size  = ((size > 0) ? size : 1);
        return from + size - 1;

    }


    // 根据每页显示的行数和当前第几页,来得出选择从结果集中的第几个.
    public static int  getRowBegin(int row_num, int page_now) {
        return (row_num * (page_now - 1) >=0 ? (row_num * (page_now - 1)) : 1);

    }


}
