package com.lx.test;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSON;
import com.lx.macrofiles.MacroEnum;
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
	public void TestAddUserInfo() {
		boolean d = userInfoServiceImpl.addUserInfo("QQQQQQQQ");
		logger.info("===你你你你你==" + d);
	}

	@Test
	public void TestRegisterUserInfo() {
		userInfoServiceImpl.registerUserInfo("皇上", "123456");
	}

	@Test
	public void TestUpdateUsersCheck() {
		boolean d = userInfoServiceImpl.updateUsersCheck("皇上", "李嘻嘻", "李百度", "爹欧尼");
		logger.info("批量审核通过=" + d);
	}

	@Test
	public void TestCheckLogin() {
		boolean da = userInfoServiceImpl.checkLogin("QQQQQQQQ", "123456");
		logger.info("===LOL  da====" + da + "==");
	}

	@Test
	public void TestSelectUserByIsPass() {
		List<UserInfo> userInfos = userInfoServiceImpl.selectUserByIsPass(MacroEnum.KUserCheckType.not_pass);
		logger.info("===A====" + userInfos.isEmpty() + "==");
		logger.info("===B=====" + userInfos.size() + "==");
		logger.info("===HH=====" + JSON.toJSONString(userInfos.get(1)) + "==");
		logger.info("===E=====" + userInfos.get(2) + "==");
		logger.info("==============haha=============");
	}

	@Test
	public void TestSelectAllUserInfoByLikeUserName() {
		List<UserInfo> userInfos = userInfoServiceImpl.selectAllUserInfoByLikeUserName("发改委");
		logger.info("===A====" + userInfos.isEmpty() + "==");
		logger.info("===B=====" + userInfos.size() + "==");
		logger.info("===C=====" + userInfos.get(1).getUserPassword() + "==");
		logger.info("===HH=====" + JSON.toJSONString(userInfos.get(1)) + "==");
		logger.info("==============haha=============");
	}

	@Test
	public void TestDelByUserName() {
		logger.info("====" + userInfoServiceImpl.delByUserName("wowowowo") + "==");
	}

	@Test
	public void TestresetPassword() {
		boolean d = userInfoServiceImpl.resetPassword("你你你你你");
		logger.info("===resetPassword====" + d + "==");
	}

	@Test
	public void TestAlterPassword() {
		boolean d = userInfoServiceImpl.alterPassword("QQQQQQQQ", "123456", "nihao");
		logger.info("===alterPassword====" + d + "==");
	}

	@Test
	public void TestGetUserNames() {
		List<String> userNames = userInfoServiceImpl.getUserNames("李百");
		logger.info("===A====" + userNames.isEmpty() + "==");
		logger.info("===B=====" + userNames.size() + "==");
		logger.info("===PPL  da====" + userNames.get(0) + "==");
		logger.info("===HH=====" + JSON.toJSONString(userNames.get(1)) + "==");
	}

}
