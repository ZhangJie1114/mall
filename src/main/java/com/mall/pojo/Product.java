package com.mall.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class Product {
    private Integer productId;

    private Integer productCategoryId;

    private String productName;

    private String productSubtitle;

    private String productMainImage;

    private BigDecimal productPrice;

    private Integer productStock;

    private Integer productStatus;

    private Date productCreateTime;

    private Date productUpdateTime;

    public Product(Integer productId, Integer productCategoryId, String productName, String productSubtitle, String productMainImage, BigDecimal productPrice, Integer productStock, Integer productStatus, Date productCreateTime, Date productUpdateTime) {
        this.productId = productId;
        this.productCategoryId = productCategoryId;
        this.productName = productName;
        this.productSubtitle = productSubtitle;
        this.productMainImage = productMainImage;
        this.productPrice = productPrice;
        this.productStock = productStock;
        this.productStatus = productStatus;
        this.productCreateTime = productCreateTime;
        this.productUpdateTime = productUpdateTime;
    }

    public Product() {
        super();
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(Integer productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public String getProductSubtitle() {
        return productSubtitle;
    }

    public void setProductSubtitle(String productSubtitle) {
        this.productSubtitle = productSubtitle == null ? null : productSubtitle.trim();
    }

    public String getProductMainImage() {
        return productMainImage;
    }

    public void setProductMainImage(String productMainImage) {
        this.productMainImage = productMainImage == null ? null : productMainImage.trim();
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getProductStock() {
        return productStock;
    }

    public void setProductStock(Integer productStock) {
        this.productStock = productStock;
    }

    public Integer getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(Integer productStatus) {
        this.productStatus = productStatus;
    }

    public Date getProductCreateTime() {
        return productCreateTime;
    }

    public void setProductCreateTime(Date productCreateTime) {
        this.productCreateTime = productCreateTime;
    }

    public Date getProductUpdateTime() {
        return productUpdateTime;
    }

    public void setProductUpdateTime(Date productUpdateTime) {
        this.productUpdateTime = productUpdateTime;
    }
}