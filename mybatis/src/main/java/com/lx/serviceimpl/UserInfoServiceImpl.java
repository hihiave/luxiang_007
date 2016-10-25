package com.lx.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lx.dao.UserInfoMapper;
import com.lx.macrofiles.MacroEnum;
import com.lx.macrofiles.MacroEnum.KMessageType;
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
		return insertUserInfo(userName, ToolEncryption.EncryptMD5("123456"), MacroEnum.KCheckType.PASS);
	}

	@Override
	public boolean registerUserInfo(String userName, String userPassword) {
		return insertUserInfo(userName, ToolEncryption.EncryptMD5(userPassword), MacroEnum.KCheckType.NOTPASS);
	}

	@Override
	public UserInfo selectUserByUserName(String userName) {
		return userInfoMapper.checkLogin(userName);
	}

	@Override
	public boolean updateUsersCheck(String... userNames) {
		boolean flag = false;
		if (userNames.length == userInfoMapper.updateUsersCheck(userNames)) {
			flag = true;
		}
		return flag;
	}

	public KMessageType checkLogin(String userName, String userPassword) {
		UserInfo userInfo = userInfoMapper.checkLogin(userName);
		if (userInfo != null && userInfo.getUserPassword().equals(ToolEncryption.EncryptMD5(userPassword))) {
			if (userInfo.getUserCheck() == 1) {
				return KMessageType.loginSuccess; // 登录成功
			}
			return KMessageType.checkNotPass; // 审核未通过
		}
		return KMessageType.loginFail;// 登录失败
	}

	@Override
	public List<UserInfo> selectUserByIsPass(int checkType) {
		return userInfoMapper.selectUserByIsPass(checkType);
	}

	@Override
	public int getCountWithNotPass() {
		return selectUserByIsPass(MacroEnum.KCheckType.NOTPASS).size();
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
		UserInfo userInfo = userInfoMapper.checkLogin(userName);
		String temp = ToolEncryption.EncryptMD5(oldPwd);
		if (userInfo.getUserPassword().equals(temp)) {
			if (userInfoMapper.updateUserPassword(userName, ToolEncryption.EncryptMD5(newPwd)) == 1) {
				flag = true;
			}
		}
		return flag;
	}

	@Override
	public boolean checkUserIsExist(String userName) {
		UserInfo userInfo = userInfoMapper.checkLogin(userName);
		if (userInfo != null) {
			return true;
		}
		return false;
	}

	@Override
	public List<String> getUserNames(String userName) {
		return userInfoMapper.getUserNames(userName);
	}
}
