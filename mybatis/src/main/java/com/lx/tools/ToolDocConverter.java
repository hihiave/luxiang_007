package com.lx.tools;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;

/**
 * 文件转换器
 */
public class ToolDocConverter {
	private static File docFile;
	private static File pdfFile;
	private static File swfFile;
	private static String errorMessage;

	/** 转换主方法 Converter
	 * @author luxiang
	 * @param filePath 文件路径,例:D:/SWFTools/1479709800.doc
	 * @return boolean,成功true;失败false
	 */
	public static boolean converter(String filePath) {
		String temp = filePath.substring(0, filePath.lastIndexOf("."));
		swfFile = new File(temp + ".swf");
		if (swfFile.exists()) {
			return true;
		}

		pdfFile = new File(temp + ".pdf");
		if (pdfFile.exists()) {
			if (pdf2swf()) {
				// pdfFile.delete();
				return true;
			}
		}

		docFile = new File(filePath);
		if (docFile.exists()) {
			if (doc2pdf()) {
				if (pdf2swf()) {
					// docFile.delete();
					// pdfFile.delete();
					return true;
				}
			}
		}
		return false;
	}

	private static boolean doc2pdf() {
		boolean flag = true;
		String openOfficeHome = "C:/Program Files (x86)/OpenOffice 4/program/";
		String command = openOfficeHome
				+ "soffice.exe -headless -accept=\"socket,host=127.0.0.1,port=8100;urp;\" -nofirststartwizard";
		try {
			Process process = Runtime.getRuntime().exec(command);
			OpenOfficeConnection connection = new SocketOpenOfficeConnection(8100);// 创建一个连接对象
			connection.connect();// 建立连接
			DocumentConverter converter = new OpenOfficeDocumentConverter(connection);// 创建一个文件转换器
			converter.convert(docFile, pdfFile);
			connection.disconnect();// 关闭连接
			process.destroy();
		} catch (java.net.ConnectException e) {
			flag = false;
			errorMessage = "openoffice服务未启动,连接错误!";
			e.printStackTrace();
		} catch (com.artofsolving.jodconverter.openoffice.connection.OpenOfficeException e) {
			flag = false;
			errorMessage = "openoffice服务器异常,读取文件错误!";
			e.printStackTrace();
		} catch (Exception e) {
			flag = false;
			errorMessage = "doc转pdf文件错误!";
			e.printStackTrace();
		}
		return flag;
	}

	private static boolean pdf2swf() {
		boolean flag = true;
		String swftoolsHome = "D:/SWFTools/pdf2swf.exe ";
		String command = swftoolsHome + pdfFile.getPath() + " -o " + swfFile.getPath() + " -s flashversion=9";
		try {
			Process process = Runtime.getRuntime().exec(command);
			loadStream(process.getInputStream());
		} catch (IOException e) {
			flag = false;
			errorMessage = "pdf转swf文件错误!";
			e.printStackTrace();
		}
		return flag;
	}

	// pdf --> swf 记载流
	private static String loadStream(InputStream in) throws IOException {
		int ptr = 0;
		in = new BufferedInputStream(in);
		StringBuffer buffer = new StringBuffer();
		while ((ptr = in.read()) != -1) {
			buffer.append((char) ptr);
		}
		return buffer.toString();
	}

	public static String getSwfFilePath() {
		return swfFile.exists() ? swfFile.getPath().replaceAll("\\\\", "/") : "swfFile不存在!";
	}

	public static String getErrorMessage() {
		return errorMessage;
	}

	// public void setOutputPath(String outputPath) {
	// this.outputPath = outputPath;
	// if (!outputPath.equals("")) {
	// String realName = fileName.substring(fileName.lastIndexOf("/"),
	// fileName.lastIndexOf("."));
	// if (outputPath.charAt(outputPath.length()) == '/') {
	// swfFile = new File(outputPath + realName + ".swf");
	// } else {
	// swfFile = new File(outputPath + realName + ".swf");
	// }
	// }
	// }

	// public static void main(String[] args) throws Exception{
	// String from="G:/Users/qmd/论文.doc";
	// String savePath="F:/SWFTools";
	// FileTransfer fileTransfer=new FileTransfer(savePath,from);
	// fileTransfer.transfer();
	// System.out.println("****************"+fileTransfer.getSavePath()+"
	// **************");
	// DocConverter docConverter=new DocConverter(fileTransfer.getSavePath());
	// docConverter.convert();
	// docConverter.getSwfFilePath();
	// System.out.println(docConverter.getSwfFilePath());
	// }

}