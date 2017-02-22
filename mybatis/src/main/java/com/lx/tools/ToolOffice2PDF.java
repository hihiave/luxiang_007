package com.lx.tools;

import java.io.File;
import java.util.Date;

import org.artofsolving.jodconverter.OfficeDocumentConverter;
import org.artofsolving.jodconverter.office.DefaultOfficeManagerConfiguration;
import org.artofsolving.jodconverter.office.OfficeManager;

import com.lx.macrofiles.MacroConstant;

/**
 * 这是一个工具类，主要是为了使Office2003-2007全部格式的文档(.doc|.docx|.xls|.xlsx|.ppt|.pptx)
 * 转化为pdf文件<br>
 */
public class ToolOffice2PDF {

	public static void main(String[] args) {
		ToolOffice2PDF office2pdf = new ToolOffice2PDF();
		office2pdf.openOfficeToPDF("C:\\temp\\1487577137.doc", "D:\\temp\\fefefe.pdf");
		//office2pdf.openOfficeToPDF("e:/test." + OFFICE_PPTX, null);
	}

	// 使Office2003-2007全部格式的文档(.doc|.docx|.xls|.xlsx|.ppt|.pptx) 转化为pdf文件<br>
	// 源文件路径，如："e:/test.docx" ----> 目标文件路径，如："e:/test_docx.pdf"
	public boolean openOfficeToPDF(String inputFilePath, String outputFilePath) {
		return office2pdf(inputFilePath, outputFilePath);
	}

	// 跨平台性
//	public String getOfficeHome() {
//		String osName = System.getProperty("os.name");
//		if (Pattern.matches("Linux.*", osName)) {
//			return "/opt/openoffice.org3";
//		} else if (Pattern.matches("Windows.*", osName)) {
////			return "C:/Program Files (x86)/OpenOffice.org 3";
//			return "C:/Program Files (x86)/OpenOffice 4";
//		} else if (Pattern.matches("Mac.*", osName)) {
//			return "/Application/OpenOffice.org.app/Contents";
//		}
//		return null;
//	}

	// 连接OpenOffice.org 并且启动OpenOffice.org
	public OfficeManager getOfficeManager() {
		DefaultOfficeManagerConfiguration config = new DefaultOfficeManagerConfiguration();
		config.setOfficeHome(MacroConstant.officeHome);
		// 启动OpenOffice的服务
		OfficeManager officeManager = config.buildOfficeManager();
		officeManager.start();
		return officeManager;
	}

	// 2
	public void converterFile(File inputFile, String outputFilePath_end, String inputFilePath, String outputFilePath,
			OfficeDocumentConverter converter) {
		File outputFile = new File(outputFilePath_end);
		// 假如目标路径不存在,则新建该路径
		if (!outputFile.getParentFile().exists()) {
			outputFile.getParentFile().mkdirs();
		}
		converter.convert(inputFile, outputFile);
		System.out.println("文件：" + inputFilePath + "\n转换为\n目标文件：" + outputFile + "\n成功！");
	}

	// 1
	public boolean office2pdf(String inputFilePath, String outputFilePath) {
		boolean flag = false;
		OfficeManager officeManager = getOfficeManager();
		// 连接OpenOffice
		OfficeDocumentConverter converter = new OfficeDocumentConverter(officeManager);
		long begin_time = new Date().getTime();
		if (null != inputFilePath) {
			File inputFile = new File(inputFilePath);
			// 判断目标文件路径是否为空
			if (null == outputFilePath) {
				// 转换后的文件路径
				String outputFilePath_end = getOutputFilePath(inputFilePath);
				if (inputFile.exists()) {// 找不到源文件, 则返回
					converterFile(inputFile, outputFilePath_end, inputFilePath, outputFilePath, converter);
					flag = true;
				}
			} else {
				if (inputFile.exists()) {// 找不到源文件, 则返回
					converterFile(inputFile, outputFilePath, inputFilePath, outputFilePath, converter);
					flag = true;
				}
			}
			officeManager.stop();
		} else {
			System.out.println("con't find the resource");
		}
		long end_time = new Date().getTime();
		System.out.println("文件转换耗时：[" + (end_time - begin_time) + "]ms");
		return flag;
	}

	// 获取输出文件
	public String getOutputFilePath(String inputFilePath) {
		return inputFilePath.replaceAll("." + getPostfix(inputFilePath), ".pdf");
	}

	// 获取inputFilePath的后缀名，如："e:/test.pptx"的后缀名为："pptx"
	public String getPostfix(String inputFilePath) {
		return inputFilePath.substring(inputFilePath.lastIndexOf(".") + 1);
	}

}
