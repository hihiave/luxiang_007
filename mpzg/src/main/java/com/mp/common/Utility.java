package com.mp.common;


import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by CM on 2016/3/29.
 */
public class Utility {

    private static Logger logger = Logger.getLogger(Utility.class);

    private static Utility instance_;

    private Utility() {

    }

    public static Utility getInstance() {

        if(instance_ == null) {
            instance_ = new Utility();
            return instance_;
        }
        else {
            return instance_;
        }
    }




     /*
      *   返回分页的页数
      *   @param total_num 所有信息的条数
      *   @param page_size 是每一页显示的条数
      */

    public static  int getPageCount(int total_num,int page_size)  {

        int page_count = 0;

        if(total_num % page_size == 0 )  {
            page_count = total_num/ page_size;
        } else {
            page_count = total_num/ page_size + 1;
        }


        return page_count;
    }


    






}
