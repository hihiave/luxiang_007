package com.lyr.service;

import java.util.List;

import com.lyr.model.UserInfo;

public interface UserInfoService {
	
	
	/**
	 * @author 栗雨然
	 * @param userId
	 * @return 1为操作成功；0为操作失败，用户不存在
	 */
	 int deleteByPrimaryKey(Integer userId);
	
	
	/**
	 * @author 栗雨然
	 * @param record
	 * @return 1为操作成功；2为操作失败
	 */
	 int insert (UserInfo record);
	
	int insertSelective(UserInfo record);
	
	
	/**
	 * @author 栗雨然
	 * @param userId
	 * @return UserInfo
	 */
	 UserInfo selectByPrimaryKey(Integer userId);
	
	
	/**
	 * @author 栗雨然
	 * @param record
	 * @return 0为操作失败；1为操作成功
	 */
	 int updateByPrimaryKey(UserInfo record);
	
	 int updateByPrimaryKeySelective(UserInfo record);

	
	/**
	 * @author 栗雨然
	 * @param userName
	 * @return UserInfo
	 */
	 UserInfo selectByUserName(String userName);
	
	
	/**
	 * @author 栗雨然
	 * @param userName
	 * @return 0为操作失败；1为操作成功
	 */
	 int deleteByUserName(String userName);
	
	
	/**
	 * @author 栗雨然
	 * @param userId
	 * @param userCheck
	 * @return 0为操作失败；1为操作成功
	 */
	 int updateUserCheckByUserId(Integer userId, Integer userCheck);
	
	
	/**
	 * @author 栗雨然
	 * @return 所有用户信息
	 */
	List<UserInfo> selectUser();
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * @author 栗雨然
	 * @param userId
	 * @return UserInfo
	 */
	public UserInfo selectByUserId(int userId);

}
