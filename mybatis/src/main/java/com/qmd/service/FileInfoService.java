package com.qmd.service;

import java.util.List;

import com.qmd.model.FileInfo;

public interface FileInfoService {
	
	/**
	 * @author 米登
	 * @param FileId
	 * @return	一个文件信息对象fileInfo
	 */
	public FileInfo getFileInfoById(int fileId);
	
	/**
	 * @author 米登
	 * @param fileId
	 * @return 一个Boolean值 true代表删除成功 false代表删除失败
	 */
	public boolean deleteFileInfoById(int fileId);

	/**
	 * @author 米登
	 * @param FileInfo对象
	 * @return 一个Boolean值 true代表添加成功 false代表添加失败
	 */
	
	public boolean addFileInfo(FileInfo fileInfo);
	
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
	 * @param String 类型的 文件种类名称
	 * @return  boolean值 true代表更新成功
	 */
    public boolean updateFileStatusByFileCategory(String fileCategory);
    
    /**
     * @author 米登
     * @param String 文件种类
     * @return FileInfo 类型的文件信息
     */
   public List<FileInfo> getFileInfoByCategory(String fileCategory);
}


