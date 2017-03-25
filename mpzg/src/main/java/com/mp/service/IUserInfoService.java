package com.mp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mp.model.UserInfo;

@Service("UserInfoService")
public interface IUserInfoService {
	/**
	 * 通过用户名查询
	 * @param username
	 * @return
	 */
    UserInfo selectByUserName(String username);
    
    /**
     * 通过id查询密码
     * @param userId
     * @return
     */
    UserInfo selectPswById(Integer userId);
    
    /**
     * 通过用户id修改
     * @param record
     * @return
     */
    int updateUserInfoByname(UserInfo record);
    
    /**
     * 通过用户id修改密码
     * @param record
     * @return
     */
    int updateUserPswById(UserInfo record);
/**
 * 查找用户信息
 * @param begin
 * @param int_row_num
 * @return
 */
	List<UserInfo> findAllUserManageInfoPerPage(int begin, int int_row_num);
/**
 * 查找用户信息
 * @return
 */
	int findAllUserManageInfoNum();
}
