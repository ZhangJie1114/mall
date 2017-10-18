package com.mall.vo;

import java.math.BigDecimal;

/**
 * Created by root on 9/10/17.
 */
public class ProductDetailVo {

    private Integer VoId;
    private Integer VoCategoryId;
    private String VoName;
    private String VoSubtitle;
    private String VoMainImage;
    private String VoSubImages;
    private String VoDetail;
    private BigDecimal VoPrice;
    private Integer VoStock;
    private Integer VoStatus;
    private String VoCreateTime;
    private String VoUpdateTime;

    private String VoImageHost;
    private Integer VoParentCategoryId;

    public Integer getVoId() {
        return VoId;
    }

    public void setVoId(Integer voId) {
        VoId = voId;
    }

    public Integer getVoCategoryId() {
        return VoCategoryId;
    }

    public void setVoCategoryId(Integer voCategoryId) {
        VoCategoryId = voCategoryId;
    }

    public String getVoName() {
        return VoName;
    }

    public void setVoName(String voName) {
        VoName = voName;
    }

    public String getVoSubtitle() {
        return VoSubtitle;
    }

    public void setVoSubtitle(String voSubtitle) {
        VoSubtitle = voSubtitle;
    }

    public String getVoMainImage() {
        return VoMainImage;
    }

    public void setVoMainImage(String voMainImage) {
        VoMainImage = voMainImage;
    }

    public String getVoSubImages() {
        return VoSubImages;
    }

    public void setVoSubImages(String voSubImages) {
        VoSubImages = voSubImages;
    }

    public String getVoDetail() {
        return VoDetail;
    }

    public void setVoDetail(String voDetail) {
        VoDetail = voDetail;
    }

    public BigDecimal getVoPrice() {
        return VoPrice;
    }

    public void setVoPrice(BigDecimal voPrice) {
        VoPrice = voPrice;
    }

    public Integer getVoStock() {
        return VoStock;
    }

    public void setVoStock(Integer voStock) {
        VoStock = voStock;
    }

    public Integer getVoStatus() {
        return VoStatus;
    }

    public void setVoStatus(Integer voStatus) {
        VoStatus = voStatus;
    }

    public String getVoCreateTime() {
        return VoCreateTime;
    }

    public void setVoCreateTime(String voCreateTime) {
        VoCreateTime = voCreateTime;
    }

    public String getVoUpdateTime() {
        return VoUpdateTime;
    }

    public void setVoUpdateTime(String voUpdateTime) {
        VoUpdateTime = voUpdateTime;
    }

    public String getVoImageHost() {
        return VoImageHost;
    }

    public void setVoImageHost(String voImageHost) {
        VoImageHost = voImageHost;
    }

    public Integer getVoParentCategoryId() {
        return VoParentCategoryId;
    }

    public void setVoParentCategoryId(Integer voParentCategoryId) {
        VoParentCategoryId = voParentCategoryId;
    }

}
