package com.lx.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lx.dao.MyDownloadMapper;
import com.lx.model.FileInfoVo;
import com.lx.model.MyDownload;
import com.lx.service.MyDownloadService;
import com.lx.tools.Page;
import com.lx.tools.ToolDate;

@Service
public class MyDownloadServiceImpl implements MyDownloadService {

	@Autowired
	private MyDownloadMapper myDownloadMapper;

	@Override
	public boolean addMyDownload(String myDownloadUserName, int myDownloadFileId) {
		MyDownload myDownload = new MyDownload();
		myDownload.setMyDownloadUserName(myDownloadUserName);
		myDownload.setMyDownloadFileId(myDownloadFileId);
		myDownload.setMyDownloadTime(ToolDate.getCurrentTimestamp());
		if (myDownloadMapper.insertSelective(myDownload) == 1) {
			return true;
		}
		return false;
	}

	@Override
	public boolean delMyDownload(Integer... myDownloadIds) {
		if (myDownloadMapper.delMyDownloadByMyDownloadId(myDownloadIds) == myDownloadIds.length) {
			return true;
		}
		return false;
	}

	@Override
	public List<FileInfoVo> getMyDownload(String userName, Page page) {
		if (page != null && userName != null) {
			int totalCount = myDownloadMapper.selectMyDownloadByMyDownloadUserNameCount(userName);
			page.setTotalCount(totalCount);
			page.init();
			return myDownloadMapper.selectMyDownloadByMyDownloadUserName(userName.trim(), page);
		}
		return null;
	}

	@Override
	public boolean checkDownloadIsExist(String userName, int myDownloadFileId) {
		if (myDownloadMapper.selectByUserNameAndFildId(userName, myDownloadFileId) != null) {
			return true;
		}
		return false;
	}

}
