package com.lx.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lx.dao.FileInfoMapper;
import com.lx.macrofiles.MacroEnum.KCheckType;
import com.lx.macrofiles.MacroEnum.KFilePropertyType;
import com.lx.model.FileInfo;
import com.lx.service.FileInfoService;

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
	public List<FileInfo> getFileByFileId(Integer... fileIds) {
		return fileInfoMapper.selectAllFileByFileId(fileIds);
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
	public boolean checkFileIsExist(String fileName) {
		if (fileInfoMapper.selectFileByFileName(fileName) != null) {
			return true;
		}
		return false;
	}

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
	public List<FileInfo> getFileByFilePropertyWithPass(String fileCategory, KFilePropertyType filePropertyType,
			String value, Page page) {
		if (page != null && value != null) {
			FileInfo fileInfo = new FileInfo();
			switch (filePropertyType) {
			case title:
				fileInfo.setFileName(value.trim());
				break;
			case author:
				fileInfo.setFileAuthor(value.trim());
				break;
			case keyword:
				fileInfo.setFileKeywords(value.trim());
				break;
			default:
				break;
			}
			fileInfo.setFileCategory(fileCategory);
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

}
