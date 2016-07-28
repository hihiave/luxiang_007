package com.lyr.test;

import java.util.List;

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
	public void DeleteByUserId() {
		System.out.print("=====This is deletByPrimaryKey=====");
		int flag = userInfoServiceImpl.deleteByPrimaryKey(1001);
		System.out.println("==========" + flag + "=========");
	}
	
	
	@Test
	public void InsertUserInfo() {
		System.out.print("=====This is insert=====");
		UserInfo userinfo=new UserInfo();
		userinfo.setUserName("general");
		int flag=userInfoServiceImpl.insert(userinfo);
		System.out.println("==========" + flag + "=========");
	}
	
	
	@Test
	public void SelectByUserId(){
		System.out.print("=====This is selectByPrimaryKey=====");
		UserInfo userInfo = userInfoServiceImpl.selectByUserId(1);
		System.out.println("==========" + userInfo.getUserName() + "=========");
	}

	
	@Test
	public void SelectByUserName(){
		System.out.print("=====This is selectByUserName=====");
		UserInfo userInfo = userInfoServiceImpl.selectByUserName("liyuran");
		System.out.println("==========" + userInfo.getUserPassword() + "=========");
	}
	
	
	@Test
	public void DeleteByUserName(){
		System.out.print("=====This is deleteByUserName=====");
		int flag = userInfoServiceImpl.deleteByUserName("crazy");
		System.out.println("==========" + flag + "=========");
	}
	
	
	@Test
	public void UpdateUserCheckByUserId(){
		System.out.print("=====This is updateUserCheckByUserId=====");
		int flag=userInfoServiceImpl.updateUserCheckByUserId(4, 1);
				System.out.println("==========" + flag + "=========");		
	}
	
	
	@Test
	public void SelectUser(){
		System.out.print("=====This is selectUser=====");
		List<UserInfo> userInfo = userInfoServiceImpl.selectUser();
				System.out.println("==========" + userInfo.get(0).getUserName() + "=========");		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
