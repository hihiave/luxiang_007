package com.lx.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.lx.dao.DocRecordMapper;
import com.lx.model.DocRecord;
import com.lx.service.DocRecordService;

@Service("docRecordService")
public class DocRecordServiceImpl implements DocRecordService {

	@Autowired
	private DocRecordMapper docRecordMapper;

	// 删除数据库文档记录
	public int deleteAllDoc() {
		return docRecordMapper.deleteAllDoc();
	}

	// 创建数据库文档记录**************************
	public int createDoc(DocRecord docrec) {
		return docRecordMapper.createDoc(docrec);
	}

	public DocRecord getDocByFileName(String strName) {
		return docRecordMapper.getDocByFileName(strName);
	}

	// 查询最新文档**************************
	public int getLatestDocument(String doctype) {
		DocRecord docRecord = docRecordMapper.getDocTopOne(doctype);
		System.out.println("=====查询最新文档==========" + JSON.toJSONString(docRecord));
		if (docRecord != null) {
			return docRecord.getLastModify();
		}
		return 0;
	}

	public DocRecord getDocById(int id) {
		return docRecordMapper.getDocById(id);
	}

	// 根据ID删除数据库文档记录
	public int deleteDocById(int id) {
		return docRecordMapper.deleteDocById(id);
	}
}
