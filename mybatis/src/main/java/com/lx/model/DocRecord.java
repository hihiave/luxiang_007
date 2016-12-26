package com.lx.model;

/**
 * 文档实体类
 */
public class DocRecord {
	private int id;
	private String fileName;
	private String docType;
	private int lastModify;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getDocType() {
		return docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
	}

	public int getLastModify() {
		return lastModify;
	}

	public void setLastModify(int lastModify) {
		this.lastModify = lastModify;
	}

}
