package com.lx.controller;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lx.model.UserInfo;
import com.lx.service.UserInfoService;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSetMetaData;
import com.mysql.jdbc.Statement;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/UserInfoController")
public class UserInfoController {

	@Autowired
	UserInfoService userInfoService;

	@RequestMapping("/login")
	public String login(String username, String password, HttpSession httpSession) {
		System.out.println("======username=====" + username);
		System.out.println("======password=====" + password);
		if (userInfoService.checkLogin(username, password)) {
            httpSession.setAttribute("username",username);
			httpSession.setAttribute("flag", true);
			return "knowledgebase/adm-check";
		}
		
		return "knowledgebase/Login";
	}
    @RequestMapping("/regist")
    public String regist(String username, String password, HttpSession httpSession){
        System.out.println("======username=====" + username);
        System.out.println("======password=====" + password);
        if (userInfoService.registerUserInfo(username, password)) {
            httpSession.setAttribute("username",username);
            httpSession.setAttribute("flag", true);
            return "knowledgebase/adm-check";
        }
        else {
            return "knowledgebase/Register";
        }
    }

    @RequestMapping("/adm-adduser")
    public String admadduser(String username, HttpServletRequest request){
        System.out.println(username+"-------___________");
        if (userInfoService.addUserInfo(username)){
            return "knowledgebase/adm-inquire";
        }
        return null;
    }

//    @RequestMapping(value="get_export_select_info", method= RequestMethod.POST)
	@RequestMapping(value = "/inquire", method = RequestMethod.POST)
    @ResponseBody
	public Map<String, Object> inquire(String username_search,HttpSession httpSession) {

        System.out.println(username_search+"!!!!!!!!!!!!!!!!!!___________");
		List<UserInfo> userInfos = userInfoService.selectAllUserInfoByLikeUserName(username_search);
        System.out.println(userInfos.size()+"个匹配选项");
        for (int i = 0;i < userInfos.size();i++){
            System.out.println("第"+(i+1)+"个匹配"+userInfos.get(i).getUserName());
        }
        //ModelAndView modelAndView = new ModelAndView();
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("UserInfo_search",userInfos);
        //System.out.println(map.get(userInfos) + "wowowowo");
        //modelAndView.addAllObjects(map);
        //httpSession.setAttribute("UserInfo", userInfos);
        return map;

	}

	// @RequestMapping("/login")
	// public String login(String userName, HttpServletRequest request) {
	// List<UserInfo> userInfos =
	// userInfoServiceImpl.selectAllUserInfoByLikeUserName(userName);
	// UserInfo userInfo = userInfos.get(0);
	// request.setAttribute("userInfo", userInfo);
	//
	// return "showUser";
	// }
	public void test(){
		System.out.println("=======================");
	}
	

}