package com.lx.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.lx.dao.FileInfoMapper;
import com.lx.macrofiles.MacroEnum.KButtonType;
import com.lx.macrofiles.MacroEnum.KCheckType;
import com.lx.macrofiles.MacroEnum.KFileType;
import com.lx.model.FileInfo;
import com.lx.service.FileInfoService;

@Service
public class FileInfoServiceImpl implements FileInfoService {

	@Autowired
	private FileInfoMapper fileInfoMapper;

	@Override
	public boolean addFileInfo(FileInfo fileInfo) {
		if (fileInfoMapper.insertSelective(fileInfo) == 1) {
			return true;
		}
		return false;
	}

	@Override
	public boolean delFileInfoById(Integer... fileIds) {
		if (fileInfoMapper.delFileInfoById(fileIds) == fileIds.length) {
			return true;
		}
		return false;
	}

	@Override
	public boolean checkFileIsExist(String fileName) {
		if (fileInfoMapper.getFileByFileName(fileName) != null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean batchFilesIsPass(KCheckType checkType, Integer... fileIds) {
		if (fileIds.length == fileInfoMapper.updateFilesCheck(checkType.getValue(), fileIds)) {
			return true;
		}
		return false;
	}

	@Override
	public List<FileInfo> selectFileByIsPass(KCheckType checkType) {
		return fileInfoMapper.selectFileByfileCheck(checkType.getValue());
	}

	@Override
	public List<FileInfo> selectMyFileInfo(String userName, KCheckType checkType) {
		return fileInfoMapper.selectFileByfileAuthorWithFileCheck(userName, checkType.getValue());

	}

	// **********用于一些查询的方法**********
	private List<FileInfo> getFileInfo(FileInfo fileInfo) {
		System.out.println("fileInfo============" + JSON.toJSON(fileInfo));
		return fileInfoMapper.getFileInfo(fileInfo);
	}

	@Override
	public List<FileInfo> getFileByLikeFileName(String fileName, String fileCategory, KButtonType buttonType) {
		FileInfo fileInfo = new FileInfo();
		fileInfo.setFileName(fileName);
		fileInfo.setFileCategory(fileCategory);
		switch (buttonType) {
		case MyUploadButton:
			return getFileInfo(fileInfo);
		case PublicFileButton:
			fileInfo.setFileIsVisible(KFileType.PUBLICFILE);
			return getFileInfo(fileInfo);
		default:
			return null;
		}
	}

	@Override
	public List<FileInfo> getFileByLikeFileAuthor(String fileAuthor, String fileCategory, KButtonType buttonType) {
		FileInfo fileInfo = new FileInfo();
		fileInfo.setFileAuthor(fileAuthor);
		fileInfo.setFileCategory(fileCategory);
		switch (buttonType) {
		case MyUploadButton:
			return getFileInfo(fileInfo);
		case PublicFileButton:
			fileInfo.setFileIsVisible(KFileType.PUBLICFILE);
			return getFileInfo(fileInfo);
		default:
			return null;
		}
	}

}
