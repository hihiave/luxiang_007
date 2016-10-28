package com.lx.macrofiles;

public class MacroEnum {

	/**
	 * 审核类型
	 */
	public interface KCheckType {
		// 审核是否通过
		public static final int PASS = 1;
		public static final int NOTPASS = 0;

		// 文件公有还是私有
		public static final String PUBLICFILE = "公有";
		public static final String PRIVATEFILE = "私有";
	}

	/**
	 * 消息类型
	 */
	public enum KMessageType {
		loginSuccess, // 登录成功
		loginFail, // 登录失败（用户名或密码错误）
		checkNotPass // 审核未通过
	}
	
	/**
	 * 按钮类型
	 */
	public enum KButtonType{
		MyUploadButton, // 我的上传
		PublicFileButton, // 公有文件
	}
	
	
	/**
	 * 时间单位
	 */
	public enum KDateType {
		Year, Month, Week, Day, Hour, Minute, Second;
	}

}
