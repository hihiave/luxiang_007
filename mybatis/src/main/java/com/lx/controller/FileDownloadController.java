package com.lx.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lx.model.FileInfo;
import com.lx.service.FileInfoService;

@Controller
@RequestMapping("/FileDownloadController")
public class FileDownloadController {

	@Autowired
	FileInfoService fileInfoService;

	@RequestMapping("/fileDownload")
	protected void fileDownload(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		String basePath = request.getSession().getServletContext().getRealPath("");
		FileInfo fileInfo = fileInfoService.getFileByFileId(Integer.parseInt(request.getParameter("fileid")));
		// 获取下载文件的url
		String fileUrl = basePath + request.getParameter("filename");
		
		System.out.println("=============fileUrl=====xiazai==========" + fileUrl);
		// String fileNameFull = fileUrl.substring(fileUrl.lastIndexOf("/") +
		// 1);
		String fileNameExtension = fileUrl.substring(fileUrl.lastIndexOf(".") + 1);
		String downloadName = fileInfo.getFileName() + "." + fileNameExtension;
		fileUrl = new String(fileUrl.getBytes("ISO8859_1"), "UTF-8");
		
		
		// 判定资源是否存在
		if (!new File(fileUrl).exists()) {
			request.getSession().setAttribute("message", "noexist");
			response.sendRedirect(request.getHeader("Referer"));
			return;
		}
		// 设置响应头
		response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(downloadName, "UTF-8"));
		// 读取要下载的文件 保存到文件输入流
		FileInputStream in = new FileInputStream(fileUrl);
		// 创建输出流
		OutputStream out = response.getOutputStream();
		byte buffer[] = new byte[1024];
		int len = 0;
		while ((len = in.read(buffer)) > 0) {
			out.write(buffer, 0, len);
		}
		in.close();
		out.close();
	}

}
