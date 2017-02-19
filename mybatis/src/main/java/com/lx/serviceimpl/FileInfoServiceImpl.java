package com.lx.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.lx.dao.FileInfoMapper;
import com.lx.macrofiles.MacroEnum.KCheckType;
import com.lx.macrofiles.MacroEnum.KFilePropertyType;
import com.lx.model.FileInfo;
import com.lx.service.FileInfoService;
import com.lx.tools.Page;

@Service
public class FileInfoServiceImpl implements FileInfoService {

	@Autowired
	private FileInfoMapper fileInfoMapper;

	// **********用于处理一些业务逻辑的方法**********
	@Override
	public boolean addFileInfo(FileInfo fileInfo) {
		if (fileInfoMapper.insertSelective(fileInfo) == 1) {
			return true;
		}
		return false;
	}

	@Override
	public boolean delFilesById(Integer... fileIds) {
		if (fileInfoMapper.delFilesById(fileIds) == fileIds.length) {
			return true;
		}
		return false;
	}

	@Override
	public FileInfo getFileByFileId(Integer fileId) {
		return fileInfoMapper.selectByPrimaryKey(fileId);
	}

	@Override
	public boolean updateFileByFileId(Integer fileId, FileInfo fileInfo) {
		fileInfo.setFileId(fileId);
		if (fileInfoMapper.updateByPrimaryKeySelective(fileInfo) == 1) {
			return true;
		}
		return false;
	}

	@Override
	public boolean alterFileCategroy(String fileAuthor, String oldFileCategory, String newFileCategory) {
		if (fileInfoMapper.updateFileCategory(fileAuthor, oldFileCategory, newFileCategory) < 0) {
			return false;
		}
		return true;
	}

//	@Override
//	public boolean checkFileIsExist(String fileName) {
//		if (fileInfoMapper.selectFileByFileName(fileName) != null) {
//			return true;
//		}
//		return false;
//	}

	@Override
	public boolean batchFilesIsPass(KCheckType checkType, Integer... fileIds) {
		if (fileIds.length == fileInfoMapper.updateFilesCheck(checkType.getValue(), fileIds)) {
			return true;
		}
		return false;
	}

	@Override
	public int getCountWithWaitForCheck() {
		return fileInfoMapper.getFileInfoCount(KCheckType.waitForCheck.getValue(), null);
	}

	@Override
	public int getCountWithInvalid(String userName) {
		return fileInfoMapper.selectFileByfileAuthorWithFileCheckCount(userName, KCheckType.invalid.getValue());
	}

	// **********用于一些查询的方法**********
	@Override
	public List<FileInfo> selectMyFileInfo(String userName, Page page, KCheckType... checkTypes) {
		if (page != null && userName != null) {
			Integer[] fileChecks = new Integer[checkTypes.length];
			for (int i = 0; i < checkTypes.length; i++) {
				fileChecks[i] = checkTypes[i].getValue();
			}
			int totalCount = fileInfoMapper.selectFileByfileAuthorWithFileCheckCount(userName.trim(), fileChecks);
			page.setTotalCount(totalCount);
			page.init();
			return fileInfoMapper.selectFileByFileAuthorWithFileCheck(userName.trim(), page, fileChecks);
		}
		return null;
	}

	@Override
	public List<FileInfo> selectMyFileInfoByCondition(String userName, String fileIsVisible, String fileCategory,
			Page page) {
		if (page != null && userName != null) {
			int totalCount = fileInfoMapper.selectFileByConditionCount(userName.trim(), fileIsVisible, fileCategory);
			page.setTotalCount(totalCount);
			page.init();
			return fileInfoMapper.selectFileByCondition(userName.trim(), fileIsVisible, fileCategory, page);
		}
		return null;
	}

	@Override
	public List<FileInfo> getFileByFilePropertyWithPass(String fileCategory, KFilePropertyType filePropertyType,
			String value, Page page) {
		if (value == null) {
			value = "";
		}
		if (page != null) {
			FileInfo fileInfo = new FileInfo();
			switch (filePropertyType) {
			case author:
				fileInfo.setFileAuthor(value.trim());
				break;
			case keyword:
				fileInfo.setFileKeywords(value.trim());
				break;
			default:
				fileInfo.setFileName(value.trim());
				break;
			}
			if (!"类别".equals(fileCategory)) {
				fileInfo.setFileCategory(fileCategory);
			}

			int totalCount = fileInfoMapper.getFileInfoCount(KCheckType.pass.getValue(), fileInfo);
			page.setTotalCount(totalCount);
			page.init();
			return fileInfoMapper.getFileInfo(KCheckType.pass.getValue(), fileInfo, page);
		}
		return null;
	}

	@Override
	public List<FileInfo> getFileWithWaitForCheck(Page page) {
		if (page != null) {
			int totalCount = getCountWithWaitForCheck();
			page.setTotalCount(totalCount);
			page.init();
			return fileInfoMapper.getFileInfo(KCheckType.waitForCheck.getValue(), null, page);
		}
		return null;
	}

	// **********用于获取一些智能下拉提示**********
	@Override
	public List<String> getIntelligentPrompt(KFilePropertyType filePropertyType, String value) {
		if (value == null) {
			value = "";
		}
		switch (filePropertyType) {
		case author:
			return fileInfoMapper.getfileAuthors(value.trim());
		case keyword:
			return null;
		default:
			return fileInfoMapper.getFileNames(value.trim());
		}
	}

}
