package com.lx.tools;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 文件转移
 */
public class ToolFileTransfer {

	private static String toFilePath;

	/** 文件转移
	 * @author luxiang
	 * @param fromFilePath 起始文件路径,例:C:/Users/1479709800.doc
	 * @param toDirPath 目的目录路径,例:D:/SWFTools
	 * @return boolean,成功true;失败false
	 */
	public static boolean transfer(String fromFilePath, String toDirPath) {
		boolean flag = true;
		// new File("fromFilePath").getName();
		toFilePath = toDirPath + "/" + fromFilePath.substring(fromFilePath.lastIndexOf("/") + 1);

		InputStream in = null;
		FileOutputStream out = null;
		try {
			in = new FileInputStream(fromFilePath);
			out = new FileOutputStream(toFilePath);
			byte buffer[] = new byte[1024];
			int len = 0;
			while ((len = in.read(buffer)) > 0) {
				out.write(buffer, 0, len);
			}
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				flag = false;
				e.printStackTrace();
			}
		}
		return flag;
	}

	/**
	 * 返回目的文件路径,例:D:/SWFTools/1479709800.doc
	 */
	public static String getToFilePath() {
		return toFilePath;
	}
	
}
