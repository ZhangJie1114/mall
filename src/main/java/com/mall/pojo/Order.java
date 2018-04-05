package com.mall.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class Order {
    private Integer orderId;

    private Long orderNo;

    private Integer orderUserId;

    private Integer orderShippingId;

    private BigDecimal orderPayment;

    private Integer orderPaymentType;

    private Integer orderPostage;

    private Integer orderStatus;

    private Date orderPaymentTime;

    private Date orderSendTime;

    private Date orderEndTime;

    private Date orderCloseTime;

    private Date orderCreateTime;

    private Date orderUpdateTime;

    public Order(Integer orderId, Long orderNo, Integer orderUserId, Integer orderShippingId, BigDecimal orderPayment, Integer orderPaymentType, Integer orderPostage, Integer orderStatus, Date orderPaymentTime, Date orderSendTime, Date orderEndTime, Date orderCloseTime, Date orderCreateTime, Date orderUpdateTime) {
        this.orderId = orderId;
        this.orderNo = orderNo;
        this.orderUserId = orderUserId;
        this.orderShippingId = orderShippingId;
        this.orderPayment = orderPayment;
        this.orderPaymentType = orderPaymentType;
        this.orderPostage = orderPostage;
        this.orderStatus = orderStatus;
        this.orderPaymentTime = orderPaymentTime;
        this.orderSendTime = orderSendTime;
        this.orderEndTime = orderEndTime;
        this.orderCloseTime = orderCloseTime;
        this.orderCreateTime = orderCreateTime;
        this.orderUpdateTime = orderUpdateTime;
    }

    public Order() {
        super();
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getOrderUserId() {
        return orderUserId;
    }

    public void setOrderUserId(Integer orderUserId) {
        this.orderUserId = orderUserId;
    }

    public Integer getOrderShippingId() {
        return orderShippingId;
    }

    public void setOrderShippingId(Integer orderShippingId) {
        this.orderShippingId = orderShippingId;
    }

    public BigDecimal getOrderPayment() {
        return orderPayment;
    }

    public void setOrderPayment(BigDecimal orderPayment) {
        this.orderPayment = orderPayment;
    }

    public Integer getOrderPaymentType() {
        return orderPaymentType;
    }

    public void setOrderPaymentType(Integer orderPaymentType) {
        this.orderPaymentType = orderPaymentType;
    }

    public Integer getOrderPostage() {
        return orderPostage;
    }

    public void setOrderPostage(Integer orderPostage) {
        this.orderPostage = orderPostage;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getOrderPaymentTime() {
        return orderPaymentTime;
    }

    public void setOrderPaymentTime(Date orderPaymentTime) {
        this.orderPaymentTime = orderPaymentTime;
    }

    public Date getOrderSendTime() {
        return orderSendTime;
    }

    public void setOrderSendTime(Date orderSendTime) {
        this.orderSendTime = orderSendTime;
    }

    public Date getOrderEndTime() {
        return orderEndTime;
    }

    public void setOrderEndTime(Date orderEndTime) {
        this.orderEndTime = orderEndTime;
    }

    public Date getOrderCloseTime() {
        return orderCloseTime;
    }

    public void setOrderCloseTime(Date orderCloseTime) {
        this.orderCloseTime = orderCloseTime;
    }

    public Date getOrderCreateTime() {
        return orderCreateTime;
    }

    public void setOrderCreateTime(Date orderCreateTime) {
        this.orderCreateTime = orderCreateTime;
    }

    public Date getOrderUpdateTime() {
        return orderUpdateTime;
    }

    public void setOrderUpdateTime(Date orderUpdateTime) {
        this.orderUpdateTime = orderUpdateTime;
    }
}