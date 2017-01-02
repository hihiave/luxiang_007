package com.lx.tools;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.ConnectException;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeException;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;
import com.lx.macrofiles.MacroConstant;
import com.lx.macrofiles.MacroEnum;

/**
 * 文件转换器
 */
public class ToolDocConverter {
	private static File docFile;
	private static File pdfFile;
	private static File swfFile;

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

	// docPath : "C:\\temp\\1483359477.doc"
	// pdfDir : "D:\\temp\\"
	public static boolean docToPdf(String docPath, String pdfDir) {
		boolean flag = false;
		File docFile = new File(docPath);
		File pdfFile = new File(pdfDir + ToolString.getFilename(ToolString.getFilenameFull(docPath)) + ".pdf");
		if (docFile.exists()) {
			String command = MacroConstant.openOfficeHome + MacroConstant.openOfficeHomeCmd;
			try {
				Process process = Runtime.getRuntime().exec(command);
				OpenOfficeConnection connection = new SocketOpenOfficeConnection(8100);// 创建一个连接对象
				connection.connect();// 建立连接
				DocumentConverter converter = new OpenOfficeDocumentConverter(connection);// 创建一个文件转换器
				converter.convert(docFile, pdfFile);
				connection.disconnect();// 关闭连接
				process.destroy();
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			if (pdfFile.exists()) {
				flag = true;
			}
		}
		return flag;
	}

	private static boolean doc2pdf() {
		boolean flag = true;
		String command = MacroConstant.openOfficeHome + MacroConstant.openOfficeHomeCmd;
		try {
			Process process = Runtime.getRuntime().exec(command);
			OpenOfficeConnection connection = new SocketOpenOfficeConnection(8100);// 创建一个连接对象
			connection.connect();// 建立连接
			DocumentConverter converter = new OpenOfficeDocumentConverter(connection);// 创建一个文件转换器
			converter.convert(docFile, pdfFile);
			connection.disconnect();// 关闭连接
			process.destroy();
		} catch (ConnectException e) {
			flag = false;
			MacroEnum.ErrMessage = "openoffice服务未启动,连接错误!";
			e.printStackTrace();
		} catch (OpenOfficeException e) {
			flag = false;
			MacroEnum.ErrMessage = "openoffice服务器异常,读取文件错误!";
			e.printStackTrace();
		} catch (Exception e) {
			flag = false;
			MacroEnum.ErrMessage = "doc转pdf文件错误!";
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
			MacroEnum.ErrMessage = "pdf转swf文件错误!";
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

}