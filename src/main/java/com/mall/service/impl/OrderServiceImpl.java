package com.mall.service.impl;

import com.alipay.api.AlipayResponse;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.alipay.demo.trade.config.Configs;
import com.alipay.demo.trade.model.ExtendParams;
import com.alipay.demo.trade.model.GoodsDetail;
import com.alipay.demo.trade.model.builder.AlipayTradePrecreateRequestBuilder;
import com.alipay.demo.trade.model.result.AlipayF2FPrecreateResult;
import com.alipay.demo.trade.service.AlipayTradeService;
import com.alipay.demo.trade.service.impl.AlipayTradeServiceImpl;
import com.alipay.demo.trade.utils.ZxingUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mall.common.Const;
import com.mall.common.ServerResponse;
import com.mall.dao.*;
import com.mall.pojo.*;
import com.mall.service.IOrderService;
import com.mall.util.BigDecimalUtil;
import com.mall.util.DateTimeUtil;
import com.mall.util.FTPUtil;
import com.mall.util.PropertiesUtil;
import com.mall.vo.OrderItemVo;
import com.mall.vo.OrderProductVo;
import com.mall.vo.OrderVo;
import com.mall.vo.ShippingVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by root on 17-10-12.
 */
@Service("iOrderService")
public class OrderServiceImpl implements IOrderService {

    //支付日志
    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    // 从Demo的Main拿过来略修改
    private static  AlipayTradeService tradeService;
    static {

        /** 一定要在创建AlipayTradeService之前调用Configs.init()设置默认参数
         *  Configs会读取classpath下的zfbinfo.properties文件配置信息，如果找不到该文件则确认该文件是否在classpath目录
         */
        Configs.init("zfbinfo.properties");

        /** 使用Configs提供的默认参数
         *  AlipayTradeService可以使用单例或者为静态成员对象，不需要反复new
         */
        tradeService = new AlipayTradeServiceImpl.ClientBuilder().build();
    }

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private PayInfoMapper payInfoMapper;

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ShippingMapper shippingMapper;

    public ServerResponse createOrder(Integer userId, Integer shippingId){
        //从购物车中获取数据
        List<Cart> cartList = cartMapper.selectCheckedCartByUserId(userId);

        //计算这个订单的总价格
        ServerResponse serverResponse = this.getCartOrderItem(userId, cartList);
        if(!serverResponse.isSuccess()){
            return serverResponse;
        }
        //强转
        List<OrderItem> orderItemList = (List<OrderItem>)serverResponse.getData();
        BigDecimal payment = this.getOrderTotalPrice(orderItemList);

        //生成订单
        Order order = this.assembleOrder(userId, shippingId, payment);
        if(order == null){
            return ServerResponse.createByErrorMessage("生成订单错误");
        }

        if(CollectionUtils.isEmpty(orderItemList)){
            return ServerResponse.createByErrorMessage("购物车为空");
        }
        for(OrderItem orderItem : orderItemList){
            orderItem.setOrderItemOrderNo(order.getOrderNo());
        }

        //Mybaits 批量插入
        orderItemMapper.batchInsert(orderItemList);

        //生成成功，减少产品库存
        this.reduceProductStock(orderItemList);

        //清空购物车中已下单的产品
        this.cleanCart(cartList);

        //返回给前端数据
        OrderVo orderVo = assembleOrderVo(order, orderItemList);
        return ServerResponse.createBySuccess(orderVo);

    }

