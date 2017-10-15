package com.mall.service;

import com.mall.common.ServerResponse;

import java.util.Map;

/**
 * Created by root on 17-10-12.
 */
public interface IOrderService {

    ServerResponse pay(Long orderNo, Integer userId, String path);

    ServerResponse aliCallback(Map<String, String> params);

    ServerResponse queryOrderPayStatus(Integer userId, Long orderNo);

}
