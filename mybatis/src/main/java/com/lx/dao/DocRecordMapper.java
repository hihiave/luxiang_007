package com.lx.dao;

import com.lx.model.DocRecord;

public interface DocRecordMapper {
	public int createDoc(DocRecord docrec);

	public DocRecord getDocByFileName(String strName);

	public DocRecord getDocTopOne(String doctype);

	public DocRecord getDocById(int id);

	public int deleteDocById(int id);

	public int deleteAllDoc();
}