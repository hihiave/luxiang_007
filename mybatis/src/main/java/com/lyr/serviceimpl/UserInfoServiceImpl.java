package com.lyr.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lyr.dao.UserInfoMapper;
import com.lyr.model.UserInfo;
import com.lyr.service.UserInfoService;

@Service
public class UserInfoServiceImpl implements UserInfoService {
	
	
	@Autowired
	private UserInfoMapper userInfoMapper;
	
	@Override
	public UserInfo getUserInfoById(int userId){
		
		return userInfoMapper.selectByPrimaryKey(userId);
	}
	
	@Override
	public boolean delectUserInfoByName(String userName){
		
		boolean flag = false;
		if(userInfoMapper.delect(userName)==1){
			flag=true;
		}
		return flag;
	}
	
}
