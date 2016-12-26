package com.lx.controller;

import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lx.model.DocumentEntity;
import com.lx.tools.Page;
import com.lx.tools.PageNumBean;
import com.lx.tools.ToolSearchDocIndex;

/**
 * 索引文档搜索
 */
@Controller
public class SearchController {
	int topNum = 200;

	// 文档搜索
	@RequestMapping("/searchIndex")
	public ModelAndView searchIndex(HttpServletRequest request) throws Exception {
		System.out.println("==============searchIndex==============");

		String queryStr = request.getParameter("fieldname");
		String searchType = request.getParameter("searchType");
		String filetype = request.getParameter("filetype");
		int topNum = Integer.parseInt(request.getParameter("topNum"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));

		String page_Now = request.getParameter("page_Now");

		System.out.println("======1============" + queryStr); // 检索内容
		System.out.println("======2============" + searchType); // 精确还是模糊
		System.out.println("======3============" + filetype); // pdf doc all
		System.out.println("======4============" + topNum); // 检索前topNum条
		System.out.println("======5============" + pageSize); // 页大小
		System.out.println("======6============" + page_Now); // 当前页

		System.out.println("=====1=================== queryStr== " + queryStr);
		if (queryStr == null) {
			// tomcat默认采用ISO-8859-1方式获取URI中的参数
			queryStr = request.getParameter("sk");
			// fieldname=new String(fieldname.getBytes("ISO-8859-1"),"utf-8");
			// //转换为utf-8
		}
		System.out.println("=====2=================== fieldname== " + queryStr);

		int pageNow = 1;
		if (page_Now != null) {
			pageNow = Integer.valueOf(page_Now);
		}
		System.out.println("==========pageNow===" + pageNow);

		Page page = new Page(pageNow, pageSize);// request.getContextPath().substring(1)

		List<DocumentEntity> documentEntities = ToolSearchDocIndex.getSearchResult(queryStr, searchType, filetype,
				topNum, page);

		System.out.println("============================开始========================" + documentEntities.size());
		for (int i = 0; i < documentEntities.size(); i++) {
			System.out.println("========hahaha=============" + documentEntities.get(i).getFileUrl());
		}
		System.out.println("============================结束========================");

		// 得到总的记录数
		int totalCount = page.getTotalCount();

		PageNumBean pageBean = (PageNumBean) request.getAttribute("pageNumBean");

		System.out.println("===================pageBean================" + pageBean);
		if (pageBean == null) {
			pageBean = new PageNumBean(1, totalCount, pageSize, pageSize);
			request.setAttribute("pageNumBean", pageBean);
		}

		Integer downPageNum = pageNow + 1;
		if (downPageNum > pageBean.getTotalPageCount())
			downPageNum = null;
		Integer upPageNum = pageNow - 1;
		if (upPageNum == 0)
			upPageNum = null;

		pageBean.setDownPageNum(downPageNum);
		pageBean.setUpPageNum(upPageNum);
		pageBean.setPageNow(pageNow);

		request.setAttribute("pageNumBean", pageBean);
		request.setAttribute("queryStr", queryStr);
		request.setAttribute("sk1", URLEncoder.encode(queryStr, "UTF-8"));

		return new ModelAndView("result").addObject("pageUrl", "searchIndex.do?page_Now=")
				.addObject("rsize", totalCount).addObject("rlist", documentEntities);

		// fieldname = new String(fieldname.getBytes("ISO-8859-1"), "UTF-8");
	}
}
