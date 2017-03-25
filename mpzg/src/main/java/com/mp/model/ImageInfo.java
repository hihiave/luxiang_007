package com.mp.model;

public class ImageInfo {
    private Integer imageId;

    private String imageKakou;

    private String imageDate;

    private String imageDisc;

    private Integer imageStatus;

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    public String getImageKakou() {
        return imageKakou;
    }

    public void setImageKakou(String imageKakou) {
        this.imageKakou = imageKakou == null ? null : imageKakou.trim();
    }

    public String getImageDate() {
        return imageDate;
    }

    public void setImageDate(String imageDate) {
        this.imageDate = imageDate == null ? null : imageDate.trim();
    }

    public String getImageDisc() {
        return imageDisc;
    }

    public void setImageDisc(String imageDisc) {
        this.imageDisc = imageDisc == null ? null : imageDisc.trim();
    }

    public Integer getImageStatus() {
        return imageStatus;
    }

    public void setImageStatus(Integer imageStatus) {
        this.imageStatus = imageStatus;
    }
}