    //组装订单
    private OrderVo assembleOrderVo(Order order, List<OrderItem> orderItemList){
        OrderVo orderVo = new OrderVo();
        orderVo.setVoOrderNo(order.getOrderNo());
        orderVo.setVoPayment(order.getOrderPayment());
        orderVo.setVoPaymentType(order.getOrderPaymentType());
        //返回枚举代码所对应的描述属性
        orderVo.setVoPaymentTypeDesc(Const.PaymentTypeEnum.codeOf(order.getOrderPaymentType()).getValue());

        orderVo.setVoPostage(order.getOrderPostage());
        orderVo.setVoStatus(order.getOrderStatus());
        //返回枚举代码所对应的描述属性
        orderVo.setVoStatusDesc(Const.OrderStatusEnum.codeOf(order.getOrderStatus()).getValue());

        orderVo.setVoShippingId(order.getOrderShippingId());
        Shipping shipping = shippingMapper.selectByPrimaryKey(order.getOrderShippingId());
        if(shipping != null){
            orderVo.setVoReceiverName(shipping.getShippingReceiverName());
            orderVo.setVoShippingVo(assembleShippingVo(shipping));
        }

        orderVo.setVoPaymentTime(DateTimeUtil.dateToStr(order.getOrderPaymentTime()));
        orderVo.setVoSendTime(DateTimeUtil.dateToStr(order.getOrderSendTime()));
        orderVo.setVoEndTime(DateTimeUtil.dateToStr(order.getOrderEndTime()));
        orderVo.setVoCreateTime(DateTimeUtil.dateToStr(order.getOrderCreateTime()));
        orderVo.setVoCloseTime(DateTimeUtil.dateToStr(order.getOrderCloseTime()));

        //设置图片地址的前缀路径
        orderVo.setVoImageHost(PropertiesUtil.getProperty("ftp.server.http.prefix"));

        List<OrderItemVo> orderItemVoList = Lists.newArrayList();

        for(OrderItem orderItem : orderItemList){
            OrderItemVo orderItemVo = assembleOrderItemVo(orderItem);
            orderItemVoList.add(orderItemVo);
        }
        orderVo.setVoOrderItemVoList(orderItemVoList);

        return orderVo;
    }

    //组装订单明细
    private OrderItemVo assembleOrderItemVo(OrderItem orderItem){
        OrderItemVo orderItemVo = new OrderItemVo();
        orderItemVo.setVoOrderNo(orderItem.getOrderItemOrderNo());
        orderItemVo.setVoProductId(orderItem.getOrderItemProductId());
        orderItemVo.setVoProductName(orderItem.getOrderItemProductName());
        orderItemVo.setVoProductImage(orderItem.getOrderItemProductImage());
        orderItemVo.setVoCurrentUnitPrice(orderItem.getOrderItemCurrentUnitPrice());
        orderItemVo.setVoQuantity(orderItem.getOrderItemQuantity());
        orderItemVo.setVoTotalPrice(orderItem.getOrderItemTotalPrice());

        orderItemVo.setVoCreateTime(DateTimeUtil.dateToStr(orderItem.getOrderItemCreateTime()));
        return orderItemVo;
    }

    //组装收货地址
    private ShippingVo assembleShippingVo(Shipping shipping){
        ShippingVo shippingVo = new ShippingVo();
        shippingVo.setVoReceiverName(shipping.getShippingReceiverName());
        shippingVo.setVoReceiverAddress(shipping.getShippingReceiverAddress());
        shippingVo.setVoReceiverProvince(shipping.getShippingReceiverProvince());
        shippingVo.setVoReceiverCity(shipping.getShippingReceiverCity());
        shippingVo.setVoReceiverDistrict(shipping.getShippingReceiverDistrict());
        shippingVo.setVoReceiverMobile(shipping.getShippingReceiverMobile());
        shippingVo.setVoReceiverZip(shipping.getShippingReceiverZip());
        shippingVo.setVoReceiverPhone(shippingVo.getVoReceiverPhone());
        return shippingVo;
    }

    //更新购物车
    private void cleanCart(List<Cart> cartList){
        for(Cart cart : cartList){
            cartMapper.deleteByPrimaryKey(cart.getCartId());
        }
    }

    //更新产品库存
    private void reduceProductStock(List<OrderItem> orderItemList){
        for(OrderItem orderItem : orderItemList){
            Product product = productMapper.selectByPrimaryKey(orderItem.getOrderItemProductId());
            product.setProductStock(product.getProductStock() - orderItem.getOrderItemQuantity());
            productMapper.updateByPrimaryKeySelective(product);
        }
    }

    //设置订单信息
    private Order assembleOrder(Integer userId, Integer shippingId, BigDecimal payment){
        Order order = new Order();
        long orderNo = this.generateOrderNo();
        order.setOrderNo(orderNo);
        order.setOrderStatus(Const.OrderStatusEnum.NO_PAY.getCode());
        order.setOrderPaymentType(Const.PaymentTypeEnum.ONLINE_PAY.getCode());
        order.setOrderPayment(payment);

        order.setOrderUserId(userId);
        order.setOrderShippingId(shippingId);

        int rowCount = orderMapper.insert(order);
        if(rowCount > 0){
            return order;
        }
        return null;
    }

    //时间戳+随机数0~100的方式生成订单号,适用低并发的情况
    //高并发可以使用缓存池
    private long generateOrderNo(){
        long currentTime = System.currentTimeMillis();
        return currentTime + new Random().nextInt(100);
    }

