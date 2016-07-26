package com.lx.dao;

import java.util.List;

import com.lx.model.UserInfo;

public interface UserInfoMapper {
	int deleteByPrimaryKey(Integer userId);

	int insert(UserInfo record);

	int insertSelective(UserInfo record);

	UserInfo selectByPrimaryKey(Integer userId);

	int updateByPrimaryKeySelective(UserInfo record);

	int updateByPrimaryKey(UserInfo record);

	

	List<UserInfo> selectAllUserInfoByLikeUserName(String userName);
	int deleteByUserName(String userName);

}