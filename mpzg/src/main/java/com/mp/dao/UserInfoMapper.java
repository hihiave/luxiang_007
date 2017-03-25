package com.mp.dao;

import java.util.List;
import java.util.Map;

import com.mp.model.UserInfo;

public interface UserInfoMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);
    
    UserInfo selectByUserName(String username);
    
    UserInfo selectPswById(Integer userId);
    
    int updateUserPswById(UserInfo record);

	List<UserInfo> findAllUserManageInfoPerPage(Map<String, Object> data);

	int findAllUserManageInfoNum();
}