package com.lx.test;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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
	public void TestAddCategory() {
		logger.info("===============TestAddCategory=============");
		boolean d = categoryService.addCategory("");
		logger.info("===========添加=============" + d);
	}

	@Test
	public void TestDelCategory() {
		logger.info("===============TestDelCategory=============");
		boolean d = categoryService.delCategory("jjjpp");
		logger.info("===========删除=============" + d);
	}
}
