package com.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.mall.common.ServerResponse;
import com.mall.dao.ShippingMapper;
import com.mall.pojo.Shipping;
import com.mall.service.IShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by root on 17-10-10.
 */
@Service("iShippingService")
public class ShippingServiceImpl implements IShippingService{

    @Autowired
    private ShippingMapper shippingMapper;

    public ServerResponse add(Integer userId, Shipping shipping){
        shipping.setShippingUserId(userId);
        int rowCount = shippingMapper.insert(shipping);
        if(rowCount > 0){
            //映射Map接口的 V put(K key, V value) 方法；K键的类型，V值的类型；如果键不存在，就返回null；否则返回之前与键关联的值。
            Map result = Maps.newHashMap();
            result.put("shippingId", shipping.getShippingId());
            return ServerResponse.createBySuccess("新建收货地址成功", result);
        }
        return ServerResponse.createByErrorMessage("新建收货地址失败");
    }

    public ServerResponse<String> delete(Integer userId, Integer shippingId){
        int resultCount = shippingMapper.deleteByPrimaryKey(shippingId);
        if(resultCount > 0){
            return ServerResponse.createBySuccess("删除收货地址成功");
        }
        return ServerResponse.createByErrorMessage("删除收货地址失败");
    }

    public ServerResponse<String> update(Integer userId, Shipping shipping){
        shipping.setShippingUserId(userId);
        //使用选择性更新updateByShipping,前端默认没有传入更新时间，需设置
        shipping.setShippingUpdateTime(new Date());
        int rowCount = shippingMapper.updateByShippingSelective(shipping);
        if(rowCount > 0){
            return ServerResponse.createBySuccess("更新收货地址成功");
        }
        return ServerResponse.createByErrorMessage("更新收货地址失败");
    }

    public ServerResponse<Shipping> select(Integer userId, Integer shippingId){
        Shipping shipping = shippingMapper.selectByShippingIdAndUserId(userId ,shippingId);
        if(shipping == null){
            return ServerResponse.createByErrorMessage("查询收货地址失败");
        }
        return ServerResponse.createBySuccess("查询收货地址成功", shipping);
    }

    public ServerResponse<PageInfo> list(Integer userId, int pageNum, int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<Shipping> shippingList = shippingMapper.selectByUserId(userId);
        PageInfo pageInfo = new PageInfo(shippingList);
        return ServerResponse.createBySuccess(pageInfo);
    }
}