    //计算所有订单总价格
    private BigDecimal getOrderTotalPrice(List<OrderItem> orderItemList){
        BigDecimal payment = new BigDecimal("0");
        for(OrderItem orderItem : orderItemList){
            payment = BigDecimalUtil.add(payment.doubleValue(), orderItem.getOrderItemTotalPrice().doubleValue());
        }
        return payment;
    }

    //计算订单的总价格
    private ServerResponse getCartOrderItem(Integer userId, List<Cart> cartList){
        List<OrderItem> orderItemList = Lists.newArrayList();
        if(CollectionUtils.isEmpty(cartList)){
            return ServerResponse.createByErrorMessage("购物车为空");
        }

        //校验购物车中的数据，包括产品的状态和数量
        for(Cart cartItem : cartList){
            OrderItem orderItem = new OrderItem();
            Product product = productMapper.selectByPrimaryKey(cartItem.getCartProductId());
            if(Const.ProductStatusEnum.ON_SALE.getCode() != product.getProductStatus()){
                return ServerResponse.createByErrorMessage("产品" + product.getProductName() + "不是在线售卖状态");
            }

            //校验库存
            if(cartItem.getCartQuantity() > product.getProductStock()){
                return ServerResponse.createByErrorMessage("产品" + product.getProductName() + "库存不足");
            }

            orderItem.setOrderItemUserId(userId);
            orderItem.setOrderItemProductId(product.getProductId());
            orderItem.setOrderItemProductName(product.getProductName());
            orderItem.setOrderItemProductImage(product.getProductMainImage());
            //交易快照，记录购买时的价格、数量和总价
            orderItem.setOrderItemCurrentUnitPrice(product.getProductPrice());
            orderItem.setOrderItemQuantity(cartItem.getCartQuantity());
            orderItem.setOrderItemTotalPrice(BigDecimalUtil.mul(product.getProductPrice().doubleValue(), cartItem.getCartQuantity()));

            orderItemList.add(orderItem);
        }
        return ServerResponse.createBySuccess(orderItemList);
    }

