package com.lyr.dao;

import java.util.List;

import com.lyr.model.UserInfo;

public interface UserInfoMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(UserInfo record);
    
    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);

    int deleteByUserName(String userName);
    
    UserInfo selectByUserName(String userName);
    
    int updateUserCheckByUserId(UserInfo record);
    
    List<UserInfo> selectUser();
    
    
    
    
    
    
    
}