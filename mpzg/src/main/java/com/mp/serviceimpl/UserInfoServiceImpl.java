package com.mp.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mp.dao.UserInfoMapper;
import com.mp.model.UserInfo;
import com.mp.service.IUserInfoService;
@Service
public class UserInfoServiceImpl  implements IUserInfoService {
	@Autowired
    private UserInfoMapper userinfo_mapper;
	
	/**
	 * 查询用户信息
	 */
	public UserInfo selectByUserName(String username) {
        if (username == null){
            return null;
        }
        else
            return userinfo_mapper.selectByUserName(username);
    }
	
	@Override
	public int updateUserInfoByname(UserInfo record){
		int num = 0 ;
		num = userinfo_mapper.updateByPrimaryKeySelective(record);
		return num;
		
	}
	
	/**
	 * 查询用户信息
	 */
	public UserInfo selectPswById(Integer userId) {
        if (userId == null){
            return null;
        }
        else
            return userinfo_mapper.selectPswById(userId);
    }
	
	public int updateUserPswById(UserInfo record){
		int num = 0 ;
		num = userinfo_mapper.updateUserPswById(record);
		return num;
		
	}
	
	 @Override
	    public List<UserInfo> findAllUserManageInfoPerPage(
	            int begin, int size) {
	        // TODO Auto-generated method stub
	        Map<String, Object>  data = new HashMap<String, Object>();
	        data.put("begin", begin);
	        data.put("size", size);
	        return userinfo_mapper.findAllUserManageInfoPerPage(data);
	    }

	    @Override
	    public int findAllUserManageInfoNum() {
	        // TODO Auto-generated method stub
	        return userinfo_mapper.findAllUserManageInfoNum();
	    }

}
