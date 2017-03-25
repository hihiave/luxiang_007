package com.mp.model;

public class FileInfo {
    private Integer fileId;

    private String fileName;

    private String fileUrl;

    private String fileDisc;

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl == null ? null : fileUrl.trim();
    }

    public String getFileDisc() {
        return fileDisc;
    }

    public void setFileDisc(String fileDisc) {
        this.fileDisc = fileDisc == null ? null : fileDisc.trim();
    }
}