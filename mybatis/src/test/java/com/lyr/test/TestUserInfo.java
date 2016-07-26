package com.lyr.test;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lyr.model.UserInfo;
import com.lyr.serviceimpl.UserInfoServiceImpl;

public class TestUserInfo {

	ApplicationContext app = null;
	private UserInfoServiceImpl userInfoServiceImpl;
	
	private static final Logger logger = Logger.getLogger(TestUserInfo.class);
	
	@Before
	public void init() {

		app = new ClassPathXmlApplicationContext("classpath:lyr/applicationContext.xml");
		userInfoServiceImpl = app.getBean(UserInfoServiceImpl.class);
	}
	
	@Test
	public void TestGetUserInfoById(){
		UserInfo userInfo = userInfoServiceImpl.getUserInfoById(1);
		System.out.println("==========" + userInfo.getUserName() + "=========");
		
		System.out.println("=================");
		System.out.println("==============");
		System.out.println("=================");
		System.out.println("==============");
		
	}
}
