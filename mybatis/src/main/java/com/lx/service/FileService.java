package com.lx.service;

import java.util.List;

import com.lx.model.File;

public interface FileService {
	
	/** 通过文件作者获取文件信息
	 * @author luxiang
	 * @param fileAuthor
	 * @return 一组文件信息:List<File>
	 */
	List<File> selectFileByFileAuthor(String fileAuthor);
	
	/** 通过一组关键词获取文件信息
	 * @author luxiang
	 * @param keys
	 * @return 一组文件信息:List<File>
	 */
	List<File> selectFileByKeywords(String... keywords);

	/** 通过文件类别获取文件信息
	 * @author luxiang
	 * @param fileCategory
	 * @return 一组文件信息:List<File>
	 */
	List<File> selectFileByFileCategory(String fileCategory);
}
