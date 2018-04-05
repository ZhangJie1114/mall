package com.mall.vo;

import java.math.BigDecimal;

/**
 * Created by root on 17-10-6.
 */
public class CartProductVo {

//结合了产品和购物车的一个抽象对象

    private Integer VoId;
    private Integer VoUserId;
    private Integer VoProductId;
    private Integer VoQuantity;//购物车中此商品的数量
    private String VoProductName;
    private String VoProductSubtitle;
    private String VoProductMainImage;
    private BigDecimal VoProductPrice;
    private Integer VoProductStatus;
    private BigDecimal VoProductTotalPrice;
    private Integer VoProductStock;
    private Integer VoProductChecked;//此商品是否勾选

    private String VoLimitQuantity;//限制数量的一个返回结果

    public Integer getVoId() {
        return VoId;
    }

    public void setVoId(Integer voId) {
        VoId = voId;
    }

    public Integer getVoUserId() {
        return VoUserId;
    }

    public void setVoUserId(Integer voUserId) {
        VoUserId = voUserId;
    }

    public Integer getVoProductId() {
        return VoProductId;
    }

    public void setVoProductId(Integer voProductId) {
        VoProductId = voProductId;
    }

    public Integer getVoQuantity() {
        return VoQuantity;
    }

    public void setVoQuantity(Integer voQuantity) {
        VoQuantity = voQuantity;
    }

    public String getVoProductName() {
        return VoProductName;
    }

    public void setVoProductName(String voProductName) {
        VoProductName = voProductName;
    }

    public String getVoProductSubtitle() {
        return VoProductSubtitle;
    }

    public void setVoProductSubtitle(String voProductSubtitle) {
        VoProductSubtitle = voProductSubtitle;
    }

    public String getVoProductMainImage() {
        return VoProductMainImage;
    }

    public void setVoProductMainImage(String voProductMainImage) {
        VoProductMainImage = voProductMainImage;
    }

    public BigDecimal getVoProductPrice() {
        return VoProductPrice;
    }

    public void setVoProductPrice(BigDecimal voProductPrice) {
        VoProductPrice = voProductPrice;
    }

    public Integer getVoProductStatus() {
        return VoProductStatus;
    }

    public void setVoProductStatus(Integer voProductStatus) {
        VoProductStatus = voProductStatus;
    }

    public BigDecimal getVoProductTotalPrice() {
        return VoProductTotalPrice;
    }

    public void setVoProductTotalPrice(BigDecimal voProductTotalPrice) {
        VoProductTotalPrice = voProductTotalPrice;
    }

    public Integer getVoProductStock() {
        return VoProductStock;
    }

    public void setVoProductStock(Integer voProductStock) {
        VoProductStock = voProductStock;
    }

    public Integer getVoProductChecked() {
        return VoProductChecked;
    }

    public void setVoProductChecked(Integer voProductChecked) {
        VoProductChecked = voProductChecked;
    }

    public String getVoLimitQuantity() {
        return VoLimitQuantity;
    }

    public void setVoLimitQuantity(String voLimitQuantity) {
        VoLimitQuantity = voLimitQuantity;
    }

}
