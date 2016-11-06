package com.lx.service;

import java.util.List;

import com.lx.macrofiles.MacroEnum.KCheckType;
import com.lx.macrofiles.MacroEnum.KFilePropertyType;
import com.lx.model.FileInfo;

public interface FileInfoService {

	//**********用于处理一些业务逻辑的方法**********
	/** 添加一个文件信息，必须指定文件名
	 * @author luxiang
	 * @param fileInfo
	 * @return boolean，true表示添加成功，false表示添加失败
	 */
	public boolean addFileInfo(FileInfo fileInfo);

	/** 通过文件id批量删除文件
	 * @author luxiang
	 * @param fileIds
	 * @return boolean，true表示删除成功，false表示删除失败
	 */
	public boolean delFilesById(Integer... fileIds);

	/** 通过文件名检查文件是否存在
	 * @author luxiang
	 * @param fileName 文件名
	 * @return boolean，true表示存在，false表示不存在
	 */
	public boolean checkFileIsExist(String fileName);
	
	/** 通过文件id批量修改审核类型(审核通过pass,文件失效invalid等等)
	 * @author luxiang
	 * @param checkType 审核类型，请查看枚举
	 * @param fileIds 批量文件fileIds
	 * @return boolean，true表示操作成功，false表示操作失败
	 */
	public boolean batchFilesIsPass(KCheckType checkType, Integer... fileIds);
	
	/** 通过审核类型查询文件(公有:待审核WaitForCheck,公有文件Pass)
	 * @author luxiang
	 * @param checkType 审核类型，请查看枚举
	 * @return 一个文件对象列表 FileInfo
	 */
	public List<FileInfo> selectFileByIsPass(KCheckType checkType);
	
	/** 通过用户名查询我的文件信息(我的上传pass,我的待审核waitforcheck,我的未通过notpass,已删除文件Invalid)
	 * @author luxiang
	 * @param userName 我的用户名
	 * @param checkType 审核类型，请查看枚举
	 * @return 一个文件对象列表 FileInfo
	 */
	List<FileInfo> selectMyFileInfo(String userName, KCheckType checkType);
	
	//**********用于一些查询的方法**********
	/** 通过文件属性(标题,作者等等)模糊查询文件
	 * @author luxiang
	 * @param fileCategory 文件类别(若不指定,则传入null)
	 * @param filePropertyType 文件属性类型，请查看枚举
	 * @param value 文件属性类型的值
	 * @return 一个文件对象列表 FileInfo
	 */
	public List<FileInfo> getFileByLikeFileProperty(String fileCategory, KFilePropertyType filePropertyType, String value);
	
}
