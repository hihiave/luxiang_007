package com.lx.model;

import java.io.Serializable;

/**
 * 文档实体Bean
 */
public class DocumentEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String filename;
	private String type;
	private String fileUrl;
	private String fileKeywords;
	private String fileDesc;
	private String contents;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	// get & set
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public String getFileKeywords() {
		return fileKeywords;
	}

	public void setFileKeywords(String fileKeywords) {
		this.fileKeywords = fileKeywords;
	}

	public String getFileDesc() {
		return fileDesc;
	}

	public void setFileDesc(String fileDesc) {
		this.fileDesc = fileDesc;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

}
