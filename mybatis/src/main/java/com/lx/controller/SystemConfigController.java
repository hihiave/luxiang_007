package com.lx.controller;

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
	public String backup(HttpServletRequest request) {
		logger.info("=================backup==================");

		return null;

	}

	// 还原
	@RequestMapping("/restore")
	public String restore(HttpServletRequest request) {
		logger.info("=================restore==================");
		
		return null;
	}
}
