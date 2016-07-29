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
    
    
    //栗雨然

    int deleteByUserName(String userName);
    
    List<UserInfo> selectByUserName(String userName);
    
    int updateUserCheckByUserId(UserInfo record);
    
    List<UserInfo> selectUser();

    
    
    
    
    
    
    
}