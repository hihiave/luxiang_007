package com.lx.model;

public class MyDownload {
    private Integer myDownloadId;

    private String myDownloadUserName;

    private Integer myDownloadFileId;

    private Integer myDownloadTime;

    private Integer myDownloadStatus;

    public Integer getMyDownloadId() {
        return myDownloadId;
    }

    public void setMyDownloadId(Integer myDownloadId) {
        this.myDownloadId = myDownloadId;
    }

    public String getMyDownloadUserName() {
        return myDownloadUserName;
    }

    public void setMyDownloadUserName(String myDownloadUserName) {
        this.myDownloadUserName = myDownloadUserName == null ? null : myDownloadUserName.trim();
    }

    public Integer getMyDownloadFileId() {
        return myDownloadFileId;
    }

    public void setMyDownloadFileId(Integer myDownloadFileId) {
        this.myDownloadFileId = myDownloadFileId;
    }

    public Integer getMyDownloadTime() {
        return myDownloadTime;
    }

    public void setMyDownloadTime(Integer myDownloadTime) {
        this.myDownloadTime = myDownloadTime;
    }

    public Integer getMyDownloadStatus() {
        return myDownloadStatus;
    }

    public void setMyDownloadStatus(Integer myDownloadStatus) {
        this.myDownloadStatus = myDownloadStatus;
    }
}