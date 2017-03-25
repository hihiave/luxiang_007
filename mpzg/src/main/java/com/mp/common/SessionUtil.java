package com.mp.common;

import com.mp.model.UserInfo;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by CM on 2016/6/24.
 */
public class SessionUtil {


    public static  List<String> getPriorityFromSession(HttpSession session) {
        List<String> priorities = (List<String>)session.getAttribute("Priority");

        priorities = (priorities == null ? new ArrayList<String>(): priorities);
        return priorities;
    }


    public static int getUserIdFromSession(HttpSession session) {
        assert(session != null);
        UserInfo user_info=(UserInfo) session.getAttribute("UserInfo");
        assert (user_info != null);
        return user_info.getUserId();
    }


    public static  UserInfo getUserFromSession(HttpSession session) {
        UserInfo user = (UserInfo)session.getAttribute("UserInfo");
        assert (user != null);
        return user;
    }




}
