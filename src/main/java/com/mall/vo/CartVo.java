package com.mall.vo;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by root on 17-10-6.
 */
public class CartVo {

    private List<CartProductVo> VoCartProductVoList;
    private BigDecimal VoCartTotalPrice;
    private Boolean VoAllChecked;//是否已经都勾选
    private String VoImageHost;

    public List<CartProductVo> getVoCartProductVoList() {
        return VoCartProductVoList;
    }

    public void setVoCartProductVoList(List<CartProductVo> voCartProductVoList) {
        VoCartProductVoList = voCartProductVoList;
    }

    public BigDecimal getVoCartTotalPrice() {
        return VoCartTotalPrice;
    }

    public void setVoCartTotalPrice(BigDecimal voCartTotalPrice) {
        VoCartTotalPrice = voCartTotalPrice;
    }

    public Boolean getVoAllChecked() {
        return VoAllChecked;
    }

    public void setVoAllChecked(Boolean voAllChecked) {
        VoAllChecked = voAllChecked;
    }

    public String getVoImageHost() {
        return VoImageHost;
    }

    public void setVoImageHost(String voImageHost) {
        VoImageHost = voImageHost;
    }

}

