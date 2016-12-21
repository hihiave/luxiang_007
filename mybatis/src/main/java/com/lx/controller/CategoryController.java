package com.lx.controller;

import java.util.ArrayList;
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
import com.lx.service.CategoryService;

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


	@RequestMapping(value = "/get_child_category", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public Map<String, Object> get_child_category(HttpSession httpSession, HttpServletRequest httpServletRequest) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<Category> child1 = new ArrayList<Category>();
		for(int i=0;i<4;i++){
			Category cate=new Category();
			cate.setCategoryName("专利"+i);
			child1.add(cate);
		}
		
		List<Category> child2 = new ArrayList<Category>();
		for(int i=0;i<4;i++){
			Category cate=new Category();
			cate.setCategoryName("论文"+i);
			child2.add(cate);
		}
		map.put("child1", child1);
		map.put("child2", child2);
		return map;
	}
	}