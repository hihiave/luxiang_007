package com.lx.macrofiles;

public class MacroEnum {

	/**
	 * 审核类型
	 */
	public enum KCheckType {
		waitForCheck(0), // 等待审核
		pass(1), // 审核通过
		notPass(-1), // 审核不通过
		invalid(-2); // 文件失效,我的垃圾箱

		KCheckType(int value) {
			this.value = value;
		}

		private int value;

		public int getValue() {
			return value;
		}

	}

	/**
	 * 文件属性类型
	 */
	public enum KFilePropertyType {
		fullText, // 全文
		title, // 标题
		author, // 作者
		keyword; // 关键词
	}

	/**
	 * 消息类型
	 */
	public enum KMessageType {
		loginSuccess, // 登录成功
		loginFail, // 登录失败（用户名或密码错误）
		checkNotPass; // 审核未通过
	}

	/**
	 * 文件可见类型
	 */
	public interface KFileVisibleType {
		// 文件公有还是私有
		public static final String PUBLIC_FILE = "公有";
		// public static final String PRIVATEFILE = "私有";
	}

	/**
	 * 支持上传的文件格式
	 */
	public enum KFileFormatType {
		doc, xls, ppt, pdf, txt;
	}
	
	/**
	 * 检索类型
	 */
	public enum KSearchType {
		accurate, // 精确的
		fuzzy, // 模糊的
		prefix; // 前缀
	}

	/**
	 * 时间单位
	 */
	public enum KDateType {
		year, month, week, day, hour, minute, second;
	}

	public static String ErrMessage;
}
