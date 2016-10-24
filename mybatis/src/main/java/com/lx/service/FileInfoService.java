package com.lx.service;

import java.util.List;

import com.lx.model.FileInfo;

public interface FileInfoService {

	/** 添加一个文件信息，必须指定文件名
	 * @author 米登
	 * @param FileInfo
	 * @return boolean，true表示添加成功，false表示添加失败
	 */
	public boolean addFileInfo(FileInfo fileInfo);

	/** 通过文件id批量删除文件
	 * @author luxiang
	 * @param fileIds
	 * @return boolean，true表示删除成功，false表示删除失败
	 */
	public boolean delFileInfoById(Integer... fileIds);
	
	/** 通过上传者查询我的文件信息
	 * @author luxiang
	 * @param fileAuthor
	 * @return 一个用户对象列表 FileInfo
	 */
	public List<FileInfo> selectFileInfoByfileAuthor(String fileAuthor); 
	
	/** 查询公有文件信息
	 * @author luxiang
	 * @return 一个用户对象列表 FileInfo
	 */
	public List<FileInfo> selectPublicFileInfo();
	
	
	
	
	/**
	 * 通过文件fileId查询文件
	 * 
	 * @author 米登
	 * @param fileId
	 * @return 一个文件信息FileInfo
	 */
	public FileInfo getFileInfoById(int fileId);


	
	
	
	/**
	 * @author 米登
	 * @param fileName
	 * @return 一个文件信息对象fileInfo
	 */
	public FileInfo getFileInfoByName(String fileName);

	/**
	 * @author 米登
	 * @param fileCategory
	 * @return 一个int值 某一个文件种类中的文件个数
	 */
	public int getFileInfoCountByCategory(String fileCategory);

	/**
	 * @author 米登
	 * @param 一个文件信息对象fileInfo
	 * @return boolean值 true代表更新成功
	 */
	public boolean updateFileInfoById(FileInfo fileInfo);

	/**
	 * @author 米登
	 * @param String
	 *            类型的 文件种类名称
	 * @return boolean值 true代表更新成功
	 */
	public boolean updateFileStatusByFileCategory(String fileCategory);

	/**
	 * @author 米登
	 * @param String
	 *            文件种类
	 * @return FileInfo 类型的文件信息
	 */
	public List<FileInfo> getFileInfoByCategory(String fileCategory);
}