    //支付宝当面付（扫码）
    public ServerResponse pay(Long orderNo, Integer userId, String path){
        Map<String, String> resultMap = Maps.newHashMap();
        Order order = orderMapper.selectByUserIdAndOrderNo(userId, orderNo);
        if(order == null){
            return ServerResponse.createByErrorMessage("订单不存在");
        }
        resultMap.put("orderNo", String.valueOf(order.getOrderNo()));


        // (必填) 商户网站订单系统中唯一订单号，64个字符以内，只能包含字母、数字、下划线，
        // 需保证商户系统端不能重复，建议通过数据库sequence生成，
        //String outTradeNo = "tradeprecreate" + System.currentTimeMillis()
        //        + (long) (Math.random() * 10000000L);
        String outTradeNo = order.getOrderNo().toString();

        // (必填) 订单标题，粗略描述用户的支付目的。如“xxx品牌xxx门店当面付扫码消费”
        //String subject = "xxx品牌xxx门店当面付扫码消费";
        String subject = new StringBuilder().append("嘉士邦商城当面付扫码消费,订单号：").append(outTradeNo).toString();

        // (必填) 订单总金额，单位为元，不能超过1亿元
        // 如果同时传入了【打折金额】,【不可打折金额】,【订单总金额】三者,则必须满足如下条件:【订单总金额】=【打折金额】+【不可打折金额】
        //String totalAmount = "0.01";
        String totalAmount = order.getOrderPayment().toString();

        // (可选) 订单不可打折金额，可以配合商家平台配置折扣活动，如果酒水不参与打折，则将对应金额填写至此字段
        // 如果该值未传入,但传入了【订单总金额】,【打折金额】,则该值默认为【订单总金额】-【打折金额】
        String undiscountableAmount = "0";

        // 卖家支付宝账号ID，用于支持一个签约账号下支持打款到不同的收款账号，(打款到sellerId对应的支付宝账号)
        // 如果该字段为空，则默认为与支付宝签约的商户的PID，也就是appid对应的PID
        String sellerId = "";

        // 订单描述，可以对交易或商品进行一个详细地描述，比如填写"购买商品2件共15.00元"
        //String body = "购买商品3件共20.00元";
        String body = new StringBuilder().append("嘉士邦商城订单号").append(outTradeNo).append("购买商品共消费").append(totalAmount).append("元").toString();

        // 商户操作员编号，添加此参数可以为商户操作员做销售统计
        String operatorId = "test_operator_id";

        // (必填) 商户门店编号，通过门店号和商家后台可以配置精准到门店的折扣信息，详询支付宝技术支持
        String storeId = "test_store_id";

        // 业务扩展参数，目前可添加由支付宝分配的系统商编号(通过setSysServiceProviderId方法)，详情请咨询支付宝技术支持
        ExtendParams extendParams = new ExtendParams();
        extendParams.setSysServiceProviderId("2088100200300400500");

        // 支付超时，定义为120分钟
        String timeoutExpress = "120m";

        // 商品明细列表，需填写购买商品详细信息，
        List<GoodsDetail> goodsDetailList = new ArrayList<GoodsDetail>();

        List<OrderItem> orderItemList = orderItemMapper.getByUserIdAndOrderNo(userId, orderNo);
        for(OrderItem orderItem : orderItemList){
            //单位默认是分，需要转成元
            GoodsDetail goods = GoodsDetail.newInstance(orderItem.getOrderItemProductId().toString(), orderItem.getOrderItemProductName(),
                    BigDecimalUtil.mul(orderItem.getOrderItemCurrentUnitPrice().doubleValue(), new Double(100).doubleValue()).longValue(),
                    orderItem.getOrderItemQuantity());
            goodsDetailList.add(goods);
        }

        // 创建一个商品信息，参数含义分别为商品id（使用国标）、名称、单价（单位为分）、数量，如果需要添加商品类别，详见GoodsDetail
        //GoodsDetail goods1 = GoodsDetail.newInstance("goods_id001", "xxx小面包", 1000, 1);

        // 创建好一个商品后添加至商品明细列表
        //goodsDetailList.add(goods1);

        // 继续创建并添加第一条商品信息，用户购买的产品为“黑人牙刷”，单价为5.00元，购买了两件
        //GoodsDetail goods2 = GoodsDetail.newInstance("goods_id002", "xxx牙刷", 500, 2);
        //goodsDetailList.add(goods2);

        // 创建扫码支付请求builder，设置请求参数
        AlipayTradePrecreateRequestBuilder builder = new AlipayTradePrecreateRequestBuilder()
                .setSubject(subject).setTotalAmount(totalAmount).setOutTradeNo(outTradeNo)
                .setUndiscountableAmount(undiscountableAmount).setSellerId(sellerId).setBody(body)
                .setOperatorId(operatorId).setStoreId(storeId).setExtendParams(extendParams)
                .setTimeoutExpress(timeoutExpress)
                //                .setNotifyUrl("http://www.test-notify-url.com")//支付宝服务器主动通知商户服务器里指定的页面http路径,根据需要设置
                .setNotifyUrl(PropertiesUtil.getProperty("alipay.callback.url"))//这里是授权回调地址
                .setGoodsDetailList(goodsDetailList);

        AlipayF2FPrecreateResult result = tradeService.tradePrecreate(builder);
        switch (result.getTradeStatus()) {
            case SUCCESS:
                logger.info("支付宝预下单成功: )");

                AlipayTradePrecreateResponse response = result.getResponse();
                dumpResponse(response);

                //判断支付二维码的文件夹是否存在，是否有写入权限
                File folder = new File(path);
                //如果没有那么创建改文件夹
                if(!folder.exists()){
                    folder.setWritable(true);
                    folder.mkdir();
                }

                //path路径后边默认没有/号，需要有/号
                // 需要修改为运行机器上的路径
                String qrPath = String.format(path + "/qr-%s.png", response.getOutTradeNo());

                //支付二维码文件名
                String qrFileName = String.format("qr-%s.png", response.getOutTradeNo());

                //支付二维码文件生成
                ZxingUtils.getQRCodeImge(response.getQrCode(), 256, qrPath);

                File targetFile = new File(path, qrFileName);
                try{
                    FTPUtil.uploadFile(Lists.newArrayList(targetFile));
                }catch(IOException e){
                    logger.error("上传支付二维码文件异常", e);
                }
                logger.info("qrPath:" + qrPath);

                //logger.info("filePath:" + filePath);
                //                ZxingUtils.getQRCodeImge(response.getQrCode(), 256, filePath);

                String qrUrl = PropertiesUtil.getProperty("ftp.server.http.prefix") + targetFile.getName();
                resultMap.put("qrUrl", qrUrl);

                return ServerResponse.createBySuccess(resultMap);
                //break;

            case FAILED:
                logger.error("支付宝预下单失败!!!");
                //break;
                return ServerResponse.createByErrorMessage("支付宝预下单失败!!!");

            case UNKNOWN:
                logger.error("系统异常，预下单状态未知!!!");
                //break;
                return ServerResponse.createByErrorMessage("系统异常，预下单状态未知!!!");

            default:
                logger.error("不支持的交易状态，交易返回异常!!!");
                //break;
                return ServerResponse.createByErrorMessage("不支持的交易状态，交易返回异常!!!");
        }

    }

