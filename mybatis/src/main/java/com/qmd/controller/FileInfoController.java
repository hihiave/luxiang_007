package com.qmd.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lx.model.FileInfo;
import com.lx.service.FileInfoService;

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
	
	@RequestMapping(value = "/privatefile",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> privatefile(HttpSession httpSession,HttpServletRequest httpServletRequest){
		Map<String,Object> map = new HashMap<String, Object>();
		
		FileInfo fileInfo1 = new FileInfo();
		fileInfo1.setFileName("XXX动员大会 ");	
		fileInfo1.setFileUploadTime(2012);
		
		FileInfo fileInfo2 = new FileInfo();
		fileInfo2.setFileName("关于深度开展XXX的研讨");
		fileInfo2.setFileUploadTime(2011);
	
		
		FileInfo fileInfo3 = new FileInfo();
		fileInfo3.setFileName("全国人民代表大会");
		fileInfo3.setFileUploadTime(2010);
		
		FileInfo fileInfo4 = new FileInfo();
		fileInfo4.setFileName("政协常务会议");
		fileInfo4.setFileUploadTime(2016);
		
		List<FileInfo> pri_file=new ArrayList<FileInfo>();
		pri_file.add(fileInfo1);
		pri_file.add(fileInfo2);
		pri_file.add(fileInfo3);
		pri_file.add(fileInfo4);
		map.put("pri_file", pri_file);
		return map;
	}

	
	
	@RequestMapping(value = "/publicfile",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> publicfile(HttpSession httpSession,HttpServletRequest httpServletRequest){
		Map<String,Object> map = new HashMap<String, Object>();
		FileInfo fileInfo1 = new FileInfo();
		fileInfo1.setFileCategory("专利类");
		fileInfo1.setFileName("哈利波特");
		fileInfo1.setFileAuthor("王小二");
		fileInfo1.setFileUploadTime(2012);
		fileInfo1.setFileDownloadCount(22);
		
		FileInfo fileInfo2 = new FileInfo();
		fileInfo2.setFileCategory("期刊类");
		fileInfo2.setFileName("蜘蛛侠");
		fileInfo2.setFileAuthor("王小三");
		fileInfo2.setFileUploadTime(2011);
		fileInfo2.setFileDownloadCount(23);
		
		FileInfo fileInfo3 = new FileInfo();
		fileInfo3.setFileCategory("论文类");
		fileInfo3.setFileName("钢铁侠");
		fileInfo3.setFileAuthor("王小四");
		fileInfo3.setFileUploadTime(2010);
		fileInfo3.setFileDownloadCount(24);
		
		FileInfo fileInfo4 = new FileInfo();
		fileInfo4.setFileCategory("专利类");
		fileInfo4.setFileName("蝙蝠侠");
		fileInfo4.setFileAuthor("王小五");
		fileInfo4.setFileUploadTime(2016);
		fileInfo4.setFileDownloadCount(25);
		
		List<FileInfo> pub_file=new ArrayList<FileInfo>();
		pub_file.add(fileInfo1);
		pub_file.add(fileInfo2);
		pub_file.add(fileInfo3);
		pub_file.add(fileInfo4);
		map.put("pub_file", pub_file);
		return map;
	}

}
