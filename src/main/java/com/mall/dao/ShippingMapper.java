package com.mall.dao;

import com.mall.pojo.Shipping;

public interface ShippingMapper {
    int deleteByPrimaryKey(Integer shippingId);

    int insert(Shipping record);

    int insertSelective(Shipping record);

    Shipping selectByPrimaryKey(Integer shippingId);

    int updateByPrimaryKeySelective(Shipping record);

    int updateByPrimaryKey(Shipping record);
}