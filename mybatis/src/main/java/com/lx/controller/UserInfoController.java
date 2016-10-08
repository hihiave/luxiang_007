package com.lx.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lx.model.UserInfo;
import com.lx.serviceimpl.UserInfoServiceImpl;

@Controller
@RequestMapping("/UserInfoController")
public class UserInfoController {

	@Autowired
	UserInfoServiceImpl userInfoServiceImpl;

	@RequestMapping("/login")
	public String login(String name , HttpServletRequest request) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		System.out.println("======username=====" + username );
		System.out.println("======password=====" + password );
		System.out.println("======name=====" + name );
		
		//List<UserInfo> userInfos = userInfoServiceImpl.selectAllUserInfoByLikeUserName(userName);
		//UserInfo userInfo = userInfos.get(0);
		//request.setAttribute("userInfo", userInfo);
		
		return "showUser";
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
