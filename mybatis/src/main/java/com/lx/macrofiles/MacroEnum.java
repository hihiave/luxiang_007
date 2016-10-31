package com.lx.macrofiles;

public class MacroEnum {

	/**
	 * 审核类型
	 */
	public enum KCheckType {
		WAITFORCHECK(0), // 等待审核
		PASS(1), // 审核通过
		NOTPASS(-1); // 审核不通过

		KCheckType(int value) {
			this.value = value;
		}

		private int value;

		public int getValue() {
			return value;
		}

	}

	public interface KFileType {
		// 文件公有还是私有
		public static final String PUBLICFILE = "公有";
		//public static final String PRIVATEFILE = "私有";
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
	public enum KButtonType {
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
