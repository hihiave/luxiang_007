package com.lx.macrofiles;

public interface MacroConstant {

	// **********路径常量**********
	/* 绝对路径 */
	public static final String TEMP = "C:\\temp\\"; // 存放txt的临时目录

	public static final String INDEXDIR = "C:\\lucene\\luceneindex\\"; // 索引目录

	// pdf-->txt 转换器的路径
	public static final String ConvertorPATH = "C:\\lucene\\xpdf\\bin32\\pdftotext";

	/* 相对路径 */
	public static final String PDFDIR = "datadir\\pdfdir\\"; // pdf目录

	public static final String DOCDIR = "datadir\\worddir\\"; // doc目录

	public static final String DOC_TEMP = "datadir\\temp\\"; // 预览目录

	// **********工具目录常量**********
	public static final String openOfficeHome = "C:/Program Files (x86)/OpenOffice 4/program/";
	// public static final String openOfficeHome = "C:/Program Files/OpenOffice 4/program/";
	public static final String openOfficeHomeCmd = "soffice.exe -headless -accept=\"socket,host=127.0.0.1,port=8100;urp;\" -nofirststartwizard";

	// **********类别常量**********
	public static final String level0_category = "1482422400"; // 0级类别,用来索引1级类别

	// **********ID常量**********
	public static final int ADMIN = 1;

}
