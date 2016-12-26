package com.lx.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import com.lx.macrofiles.MacroEnum;
import com.lx.macrofiles.SystemConstant;

public class XpdfParser {

	/**
	 * 获取由pdf-->txt文档后文档内容
	 */
	public static String getPDFFileContents(String filePath, String filename) {
		System.out.println("====================1====================");

		String txtCanonicalPath = xpdfParser(filePath, filename);
		if (txtCanonicalPath == null)
			return null;

		System.out.println("===========txtCanonicalPath======" + txtCanonicalPath);

		// 等待pdf-->txt的进程执行完后,txt路径下的txt文档才存在.
		// 不然在读取txt内容时会报文件不存在异常.
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
			MacroEnum.ErrMessage = "线程中断错误!";
		}
		return getTxtContents(txtCanonicalPath);
	}

	/**
	 * pdf转换txt,获取txt文档全路径
	 */
	private static String xpdfParser(String filePath, String filename) {
		System.out.println("-----------------------------xpdfParser----------------------");
		File file = new File(SystemConstant.TEMP);
		if (!file.exists())
			file.mkdirs();

		XpdfParams xparam = new XpdfParams();
		// xparam.setLayout("-layout");
		xparam.setConvertor(SystemConstant.ConvertorPATH);
		xparam.setEncoding("-enc UTF-8");
		xparam.setSource(filePath);
		xparam.setTarget(SystemConstant.TEMP + filename + ".txt");
		String command = xparam.getCommand();
		// String target = xparam.getTarget();
		try {
			System.out.println("------command------1------------" + command);
			Runtime.getRuntime().exec(command);
		} catch (IOException e) {
			e.printStackTrace();
			MacroEnum.ErrMessage = "pdf转换txt错误!";
			System.out.println("------------pdf转换txt错误!------------");
			return null;
		}
		return xparam.getTarget();
	}

	/**
	 * 获取txt文档内容
	 */
	private static String getTxtContents(String txtCanonicalPath) {
		System.out.println("=================getTxtContents================" + txtCanonicalPath);
		StringBuffer stringBuffer = new StringBuffer();
		try {
			String line = "";
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(new FileInputStream(txtCanonicalPath), "UTF-8"));
			line = reader.readLine();
			while (line != null) {
				if (line.length() > 0) {
					stringBuffer.append(line.trim());
					// strBuffer.append("\n");
				}
				line = reader.readLine();
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
			MacroEnum.ErrMessage = "获取txt文档内容错误!";
			System.out.println("=====================获取txt文档内容错误!====================");
			return null;
		}
		// if(strBuffer.toString().length()<=500)
		return stringBuffer.toString();
		// else return strBuffer.toString().substring(0,500);
	}

}
