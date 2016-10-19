package com.qmd.test;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lx.model.FileInfo;
import com.lx.service.FileInfoService;
import com.lx.serviceimpl.FileInfoServiceImpl;

public class TestFileInfo {

	@Autowired
	FileInfoService fileInfoService;

	private Logger logger = Logger.getLogger(TestFileInfo.class);

	@Before
	public void init() {
		ApplicationContext app = new ClassPathXmlApplicationContext("classpath:lx/applicationContext.xml");
		fileInfoService = app.getBean(FileInfoServiceImpl.class);
	}

	@Test
	public void TestAddFileInfo() {
		logger.info("===============TestAddFileInfo=============");
		FileInfo fileInfo = new FileInfo();
		fileInfo.setFileName("西游记");
		fileInfo.setFileAuthor("luxiang");
		fileInfo.setFileUploadTime(0);
		boolean d = fileInfoService.addFileInfo(fileInfo);
		logger.info("===============" + d);

	}

}
