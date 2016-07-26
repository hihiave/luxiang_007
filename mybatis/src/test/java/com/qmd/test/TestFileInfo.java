package com.qmd.test;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.qmd.model.FileInfo;
import com.qmd.serviceimpl.FileInfoServiceImpl;

public class TestFileInfo {
	
	private Logger logger = Logger.getLogger(TestFileInfo.class);
	
	@Test
	public void TestFileInfo(){

		FileInfoServiceImpl fileInfoServiceImpl ;
		
		ApplicationContext app = new ClassPathXmlApplicationContext("classpath:qmd/applicationContext.xml");
		fileInfoServiceImpl = app.getBean(FileInfoServiceImpl.class);
		
		FileInfo fileInfo = fileInfoServiceImpl.getFileInfoById(1);
		
		logger.info(fileInfo.getFileName() + "===============");
		
		boolean d = fileInfoServiceImpl.deleteFileInfoById(3);
		logger.info(d + "========d=======");
		
	}



}
