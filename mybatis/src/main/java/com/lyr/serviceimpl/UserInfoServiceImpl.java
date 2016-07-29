package com.lyr.serviceimpl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lyr.dao.UserInfoMapper;
import com.lyr.model.UserInfo;
import com.lyr.service.UserInfoService;
import com.lyr.util.StringUtil;

@Service
public class UserInfoServiceImpl implements UserInfoService {
	
	
	@Autowired
	private UserInfoMapper userInfoMapper;
	
	private static final Logger logger = Logger.getLogger(UserInfoServiceImpl.class);
	
	
	
	@Override
	public int deleteByPrimaryKey(Integer userId){
		int flag;
		flag = userInfoMapper.deleteByPrimaryKey(userId);
		return flag;
	}
	
	
	@Override
	public int insert (UserInfo record){
		int flag = 0;
		flag = userInfoMapper.insertSelective(record);
		return flag;
	}
	
	
	@Override
	public int insertSelective(UserInfo record) {
		return userInfoMapper.insertSelective(record);
	}

	
	@Override
	public UserInfo selectByPrimaryKey(Integer userId){
		return userInfoMapper.selectByPrimaryKey(userId);
	}
	
	
	@Override
	public int updateByPrimaryKey(UserInfo record){
		int flag;
		flag = userInfoMapper.updateByPrimaryKey(record);
		return flag;
	}
	
	
	@Override
	public int updateByPrimaryKeySelective(UserInfo record) {
		int flag = 0;
		flag = userInfoMapper.updateByPrimaryKeySelective(record);
		return flag;
	}

	
	@Override
	public List<UserInfo> selectByUserName(String userName){
		if(!StringUtil.isEmpty(userName)){
			return userInfoMapper.selectByUserName(userName);
		}else{
			return selectUser();
		}
	}
	
	
	@Override
	public int deleteByUserName(String userName){
		int flag = 0;
		if(userInfoMapper.deleteByUserName(userName)==1){
			flag = 1;
		}
		return flag;
	}
		
		@Override
		public int updateUserCheckByUserId(Integer userId, Integer userCheck){
			int flag = 0;
			UserInfo userInfo = new UserInfo();
			userInfo.setUserId(userId);
			userInfo.setUserCheck(userCheck);
			flag = userInfoMapper.updateUserCheckByUserId(userInfo);
			return flag;
		}
		
		
		@Override
		public List<UserInfo> selectUser(){
			return userInfoMapper.selectUser();
		}
		
		
		@Override
		public int updateUserPassword(UserInfo userInfo,String oldPassword,String newPassword){
			int flag = 0;
			if(userInfo != null && !StringUtil.getStringUtil().isEmpty(oldPassword) && !StringUtil.getStringUtil().isEmpty(newPassword)){
				String oldMd5Password = StringUtil.getStringUtil().MD5(oldPassword.trim());
				if(!oldMd5Password.equals(userInfo.getUserPassword().trim())){
					return -1;
				}else{
					String newMd5Password = StringUtil.getStringUtil().MD5(newPassword.trim());
					userInfo.setUserPassword(newMd5Password);
					flag = updateByPrimaryKeySelective(userInfo);
				}
			}
			return flag;
		}
		
	
	@Override
	public UserInfo selectByUserId(int userId){	
		return userInfoMapper.selectByPrimaryKey(userId);
	}
	
	
}
