package com.test.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test.model.UserInfo;
import com.test.service.UserInfoService;

@Controller
@RequestMapping("/haha")
public class UserInfoController {
	@Autowired
	UserInfoService userInfoService;

	@RequestMapping("/hehe")
	public String showUserInfo(int userId, HttpServletRequest request) {
		System.out.println("================");
        System.out.println("****************");
		UserInfo userInfo = userInfoService.getUserInfoById(userId);
		request.setAttribute("userInfo", userInfo);
		return "showUser";

	}
}
