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
public class LoginController {

	private static final Logger logger=Logger.getLogger(LoginController.class);
	@Autowired
	private IUserInfoService userinfo_service;

//    @Autowired
//    private IUserInfoWithRightInfoService priority_service_;
	/**
	 * 跳转登录界面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/login_check")
    public String loginMpzg(HttpServletRequest request, ModelMap model) {
        //System.out.println("hell world");
        return "login";
    }
	
	/**
	 * 登录
	 * @param request
	 * @param session
	 * @return
	 */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public String checkUser(HttpServletRequest request,HttpSession session){
    	System.out.println("hello world");
    	StringUtil stringutil=StringUtil.getInstance();
    	
    	String user_name=request.getParameter("userName");
    	String user_Pwd=request.getParameter("userPwd");
    	
//    	logger.info("===user_name = " + user_name);
//    	logger.info("===user_Pwd = " + user_Pwd);
    	
    	UserInfo user_info=userinfo_service.selectByUserName(user_name);

    	if(user_info==null){
    		return "none";
    	}
    	
//    	if(!user_info.getUserStatus().equals("1")){
//    		return "none";
//    	}
    	else
    	{
    		//user_Pwd=stringutil.parseStrToMd5L32(user_Pwd);//����
    		//if(!user_Pwd.equals(user_info.getUserPassword()))//
    		if(!user_Pwd.equals(user_info.getUserPassword()))
    			return "wrong";
    		else{
                int user_id = user_info.getUserId();
//                List<String> priority = priority_service_.findUserPriorityByUserId(user_id);
    			session.setAttribute("UserInfo", user_info);
//                session.setAttribute("Priority", priority);
    			return "true";
    		}
    	}
    }
    
    /**
     * 退出
     * @param session
     * @return
     */
    @RequestMapping(value = "/logout")
    @ResponseBody
    public Map<String,Object> systemLogOut(HttpSession session){
    	logger.info("===login out = " );
    	Map<String,Object> map=new HashMap<String,Object>();
    	UserInfo userinfo=(UserInfo) session.getAttribute("UserInfo");
    	String username="";
    	if(userinfo==null){
    		username="none";
    	}
    	else
    	{
    		session.removeAttribute("UserInfo");
//			session.removeAttribute("Priority");
    		username=userinfo.getUserName();
    		session.invalidate();
    	}
    	map.put("username", username);
    	return map;
    }
    
    /**
     * 跳转界面
     * @param request
     * @param model
     * @return
     */
	@RequestMapping("/zgmp/check_monitoring")
    public String loginItsm(HttpServletRequest request, ModelMap model) {
        System.out.println("check monitoring");
        return "index_monitoring";
    }
}