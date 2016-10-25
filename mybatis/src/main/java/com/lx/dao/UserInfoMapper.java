package com.lx.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lx.model.UserInfo;

public interface UserInfoMapper {
	
	int deleteByPrimaryKey(Integer userId);

	int insert(UserInfo record);

	int insertSelective(UserInfo record);

	UserInfo selectByPrimaryKey(Integer userId);

	int updateByPrimaryKeySelective(UserInfo record);

	int updateByPrimaryKey(UserInfo record);

	//**********用于处理一些业务逻辑的方法
	int updateUsersCheck(@Param("userNames") String... userNames);
	UserInfo checkLogin(String userName);
	List<UserInfo> selectUserByIsPass(int checkType);
	List<UserInfo> selectAllUserInfoByLikeUserName(String userName);
	int deleteByUserName(String userName);
	int updateUserPassword(@Param("userName") String userName, @Param("newPwd") String newPwd);
	
	//**********用于获取一些智能下拉提示
	List<String> getUserNames(String userName);


}