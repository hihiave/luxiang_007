package com.lx.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lx.service.MyDownloadService;

@Controller
@RequestMapping("/MyDownloadController")
public class MyDownloadController {

	@Autowired
	MyDownloadService myDownloadService;
	
	/**
	 * 删除我的下载记录
	 */
	public Map<String, Object> delMyDownload(HttpSession httpSession) {
		
		//  myDownloadIds 我的下载记录的id号, 批量的
		myDownloadService.delMyDownload(myDownloadIds);
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		return map;
	}
	
	/**
	 * 获取我的下载记录
	 */
	public Map<String, Object> getMyDownload() {
		
		//  userName 用户名
		//   page 分页
		
		myDownloadService.getMyDownload(userName, page);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		return map;
	}
}
