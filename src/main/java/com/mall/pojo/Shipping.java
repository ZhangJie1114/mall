package com.mall.pojo;

import java.util.Date;

public class Shipping {
    private Integer shippingId;

    private Integer shippingUserId;

    private String shippingReceiverName;

    private String shippingReceiverPhone;

    private String shippingReceiverMobile;

    private String shippingReceiverProvince;

    private String shippingReceiverCity;

    private String shippingReceiverDistrict;

    private String shippingReceiverAddress;

    private String shippingReceiverZip;

    private Date shippingCreateTime;

    private Date shippingUpdateTime;

    public Shipping(Integer shippingId, Integer shippingUserId, String shippingReceiverName, String shippingReceiverPhone, String shippingReceiverMobile, String shippingReceiverProvince, String shippingReceiverCity, String shippingReceiverDistrict, String shippingReceiverAddress, String shippingReceiverZip, Date shippingCreateTime, Date shippingUpdateTime) {
        this.shippingId = shippingId;
        this.shippingUserId = shippingUserId;
        this.shippingReceiverName = shippingReceiverName;
        this.shippingReceiverPhone = shippingReceiverPhone;
        this.shippingReceiverMobile = shippingReceiverMobile;
        this.shippingReceiverProvince = shippingReceiverProvince;
        this.shippingReceiverCity = shippingReceiverCity;
        this.shippingReceiverDistrict = shippingReceiverDistrict;
        this.shippingReceiverAddress = shippingReceiverAddress;
        this.shippingReceiverZip = shippingReceiverZip;
        this.shippingCreateTime = shippingCreateTime;
        this.shippingUpdateTime = shippingUpdateTime;
    }

    public Shipping() {
        super();
    }

    public Integer getShippingId() {
        return shippingId;
    }

    public void setShippingId(Integer shippingId) {
        this.shippingId = shippingId;
    }

    public Integer getShippingUserId() {
        return shippingUserId;
    }

    public void setShippingUserId(Integer shippingUserId) {
        this.shippingUserId = shippingUserId;
    }

    public String getShippingReceiverName() {
        return shippingReceiverName;
    }

    public void setShippingReceiverName(String shippingReceiverName) {
        this.shippingReceiverName = shippingReceiverName == null ? null : shippingReceiverName.trim();
    }

    public String getShippingReceiverPhone() {
        return shippingReceiverPhone;
    }

    public void setShippingReceiverPhone(String shippingReceiverPhone) {
        this.shippingReceiverPhone = shippingReceiverPhone == null ? null : shippingReceiverPhone.trim();
    }

    public String getShippingReceiverMobile() {
        return shippingReceiverMobile;
    }

    public void setShippingReceiverMobile(String shippingReceiverMobile) {
        this.shippingReceiverMobile = shippingReceiverMobile == null ? null : shippingReceiverMobile.trim();
    }

    public String getShippingReceiverProvince() {
        return shippingReceiverProvince;
    }

    public void setShippingReceiverProvince(String shippingReceiverProvince) {
        this.shippingReceiverProvince = shippingReceiverProvince == null ? null : shippingReceiverProvince.trim();
    }

    public String getShippingReceiverCity() {
        return shippingReceiverCity;
    }

    public void setShippingReceiverCity(String shippingReceiverCity) {
        this.shippingReceiverCity = shippingReceiverCity == null ? null : shippingReceiverCity.trim();
    }

    public String getShippingReceiverDistrict() {
        return shippingReceiverDistrict;
    }

    public void setShippingReceiverDistrict(String shippingReceiverDistrict) {
        this.shippingReceiverDistrict = shippingReceiverDistrict == null ? null : shippingReceiverDistrict.trim();
    }

    public String getShippingReceiverAddress() {
        return shippingReceiverAddress;
    }

    public void setShippingReceiverAddress(String shippingReceiverAddress) {
        this.shippingReceiverAddress = shippingReceiverAddress == null ? null : shippingReceiverAddress.trim();
    }

    public String getShippingReceiverZip() {
        return shippingReceiverZip;
    }

    public void setShippingReceiverZip(String shippingReceiverZip) {
        this.shippingReceiverZip = shippingReceiverZip == null ? null : shippingReceiverZip.trim();
    }

    public Date getShippingCreateTime() {
        return shippingCreateTime;
    }

    public void setShippingCreateTime(Date shippingCreateTime) {
        this.shippingCreateTime = shippingCreateTime;
    }

    public Date getShippingUpdateTime() {
        return shippingUpdateTime;
    }

    public void setShippingUpdateTime(Date shippingUpdateTime) {
        this.shippingUpdateTime = shippingUpdateTime;
    }
}