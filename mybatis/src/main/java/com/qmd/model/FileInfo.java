package com.qmd.model;

public class FileInfo {
    private Integer fileId;

    private String fileName;

    private String fileAuthor;

    private Integer fileUploadTime;

    private String fileKeywords;

    private String fileCategory;

    private String fileDesc;

    private String fileCheck;

    private String fileIsVisible;

    private Integer fileDownloadCount;

    private String fileUrl;

    private Integer fileStatus;

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

    public String getFileAuthor() {
        return fileAuthor;
    }

    public void setFileAuthor(String fileAuthor) {
        this.fileAuthor = fileAuthor == null ? null : fileAuthor.trim();
    }

    public Integer getFileUploadTime() {
        return fileUploadTime;
    }

    public void setFileUploadTime(Integer fileUploadTime) {
        this.fileUploadTime = fileUploadTime;
    }

    public String getFileKeywords() {
        return fileKeywords;
    }

    public void setFileKeywords(String fileKeywords) {
        this.fileKeywords = fileKeywords == null ? null : fileKeywords.trim();
    }

    public String getFileCategory() {
        return fileCategory;
    }

    public void setFileCategory(String fileCategory) {
        this.fileCategory = fileCategory == null ? null : fileCategory.trim();
    }

    public String getFileDesc() {
        return fileDesc;
    }

    public void setFileDesc(String fileDesc) {
        this.fileDesc = fileDesc == null ? null : fileDesc.trim();
    }

    public String getFileCheck() {
        return fileCheck;
    }

    public void setFileCheck(String fileCheck) {
        this.fileCheck = fileCheck == null ? null : fileCheck.trim();
    }

    public String getFileIsVisible() {
        return fileIsVisible;
    }

    public void setFileIsVisible(String fileIsVisible) {
        this.fileIsVisible = fileIsVisible == null ? null : fileIsVisible.trim();
    }

    public Integer getFileDownloadCount() {
        return fileDownloadCount;
    }

    public void setFileDownloadCount(Integer fileDownloadCount) {
        this.fileDownloadCount = fileDownloadCount;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl == null ? null : fileUrl.trim();
    }

    public Integer getFileStatus() {
        return fileStatus;
    }

    public void setFileStatus(Integer fileStatus) {
        this.fileStatus = fileStatus;
    }
}