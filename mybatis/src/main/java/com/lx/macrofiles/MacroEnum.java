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
		keyword // 关键词
	}

	/**
	 * 消息类型
	 */
	public enum KMessageType {
		success, // 登录成功
		fail, // 登录失败（用户名或密码错误）
		checkNotPass, // 审核未通过
		exists, // 存在
		nonExists // 不存在
	}

	/**
	 * 文件可见类型
	 */
	public enum KFileVisibleType {

		publicFile(0), // 公有文件 :0 等待审核
		privateFile(1); // 私有文件 :1 审核通过

		KFileVisibleType(int value) {
			this.value = value;
		}

		private int value;

		public int getValue() {
			return value;
		}
	}

	/**
	 * 支持上传的文件格式
	 */
	public enum KFileFormatType {
		doc, docx, ppt, pptx, xlsx, pdf
	}

	/**
	 * 检索类型
	 */
	public enum KSearchType {
		accurate, // 精确的
		fuzzy, // 模糊的
		prefix // 前缀
	}

	/**
	 * 时间单位
	 */
	public enum KDateType {
		year, month, week, day, hour, minute, second
	}

	public static String ErrMessage;
}
