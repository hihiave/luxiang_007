package com.lx.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lx.model.FileInfo;

public interface FileInfoMapper {

	int deleteByPrimaryKey(Integer fileId);

	int insert(FileInfo record);

	int insertSelective(FileInfo record);

	FileInfo selectByPrimaryKey(Integer fileId);

	int updateByPrimaryKeySelective(FileInfo record);

	int updateByPrimaryKey(FileInfo record);
	
	//**********用于处理一些业务逻辑的方法**********
	int delFileInfoById(@Param("fileIds") Integer... fileIds);
	FileInfo getFileByFileName(String fileName);
	List<FileInfo> selectFileByIsPass(int checkType);
	List<FileInfo> selectMyFileInfo(String userName);
	List<FileInfo> selectPublicFileInfo();
	
	//**********用于一些查询的方法**********
	List<FileInfo> getFileInfo(FileInfo fileInfo);
	
}