package com.lx.macrofiles;

public interface MacroConstant {

	// **********路径常量**********
	/* 绝对路径 */
	public static final String TEMP = "C:\\temp\\"; // 文件上传和存放txt的临时目录

	public static final String PDF_DIR = "C:\\lucene\\datadir\\pdfdir\\"; // pdf目录
	public static final String WORD_DIR = "C:\\lucene\\datadir\\worddir\\"; // word目录
	public static final String PPT_DIR = "C:\\lucene\\datadir\\pptdir\\"; // ppt目录
	public static final String EXCEL_DIR = "C:\\lucene\\datadir\\exceldir\\"; // excel目录

	public static final String INDEXDIR = "C:\\lucene\\luceneindex\\"; // 索引目录

	// pdf-->txt 转换器的路径
	public static final String ConvertorPATH = "C:\\lucene\\xpdf\\bin32\\pdftotext";

	/* 相对路径 */
	public static final String PUBLIC_DIR = "datadir\\publicdir\\"; // 公有预览目录，全是PDF，用于建立索引
	public static final String PRIVATE_DIR = "datadir\\privatedir\\"; // 私有预览目录，全是PDF，不用于建立索引

	// **********工具目录常量**********
	// public static final String openOfficeHome = "C:/Program Files
	// (x86)/OpenOffice 4/program/";
	// public static final String openOfficeHome = "C:/Program Files/OpenOffice
	// 4/program/";
	// public static final String openOfficeHomeCmd = "soffice.exe -headless
	// -accept=\"socket,host=127.0.0.1,port=8100;urp;\" -nofirststartwizard";

	public static final String officeHome = "C:/Program Files (x86)/OpenOffice 4";
	// public static final String officeHome = "C:/Program Files/OpenOffice 4";

	// **********类别常量**********
	public static final String level0_category = "1482422400"; // 0级类别,用来索引1级类别

	// **********ID常量**********
	public static final int ADMIN = 1;

	public static final int PDF = 1; // PDF file_status = 1
	public static final int DOC = 2; // DOC file_status = 2
	public static final int DOCX = 3; // DOC file_status = 3
	public static final int PPT = 4; // DOC file_status = 4
	public static final int PPTX = 5; // DOC file_status = 5
	public static final int XLSX = 6; // DOC file_status = 6

	// **********最新一次创建索引地址常量**********
	public static final String TIME = "C:\\lucene\\indextime\\time.txt";

}
