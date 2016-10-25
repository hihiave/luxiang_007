package com.lx.test;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSON;
import com.lx.macrofiles.MacroEnum;
import com.lx.macrofiles.MacroEnum.KMessageType;
import com.lx.model.UserInfo;
import com.lx.service.UserInfoService;
import com.lx.serviceimpl.UserInfoServiceImpl;

public class TestUserInfo {

	UserInfoService userInfoService;
	ApplicationContext app = null;
	private static final Logger logger = Logger.getLogger(TestUserInfo.class);

	@Before
	public void init() {
		app = new ClassPathXmlApplicationContext("classpath:lx/applicationContext.xml");
		userInfoService = app.getBean(UserInfoServiceImpl.class);
	}

	@Test
	public void TestAddUserInfo() {
		boolean d = userInfoService.addUserInfo("dddddddffdd");
		logger.info("===你你你你你==" + d);
	}

	@Test
	public void TestRegisterUserInfo() {
		userInfoService.registerUserInfo("皇上", "123456");
	}

	@Test
	public void TestSelectUserByUserName() {
		UserInfo userInfo = userInfoService.selectUserByUserName("luxiangd");
		logger.info("===userInfo==" + JSON.toJSONString(userInfo));
	}

	@Test
	public void TestUpdateUsersCheck() {
		boolean d = userInfoService.updateUsersCheck("rr", "ee");
		logger.info("批量审核通过=" + d);
	}

	@Test
	public void TestCheckLogin() {
		KMessageType messageType = userInfoService.checkLogin("luxiang", "123456");
		logger.info("===LOL  da====" + messageType + "==");
	}

	@Test
	public void TestSelectUserByIsPass() {
		List<UserInfo> userInfos = userInfoService.selectUserByIsPass(MacroEnum.KCheckType.NOTPASS);
		logger.info("===A====" + userInfos.isEmpty() + "==");
		logger.info("===B=====" + userInfos.size() + "==");
		logger.info("===HH=====" + JSON.toJSONString(userInfos.get(1)) + "==");
		logger.info("===E=====" + userInfos.get(2) + "==");
		logger.info("==============haha=============");
	}

	@Test
	public void TestGetCountWithNotPass() {
		int i = userInfoService.getCountWithNotPass();

		logger.info("=======TestGetCountWithNotPass=====" + i);
	}

	@Test
	public void TestSelectAllUserInfoByLikeUserName() {
		List<UserInfo> userInfos = userInfoService.selectAllUserInfoByLikeUserName("李");
		logger.info("===A====" + userInfos.isEmpty() + "==");
		logger.info("===B=====" + userInfos.size() + "==");
		logger.info("===HH=====" + JSON.toJSONString(userInfos) + "==");
		logger.info("==============haha=============");
	}

	@Test
	public void TestDelByUserName() {
		logger.info("====" + userInfoService.delByUserName("wowowowo") + "==");
	}

	@Test
	public void TestResetPassword() {
		boolean d = userInfoService.resetPassword("q1111");
		logger.info("===resetPassword====" + d + "==");
	}

	@Test
	public void TestAlterPassword() {
		boolean d = userInfoService.alterPassword("wusongze", "123456", "456789");
		logger.info("===alterPassword====" + d + "==");
	}

	@Test
	public void TestCheckUserIsExist() {
		boolean d = userInfoService.checkUserIsExist("luxiang11");
		logger.info("===CheckUserIsExist====" + d + "==");
	}

	@Test
	public void TestGetUserNames() {
		List<String> userNames = userInfoService.getUserNames("李");
		logger.info("===A====" + userNames.isEmpty() + "==");
		logger.info("===B=====" + userNames.size() + "==");
		logger.info("===PPL  da====" + userNames.get(0) + "==");
		logger.info("===HH=====" + JSON.toJSONString(userNames) + "==");
	}

}
