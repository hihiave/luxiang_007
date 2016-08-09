package com.lx.test;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSON;
import com.lx.model.File;
import com.lx.serviceimpl.FileServiceImpl;

public class TestFile {

	FileServiceImpl fileServiceImpl;
	ApplicationContext app = null;
	private static final Logger logger = Logger.getLogger(TestFile.class);

	@Before
	public void init() {
		app = new ClassPathXmlApplicationContext("classpath:lx/applicationContext.xml");
		fileServiceImpl = app.getBean(FileServiceImpl.class);
	}

	@Test
	public void TestSelectFileByFileAuthor() {
		List<File> files = fileServiceImpl.selectFileByFileAuthor("吴");
		logger.info("===1====" + files.isEmpty() + "==");
		logger.info("===B=====" + files.size() + "==");
		logger.info("===C=====" + files.get(1).getFileCategory() + "==");
		logger.info("===HH=====" + JSON.toJSONString(files.get(1)) + "==");
		// logger.info("===E=====" + files.get(2) + "==");
	}

	//*******************************************
	@Test
	public void TestSelectFileBykeys() {
		List<File> files = fileServiceImpl.selectFileByKeywords("十三五", "环境保护");
		logger.info("===2====" + files.isEmpty() + "==");
		logger.info("===B=====" + files.size() + "==");
		logger.info("===C=====" + files.get(0).getFileUploadTime() + "==");
	}
	
	@Test
	public void TestSelectFileByFileCategory(){
		List<File> files = fileServiceImpl.selectFileByFileCategory("文献");
		logger.info("===3====" + files.isEmpty() + "==");
		logger.info("===B=====" + files.size() + "==");
		logger.info("===C=====" + files.get(0).getFileUploadTime() + "==");
		logger.info("===D=====" + JSON.toJSONString(files.get(0)) + "==");
	}
	
}
