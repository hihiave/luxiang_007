package com.lx.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 文件转移
 */
public class ToolFileTransfer {
	private String savePath;// 转移地址
	private String from;// 源文件地址
	private File file;// 文件类型
	private String fileName;// 文件名称

	public ToolFileTransfer(String savePath, String from) {
		init(savePath, from);
	}

	private boolean init(String savePath, String from) {
		this.savePath = savePath;
		this.from = from;
		this.fileName = from.substring(from.lastIndexOf("/") + 1);
		System.out.println(fileName);
		this.file = new File(from);
		if (file.exists()) {
			System.out.println("文件存在");
			return true;
		} else {
			System.out.println("文件不存在");
			return false;
		}
	}

	public void transfer() {
		InputStream in = null;
		FileOutputStream out = null;
		try {
			in = new FileInputStream(this.from);
			out = new FileOutputStream(this.savePath + "/" + this.fileName);
			byte buffer[] = new byte[1024];
			int len = 0;

			while ((len = in.read(buffer)) > 0) {
				out.write(buffer, 0, len);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
				if (out != null) {
					out.close();
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public String getFileName() {
		return this.fileName;
	}

	public String getSavePath() {
		return this.savePath + "/" + this.fileName;
	}

	// public static void main(String[] args){
	// String savePath="F:/SWFTools/nice.swf";
	//// String from="G:/Users/qmd/hahaha.pdf";
	//// FileTransfer fileTransfer=new FileTransfer(savePath,from);
	//// fileTransfer.transfer();
	//// System.out.println(fileTransfer.getFileName());
	//// System.out.println(fileTransfer.getSavePath());
	//
	// System.out.println(savePath.substring(savePath.lastIndexOf("/")+1));
	// }
}
