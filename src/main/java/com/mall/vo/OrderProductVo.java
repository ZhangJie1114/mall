package com.mall.vo;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by root on 17-10-16.
 */
public class OrderProductVo {

    private List<OrderItemVo> VoOrderItemVoList;
    private BigDecimal VoProductTotalPrice;
    private String VoImageHost;

    public List<OrderItemVo> getVoOrderItemVoList() {
        return VoOrderItemVoList;
    }

    public void setVoOrderItemVoList(List<OrderItemVo> voOrderItemVoList) {
        VoOrderItemVoList = voOrderItemVoList;
    }

    public BigDecimal getVoProductTotalPrice() {
        return VoProductTotalPrice;
    }

    public void setVoProductTotalPrice(BigDecimal voProductTotalPrice) {
        VoProductTotalPrice = voProductTotalPrice;
    }

    public String getVoImageHost() {
        return VoImageHost;
    }

    public void setVoImageHost(String voImageHost) {
        VoImageHost = voImageHost;
    }

}
