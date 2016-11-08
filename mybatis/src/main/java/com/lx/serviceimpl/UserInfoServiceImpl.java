package com.lx.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lx.dao.UserInfoMapper;
import com.lx.macrofiles.MacroEnum.KCheckType;
import com.lx.macrofiles.MacroEnum.KMessageType;
import com.lx.model.UserInfo;
import com.lx.service.UserInfoService;
import com.lx.tool.ToolDate;
import com.lx.tool.ToolEncryption;

@Service
public class UserInfoServiceImpl implements UserInfoService {

	@Autowired
	private UserInfoMapper userInfoMapper;

	// **********用于处理一些业务逻辑的方法**********
	private boolean insertUserInfo(String userName, String userPassword, KCheckType checkType) {
		boolean flag = false;
		UserInfo userInfo = new UserInfo();
		userInfo.setUserName(userName);
		userInfo.setUserPassword(userPassword);
		userInfo.setUserRole("普通用户");
		userInfo.setUserCheck(checkType.getValue());
		userInfo.setUserRegisterTime(ToolDate.getCurrentTimestamp());
		if (userInfoMapper.insertSelective(userInfo) == 1) {
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean addUserInfo(String userName) {
		return insertUserInfo(userName, ToolEncryption.EncryptMD5("123456"), KCheckType.pass);
	}

	@Override
	public boolean registerUserInfo(String userName, String userPassword) {
		return insertUserInfo(userName, ToolEncryption.EncryptMD5(userPassword), KCheckType.waitForCheck);
	}

	@Override
	public boolean delUsersByUserName(String... userNames) {
		if (userInfoMapper.delUsersByUserName(userNames) == userNames.length) {
			return true;
		}
		return false;
	}

	@Override
	public UserInfo selectUserByUserName(String userName) {
		return userInfoMapper.selectUserByUserName(userName);
	}

	@Override
	public boolean checkUserIsExist(String userName) {
		if (selectUserByUserName(userName) != null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean batchUsersPass(String... userNames) {
		if (userInfoMapper.updateUsersCheck(userNames) == userNames.length) {
			return true;
		}
		return false;
	}

	public KMessageType checkLogin(String userName, String userPassword) {
		UserInfo userInfo = selectUserByUserName(userName);
		if (userInfo != null && userInfo.getUserPassword().equals(ToolEncryption.EncryptMD5(userPassword))) {
			if (userInfo.getUserCheck() == 1) {
				return KMessageType.loginSuccess; // 登录成功
			}
			return KMessageType.checkNotPass; // 审核未通过
		}
		return KMessageType.loginFail;// 登录失败
	}

	@Override
	public boolean resetPassword(String userName) {
		if (userInfoMapper.updateUserPasswordByUserName(userName, ToolEncryption.EncryptMD5("123456")) == 1) {
			return true;
		}
		return false;
	}

	@Override
	public boolean alterPassword(String userName, String oldPwd, String newPwd) {
		boolean flag = false;
		UserInfo userInfo = selectUserByUserName(userName);
		String temp = ToolEncryption.EncryptMD5(oldPwd);
		if (userInfo.getUserPassword().equals(temp)) {
			if (userInfoMapper.updateUserPasswordByUserName(userName, ToolEncryption.EncryptMD5(newPwd)) == 1) {
				flag = true;
			}
		}
		return flag;
	}

	@Override
	public List<UserInfo> selectUserByIsPass(KCheckType checkType, Page page, String userName) {
		if (userName == null) {
			userName = "";
		}
		if (page != null) {
			int totalCount = userInfoMapper.selectUserByUserCheckCount(checkType.getValue(), userName.trim());
			page.setTotalCount(totalCount);
			page.init();
			return userInfoMapper.selectUserByUserCheck(checkType.getValue(), page, userName.trim());
		}
		return null;
	}

	@Override
	public int getCountWithWaitForCheck() {
		return userInfoMapper.selectUserByUserCheckCount(KCheckType.waitForCheck.getValue(), "");
	}

	// **********用于获取一些智能下拉提示**********
	@Override
	public List<String> getUserNames(String userName) {
		return userInfoMapper.getUserNames(userName);
	}
}
