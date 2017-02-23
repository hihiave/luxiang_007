package com.lx.tools;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.lx.macrofiles.MacroConstant;
import com.lx.macrofiles.MacroEnum.KFileFormatType;
import com.lx.model.FileInfo;

public class ToolDeleteDoc {

	// 删除预览文件
	public static boolean deletePreviewFile(List<FileInfo> fileInfos, HttpServletRequest request) {
		boolean flag = false;

		String basePath = request.getSession().getServletContext().getRealPath("");
		String filePath = "";

		File file = null;
		for (int i = 0; i < fileInfos.size(); i++) {
			filePath = basePath + fileInfos.get(i).getFileUrl();
			file = new File(filePath);
			if (file.exists() && file.isFile()) {
				flag = file.delete(); // 删除tomcat里的用来预览的PDF文件
			}
		}
		return flag;
	}

	// 删除资源文件
	public static boolean deleteResourceFile(List<FileInfo> fileInfos) {
		boolean flag = false;
		String filePath = "";
		FileInfo fileInfo = null;
		File file = null;
		for (int i = 0; i < fileInfos.size(); i++) {
			fileInfo = fileInfos.get(i);
			switch (fileInfo.getFileStatus()) {
			case MacroConstant.PDF:
				filePath = MacroConstant.PDF_DIR + fileInfo.getFileUploadTime() + "." + KFileFormatType.pdf;
				break;
			case MacroConstant.DOC:
				filePath = MacroConstant.WORD_DIR + fileInfo.getFileUploadTime() + "." + KFileFormatType.doc;
				break;
			case MacroConstant.DOCX:
				filePath = MacroConstant.WORD_DIR + fileInfo.getFileUploadTime() + "." + KFileFormatType.docx;
				break;
			case MacroConstant.PPT:
				filePath = MacroConstant.PPT_DIR + fileInfo.getFileUploadTime() + "." + KFileFormatType.ppt;
				break;
			case MacroConstant.PPTX:
				filePath = MacroConstant.PPT_DIR + fileInfo.getFileUploadTime() + "." + KFileFormatType.pptx;
				break;
			case MacroConstant.XLSX:
				filePath = MacroConstant.EXCEL_DIR + fileInfo.getFileUploadTime() + "." + KFileFormatType.xlsx;
				break;
			default:
				break;
			}

			file = new File(filePath);
			if (file.exists() && file.isFile()) {
				flag = file.delete(); // 删除tomcat里的用来预览的PDF文件
			}

		}
		return flag;
	}

}
