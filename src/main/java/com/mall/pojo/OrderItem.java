package com.mall.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class OrderItem {
    private Integer orderItemId;

    private Integer orderItemUserId;

    private Long orderItemOrderNo;

    private Integer orderItemProductId;

    private String orderItemProductName;

    private String orderItemProductImage;

    private BigDecimal orderItemCurrentUnitPrice;

    private Integer orderItemQuantity;

    private BigDecimal orderItemTotalPrice;

    private Date orderItemCreateTime;

    private Date orderItemUpdateTime;

    public OrderItem(Integer orderItemId, Integer orderItemUserId, Long orderItemOrderNo, Integer orderItemProductId, String orderItemProductName, String orderItemProductImage, BigDecimal orderItemCurrentUnitPrice, Integer orderItemQuantity, BigDecimal orderItemTotalPrice, Date orderItemCreateTime, Date orderItemUpdateTime) {
        this.orderItemId = orderItemId;
        this.orderItemUserId = orderItemUserId;
        this.orderItemOrderNo = orderItemOrderNo;
        this.orderItemProductId = orderItemProductId;
        this.orderItemProductName = orderItemProductName;
        this.orderItemProductImage = orderItemProductImage;
        this.orderItemCurrentUnitPrice = orderItemCurrentUnitPrice;
        this.orderItemQuantity = orderItemQuantity;
        this.orderItemTotalPrice = orderItemTotalPrice;
        this.orderItemCreateTime = orderItemCreateTime;
        this.orderItemUpdateTime = orderItemUpdateTime;
    }

    public OrderItem() {
        super();
    }

    public Integer getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Integer orderItemId) {
        this.orderItemId = orderItemId;
    }

    public Integer getOrderItemUserId() {
        return orderItemUserId;
    }

    public void setOrderItemUserId(Integer orderItemUserId) {
        this.orderItemUserId = orderItemUserId;
    }

    public Long getOrderItemOrderNo() {
        return orderItemOrderNo;
    }

    public void setOrderItemOrderNo(Long orderItemOrderNo) {
        this.orderItemOrderNo = orderItemOrderNo;
    }

    public Integer getOrderItemProductId() {
        return orderItemProductId;
    }

    public void setOrderItemProductId(Integer orderItemProductId) {
        this.orderItemProductId = orderItemProductId;
    }

    public String getOrderItemProductName() {
        return orderItemProductName;
    }

    public void setOrderItemProductName(String orderItemProductName) {
        this.orderItemProductName = orderItemProductName == null ? null : orderItemProductName.trim();
    }

    public String getOrderItemProductImage() {
        return orderItemProductImage;
    }

    public void setOrderItemProductImage(String orderItemProductImage) {
        this.orderItemProductImage = orderItemProductImage == null ? null : orderItemProductImage.trim();
    }

    public BigDecimal getOrderItemCurrentUnitPrice() {
        return orderItemCurrentUnitPrice;
    }

    public void setOrderItemCurrentUnitPrice(BigDecimal orderItemCurrentUnitPrice) {
        this.orderItemCurrentUnitPrice = orderItemCurrentUnitPrice;
    }

    public Integer getOrderItemQuantity() {
        return orderItemQuantity;
    }

    public void setOrderItemQuantity(Integer orderItemQuantity) {
        this.orderItemQuantity = orderItemQuantity;
    }

    public BigDecimal getOrderItemTotalPrice() {
        return orderItemTotalPrice;
    }

    public void setOrderItemTotalPrice(BigDecimal orderItemTotalPrice) {
        this.orderItemTotalPrice = orderItemTotalPrice;
    }

    public Date getOrderItemCreateTime() {
        return orderItemCreateTime;
    }

    public void setOrderItemCreateTime(Date orderItemCreateTime) {
        this.orderItemCreateTime = orderItemCreateTime;
    }

    public Date getOrderItemUpdateTime() {
        return orderItemUpdateTime;
    }

    public void setOrderItemUpdateTime(Date orderItemUpdateTime) {
        this.orderItemUpdateTime = orderItemUpdateTime;
    }
}