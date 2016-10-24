package com.qmd.test;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSON;
import com.lx.macrofiles.MacroEnum.KCheckType;
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
	public void TestSelectFileInfoByfileAuthor() {
		logger.info("===============TestSelectFileInfoByfileAuthor=============");

		List<FileInfo> fileInfos = fileInfoService.selectFileInfoByfileAuthor(null);
		logger.info("=========查询======" + JSON.toJSONString(fileInfos));

	}

	@Test
	public void TestSelectPublicFileInfo() {
		logger.info("===============TestSelectPublicFileInfo=============");
		List<FileInfo> fileInfos = fileInfoService.selectPublicFileInfo();
		logger.info("=========公有文件======" + JSON.toJSONString(fileInfos));
	}

}
