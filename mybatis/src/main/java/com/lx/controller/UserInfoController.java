package com.lx.controller;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
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
			httpSession.setAttribute("flag", true);
			return "knowledgebase/public_file_tab";
		}
		
		return "redirect:/UserInfoController/ddd.do";
	}

	@RequestMapping("/ddd")
	public void ddd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<UserInfo> userInfos = userInfoService.selectAllUserInfoByLikeUserName("李");

		request.setAttribute("UserInfo", userInfos);
		request.getRequestDispatcher("../knowledgebase/adm-inquire.jsp").forward(request, response);
		
		//"/mybatis/UserInfoController/ddd.do";
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

}
