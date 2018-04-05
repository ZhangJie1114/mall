package com.mall.dao;

import com.mall.pojo.Shipping;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShippingMapper {
    int deleteByPrimaryKey(Integer shippingId);

    int insert(Shipping record);

    int insertSelective(Shipping record);

    Shipping selectByPrimaryKey(Integer shippingId);

    int updateByPrimaryKeySelective(Shipping record);

    int updateByPrimaryKey(Shipping record);

    int deleteByShippingIdAndUserId(@Param(value = "userId") Integer userId, @Param(value = "shippingId") Integer shippingId);

    int updateByShippingSelective(Shipping record);

    Shipping selectByShippingIdAndUserId(@Param(value = "userId") Integer userId, @Param(value = "shippingId") Integer shippingId);

    List<Shipping> selectByUserId(Integer userId);

}