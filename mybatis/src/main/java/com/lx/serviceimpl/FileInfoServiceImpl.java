package com.lx.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.lx.dao.FileInfoMapper;
import com.lx.macrofiles.MacroEnum.KCheckType;
import com.lx.macrofiles.MacroEnum.KFilePropertyType;
import com.lx.model.FileInfo;
import com.lx.service.FileInfoService;

@Service
public class FileInfoServiceImpl implements FileInfoService {

	@Autowired
	private FileInfoMapper fileInfoMapper;

	// **********用于处理一些业务逻辑的方法**********
	@Override
	public boolean addFileInfo(FileInfo fileInfo) {
		if (fileInfoMapper.insertSelective(fileInfo) == 1) {
			return true;
		}
		return false;
	}

	@Override
	public boolean delFilesById(Integer... fileIds) {
		if (fileInfoMapper.delFilesById(fileIds) == fileIds.length) {
			return true;
		}
		return false;
	}

	@Override
	public FileInfo getFileByFileId(Integer fileId) {
		return fileInfoMapper.selectByPrimaryKey(fileId);
	}

	@Override
	public boolean updateFileByFileId(Integer fileId, FileInfo fileInfo) {
		fileInfo.setFileId(fileId);
		if (fileInfoMapper.updateByPrimaryKeySelective(fileInfo) == 1) {
			return true;
		}
		return false;
	}

	@Override
	public boolean checkFileIsExist(String fileName) {
		if (fileInfoMapper.selectFileByFileName(fileName) != null) {
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
	public List<FileInfo> getFileByLikeFileProperty(String fileCategory, KFilePropertyType filePropertyType,
			String value) {
		FileInfo fileInfo = new FileInfo();
		fileInfo.setFileCategory(fileCategory);
		switch (filePropertyType) {
		case title:
			fileInfo.setFileName(value);
			break;
		case author:
			fileInfo.setFileAuthor(value);
			break;
		case keyword:
			fileInfo.setFileKeywords(value);
			break;
		default:
			break;
		}

		return getFileInfo(fileInfo);
	}

}
