package com.lx.test;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSON;
import com.lx.macrofiles.MacroEnum.KButtonType;
import com.lx.macrofiles.MacroEnum.KCheckType;
import com.lx.model.FileInfo;
import com.lx.service.FileInfoService;
import com.lx.serviceimpl.FileInfoServiceImpl;

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
		fileInfo.setFileName("西游记");
		fileInfo.setFileAuthor("luxiang");
		fileInfo.setFileUploadTime(0);
		fileInfo.setFileKeywords("两学一做");
		fileInfo.setFileCategory("科技成果");
		fileInfo.setFileDesc("hahahahaha");
		fileInfo.setFileIsVisible(KCheckType.PUBLICFILE);
		fileInfo.setFileUrl("F/");
		boolean d = fileInfoService.addFileInfo(fileInfo);
		logger.info("=========上传======" + d);
	}

	@Test
	public void TestDeleteFileInfoById() {
		logger.info("===============TestDeleteFileInfoById=============");
		boolean d = fileInfoService.delFileInfoById(23, 22);
		logger.info("=========删除======" + d);
	}

	@Test
	public void TestCheckFileIsExist() {
		logger.info("===============TestCheckFileIsExist=============");
		boolean d = fileInfoService.checkFileIsExist("西游记1");
		logger.info("=========检查======" + d);
	}

	@Test
	public void TestUpdateFilesCheck() {
		logger.info("===============TestUpdateFilesCheck=============");
		boolean d = fileInfoService.updateFilesCheck(1, 2);
		logger.info("=========检查======" + d);
	}

	@Test
	public void TestSelectFileByIsPass() {
		logger.info("===============TestSelectFileByIsPass=============");
		List<FileInfo> fileInfos = fileInfoService.selectFileByIsPass(KCheckType.PASS);
		logger.info("=========通过审核文件======" + JSON.toJSONString(fileInfos));
	}

	@Test
	public void TestSelectMyFileInfo() {
		logger.info("===============TestSelectMyFileInfo=============");
		List<FileInfo> fileInfos = fileInfoService.selectMyFileInfo("luxiang");
		logger.info("=========查询======" + JSON.toJSONString(fileInfos));
	}

	@Test
	public void TestSelectPublicFileInfo() {
		logger.info("===============TestSelectPublicFileInfo=============");
		List<FileInfo> fileInfos = fileInfoService.selectPublicFileInfo();
		logger.info("=========公有文件======" + JSON.toJSONString(fileInfos));
	}

	// **********用于一些查询的方法**********
	@Test
	public void TestSelectFileInfo() {
		logger.info("===============TestSelectFileInfo=============");
		FileInfo fileInfo = new FileInfo();
		// fileInfo.setFileName("十三五");
		fileInfo.setFileAuthor("luxiang");
		// fileInfo.setFileCategory("科技成果");
		fileInfo.setFileIsVisible(KCheckType.PUBLICFILE);
	}

	@Test
	public void TestGetFileByLikeFileName() {
		logger.info("===============TestGetFileByLikeFileName=============");
		List<FileInfo> fileInfos = fileInfoService.getFileByLikeFileName("十三五", "科技成果", KButtonType.PublicFileButton);
		logger.info("========文件名=======" + JSON.toJSONString(fileInfos));
	}

	@Test
	public void TestGetFileByLikeFileAuthor() {
		logger.info("===============TestGetFileByLikeFileAuthor=============");
		List<FileInfo> fileInfos = fileInfoService.getFileByLikeFileAuthor("luxiang", null,
				KButtonType.PublicFileButton);
		logger.info("========作者=======" + JSON.toJSONString(fileInfos));

	}

}
