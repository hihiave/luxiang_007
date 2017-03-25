package com.mp.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MonitoringHistoryController {
	private static Logger logger = Logger.getLogger(MonitoringHistoryController.class);
	/**
     * 跳转界面
     * @param request
     * @param model
     * @return
     */
	@RequestMapping("/zgmp/check_monitoring_history")
    public String loginItsm(HttpServletRequest request, ModelMap model) {
        System.out.println("check monitoringHistory");
        return "monitoring_history";
    }
}
