package com.lx.service;

import java.util.List;

import com.lx.model.UserInfo;

public interface UserInfoService {
	
	/** 通过用户名模糊查询所有用户，比如，查“三”，找到“李三”，“张三”
	 * @author luxiang
	 * @param userName
	 * @return 一个用户对象列表 UserInfo
	 */
	List<UserInfo> selectAllUserInfoByLikeUserName(String userName);

	/**
	 * @author luxiang
	 * @param userName
	 * @return 一个boolean，true表示删除成功，false表示删除失败
	 */
	boolean delByUserName(String userName);

	
	/**
	 * @author luxiang
	 * @param userInfo对象
	 * @return 一个boolean，true表示添加成功，false表示添加失败
	 */
	public boolean addUserInfo(UserInfo userInfo);

}
