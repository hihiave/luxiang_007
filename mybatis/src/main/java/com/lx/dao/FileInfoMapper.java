package com.lx.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lx.model.FileInfo;
import com.lx.serviceimpl.Page;

public interface FileInfoMapper {

	int deleteByPrimaryKey(Integer fileId);

	int insert(FileInfo record);

	int insertSelective(FileInfo record);

	FileInfo selectByPrimaryKey(Integer fileId);

	int updateByPrimaryKeySelective(FileInfo record);

	int updateByPrimaryKey(FileInfo record);

	// **********用于处理一些业务逻辑的方法**********
	int delFilesById(@Param("fileIds") Integer... fileIds);
	FileInfo selectFileByFileName(String fileName);
	int updateFilesCheck(@Param("fileCheck") int fileCheck, @Param("fileIds") Integer... fileIds);

	// **********用于一些查询的方法**********
	// 我的
	int selectFileByfileAuthorWithFileCheckCount(@Param("fileAuthor") String fileAuthor,
			@Param("fileChecks") Integer... fileChecks);
	List<FileInfo> selectFileByFileAuthorWithFileCheck(@Param("fileAuthor") String fileAuthor, 
			@Param("page") Page page, @Param("fileChecks") Integer... fileChecks);

	// 文件
	int getFileInfoCount(@Param("fileCheck") int fileCheck, 
			@Param("fileInfo") FileInfo fileInfo);
	List<FileInfo> getFileInfo(@Param("fileCheck") int fileCheck, 
			@Param("fileInfo") FileInfo fileInfo, @Param("page") Page page);
	
	
	
	// List<FileInfo> getFileInfoByKeywords(@Param("keywords") String...
	// keywords);

	// <select id="selectFileByKeywords" resultMap="BaseResultMap">
	// select * from File e where e.file_id in
	// <foreach collection="keywords" item="keyword" index="index"
	// open="(" close=")" separator=",">
	//
	// select file_id from File where file_keywords like
	// CONCAT(CONCAT('%',#{keyword},'%'))
	//
	// </foreach>
	// </select>

}