package com.lx.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/SystemConfigController")
public class SystemConfigController {

	private static Logger logger = Logger.getLogger(SystemConfigController.class);

	// 备份
	@RequestMapping("/backup")
	public Map<String, Object> backup(HttpServletRequest request) {
		logger.info("=================backup==================");
		Map<String, Object> map = new HashMap<String, Object>();
		if(true){
			map.put("flag", "chenggong");
			return map;
		}
		
		map.put("flag", "shibai");
		return map;
	}

	// 还原
	@RequestMapping("/restore")
	public Map<String, Object> restore(HttpServletRequest request) {
		logger.info("=================restore==================");
		Map<String, Object> map = new HashMap<String, Object>();
		if(true){
			map.put("flag", "chenggong");
			return map;
		}
		
		map.put("flag", "shibai");
		return map;
	}
	//初始化索引
	@RequestMapping("/build")
	public Map<String, Object> build(HttpServletRequest request) {
		logger.info("=================restore==================");
		Map<String, Object> map = new HashMap<String, Object>();
		if(true){
			map.put("flag", "chenggong");
			return map;
		}
		
		map.put("flag", "shibai");
		return map;
	}
	//建立pdf
	@RequestMapping("/build_pdf")
	public Map<String, Object> build_pdf(HttpServletRequest request) {
		logger.info("=================restore==================");
		Map<String, Object> map = new HashMap<String, Object>();
		if(true){
			map.put("flag", "chenggong");
			return map;
		}
		
		map.put("flag", "shibai");
		return map;
	}
	//建立word
	@RequestMapping("/build_word")
	public Map<String, Object> build_word(HttpServletRequest request) {
		logger.info("=================restore==================");
		Map<String, Object> map = new HashMap<String, Object>();
		if(true){
			map.put("flag", "chenggong");
			return map;
		}
		
		map.put("flag", "shibai");
		return map;
	}
}
