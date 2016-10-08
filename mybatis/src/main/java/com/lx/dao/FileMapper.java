package com.lx.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lx.model.File;

public interface FileMapper {
	int deleteByPrimaryKey(Integer fileId);

	int insert(File record);

	int insertSelective(File record);

	File selectByPrimaryKey(Integer fileId);

	int updateByPrimaryKeySelective(File record);

	int updateByPrimaryKey(File record);

	//**********查询相应的文件信息
	List<File> selectFileByFileAuthor(String fileAuthor);
	List<File> selectFileByKeywords(@Param("keywords") String... keywords);
	List<File> selectFileByFileCategory(String fileCategory);
	
	//*********查询智能下拉提示信息
	//List<String> select
	
	
	//**********************luxiang
}