package com.mall.vo;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by root on 17-10-15.
 */
public class OrderVo {

    private Long VoOrderNo;
    private BigDecimal VoPayment;
    private Integer VoPaymentType;
    private String VoPaymentTypeDesc;
    private Integer VoPostage;
    private Integer VoStatus;
    private String VoStatusDesc;
    private String VoPaymentTime;
    private String VoSendTime;
    private String VoEndTime;
    private String VoCloseTime;
    private String VoCreateTime;

    //订单的明细
    private List<OrderItemVo> VoOrderItemVoList;

    private String VoImageHost;
    private Integer VoShippingId;
    private String VoReceiverName;

    private ShippingVo VoShippingVo;

    public Long getVoOrderNo() {
        return VoOrderNo;
    }

    public void setVoOrderNo(Long voOrderNo) {
        VoOrderNo = voOrderNo;
    }

    public BigDecimal getVoPayment() {
        return VoPayment;
    }

    public void setVoPayment(BigDecimal voPayment) {
        VoPayment = voPayment;
    }

    public Integer getVoPaymentType() {
        return VoPaymentType;
    }

    public void setVoPaymentType(Integer voPaymentType) {
        VoPaymentType = voPaymentType;
    }

    public String getVoPaymentTypeDesc() {
        return VoPaymentTypeDesc;
    }

    public void setVoPaymentTypeDesc(String voPaymentTypeDesc) {
        VoPaymentTypeDesc = voPaymentTypeDesc;
    }

    public Integer getVoPostage() {
        return VoPostage;
    }

    public void setVoPostage(Integer voPostage) {
        VoPostage = voPostage;
    }

    public Integer getVoStatus() {
        return VoStatus;
    }

    public void setVoStatus(Integer voStatus) {
        VoStatus = voStatus;
    }

    public String getVoStatusDesc() {
        return VoStatusDesc;
    }

    public void setVoStatusDesc(String voStatusDesc) {
        VoStatusDesc = voStatusDesc;
    }

    public String getVoPaymentTime() {
        return VoPaymentTime;
    }

    public void setVoPaymentTime(String voPaymentTime) {
        VoPaymentTime = voPaymentTime;
    }

    public String getVoSendTime() {
        return VoSendTime;
    }

    public void setVoSendTime(String voSendTime) {
        VoSendTime = voSendTime;
    }

    public String getVoEndTime() {
        return VoEndTime;
    }

    public void setVoEndTime(String voEndTime) {
        VoEndTime = voEndTime;
    }

    public String getVoCloseTime() {
        return VoCloseTime;
    }

    public void setVoCloseTime(String voCloseTime) {
        VoCloseTime = voCloseTime;
    }

    public String getVoCreateTime() {
        return VoCreateTime;
    }

    public void setVoCreateTime(String voCreateTime) {
        VoCreateTime = voCreateTime;
    }

    public List<OrderItemVo> getVoOrderItemVoList() {
        return VoOrderItemVoList;
    }

    public void setVoOrderItemVoList(List<OrderItemVo> voOrderItemVoList) {
        VoOrderItemVoList = voOrderItemVoList;
    }

    public String getVoImageHost() {
        return VoImageHost;
    }

    public void setVoImageHost(String voImageHost) {
        VoImageHost = voImageHost;
    }

    public Integer getVoShippingId() {
        return VoShippingId;
    }

    public void setVoShippingId(Integer voShippingId) {
        VoShippingId = voShippingId;
    }

    public String getVoReceiverName() {
        return VoReceiverName;
    }

    public void setVoReceiverName(String voReceiverName) {
        VoReceiverName = voReceiverName;
    }

    public ShippingVo getVoShippingVo() {
        return VoShippingVo;
    }

    public void setVoShippingVo(ShippingVo voShippingVo) {
        VoShippingVo = voShippingVo;
    }

}
