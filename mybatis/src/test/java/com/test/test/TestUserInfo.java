package com.test.test;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.test.model.UserInfo;
import com.test.serviceimpl.UserInfoServiceImpl;

public class TestUserInfo {

	ApplicationContext app = null;
	private UserInfoServiceImpl userInfoServiceImpl;

	private static final Logger logger = Logger.getLogger(TestUserInfo.class);

	@Before
	public void init() {
		app = new ClassPathXmlApplicationContext("test/applicationContext.xml");
		userInfoServiceImpl = app.getBean(UserInfoServiceImpl.class);
	}

	@Test
	public void TestGetUserInfoById() {
		logger.info("=========TestGetUserInfoById===========");
		UserInfo userInfo = userInfoServiceImpl.getUserInfoById(1001);

		logger.info("==========" + userInfo.getUserName() + "=====");
	}

	@Test
	public void TestDelByUserId() {
		if (userInfoServiceImpl.delUserInfoById(455)) {
			logger.info("删除 id 455 成功!");
		} else {
			logger.info("删除 id 455 失败!");
		}
	}

}
