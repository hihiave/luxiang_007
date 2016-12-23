package com.lx.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lx.model.FileInfo;
import com.lx.tools.Page;

public interface FileInfoMapper {

	int deleteByPrimaryKey(Integer fileId);

	int insert(FileInfo record);

	int insertSelective(FileInfo record);

	FileInfo selectByPrimaryKey(Integer fileId);

	int updateByPrimaryKeySelective(FileInfo record);

	int updateByPrimaryKey(FileInfo record);

	// **********用于处理一些业务逻辑的方法**********
	int delFilesById(@Param("fileIds") Integer... fileIds);
	int updateFileCategory(@Param("fileAuthor") String fileAuthor, @Param("oldFileCategory") String oldFileCategory,
			@Param("newFileCategory") String newFileCategory);
	FileInfo selectFileByFileName(String fileName);
	int updateFilesCheck(@Param("fileCheck") int fileCheck, @Param("fileIds") Integer... fileIds);

	// **********用于一些查询的方法**********
	// 我的
	int selectFileByfileAuthorWithFileCheckCount(@Param("fileAuthor") String fileAuthor,
			@Param("fileChecks") Integer... fileChecks);
	List<FileInfo> selectFileByFileAuthorWithFileCheck(@Param("fileAuthor") String fileAuthor, @Param("page") Page page,
			@Param("fileChecks") Integer... fileChecks);

	// 文件
	int getFileInfoCount(@Param("fileCheck") int fileCheck, @Param("fileInfo") FileInfo fileInfo);
	List<FileInfo> getFileInfo(@Param("fileCheck") int fileCheck, @Param("fileInfo") FileInfo fileInfo,
			@Param("page") Page page);

	// **********用于获取一些智能下拉提示**********
	List<String> getFileNames(String fileName);
	List<String> getfileAuthors(String fileAuthor);
}