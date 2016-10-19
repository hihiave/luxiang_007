package com.qmd.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qmd.model.FileInfo;
import com.qmd.service.FileInfoService;

@Controller
@RequestMapping("/qmd")
public class FileInfoController {

	@Autowired
	FileInfoService fileInfoService;

	@RequestMapping("/fy")
	public String showFileInfo(HttpServletRequest request) {
		System.out.println("=====================");
		// FileInfo fileInfo = fileInfoServiceImpl.getFileInfoById(fileId);

		FileInfo fileInfo = new FileInfo();
		fileInfo.setFileCategory("专利类");
		fileInfo.setFileName("哈利波特");
		
		
		request.setAttribute("fileInfo", fileInfo);
		
		return "showFileInfo";
		// System.out.println;
	}

}
