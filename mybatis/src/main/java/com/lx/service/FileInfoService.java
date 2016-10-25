package com.lx.service;

import java.util.List;

import com.lx.model.FileInfo;

public interface FileInfoService {

	//**********用于处理一些业务逻辑的方法**********
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

	/** 通过文件名检查文件是否存在
	 * @author luxiang
	 * @return boolean，true表示存在，false表示不存在
	 */
	public boolean checkFileIsExist(String fileName);
	
	/** 通过审核是否通过查询文件
	 * @author luxiang
	 * @param checkType
	 * @return 一个文件对象列表 FileInfo
	 */
	public List<FileInfo> selectFileByIsPass(int checkType);
	
	/** 通过用户名查询我的文件信息(我的上传)
	 * @author luxiang
	 * @param userName
	 * @return 一个文件对象列表 FileInfo
	 */
	List<FileInfo> selectMyFileInfo(String userName);
	
	/** 查询公有文件信息(公有文件)
	 * @author luxiang
	 * @return 一个文件对象列表 FileInfo
	 */
	public List<FileInfo> selectPublicFileInfo();
	
	//**********用于一些查询的方法**********
	List<FileInfo> getFileInfo(FileInfo fileInfo);
	
	
	/** 通过文件名模糊查询文件，比如，查“三”，找到“十三”，“十三五”
	 * @author luxiang
	 * @param fileName
	 * @return 一个文件对象列表 FileInfo
	 */
	public List<FileInfo> selectFileInfoByFileName(String fileName);
	
	/** 
	 * @author luxiang
	 * @param fileAuthor
	 * @return 一个用户对象列表 FileInfo
	 */
	public List<FileInfo> selectFileInfoByfileAuthor(String fileAuthor); 
	



	
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
