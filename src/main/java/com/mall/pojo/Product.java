package com.mall.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class Product {
    private Integer productId;

    private Integer productCategoryId;

    private String productName;

    private String productSubtitle;

    private String productMainImage;

    private String productSubImages;

    private String productDetail;

    private BigDecimal productPrice;

    private Integer productStock;

    private Integer productStatus;

    private String productAmazonUs;

    private String productAmazonUk;

    private String productAmazonFr;

    private String productAmazonDe;

    private String productAmazonJp;

    private String productAmazonCa;

    private String productAmazonAu;

    private Date productCreateTime;

    private Date productUpdateTime;

    public Product(Integer productId, Integer productCategoryId, String productName, String productSubtitle, String productMainImage, String productSubImages, String productDetail, BigDecimal productPrice, Integer productStock, Integer productStatus, String productAmazonUs, String productAmazonUk, String productAmazonFr, String productAmazonDe, String productAmazonJp, String productAmazonCa, String productAmazonAu, Date productCreateTime, Date productUpdateTime) {
        this.productId = productId;
        this.productCategoryId = productCategoryId;
        this.productName = productName;
        this.productSubtitle = productSubtitle;
        this.productMainImage = productMainImage;
        this.productSubImages = productSubImages;
        this.productDetail = productDetail;
        this.productPrice = productPrice;
        this.productStock = productStock;
        this.productStatus = productStatus;
        this.productAmazonUs = productAmazonUs;
        this.productAmazonUk = productAmazonUk;
        this.productAmazonFr = productAmazonFr;
        this.productAmazonDe = productAmazonDe;
        this.productAmazonJp = productAmazonJp;
        this.productAmazonCa = productAmazonCa;
        this.productAmazonAu = productAmazonAu;
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

    public String getProductSubImages() {
        return productSubImages;
    }

    public void setProductSubImages(String productSubImages) {
        this.productSubImages = productSubImages == null ? null : productSubImages.trim();
    }

    public String getProductDetail() {
        return productDetail;
    }

    public void setProductDetail(String productDetail) {
        this.productDetail = productDetail == null ? null : productDetail.trim();
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

    public String getProductAmazonUs() {
        return productAmazonUs;
    }

    public void setProductAmazonUs(String productAmazonUs) {
        this.productAmazonUs = productAmazonUs == null ? null : productAmazonUs.trim();
    }

    public String getProductAmazonUk() {
        return productAmazonUk;
    }

    public void setProductAmazonUk(String productAmazonUk) {
        this.productAmazonUk = productAmazonUk == null ? null : productAmazonUk.trim();
    }

    public String getProductAmazonFr() {
        return productAmazonFr;
    }

    public void setProductAmazonFr(String productAmazonFr) {
        this.productAmazonFr = productAmazonFr == null ? null : productAmazonFr.trim();
    }

    public String getProductAmazonDe() {
        return productAmazonDe;
    }

    public void setProductAmazonDe(String productAmazonDe) {
        this.productAmazonDe = productAmazonDe == null ? null : productAmazonDe.trim();
    }

    public String getProductAmazonJp() {
        return productAmazonJp;
    }

    public void setProductAmazonJp(String productAmazonJp) {
        this.productAmazonJp = productAmazonJp == null ? null : productAmazonJp.trim();
    }

    public String getProductAmazonCa() {
        return productAmazonCa;
    }

    public void setProductAmazonCa(String productAmazonCa) {
        this.productAmazonCa = productAmazonCa == null ? null : productAmazonCa.trim();
    }

    public String getProductAmazonAu() {
        return productAmazonAu;
    }

    public void setProductAmazonAu(String productAmazonAu) {
        this.productAmazonAu = productAmazonAu == null ? null : productAmazonAu.trim();
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