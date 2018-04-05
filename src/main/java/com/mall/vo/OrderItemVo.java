package com.mall.vo;

import java.math.BigDecimal;

/**
 * Created by root on 17-10-15.
 */
public class OrderItemVo {

    private Long VoOrderNo;
    private Integer VoProductId;
    private String VoProductName;
    private String VoProductImage;
    private BigDecimal VoCurrentUnitPrice;
    private Integer VoQuantity;
    private BigDecimal VoTotalPrice;
    private String VoCreateTime;

    public Long getVoOrderNo() {
        return VoOrderNo;
    }

    public void setVoOrderNo(Long voOrderNo) {
        VoOrderNo = voOrderNo;
    }

    public Integer getVoProductId() {
        return VoProductId;
    }

    public void setVoProductId(Integer voProductId) {
        VoProductId = voProductId;
    }

    public String getVoProductName() {
        return VoProductName;
    }

    public void setVoProductName(String voProductName) {
        VoProductName = voProductName;
    }

    public String getVoProductImage() {
        return VoProductImage;
    }

    public void setVoProductImage(String voProductImage) {
        VoProductImage = voProductImage;
    }

    public BigDecimal getVoCurrentUnitPrice() {
        return VoCurrentUnitPrice;
    }

    public void setVoCurrentUnitPrice(BigDecimal voCurrentUnitPrice) {
        VoCurrentUnitPrice = voCurrentUnitPrice;
    }

    public Integer getVoQuantity() {
        return VoQuantity;
    }

    public void setVoQuantity(Integer voQuantity) {
        VoQuantity = voQuantity;
    }

    public BigDecimal getVoTotalPrice() {
        return VoTotalPrice;
    }

    public void setVoTotalPrice(BigDecimal voTotalPrice) {
        VoTotalPrice = voTotalPrice;
    }

    public String getVoCreateTime() {
        return VoCreateTime;
    }

    public void setVoCreateTime(String voCreateTime) {
        VoCreateTime = voCreateTime;
    }

}
