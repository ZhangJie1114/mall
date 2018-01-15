package com.mall.service;

import com.github.pagehelper.PageInfo;
import com.mall.common.ServerResponse;
import com.mall.vo.OrderVo;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * Created by root on 17-10-12.
 */
public interface IOrderService {

    ServerResponse pay(Long orderNo, Integer userId, String path);

    ServerResponse aliCallback(Map<String, String> params);

    ServerResponse queryOrderPayStatus(Integer userId, Long orderNo);

    ServerResponse createOrder(Integer userId, Integer shippingId);

    ServerResponse cancelOrder(Integer userId, Long orderNo);

    ServerResponse getOrderCartProduct(Integer userId);

    ServerResponse<OrderVo> getOrderDetail(Integer userId, Long orderNo);

    ServerResponse<PageInfo> getOrderList(Integer userId, int pageNum, int pageSize);

    //backend

    ServerResponse<PageInfo> manageOrderList(int pageNum, int PageSize);

    ServerResponse<OrderVo> manageOrderDetail(Long orderNo);

    ServerResponse<PageInfo> manageOrderSearch(Long orderNo, int pageNum, int pageSize);

    ServerResponse<String> manageOrderSendGoods(Long orderNo);

}
