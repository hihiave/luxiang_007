package com.lx.tools;

public class ToolString {

	/** 获取文件全名
	 * @author luxiang
	 * @param filePath 文件路径 例 : C:\haha\呵呵.doc
	 * @return 呵呵.doc
	 */
	public static String getFilenameFull(String filePath) {
		return filePath.substring(filePath.lastIndexOf("\\") + 1);
	}

	/** 获取文件名
	 * @author luxiang
	 * @param filenameFull 文件全名  例 : 呵呵.doc
	 * @return 呵呵
	 */
	public static String getFilename(String filenameFull) {
		return filenameFull.substring(0, filenameFull.lastIndexOf("."));
	}

	/** 获取文件扩展名
	 * @author luxiang
	 * @param filenameFull 文件全名  例 : 呵呵.doc
	 * @return doc
	 */
	public static String getFilenameExtension(String filenameFull) {
		return filenameFull.substring(filenameFull.lastIndexOf(".") + 1);
	}

}
