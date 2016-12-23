package com.lx.model;

public class Category {
    private Integer categoryId;

    private Integer categoryUserId;

    private String categoryName;

    private String categoryBelongTo;

    private Integer categoryStatus;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getCategoryUserId() {
        return categoryUserId;
    }

    public void setCategoryUserId(Integer categoryUserId) {
        this.categoryUserId = categoryUserId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName == null ? null : categoryName.trim();
    }

    public String getCategoryBelongTo() {
        return categoryBelongTo;
    }

    public void setCategoryBelongTo(String categoryBelongTo) {
        this.categoryBelongTo = categoryBelongTo == null ? null : categoryBelongTo.trim();
    }

    public Integer getCategoryStatus() {
        return categoryStatus;
    }

    public void setCategoryStatus(Integer categoryStatus) {
        this.categoryStatus = categoryStatus;
    }
}