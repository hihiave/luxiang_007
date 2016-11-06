package com.lx.test;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSON;
import com.lx.macrofiles.MacroEnum.KCheckType;
import com.lx.macrofiles.MacroEnum.KFileVisibleType;
import com.lx.model.FileInfo;
import com.lx.service.FileInfoService;
import com.lx.serviceimpl.FileInfoServiceImpl;
import com.lx.tool.ToolDate;

public class TestFileInfo {

	@Autowired
	FileInfoService fileInfoService;

	private Logger logger = Logger.getLogger(TestFileInfo.class);
	ApplicationContext app = null;

	@Before
	public void init() {
		app = new ClassPathXmlApplicationContext("classpath:lx/applicationContext.xml");
		fileInfoService = app.getBean(FileInfoServiceImpl.class);
	}

	@Test
	public void TestAddFileInfo() {
		logger.info("===============TestAddFileInfo=============");
		FileInfo fileInfo = new FileInfo();
		fileInfo.setFileName("西游记111");
		fileInfo.setFileAuthor("luxiang");
		fileInfo.setFileUploadTime(ToolDate.getCurrentTimestamp());
		fileInfo.setFileUploadTime(0);
		fileInfo.setFileKeywords("两学一做");
		fileInfo.setFileCategory("科技成果");
		fileInfo.setFileDesc("hahahahaha");
		fileInfo.setFileIsVisible(KFileVisibleType.PUBLIC_FILE);
		fileInfo.setFileUrl("F/");
		boolean d = fileInfoService.addFileInfo(fileInfo);
		logger.info("=========上传======" + d);
	}

	@Test
	public void TestDeleteFileInfoById() {
		logger.info("===============TestDeleteFileInfoById=============");
		boolean d = fileInfoService.delFilesById(23, 22);
		logger.info("=========删除======" + d);
	}

	@Test
	public void TestCheckFileIsExist() {
		logger.info("===============TestCheckFileIsExist=============");
		boolean d = fileInfoService.checkFileIsExist("西游记1");
		logger.info("=========检查======" + d);
	}

	@Test
	public void TestBatchFilesIsPass() {
		logger.info("===============TestBatchFilesIsPass=============");
		boolean d = fileInfoService.batchFilesIsPass(KCheckType.pass, 1, 2);
		// .batchFilesIsPass(1, 1);
		logger.info("=========检查======" + d);
	}

	@Test
	public void TestSelectFileByIsPass() {
		logger.info("===============TestSelectFileByIsPass=============");
		List<FileInfo> fileInfos = fileInfoService.selectFileByIsPass(KCheckType.pass);
		logger.info("=========通过审核文件======" + JSON.toJSONString(fileInfos));
	}

	@Test
	public void TestSelectMyFileInfo() {
		logger.info("===============TestSelectMyFileInfo=============");
		List<FileInfo> fileInfos = fileInfoService.selectMyFileInfo("luxiang", KCheckType.notPass);
		logger.info("=========查询======" + JSON.toJSONString(fileInfos));

	}

	// **********用于一些查询的方法**********
	@Test
	public void TestSelectFileInfo() {
		logger.info("===============TestSelectFileInfo=============");
		FileInfo fileInfo = new FileInfo();
		// fileInfo.setFileName("十三五");
		fileInfo.setFileAuthor("luxiang");
		// fileInfo.setFileCategory("科技成果");
		fileInfo.setFileIsVisible(KFileVisibleType.PUBLIC_FILE);
	}

}
