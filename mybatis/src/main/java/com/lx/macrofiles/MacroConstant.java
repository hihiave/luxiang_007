package com.lx.macrofiles;

public interface MacroConstant {

	// **********路径常量**********
	/* 绝对路径 */
	public static final String TEMP = "C:\\temp\\"; // 文件上传和存放txt的临时目录

	public static final String PDF_DIR = "C:\\lucene\\datadir\\pdfdir\\"; // pdf目录
	public static final String WORD_DIR = "C:\\lucene\\datadir\\worddir\\"; // word目录
	public static final String PPT_DIR = "C:\\lucene\\datadir\\pptdir\\"; // ppt目录
	public static final String EXCEL_DIR = "C:\\lucene\\datadir\\exceldir\\"; // excel目录
	public static final String WPS_DIR = "C:\\lucene\\datadir\\wpsdir\\"; // wps目录

	public static final String INDEX_DIR = "C:\\lucene\\luceneindex\\"; // 索引目录

	// pdf-->txt 转换器的路径
	public static final String ConvertorPATH = "C:\\lucene\\xpdf\\bin32\\pdftotext";

	/* 相对路径 */
	public static final String PUBLIC_DIR = "datadir\\publicdir\\"; // 公有预览目录，全是PDF，用于建立索引
	public static final String PRIVATE_DIR = "datadir\\privatedir\\"; // 私有预览目录，全是PDF，不用于建立索引

	// **********文档类型常量**********
	public static final int PDF = 1; // PDF file_status = 1
	public static final int DOC = 2; // DOC file_status = 2
	public static final int DOCX = 3; // DOC file_status = 3
	public static final int PPT = 4; // DOC file_status = 4
	public static final int PPTX = 5; // DOC file_status = 5
	public static final int XLSX = 6; // DOC file_status = 6
	public static final int WPS = 7; // DOC file_status = 7
	public static final int DPS = 8; // DOC file_status = 8
	public static final int ET = 9; // DOC file_status = 9

	// **********Document中的field域**********
	public static final String DOC_ID = "id"; // <id:~>
	public static final String DOC_NAME = "fileName";// <fileName:一种N层体系结构下的Web挖掘应用>
	public static final String DOC_TYPE = "type"; // <id:1><type:pdf>
	public static final String DOC_URL = "fileUrl"; // <url:~>
	public static final String DOC_KEYWORDS = "fileKeywords"; // <fileKeywords:~>
	public static final String DOC_DESC = "fileDesc"; // <fileCategory:~>
	public static final String DOC_CONTENTS = "contents"; // <contents:~>
	public static final int DOC_CONTEXT_NUM = 5000; // 关键词文本的数量
	
	// **********管理员ID常量**********
	public static final int ADMIN = 1;

	// **********文件类别常量**********
	public static final String level0_category = "1482422400"; // 0级类别,用来索引1级类别

	// **********最新一次创建索引地址常量**********
	public static final String TIME = "C:\\lucene\\indextime\\time.txt";

	// **********工具目录常量**********
	public static final String OFFICE_Home = "C:/Program Files (x86)/OpenOffice 4";
	// public static final String OFFICE_Home = "C:/Program Files/OpenOffice 4";

}
