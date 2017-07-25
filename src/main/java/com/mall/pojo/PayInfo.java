package com.mall.pojo;

import java.util.Date;

public class PayInfo {
    private Integer payInfoId;

    private Integer payInfoUserId;

    private Long payInfoOrderNo;

    private Integer payInfoPayPlatform;

    private String payInfoPlatformNumber;

    private String payInfoPlatformStatus;

    private Date payInfoCreateTime;

    private Date payInfoUpdateTime;

    public PayInfo(Integer payInfoId, Integer payInfoUserId, Long payInfoOrderNo, Integer payInfoPayPlatform, String payInfoPlatformNumber, String payInfoPlatformStatus, Date payInfoCreateTime, Date payInfoUpdateTime) {
        this.payInfoId = payInfoId;
        this.payInfoUserId = payInfoUserId;
        this.payInfoOrderNo = payInfoOrderNo;
        this.payInfoPayPlatform = payInfoPayPlatform;
        this.payInfoPlatformNumber = payInfoPlatformNumber;
        this.payInfoPlatformStatus = payInfoPlatformStatus;
        this.payInfoCreateTime = payInfoCreateTime;
        this.payInfoUpdateTime = payInfoUpdateTime;
    }

    public PayInfo() {
        super();
    }

    public Integer getPayInfoId() {
        return payInfoId;
    }

    public void setPayInfoId(Integer payInfoId) {
        this.payInfoId = payInfoId;
    }

    public Integer getPayInfoUserId() {
        return payInfoUserId;
    }

    public void setPayInfoUserId(Integer payInfoUserId) {
        this.payInfoUserId = payInfoUserId;
    }

    public Long getPayInfoOrderNo() {
        return payInfoOrderNo;
    }

    public void setPayInfoOrderNo(Long payInfoOrderNo) {
        this.payInfoOrderNo = payInfoOrderNo;
    }

    public Integer getPayInfoPayPlatform() {
        return payInfoPayPlatform;
    }

    public void setPayInfoPayPlatform(Integer payInfoPayPlatform) {
        this.payInfoPayPlatform = payInfoPayPlatform;
    }

    public String getPayInfoPlatformNumber() {
        return payInfoPlatformNumber;
    }

    public void setPayInfoPlatformNumber(String payInfoPlatformNumber) {
        this.payInfoPlatformNumber = payInfoPlatformNumber == null ? null : payInfoPlatformNumber.trim();
    }

    public String getPayInfoPlatformStatus() {
        return payInfoPlatformStatus;
    }

    public void setPayInfoPlatformStatus(String payInfoPlatformStatus) {
        this.payInfoPlatformStatus = payInfoPlatformStatus == null ? null : payInfoPlatformStatus.trim();
    }

    public Date getPayInfoCreateTime() {
        return payInfoCreateTime;
    }

    public void setPayInfoCreateTime(Date payInfoCreateTime) {
        this.payInfoCreateTime = payInfoCreateTime;
    }

    public Date getPayInfoUpdateTime() {
        return payInfoUpdateTime;
    }

    public void setPayInfoUpdateTime(Date payInfoUpdateTime) {
        this.payInfoUpdateTime = payInfoUpdateTime;
    }
}