    // 从Demo的Main拿过来略修改
    // 简单打印应答
    private void dumpResponse(AlipayResponse response) {
        if (response != null) {
            logger.info(String.format("code:%s, msg:%s", response.getCode(), response.getMsg()));
            if (StringUtils.isNotEmpty(response.getSubCode())) {
                logger.info(String.format("subCode:%s, subMsg:%s", response.getSubCode(),
                        response.getSubMsg()));
            }
            logger.info("body:" + response.getBody());
        }
    }

    //支付宝回调
    public ServerResponse aliCallback(Map<String, String> params){
        Long orderNo = Long.parseLong(params.get("out_trade_no"));
        String tradeNo = params.get("trade_no");
        String tradeStatus = params.get("trade_status");
        Order order = orderMapper.selectByOrderNo(orderNo);
        if(order == null){
            return ServerResponse.createByErrorMessage("非本商城订单，回调忽略");
        }
        if(order.getOrderStatus() >= Const.OrderStatusEnum.PAID.getCode()){
            return ServerResponse.createBySuccess("支付宝重复调用");
        }
        if(Const.AlipayCallback.TRADE_STATUS_TRADE_SUCCESS.equals(tradeStatus)){
            order.setOrderPaymentTime(DateTimeUtil.strToDate(params.get("gmt_payment")));
            order.setOrderStatus(Const.OrderStatusEnum.PAID.getCode());
            orderMapper.updateByPrimaryKeySelective(order);
        }

        PayInfo payInfo = new PayInfo();
        payInfo.setPayInfoUserId(order.getOrderUserId());
        payInfo.setPayInfoOrderNo(order.getOrderNo());
        payInfo.setPayInfoPayPlatform(Const.PayPlatformEnum.ALIPAY.getCode());
        payInfo.setPayInfoPlatformNumber(tradeNo);
        payInfo.setPayInfoPlatformStatus(tradeStatus);

        payInfoMapper.insert(payInfo);

        return ServerResponse.createBySuccess();
    }

    //订单支付状态
    public ServerResponse queryOrderPayStatus(Integer userId, Long orderNo){
        Order order = orderMapper.selectByUserIdAndOrderNo(userId, orderNo);
        if(order == null){
            return ServerResponse.createByErrorMessage("订单不存在");
        }
        if(order.getOrderStatus() >= Const.OrderStatusEnum.PAID.getCode()){
            return ServerResponse.createBySuccess();
        }
        return ServerResponse.createByError();
    }

    //取消未付款订单
    public ServerResponse<String> cancelOrder(Integer userId, Long orderNo){
        Order order = orderMapper.selectByUserIdAndOrderNo(userId, orderNo);
        if(order == null){
            return ServerResponse.createByErrorMessage("订单不存在");
        }
        if(order.getOrderStatus() != Const.OrderStatusEnum.NO_PAY.getCode()){
            return ServerResponse.createByErrorMessage("该订单已付款,无法取消");
        }
        Order updateOrder = new Order();
        updateOrder.setOrderId(order.getOrderId());
        updateOrder.setOrderStatus(Const.OrderStatusEnum.CANCELED.getCode());

        int row = orderMapper.updateByPrimaryKeySelective(updateOrder);
        if(row > 0){
            return ServerResponse.createBySuccess();
        }
        return ServerResponse.createByError();
    }

    //获取购物车中选中的商品详情
    public ServerResponse getOrderCartProduct(Integer userId){
        OrderProductVo orderProductVo = new OrderProductVo();
        //获取购物车中的数据
        List<Cart> cartList = cartMapper.selectCheckedCartByUserId(userId);
        ServerResponse serverResponse = this.getCartOrderItem(userId, cartList);
        if(!serverResponse.isSuccess()){
            return serverResponse;
        }
        List<OrderItem> orderItemList = (List<OrderItem>)serverResponse.getData();
        List<OrderItemVo> orderItemVoList = Lists.newArrayList();
        BigDecimal payment = new BigDecimal("0");
        for(OrderItem orderItem : orderItemList){
            payment = BigDecimalUtil.add(payment.doubleValue(), orderItem.getOrderItemTotalPrice().doubleValue());
            orderItemVoList.add(assembleOrderItemVo(orderItem));
        }
        orderProductVo.setVoProductTotalPrice(payment);
        orderProductVo.setVoOrderItemVoList(orderItemVoList);
        orderProductVo.setVoImageHost(PropertiesUtil.getProperty("ftp.server.http.prefix"));

        return ServerResponse.createBySuccess(orderProductVo);
    }

