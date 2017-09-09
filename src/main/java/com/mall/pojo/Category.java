package com.mall.pojo;

import java.util.Date;

public class Category {
    private Integer categoryId;

    private Integer categoryParentId;

    private String categoryName;

    private Boolean categoryStatus;

    private Integer categorySortOrder;

    private Date categoryCreateTime;

    private Date categoryUpdateTime;

    public Category(Integer categoryId, Integer categoryParentId, String categoryName, Boolean categoryStatus, Integer categorySortOrder, Date categoryCreateTime, Date categoryUpdateTime) {
        this.categoryId = categoryId;
        this.categoryParentId = categoryParentId;
        this.categoryName = categoryName;
        this.categoryStatus = categoryStatus;
        this.categorySortOrder = categorySortOrder;
        this.categoryCreateTime = categoryCreateTime;
        this.categoryUpdateTime = categoryUpdateTime;
    }

    public Category() {
        super();
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getCategoryParentId() {
        return categoryParentId;
    }

    public void setCategoryParentId(Integer categoryParentId) {
        this.categoryParentId = categoryParentId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName == null ? null : categoryName.trim();
    }

    public Boolean getCategoryStatus() {
        return categoryStatus;
    }

    public void setCategoryStatus(Boolean categoryStatus) {
        this.categoryStatus = categoryStatus;
    }

    public Integer getCategorySortOrder() {
        return categorySortOrder;
    }

    public void setCategorySortOrder(Integer categorySortOrder) {
        this.categorySortOrder = categorySortOrder;
    }

    public Date getCategoryCreateTime() {
        return categoryCreateTime;
    }

    public void setCategoryCreateTime(Date categoryCreateTime) {
        this.categoryCreateTime = categoryCreateTime;
    }

    public Date getCategoryUpdateTime() {
        return categoryUpdateTime;
    }

    public void setCategoryUpdateTime(Date categoryUpdateTime) {
        this.categoryUpdateTime = categoryUpdateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        return categoryId != null ? categoryId.equals(category.categoryId) : category.categoryId == null;

    }

    @Override
    public int hashCode() {
        return categoryId != null ? categoryId.hashCode() : 0;
    }
}
