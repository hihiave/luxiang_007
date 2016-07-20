package com.test.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.dao.UserInfoMapper;
import com.test.model.UserInfo;
import com.test.service.UserInfoService;

@Service
public class UserInfoServiceImpl implements UserInfoService {

	@Autowired
	private UserInfoMapper userInfoMapper;

	@Override
	public UserInfo getUserInfoById(int userId) {
		return userInfoMapper.selectByPrimaryKey(userId);
	}

	@Override
	public boolean delUserInfoById(int userId) {
		boolean flag = false;

		if (userInfoMapper.deleteByPrimaryKey(userId) == 1) {
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean addUserInfo(UserInfo userInfo) {

		int i = userInfoMapper.insert(userInfo);
		System.out.println("===========" + i + "====");
		return false;
	}

}
