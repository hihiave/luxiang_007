package com.lx.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lx.model.FileInfoVo;
import com.lx.model.MyDownload;
import com.lx.serviceimpl.Page;

public interface MyDownloadMapper {
	int deleteByPrimaryKey(Integer myDownloadId);

	int insert(MyDownload record);

	int insertSelective(MyDownload record);

	MyDownload selectByPrimaryKey(Integer myDownloadId);

	int updateByPrimaryKeySelective(MyDownload record);

	int updateByPrimaryKey(MyDownload record);

	// **********用于处理一些业务逻辑的方法**********
	int delMyDownloadByMyDownloadId(@Param("myDownloadIds") Integer... myDownloadIds);
	int selectMyDownloadByMyDownloadUserNameCount(String myDownloadUserName);
	List<FileInfoVo> selectMyDownloadByMyDownloadUserName(@Param("myDownloadUserName") String myDownloadUserName,
			@Param("page") Page page);

}