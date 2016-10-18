package com.lx.macrofiles;

public class MacroEnum {

	/**
	 * 时间单位
	 */
	public enum KDateType {
		Year, Month, Week, Day, Hour, Minute, Second;
	}

	/**
	 * 审核类型
	 */
	public interface KUserCheckType {
		public static final int PASS = 1;
		public static final int NOTPASS = 0;
	}

	/**
	 * 消息类型
	 */
	public enum KMessageType {
		loginSuccess, // 登录成功
		loginFail, // 登录失败（用户名或密码错误）
		checkNotPass // 审核未通过
	}
}
