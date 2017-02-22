package com.lx.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.druid.util.StringUtils;

/**
 * 创建文档索引, 包括pdf、word文档
 */
@Controller
@RequestMapping("/FileIndexController")
public class FileIndexController {

	@RequestMapping("/fileIndex")
	public String fileIndexCreate(HttpServletRequest request) {

		// 获得初始化文档类型
		String flag = request.getParameter("flag");
		if (StringUtils.isEmpty(flag)) {
			return "fileindex";
		}
		System.out.println("==========flag========" + flag);
		if (StringUtils.equals(flag, "init")) {

			// ToolCreateDocIndex.init(request);
			System.out.println("索引初始化成功！");
		} else if ("pdf".equals(flag)) {

			// boolean isSucceeded = ToolCreateDocIndex.createPDFIndex(request);

		} else if ("doc".equals(flag)) {

			// boolean isSucceeded =
			// ToolCreateDocIndex.createWordIndex(request);

		}

		System.out.println("=======fileIndexCreate=========结束==========");
		return "fileindex";
	}
	//
	// @RequestMapping("initIndex")
	// public String initIndex(HttpServletRequest request) {
	// // 获得初始化文档类型
	// String flag = request.getParameter("flag");
	// if (StringUtils.isEmpty(flag)) {
	// return "fileindex";
	// }
	// return null;
	// }

}
