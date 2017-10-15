package com.mall.controller.portal;

/**
 * Created by root on 17-10-12.
 */

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.demo.trade.config.Configs;
import com.google.common.collect.Maps;
import com.mall.common.Const;
import com.mall.common.ResponseCode;
import com.mall.common.ServerResponse;
import com.mall.pojo.User;
import com.mall.service.IOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Iterator;
import java.util.Map;

@Controller
@RequestMapping("/order/")
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private IOrderService iOrderService;

    @RequestMapping(value = "pay.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse pay(HttpSession session, Long orderNo, HttpServletRequest request){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }
        String path = request.getSession().getServletContext().getRealPath("upload");
        return iOrderService.pay(orderNo, user.getUserId(), path);
    }

    @RequestMapping("alipay_callback.do")
    @ResponseBody
    public Object alipayCallbcak(HttpServletRequest request){
        //获取支付宝回调
        Map<String, String> params = Maps.newHashMap();
        Map requestParams = request.getParameterMap();
        for(Iterator iter = requestParams.keySet().iterator();iter.hasNext();){
            String name = (String)iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for(int i = 0; i < values.length; i++){
                valueStr = (i == values.length - 1)?valueStr + values[i] : valueStr + values[i] + ",";
            }
            params.put(name, valueStr);
        }
        logger.info("支付宝回调,sign:{},trade_status:{},参数:{}", params.get("sign"), params.get("trade_status"), params.toString());

        //验证回调的正确性，是否支付宝发的，且要避免重复通知。
        //异步返回结果的验签第一步：在通知返回参数列表中，除去sign、sign_type两个参数外，凡是通知返回回来的参数皆是待验签的参数。
        //所以回调信息列表中需要除去sign和sign_type，其中sign支付宝SDK已经做了处理。在com.alipay.api.internal.util.AlipaySignature.getSignCheckContentV2()
        params.remove("sign_type");
        try{
            //异步返回结果的验签第二步：将剩下参数进行url_decode, 然后进行字典排序，组成字符串，得到待签名字符串。
            //支付宝SDK已经做了处理,在Collections.sort();
            //异步返回结果的验签第三步：将签名参数（sign）使用base64解码为字节码串。
            //支付宝SDK已经做了处理,在rsaCheck();
            //异步返回结果的验签第四步： 使用RSA的验签方法，通过签名字符串、签名参数（经过base64解码）及支付宝公钥验证签名。
            //支付宝SDK已经做了处理,rsa256CheckContent(),此处才是RSA2(SHA256)密钥类型;
            boolean alipayRSACheckedV2 = AlipaySignature.rsaCheckV2(params, Configs.getAlipayPublicKey(), "utf-8", Configs.getSignType());
            if(!alipayRSACheckedV2){
                return ServerResponse.createByErrorMessage("非法请求！验证不通过！请不要搞事情！");
            }
        }catch(AlipayApiException e) {
            logger.error("支付宝验证回调异常", e);
        }

        //异步返回结果的验签第五步：需要严格按照如下描述校验通知数据的正确性。
        //商户需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
        // 并判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
        // 同时需要校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的
        // 对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email），
        // 上述有任何一个验证不通过，则表明本次通知是异常通知，务必忽略。
        // 在上述验证通过后商户必须根据支付宝不同类型的业务通知，正确的进行不同的业务处理，
        // 并且过滤重复的通知结果数据。在支付宝的业务通知中，只有交易通知状态为TRADE_SUCCESS或TRADE_FINISHED时，
        // 支付宝才会认定为买家付款成功。
        //todo 验证各种数据



        ServerResponse serverResponse = iOrderService.aliCallback(params);
        if(serverResponse.isSuccess()){
            return Const.AlipayCallback.RESPONSE_SUCCESS;
        }
        return Const.AlipayCallback.RESPONSE_FAILED;
    }

    @RequestMapping(value = "query_order_pay_status.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<Boolean> queryOrderPayStatus(HttpSession session, Long orderNo){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }

        ServerResponse serverResponse = iOrderService.queryOrderPayStatus(user.getUserId(), orderNo);
        if(serverResponse.isSuccess()){
            return ServerResponse.createBySuccess(true);
        }
        return ServerResponse.createBySuccess(false);
    }
}
