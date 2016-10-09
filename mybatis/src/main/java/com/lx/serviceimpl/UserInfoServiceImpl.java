package com.lx.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lx.dao.UserInfoMapper;
import com.lx.macrofiles.MacroEnum;
import com.lx.model.UserInfo;
import com.lx.service.UserInfoService;
import com.lx.tool.ToolDate;
import com.lx.tool.ToolEncryption;

@Service
public class UserInfoServiceImpl implements UserInfoService {

	@Autowired
	private UserInfoMapper userInfoMapper;

	private boolean insertUserInfo(String userName, String userPassword, int checkType) {
		boolean flag = false;
		UserInfo userInfo = new UserInfo();
		userInfo.setUserName(userName);
		userInfo.setUserPassword(userPassword);
		userInfo.setUserRole("普通用户");
		userInfo.setUserCheck(checkType);
		userInfo.setUserRegisterTime(ToolDate.getCurrentTimestamp());
		if (userInfoMapper.insertSelective(userInfo) == 1) {
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean addUserInfo(String userName) {
		return insertUserInfo(userName, ToolEncryption.EncryptMD5("123456"), MacroEnum.KUserCheckType.pass);
	}

	@Override
	public boolean registerUserInfo(String userName, String userPassword) {
		return insertUserInfo(userName, ToolEncryption.EncryptMD5(userPassword), MacroEnum.KUserCheckType.not_pass);
	}

	@Override
	public boolean updateUsersCheck(String... userNames) {
		boolean flag = false;
		if (userNames.length == userInfoMapper.updateUsersCheck(userNames)) {
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean checkLogin(String userName, String userPassword) {
		boolean flag = false;
		String pwd = userInfoMapper.checkLogin(userName);
		if (pwd.equals(ToolEncryption.EncryptMD5(userPassword))) {
			flag = true;
		}
		return flag;
	}

	@Override
	public List<UserInfo> selectUserByIsPass(int checkType) {
		return userInfoMapper.selectUserByIsPass(checkType);
	}

	@Override
	public int getCountWithNotPass() {
		return selectUserByIsPass(MacroEnum.KUserCheckType.not_pass).size();
	}

	@Override
	public List<UserInfo> selectAllUserInfoByLikeUserName(String userName) {
		return userInfoMapper.selectAllUserInfoByLikeUserName(userName);
	}

	@Override
	public boolean delByUserName(String userName) {
		boolean flag = false;
		if (userInfoMapper.deleteByUserName(userName) == 1) {
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean resetPassword(String userName) {
		boolean flag = false;
		if (userInfoMapper.updateUserPassword(userName, ToolEncryption.EncryptMD5("123456")) == 1) {
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean alterPassword(String userName, String oldPwd, String newPwd) {
		boolean flag = false;
		if (checkLogin(userName, oldPwd)) {
			if (userInfoMapper.updateUserPassword(userName, ToolEncryption.EncryptMD5(newPwd)) == 1) {
				flag = true;
			}
		} else {
			System.out.println("旧密码不对");
		}
		return flag;
	}

	@Override
	public List<String> getUserNames(String userName) {
		return userInfoMapper.getUserNames(userName);
	}

}
