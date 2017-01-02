package com.lx.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lx.macrofiles.MacroConstant;
import com.lx.macrofiles.MacroEnum;
import com.lx.model.FileInfo;
import com.lx.service.FileInfoService;
import com.lx.tools.ToolString;

@Controller
@RequestMapping("/FileDownloadController")
public class FileDownloadController {

	@Autowired
	FileInfoService fileInfoService;

	@RequestMapping("/fileDownload")
	protected void fileDownload(HttpServletRequest request, HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		String basePath = request.getSession().getServletContext().getRealPath("");

		// datadir\pdfdir\1483366139.pdf or datadir\temp\1483364067.pdf
		String fileUrl = request.getParameter("filename");

		// 获取下载文件的地址
		String filePath = basePath + fileUrl;

		FileInfo fileInfo = fileInfoService.getFileByFileId(Integer.parseInt(request.getParameter("fileid")));

		if (fileInfo.getFileStatus() == MacroEnum.DOC) {
			filePath = basePath + MacroConstant.DOCDIR + ToolString.getFilename(ToolString.getFilenameFull(fileUrl))
					+ ".doc";
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
	}

}
