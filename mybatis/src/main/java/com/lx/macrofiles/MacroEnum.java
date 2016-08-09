package com.lx.macrofiles;

public class MacroEnum {

	/**
	 * 时间单位
	 */
	public enum KDateType {
		Year, Month, Week, Day, Hour, Minute, Second;
	}

	/**
	 * 用户是否通过审核
	 */
	public interface KUserCheckType {
		public static final int pass = 1;
		public static final int not_pass = 0;
	}

	/**
	 * 消息类型
	 */
	public enum KMessageType {
		OldPwdErr, AlterErr
	}
}
