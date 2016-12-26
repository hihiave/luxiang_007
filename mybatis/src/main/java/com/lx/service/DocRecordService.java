package com.lx.service;

import com.lx.model.DocRecord;

public interface DocRecordService {

	public int createDoc(DocRecord docrec);

	public DocRecord getDocByFileName(String strName);

	/**
	 * 查询最新文档,获取时间戳s
	 */
	public int getLatestDocument(String doctype);

	public DocRecord getDocById(int id);

	public int deleteDocById(int id);

	public int deleteAllDoc();
}
