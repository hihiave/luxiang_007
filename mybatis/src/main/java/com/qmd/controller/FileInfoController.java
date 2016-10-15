package com.qmd.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qmd.model.FileInfo;
import com.qmd.serviceimpl.FileInfoServiceImpl;

@Controller
@RequestMapping("/qmd")
public class FileInfoController {

	@Autowired
	FileInfoServiceImpl fileInfoServiceImpl;

	@RequestMapping("/fy")
	public String showFileInfo(int fileId, HttpServletRequest request) {
		System.out.println("=====================");
		FileInfo fileInfo = fileInfoServiceImpl.getFileInfoById(fileId);
		request.setAttribute("fileInfo", fileInfo);
		return "showFileInfo";
		// System.out.println;
	}

}
