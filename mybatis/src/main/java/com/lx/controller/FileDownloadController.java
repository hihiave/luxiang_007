package com.lx.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lx.macrofiles.MacroConstant;
import com.lx.macrofiles.MacroEnum.KFileFormatType;
import com.lx.model.FileInfo;
import com.lx.service.FileInfoService;
import com.lx.tools.ToolString;

@Controller
@RequestMapping("/FileDownloadController")
public class FileDownloadController {

	private static Logger logger = Logger.getLogger(FileDownloadController.class);

	@Autowired
	FileInfoService fileInfoService;

	@RequestMapping("/fileDownload")
	protected void fileDownload(HttpServletRequest request, HttpServletResponse response) {
		logger.info("=================fileDownload==================");
		response.setCharacterEncoding("UTF-8");

		String filePath = ""; // 下载文件的地址
		FileInfo fileInfo = fileInfoService.getFileByFileId(Integer.parseInt(request.getParameter("fileid"))).get(0);
		int fileUploadTime = fileInfo.getFileUploadTime();
		switch (fileInfo.getFileStatus()) {
		case MacroConstant.PDF:
			filePath = MacroConstant.PDF_DIR + fileUploadTime + "." + KFileFormatType.pdf;
			break;
		case MacroConstant.DOC:
			filePath = MacroConstant.WORD_DIR + fileUploadTime + "." + KFileFormatType.doc;
			break;
		case MacroConstant.DOCX:
			filePath = MacroConstant.WORD_DIR + fileUploadTime + "." + KFileFormatType.docx;
			break;
		case MacroConstant.PPT:
			filePath = MacroConstant.PPT_DIR + fileUploadTime + "." + KFileFormatType.ppt;
			break;
		case MacroConstant.PPTX:
			filePath = MacroConstant.PPT_DIR + fileUploadTime + "." + KFileFormatType.pptx;
			break;
		case MacroConstant.XLSX:
			filePath = MacroConstant.EXCEL_DIR + fileUploadTime + "." + KFileFormatType.xlsx;
			break;
		case MacroConstant.WPS:
			filePath = MacroConstant.WPS_DIR + fileUploadTime + "." + KFileFormatType.wps;
			break;
		case MacroConstant.DPS:
			filePath = MacroConstant.WPS_DIR + fileUploadTime + "." + KFileFormatType.dps;
			break;
		case MacroConstant.ET:
			filePath = MacroConstant.WPS_DIR + fileUploadTime + "." + KFileFormatType.et;
			break;
		default:
			break;
		}

		String downloadName = fileInfo.getFileName() + "." + ToolString.getFilenameExtension(filePath);

		try {
			// 判定资源是否存在
			if (!new File(filePath).exists()) {
				request.getSession().setAttribute("message", "noexist");
				response.sendRedirect(request.getHeader("Referer"));
				return;
			}

			// 设置响应头
			response.setHeader("content-disposition",
					"attachment;filename=" + URLEncoder.encode(downloadName, "UTF-8"));
			// 读取要下载的文件 保存到文件输入流
			FileInputStream in = new FileInputStream(filePath);
			// 创建输出流
			OutputStream out = response.getOutputStream();
			byte buffer[] = new byte[1024];
			int len = 0;
			while ((len = in.read(buffer)) > 0) {
				out.write(buffer, 0, len);
			}
			in.close();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// fileUrl = new String(fileUrl.getBytes("ISO8859_1"), "UTF-8");

		// String fileUrl = request.getParameter("filename");
		// datadir\publicdir\1487753866.pdf
	}

}
