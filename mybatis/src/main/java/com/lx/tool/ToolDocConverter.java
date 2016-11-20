package com.lx.tool;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;

// doc -- > pdf
public class ToolDocConverter {
	private String filePath;// (doc文件的位置)
	private String outputPath;// swf 文件的输出位置
	private String fileName;
	private File docFile;
	private File pdfFile;
	private File swfFile;

	/**
	 * 构造函数
	 */
	public ToolDocConverter(String filePath) {
		init(filePath);
		System.out.println("文件路径:" + filePath);
	}

	/**
	 * 重置file
	 * 
	 * @param filePath
	 */
	public void setFile(String filePath) {
		init(filePath);
	}

	/**
	 * 初始化
	 * 
	 * @param filePath
	 */
	private void init(String filePath) {
		this.filePath = filePath;
		fileName = filePath.substring(0, filePath.lastIndexOf("."));
		System.out.println("++++++++++++++" + fileName);
		docFile = new File(filePath);
		pdfFile = new File(fileName + ".pdf");
		swfFile = new File(fileName + ".swf");
		System.out.println(docFile.getName());
	}

	/**
	 * 转为pdf
	 * 
	 * @throws Exception
	 */
	private void doc2pdf() throws Exception {
		if (docFile.exists()) {
			if (!pdfFile.exists()) {
				String officeHome = "C:/Program Files (x86)/OpenOffice 4/program/";
				String command = officeHome
						+ "soffice.exe -headless -accept=\"socket,host=127.0.0.1,port=8100;urp;\" -nofirststartwizard";
				System.out.println(command);
				Process pro = Runtime.getRuntime().exec(command);
                System.out.println(command);
				OpenOfficeConnection connection = new SocketOpenOfficeConnection(8100);// 创建一个连接对象
                System.out.println(command);
				try {
					connection.connect();// 建立连接
					DocumentConverter converter = new OpenOfficeDocumentConverter(connection);// 创建一个文件转换器
					converter.convert(docFile, pdfFile);
					connection.disconnect();// 关闭连接
					pro.destroy();
					System.out.println("**********pdf文件转换成功,存放于:" + pdfFile.getPath() + "************");
				} catch (java.net.ConnectException e) {
					e.printStackTrace();
					System.out.println("******openoffice服务未启动 连接失败");
					throw e;
				} catch (com.artofsolving.jodconverter.openoffice.connection.OpenOfficeException e) {
					e.printStackTrace();
					System.out.println("******openoffice服务器异常 读取文件失败");
					throw e;
				} catch (Exception e) {
					e.printStackTrace();
					throw e;
				}
			} else {
				System.out.println("*********已经转换为pdf，不需转换*******");
			}
		} else {
			System.out.println("*******doc文件不存在 转换失败");
		}
	}

	/**
	 * 转换为swf
	 * 
	 * @throws Exception
	 */
	private void pdf2swf() throws Exception {
		Runtime r = Runtime.getRuntime();
		if (!swfFile.exists()) {
			if (pdfFile.exists()) {
				try {
					String swftoolsHome = "D:/SWFTools/pdf2swf.exe ";
					String command = swftoolsHome + pdfFile.getPath() + " -o " + swfFile.getPath()
							+ " -s flashversion=9";
					// String command="F:\\SWFTools\\2.bat";
					System.out.println(command);
					// "F:\\SWFTools\\pdf2swf.exe "+pdfFile.getName()+" -o
					// "+swfFile.getName()
					Process p = r.exec(command);
					System.out.println(loadStream(p.getInputStream()));
					System.err.println(loadStream(p.getErrorStream()));
					System.out.println(loadStream(p.getInputStream()));
					System.err.println("*****swf文件转换成功 文件位置：" + swfFile.getPath() + "*******");
					// if(pdfFile.exists()){
					// pdfFile.delete();
					// }
				} catch (IOException e) {
					e.printStackTrace();
					throw e;
				}
			} else {
				System.out.println("********pdf文件不存在 转换失败");
			}
		} else {
			System.out.println("*********swf文件已存在 不用转换");
		}
	}

	static String loadStream(InputStream in) throws IOException {
		int ptr = 0;
		in = new BufferedInputStream(in);
		StringBuffer buffer = new StringBuffer();
		while ((ptr = in.read()) != -1) {
			buffer.append((char) ptr);
		}
		return buffer.toString();
	}

	/**
	 * 转换主方法
	 */
	public boolean convert() {
		if (swfFile.exists()) {
			System.out.println("转换器已经开始工作 文件已经转换为swf文件");
			return true;
		}
		try {
			doc2pdf();
			pdf2swf();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		if (swfFile.exists()) {
			System.out.println("swfFile存在");
			return true;
		} else {
			System.out.println("swfFile不存在");
			return false;
		}
	}

	public String getSwfFilePath() {
		if (this.swfFile.exists()) {
			String tempPath = swfFile.getPath();
			tempPath = tempPath.replaceAll("\\\\", "/");
			System.out.println("路径为:" + tempPath);
			return tempPath;
		} else
			return "文件不存在";
	}

	public void setOutputPath(String outputPath) {
		this.outputPath = outputPath;
		if (!outputPath.equals("")) {
			String realName = fileName.substring(fileName.lastIndexOf("/"), fileName.lastIndexOf("."));
			if (outputPath.charAt(outputPath.length()) == '/') {
				swfFile = new File(outputPath + realName + ".swf");
			} else {
				swfFile = new File(outputPath + realName + ".swf");
			}
		}
	}

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