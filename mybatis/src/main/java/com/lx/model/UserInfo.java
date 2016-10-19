package com.lx.model;

public class UserInfo {
	private Integer userId;

	private String userName;

	private String userPassword;

	private String userRole;

	private Integer userCheck;

	private Integer userRegisterTime;

	private Integer userStatus;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName == null ? null : userName.trim();
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword == null ? null : userPassword.trim();
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole == null ? null : userRole.trim();
	}

	public Integer getUserCheck() {
		return userCheck;
	}

	public void setUserCheck(Integer userCheck) {
		this.userCheck = userCheck;
	}

	public Integer getUserRegisterTime() {
		return userRegisterTime;
	}

	public void setUserRegisterTime(Integer userRegisterTime) {
		this.userRegisterTime = userRegisterTime;
	}

	public Integer getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(Integer userStatus) {
		this.userStatus = userStatus;
	}
}