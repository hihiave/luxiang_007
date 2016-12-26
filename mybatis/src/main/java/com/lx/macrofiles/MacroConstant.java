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

	// **********目录常量**********
	public static final String level0_category = "1482422400"; // 0级类别,用来索引1级类别

}