    //订单详情
    public ServerResponse<OrderVo> getOrderDetail(Integer userId, Long orderNo){
        Order order = orderMapper.selectByUserIdAndOrderNo(userId, orderNo);
        if(order != null){
            List<OrderItem> orderItemList = orderItemMapper.getByUserIdAndOrderNo(userId, orderNo);
            OrderVo orderVo = assembleOrderVo(order, orderItemList);
            return ServerResponse.createBySuccess(orderVo);
        }
        return ServerResponse.createByErrorMessage("订单不存在");
    }

    //查看我的订单
    public ServerResponse<PageInfo> getOrderList(Integer userId, int pageNum, int pageSize){
        PageHelper.startPage(pageNum, pageSize);

        List<Order> orderList = orderMapper.selectByUserId(userId);
        List<OrderVo> orderVoList = assembleOrderVoList(orderList, userId);

        //分页
        PageInfo pageResult = new PageInfo(orderList);
        pageResult.setList(orderVoList);

        return ServerResponse.createBySuccess(pageResult);
    }

    //把orderList转化为orderVoList
    private List<OrderVo> assembleOrderVoList(List<Order> orderList, Integer userId){
        List<OrderVo> OrderVoList = Lists.newArrayList();
        for(Order order : orderList){
            List<OrderItem> orderItemList = Lists.newArrayList();
            if(userId == null){
                //管理员查询不需要传userId
                orderItemList = orderItemMapper.getByOrderNo(order.getOrderNo());
            }else{
                orderItemList = orderItemMapper.getByUserIdAndOrderNo(userId, order.getOrderNo());
            }
            OrderVo orderVo = assembleOrderVo(order, orderItemList);
            OrderVoList.add(orderVo);
        }
        return OrderVoList;
    }


    //backend

    public ServerResponse<PageInfo> manageOrderList(int pageNum, int PageSize){
        PageHelper.startPage(pageNum, PageSize);

        List<Order> orderList = orderMapper.selectAllOrder();
        List<OrderVo> orderVoList = this.assembleOrderVoList(orderList, null);

        //分页
        PageInfo pageResult = new PageInfo(orderList);
        pageResult.setList(orderVoList);

        return ServerResponse.createBySuccess(pageResult);
    }

    public ServerResponse<OrderVo> manageOrderDetail(Long orderNo){
        Order order = orderMapper.selectByOrderNo(orderNo);
        if(order != null){
            List<OrderItem> orderItemList = orderItemMapper.getByOrderNo(orderNo);
            OrderVo orderVo = assembleOrderVo(order, orderItemList);
            return ServerResponse.createBySuccess(orderVo);
        }
        return ServerResponse.createByErrorMessage("订单不存在");
    }

    public ServerResponse<PageInfo> manageOrderSearch(Long orderNo, int pageNum, int PageSize){
        PageHelper.startPage(pageNum, PageSize);

        Order order = orderMapper.selectByOrderNo(orderNo);
        if(order != null){
            List<OrderItem> orderItemList = orderItemMapper.getByOrderNo(orderNo);
            OrderVo orderVo = assembleOrderVo(order, orderItemList);

            //分页
            PageInfo pageResult = new PageInfo(Lists.newArrayList(order));
            pageResult.setList(Lists.newArrayList(orderVo));

            return ServerResponse.createBySuccess(pageResult);
        }
        return ServerResponse.createByErrorMessage("订单不存在");
    }

    public ServerResponse<String> manageOrderSendGoods(Long orderNo){
        Order order = orderMapper.selectByOrderNo(orderNo);
        if(order != null){
            if(order.getOrderStatus() == Const.OrderStatusEnum.PAID.getCode()){
                order.setOrderStatus(Const.OrderStatusEnum.SHIPPED.getCode());
                order.setOrderSendTime(new Date());
                orderMapper.updateByPrimaryKeySelective(order);
                return ServerResponse.createBySuccess("发货成功");
            }
        }
        return ServerResponse.createByErrorMessage("订单不存在");
    }

}
