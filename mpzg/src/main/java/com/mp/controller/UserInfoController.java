package com.mp.controller;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mp.common.StringUtil;
import com.mp.model.UserInfo;
import com.mp.service.IUserInfoService;

@Controller
public class UserInfoController {
	private static Logger logger = Logger.getLogger(UserInfoController.class);

    @Autowired
    private IUserInfoService user_info_service ;
    
    /**
     * 跳转界面
     * @param request
     * @param model
     * @return
     */
	@RequestMapping("/zgmp/check_userinfo")
    public String checkUserInfo(HttpServletRequest request, ModelMap model) {
        System.out.println("check userinfo");
        return "user_info";
    }
	
	/**
	 * 修改个人信息
	 * @param request
	 * @param map
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "modifyUserInfo", method = RequestMethod.POST)
    @ResponseBody
    public boolean  modifyUserInfo(HttpServletRequest request, ModelMap map, HttpSession session){
		System.out.println("======modify user info=====");
        int num = 0;
        UserInfo userInfo = new UserInfo();
        try {
			
			int userId = StringUtil.convertStringToInt(request.getParameter("user_id"));
			String user_name = request.getParameter("user_name");
			String user_realname = request.getParameter("user_realname");
			String user_email = request.getParameter("user_email");
			String user_sex = request.getParameter("user_sex");
			String user_phone_number = request.getParameter("user_phone_number");
			String user_role = request.getParameter("user_role");
			
			userInfo.setUserId(userId);
			userInfo.setUserName(user_name);
			userInfo.setUserRealname(user_realname);
			userInfo.setUserEmail(user_email);
			userInfo.setSex(StringUtil.convertStringToInt(user_sex));
			userInfo.setUserTelephoneNumber(user_phone_number);
			userInfo.setUserRole(user_role);
			System.out.println("======userId=====" + userId);
			System.out.println("======user_realname=====" + user_realname);
			System.out.println("======user_role=====" + user_role);
			num = user_info_service.updateUserInfoByname(userInfo);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
        
        if (num > 0) {
        	session.setAttribute("UserInfo", userInfo);
            return true;
        } else {
            return false;
        }
	}
	
	
	@RequestMapping(value = "getUserPsw", method = RequestMethod.POST)
    @ResponseBody
    public int  getUserPsw(HttpServletRequest request, ModelMap map, HttpSession session){
		System.out.println("======get user psw=====");
		Map<String, Object> datas = new HashMap<String, Object>();
		UserInfo user_info = new UserInfo();
		String user_psw = request.getParameter("user_psw");
		try {
			int userId = StringUtil.convertStringToInt(request.getParameter("id"));
			user_info=user_info_service.selectPswById(userId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (user_info.getUserPassword().equals(user_psw)) {
            return 1;
        } else {
            return 0;
        }
	}
	
	@RequestMapping(value = "modifyUserPsw", method = RequestMethod.POST)
    @ResponseBody
    public boolean  modifyUserPsw(HttpServletRequest request, ModelMap map, HttpSession session){
		int num = 0;
		try {
			UserInfo userInfo = new UserInfo();
			int userId = StringUtil.convertStringToInt(request.getParameter("user_id"));
			String user_psw_new = request.getParameter("user_psw_new");
			System.out.println("user_psw_new = " + user_psw_new);
			System.out.println("userId = " + userId);
			userInfo.setUserPassword(user_psw_new);
			userInfo.setUserId(userId);
			num = user_info_service.updateUserPswById(userInfo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (num > 0) {
            return true;
        } else {
            return false;
        }
	}
	
}
	
