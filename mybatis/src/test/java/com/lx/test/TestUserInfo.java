package com.lx.test;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSON;
import com.lx.model.UserInfo;
import com.lx.serviceimpl.UserInfoServiceImpl;

public class TestUserInfo {

	UserInfoServiceImpl userInfoServiceImpl;
	ApplicationContext app = null;
	private static final Logger logger = Logger.getLogger(TestUserInfo.class);

	@Before
	public void init() {
		app = new ClassPathXmlApplicationContext("classpath:lx/applicationContext.xml");
		userInfoServiceImpl = app.getBean(UserInfoServiceImpl.class);
	}

	@Test
	public void TestSelectAllUserInfoByLikeUserName() {
		List<UserInfo> userInfos = userInfoServiceImpl.selectAllUserInfoByLikeUserName("发改委");
		logger.info("===A====" + userInfos.isEmpty() + "==");
		logger.info("===B=====" + userInfos.size() + "==");
		logger.info("===C=====" + userInfos.get(1).getUserPassword() + "==");
		logger.info("===HH=====" + JSON.toJSONString(userInfos.get(1)) + "==");
		logger.info("===E=====" + userInfos.get(2) + "==");
	}

	@Test
	public void TestDelByUserName() {
		logger.info("====" + userInfoServiceImpl.delByUserName("大家") + "==");
		
	}

	@Test
	public void TestAddUserInfo() {

		UserInfo userInfo = new UserInfo();
		userInfo.setUserName("爹欧尼");
		userInfo.setUserPassword("123456");
		userInfo.setUserCheck(1);
		userInfo.setUserRole("管理员");

		userInfoServiceImpl.addUserInfo(userInfo);

	}

}
