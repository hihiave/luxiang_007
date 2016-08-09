package com.lx.tool;

import java.security.MessageDigest;

public class ToolEncryption {

	// MD5加密。32位
	public static String EncryptMD5(String inStr) {
		MessageDigest md5 = null;
		byte[] byteArray = null;

		try {
			md5 = MessageDigest.getInstance("MD5");
			byteArray = inStr.getBytes("UTF-8");
		} catch (Exception e1) {
			e1.printStackTrace();
			return "";
		}
		byte[] md5Bytes = md5.digest(byteArray);

		StringBuffer hexValue = new StringBuffer();

		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16)
				hexValue.append("0");
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();
	}

}
