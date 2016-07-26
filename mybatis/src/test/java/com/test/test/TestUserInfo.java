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

	// 方法不严谨，插入的时候应判断，用户id是否已经存在了，因为id号是 主键
	// @Test
	// public void TestAddUserInfo() {
	// UserInfo userInfo = new UserInfo();
	// userInfo.setUserId(200);
	// userInfoServiceImpl.addUserInfo(userInfo);
	//
	// }

}
