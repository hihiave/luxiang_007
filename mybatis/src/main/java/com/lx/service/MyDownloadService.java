package com.lx.service;

import java.util.List;

import com.lx.model.FileInfo;
import com.lx.model.FileInfoVo;
import com.lx.serviceimpl.Page;

public interface MyDownloadService {

	/** 添加一条我的下载记录
	 * @author luxiang
	 * @param myDownloadUserName 我的下载用户名
	 * @param myDownloadFileId 下载的文件id
	 * @return boolean,true表示添加成功,false表示添加失败
	 */
	public boolean addMyDownload(String myDownloadUserName, int myDownloadFileId);

	/** 通过我的下载id批量删除我的下载记录
	 * @author luxiang
	 * @param myDownloadIds 
	 * @return boolean,true表示删除成功,false表示删除失败
	 */
	public boolean delMyDownload(Integer... myDownloadIds);

	/** 通过用户名获取我的下载记录
	 * @author luxiang
	 * @param userName 用户名
	 * @param page 分页
	 * @return 一个查询结果对象列表 FileInfoVo
	 */
	public List<FileInfoVo> getMyDownload(String userName, Page page);

}
