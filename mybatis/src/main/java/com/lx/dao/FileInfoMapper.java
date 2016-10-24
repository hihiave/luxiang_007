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
	
	//*********用于处理一些业务逻辑的方法
	int delFileInfoById(@Param("fileIds") Integer... fileIds);
	List<FileInfo> selectFileInfoByfileAuthor(String fileAuthor);
	List<FileInfo> selectPublicFileInfo();
	
}