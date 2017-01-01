package com.lx.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.druid.util.StringUtils;
import com.lx.tools.ToolCreateDocIndex;

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

			ToolCreateDocIndex.init();
			System.out.println("索引初始化成功！");
		} else if ("pdf".equals(flag)) {

			boolean isSucceeded = ToolCreateDocIndex.createPDFIndex(request);
			if (isSucceeded) {
				System.out.println("==1===PDF索引创建成功！");
			} else {
				System.out.println("==1===PDF索引创建失败！");
			}

		} else if ("doc".equals(flag)) {

			boolean isSucceeded = ToolCreateDocIndex.createWordIndex(request);
			// boolean isSucceeded = wordIndex.createWordIndex(request);
			if (isSucceeded) {
				System.out.println("==2===WORD索引创建成功！");
			}
		}

		System.out.println("=======fileIndexCreate=========结束==========");
		return "fileindex";
	}

	@RequestMapping("initIndex")
	public String initIndex(HttpServletRequest request) {
		// 获得初始化文档类型
		String flag = request.getParameter("flag");
		if (StringUtils.isEmpty(flag)) {
			return "fileindex";
		}
		return null;
	}

}
