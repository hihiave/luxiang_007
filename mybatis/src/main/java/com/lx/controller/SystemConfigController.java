package com.lx.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lx.service.FileInfoService;
import com.lx.tools.ToolCreateDocIndex;

@Controller
@RequestMapping("/SystemConfigController")
public class SystemConfigController {

	private static Logger logger = Logger.getLogger(SystemConfigController.class);

	// 备份
	@RequestMapping("/backup")
	@ResponseBody
	public Map<String, Object> backup(HttpServletRequest request) {
		logger.info("=================backup==================");
		Map<String, Object> map = new HashMap<>();

		if (true) {
			map.put("flag", "chenggong");
			return map;
		}

		map.put("flag", "shibai");
		return map;
	}

	// 还原
	@RequestMapping("/restore")
	@ResponseBody
	public Map<String, Object> restore(HttpServletRequest request) {
		logger.info("=================restore==================");
		Map<String, Object> map = new HashMap<>();
		if (true) {
			map.put("flag", "chenggong");
			return map;
		}

		map.put("flag", "shibai");
		return map;
	}

	// 初始化索引
	@RequestMapping("/build")
	@ResponseBody
	public Map<String, Object> build(HttpServletRequest request, HttpSession httpSession) {
		logger.info("=================build==================");
		Map<String, Object> map = new HashMap<>();

		if ((int) httpSession.getAttribute("countWithWaitForCheck") != 0) {
			map.put("flag", "waitforcheck");
			return map;
		}

		if (true) {
			map.put("flag", "chenggong");
			return map;
		}

		map.put("flag", "shibai");
		return map;

	}

	// 创建PDF索引
	@RequestMapping("/build_pdf")
	@ResponseBody
	public Map<String, Object> build_pdf(HttpServletRequest request, HttpSession httpSession) {
		logger.info("=================build_pdf==================");
		Map<String, Object> map = new HashMap<>();

		if ((int) httpSession.getAttribute("countWithWaitForCheck") != 0) {
			map.put("flag", "waitforcheck");
			return map;
		}
		if (ToolCreateDocIndex.createPDFIndex(request)) {
			map.put("flag", "chenggong");
			return map;
		}
		map.put("flag", "shibai");
		return map;
	}

	// 创建Word索引
	@RequestMapping("/build_word")
	@ResponseBody
	public Map<String, Object> build_word(HttpServletRequest request, HttpSession httpSession) {
		logger.info("=================build_word==================");
		Map<String, Object> map = new HashMap<>();

		if ((int) httpSession.getAttribute("countWithWaitForCheck") != 0) {
			map.put("flag", "waitforcheck");
			return map;
		}
		if (ToolCreateDocIndex.createWordIndex(request)) {
			map.put("flag", "chenggong");
			return map;
		}
		map.put("flag", "shibai");
		return map;

	}
}
