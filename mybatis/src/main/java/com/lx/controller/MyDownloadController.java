package com.lx.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lx.model.FileInfoVo;
import com.lx.service.MyDownloadService;
import com.lx.tools.Page;

@Controller
@RequestMapping("/MyDownloadController")
public class MyDownloadController {

	private static Logger logger = Logger.getLogger(MyDownloadController.class);

	@Autowired
	MyDownloadService myDownloadService;

	// 添加我的下载记录
	@RequestMapping(value = "/add_my_download", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addMydownload(HttpSession httpSession, Integer fileid) {
		logger.info("=================add_my_download==================");
		String userName = (String) httpSession.getAttribute("username");
		if (!myDownloadService.checkDownloadIsExist(userName, fileid)) {
			myDownloadService.addMyDownload(userName, fileid);
		}
		return null;
	}

	// 删除我的下载记录
	@RequestMapping(value = "/del_my_download", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> delMyDownload(Integer[] myDownloadIds) {
		logger.info("=================del_my_download==================");
		// myDownloadIds 我的下载记录的id号, 批量的
		boolean result=myDownloadService.delMyDownload(myDownloadIds);
		Map<String, Object> map = new HashMap<>();
		map.put("flag", result);
		return map;
	}

	// 获取我的下载记录
	@RequestMapping(value = "/get_my_download", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getMyDownload(Integer page_Now, HttpSession httpSession) {

		Map<String, Object> map = new HashMap<>();
		String userName = (String) httpSession.getAttribute("username");
		int pageNow = 1;
		if (page_Now != null) {
			pageNow = page_Now;
		}
		Page page = new Page(pageNow);

		List<FileInfoVo> fileInfoVos = myDownloadService.getMyDownload(userName, page);
		int pageCount = page.getTotalPageCount();
		int totalCount = page.getTotalCount();
		map.put("totalCount", totalCount);
		map.put("pageNow", page_Now);
		map.put("pageCount", pageCount);
		map.put("download_file", fileInfoVos);

		return map;
	}
}
