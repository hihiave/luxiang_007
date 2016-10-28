package com.lx.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.lx.dao.FileInfoMapper;
import com.lx.macrofiles.MacroEnum.KButtonType;
import com.lx.macrofiles.MacroEnum.KCheckType;
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
	public List<FileInfo> selectFileByIsPass(int checkType) {
		return fileInfoMapper.selectFileByIsPass(checkType);
	}

	@Override
	public List<FileInfo> selectMyFileInfo(String userName) {
		return fileInfoMapper.selectMyFileInfo(userName);
	}

	@Override
	public List<FileInfo> selectPublicFileInfo() {
		return fileInfoMapper.selectPublicFileInfo();
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
			fileInfo.setFileIsVisible(KCheckType.PUBLICFILE);
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
			fileInfo.setFileIsVisible(KCheckType.PUBLICFILE);
			return getFileInfo(fileInfo);
		default:
			return null;
		}
	}

	@Override
	public boolean updateFileStatusByFileCategory(String fileCategory) {
		return false;
		// int temp = fileInfoMapper.updateFileStatusByCategory(fileCategory);
		// System.out.println(temp);
		// if (temp >= 0)
		// return true;
		// else
		// return false;
	}

	@Override
	public List<FileInfo> getFileInfoByCategory(String fileCategory) {
		return null;
		// return fileInfoMapper.selectByCategory(fileCategory);
	}

}
