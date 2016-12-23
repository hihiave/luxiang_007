package com.lx.test;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSON;
import com.lx.model.Category;
import com.lx.service.CategoryService;
import com.lx.serviceimpl.CategoryServiceImpl;

public class TestCategory {

	CategoryService categoryService;
	ApplicationContext app = null;
	private static final Logger logger = Logger.getLogger(TestCategory.class);

	@Before
	public void init() {
		app = new ClassPathXmlApplicationContext("classpath:lx/applicationContext.xml");
		categoryService = app.getBean(CategoryServiceImpl.class);
	}

	@Test
	public void addCategoryTest() {
		logger.info("===============TestAddCategory=============");
		boolean d = categoryService.addCategory(2, "四川日报", "中国人民日报");
		logger.info("===========添加=============" + d);
	}

	@Test
	public void delCategoryTest() {
		logger.info("===============TestDelCategory=============");
		boolean d = categoryService.delCategory(1001, "PP");
		logger.info("===========删除=============" + d);
	}

	@Test
	public void getAllCategoryTest() {
		logger.info("===============TestGetAllCategory=============");
		List<Category> f = categoryService.getAllCategory(21);
		logger.info("===========查询=============" + JSON.toJSONString(f));
	}

	@Test
	public void TestCheckCategoryIsExist() {
		logger.info("===============TestCheckCategoryIsExist=============");
		boolean d = categoryService.checkCategoryIsExist(21, "A");
		logger.info("===========存在=============" + d);
	}

}
