package com.lx.controller;

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
@RequestMapping("/CategoryController")
public class CategoryController {

	@Autowired
	CategoryService categortservice;

	// 添加类别
	@RequestMapping(value = "/add_cate", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public Map<String, Object> add_cate(String catename, HttpServletRequest request) {
		System.out.println(catename + "-------___________");
		boolean check_result = categortservice.checkCategoryIsExist(catename);
		Map<String, Object> map = new HashMap<String, Object>();
		if (!check_result) {
			if (categortservice.addCategory(catename)) {
				map.put("flag", "chenggong");
				return map;
			}
			map.put("flag", "shibai");
			return map;
		}
		map.put("flag", "cunzai");
		return map;
	}

	// 删除类别
	@RequestMapping(value = "/del_cate", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public Map<String, Object> del_cate(String cate_name, HttpSession httpSession,
			HttpServletRequest httpServletRequest) {
		Map<String, Object> map = new HashMap<String, Object>();

		boolean result = categortservice.delCategory(cate_name);

		map.put("flag", result);
		return map;
	}

	// 获取类别
	@RequestMapping(value = "/get_category", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public Map<String, Object> get_category(HttpSession httpSession, HttpServletRequest httpServletRequest) {
		Map<String, Object> map = new HashMap<String, Object>();

		List<Category> category = categortservice.getAllCategory();

		map.put("category", category);
		return map;
	}

}
