package com.lx.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lx.model.UserInfo;
import com.lx.serviceimpl.UserInfoServiceImpl;

@Controller
@RequestMapping("/qw")
public class UserInfoController {

	@Autowired
	UserInfoServiceImpl userInfoServiceImpl;

	@RequestMapping("/tt")
	public String dd(String userName, HttpServletRequest request) {
		List<UserInfo> userInfos = userInfoServiceImpl.selectAllUserInfoByLikeUserName(userName);
		UserInfo userInfo = userInfos.get(0);
		request.setAttribute("userInfo", userInfo);
		
		return "showUser";
	}

}