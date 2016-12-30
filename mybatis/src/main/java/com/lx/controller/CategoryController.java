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
	public Map<String, Object> add_cate(Integer userid,String catename,String categoryBelongTo,HttpServletRequest request) {
		System.out.println(catename + "-------___________");
		boolean check_result = categortservice.checkCategoryIsExist(userid,catename);
		System.out.println("catebelongto==."+categoryBelongTo+".");
		String value;
		if(categoryBelongTo==null){
			value="1482422400";
		}
		else{
			value=categoryBelongTo;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		if (!check_result) {
			if (categortservice.addCategory(userid,catename,value)) {
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
	public Map<String, Object> del_cate(Integer userid,String cate_name, HttpSession httpSession,
			HttpServletRequest httpServletRequest) {
		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println(userid+"......"+ cate_name);
		boolean result = categortservice.delCategory(userid,cate_name);
		map.put("flag", result);
		return map;
	}
	//修改类别
	@RequestMapping(value = "/change_cate", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public Map<String, Object> alertCategory(Integer userid,String username,String old_catename,
			
		String new_catename,HttpSession httpSession,HttpServletRequest httpServletRequest) {
		boolean check_result = categortservice.checkCategoryIsExist(userid,new_catename);
		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println(userid+".."+username+".."+old_catename+".."+new_catename+"."+check_result);
		if (!check_result) {
			if (categortservice.alterCategory(userid, username, old_catename, new_catename)) {
				map.put("flag", "chenggong");
				return map;
			}else{
			map.put("flag", "shibai");
			return map;
			}
		}
		map.put("flag", "cunzai");
		return map;
	}
	// 获取类别
	@RequestMapping(value = "/get_category", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public Map<String, Object> get_category(Integer userid,HttpSession httpSession, HttpServletRequest httpServletRequest) {
		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println("id=========="+userid);
		List<Category> category = categortservice.getAllCategory(userid);
		List<Category> newcate = new ArrayList<Category>();
		for(Category cate :category){
			if(cate.getCategoryBelongTo().equals("1482422400"))
				newcate.add(cate);
		}
		map.put("category", newcate);
		return map;
	}


	@RequestMapping(value = "/get_child_category", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public Map<String, Object> get_child_category(Integer userid,String categoryBelongTo,
			HttpSession httpSession, HttpServletRequest httpServletRequest) {
		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println(userid+"....."+categoryBelongTo);
		List<Category> category = categortservice.getAllCategory(userid);
		List<Category> newcate = new ArrayList<Category>();
		for(Category cate :category){
			if(cate.getCategoryBelongTo().equals(categoryBelongTo))
				newcate.add(cate);
		}
		map.put("category", newcate);
		return map;
	}
	}