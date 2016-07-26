package com.lyr.service;

import com.lyr.model.UserInfo;

public interface UserInfoService {
	
	/*@param userId
	 * @return 一个用户对象*/
	public UserInfo getUserInfoById(int userId);
	
	/*@param userName
	 * @return Boolean类型 true or false*/
	public boolean delectUserInfoByName(String userName);
}
