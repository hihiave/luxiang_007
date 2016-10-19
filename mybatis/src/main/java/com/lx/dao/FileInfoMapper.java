package com.lx.dao;

import com.lx.model.FileInfo;

public interface FileInfoMapper {

	int deleteByPrimaryKey(Integer fileId);

	int insert(FileInfo record);

	int insertSelective(FileInfo record);

	FileInfo selectByPrimaryKey(Integer fileId);

	int updateByPrimaryKeySelective(FileInfo record);

	int updateByPrimaryKey(FileInfo record);
}