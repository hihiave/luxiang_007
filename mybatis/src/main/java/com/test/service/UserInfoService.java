package com.test.service;

import com.test.model.UserInfo;

public interface UserInfoService {

	/**
	 * @author luxiang
	 * @param userId
	 * @return 一个用户对象UserInfo
	 */
	public UserInfo getUserInfoById(int userId);

	/**
	 * @author luxiang
	 * @param userId
	 * @return 一个boolean，true表示删除成功，false表示删除失败
	 */
	public boolean delUserInfoById(int userId);

	/**
	 * @author luxiang
	 * @param userInfo对象
	 * @return 一个boolean，true表示添加成功，false表示添加失败
	 */
	public boolean addUserInfo(UserInfo userInfo);
}
