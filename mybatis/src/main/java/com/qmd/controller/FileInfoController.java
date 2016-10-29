package com.qmd.controller;

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

import com.lx.model.Category;
import com.lx.model.FileInfo;
import com.lx.service.CategoryService;
import com.lx.service.FileInfoService;

@Controller
@RequestMapping("/FileInfoController")
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

	// 资源审核
	@RequestMapping(value = "/get_all_checkfile", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> get_all_checkfile(HttpSession httpSession, HttpServletRequest httpServletRequest) {
		Map<String, Object> map = new HashMap<String, Object>();

		List<FileInfo> all_checkfile = fileInfoService.selectFileByIsPass(0);

		map.put("checkfile", all_checkfile);
		return map;
	}

	// 审核文件

	  @RequestMapping(value = "/pass_file", method = RequestMethod.POST)
	 
	  @ResponseBody public Map<String, Object> pass_file(Integer[] pass_array,HttpSession httpSession, HttpServletRequest httpServletRequest) {
		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println("传入数据=========");
		boolean result = fileInfoService.updateFilesCheck(pass_array);
		map.put("flag", result);
		return map;
	}
	 
	// 下载
	@RequestMapping(value = "/down_check_file", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public void deleteuser(String select_filename, HttpServletRequest httpServletRequest) {
		System.out.println("传入数据=========" + select_filename);

	}

	// 删除
	@RequestMapping(value = "/delete_file", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> delete_file(Integer[] delete_array, HttpServletRequest httpServletRequest) {

		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println("传入数据=========");
		// System.out.println("传入数据========="+delete_array.toString());
		boolean result = fileInfoService.delFileInfoById(delete_array);
		map.put("flag", result);
		return map;
	}

	@RequestMapping(value = "/myuploadfile", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> myuploadfile(HttpSession httpSession, HttpServletRequest httpServletRequest) {
		Map<String, Object> map = new HashMap<String, Object>();
		String username = (String) httpSession.getAttribute("username");
		System.out.println(username);
		List<FileInfo> pri_file = fileInfoService.selectMyFileInfo(username);

		map.put("pri_file", pri_file);
		return map;
	}

	@RequestMapping(value = "/publicfile", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> publicfile(HttpSession httpSession, HttpServletRequest httpServletRequest) {
		Map<String, Object> map = new HashMap<String, Object>();

		List<FileInfo> pub_file = fileInfoService.selectPublicFileInfo();
		map.put("pub_file", pub_file);
		return map;
	}

}
