package com.lx.test;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSON;
import com.lx.macrofiles.MacroEnum.KCheckType;
import com.lx.macrofiles.MacroEnum.KMessageType;
import com.lx.model.UserInfo;
import com.lx.service.UserInfoService;
import com.lx.serviceimpl.UserInfoServiceImpl;
import com.lx.tools.Page;

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
	public void addUserInfoTest() {
		boolean d = userInfoService.addUserInfo("dddddddffdd");
		logger.info("===你你你你你==" + d);
	}

	@Test
	public void registerUserInfoTest() {
		userInfoService.registerUserInfo("皇上", "123456", "卢享", "305057016@qq.com");
	}

	@Test
	public void delUsersByUserNameTest() {
		logger.info("====" + userInfoService.delUsersByUserName("wowowowo") + "==");
	}

	@Test
	public void selectUserByUserNameTest() {
		UserInfo userInfo = userInfoService.selectUserByUserName("wusongze");
		logger.info("===userInfo==" + JSON.toJSONString(userInfo));
	}

	@Test
	public void checkUserIsExistTest() {
		boolean d = userInfoService.checkUserIsExist("luxiang11");
		logger.info("===CheckUserIsExist====" + d + "==");
	}

	@Test
	public void batchUsersPassTest() {
		boolean d = userInfoService.batchUsersPass("rr", "ee");
		logger.info("批量审核通过=" + d);
	}

	@Test
	public void checkLoginTest() {
		KMessageType messageType = userInfoService.checkLogin("luxiang", "123456");
		logger.info("===LOL  da====" + messageType + "==");
	}

	@Test
	public void resetPasswordTest() {
		boolean d = userInfoService.resetPassword("q1111");
		logger.info("===resetPassword====" + d + "==");
	}

	@Test
	public void alterPasswordTest() {
		boolean d = userInfoService.alterPassword("wusongze", "123456", "456789");
		logger.info("===alterPassword====" + d + "==");
	}

	@Test
	public void selectUserByIsPassTest() {
		Page page = new Page(3);
		List<UserInfo> userInfos = userInfoService.selectUserByIsPass(KCheckType.pass, page, "       ");
		logger.info("===size============" + userInfos.size());
		logger.info("===HH==============" + JSON.toJSONString(userInfos));
		logger.info("==============haha=============");
	}

	@Test
	public void getCountWithWaitForCheckTest() {
		int i = userInfoService.getCountWithWaitForCheck();
		logger.info("=======TestGetCountWithNotPass=====" + i);
	}

	// **********用于一些查询的方法**********
	@Test
	public void getUserNamesTest() {
		List<String> userNames = userInfoService.getUserNames("李");
		logger.info("===A====" + userNames.isEmpty() + "==");
		logger.info("===B=====" + userNames.size() + "==");
		logger.info("===PPL  da====" + userNames.get(0) + "==");
		logger.info("===HH=====" + JSON.toJSONString(userNames) + "==");
	}

}
