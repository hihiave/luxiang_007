package com.lx.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lx.dao.UserInfoMapper;
import com.lx.model.UserInfo;
import com.lx.service.UserInfoService;

@Service
public class UserInfoServiceImpl implements UserInfoService {

	@Autowired
	private UserInfoMapper userInfoMapper;

	@Override
	public List<UserInfo> selectAllUserInfoByLikeUserName(String userName) {
		return userInfoMapper.selectAllUserInfoByLikeUserName("%" + userName + "%");
	}

	@Override
	public boolean delByUserName(String userName) {
		boolean flag = false;
		if (userInfoMapper.deleteByUserName(userName) == 1) {
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
