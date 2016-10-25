package com.lx.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.lx.dao.FileInfoMapper;
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
	@Override
	public List<FileInfo> getFileInfo(FileInfo fileInfo) {
		System.out.println("fileInfo============" + JSON.toJSON(fileInfo));
		return fileInfoMapper.getFileInfo(fileInfo);
	}

	@Override
	public List<FileInfo> selectFileInfoByFileName(String fileName) {
		return null;

	}

	@Override
	public List<FileInfo> selectFileInfoByfileAuthor(String fileAuthor) {
		return null;

		// return fileInfoMapper.selectFileInfoByfileAuthor(fileAuthor);
	}

	@Override
	public int getFileInfoCountByCategory(String fileCategory) {
		return 0;

		// int count = fileInfoMapper.selectCountByFileCategory(fileCategory);
		// System.out.println(count);
		// return count;
	}

	@Override
	public boolean updateFileInfoById(FileInfo fileInfo) {
		int temp = 0;
		temp = fileInfoMapper.updateByPrimaryKey(fileInfo);
		if (temp == 1)
			return true;
		else
			return false;
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
