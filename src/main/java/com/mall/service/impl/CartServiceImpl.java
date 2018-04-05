package com.mall.service.impl;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.mall.common.Const;
import com.mall.common.ResponseCode;
import com.mall.common.ServerResponse;
import com.mall.dao.CartMapper;
import com.mall.dao.ProductMapper;
import com.mall.pojo.Cart;
import com.mall.pojo.Product;
import com.mall.service.ICartService;
import com.mall.util.BigDecimalUtil;
import com.mall.util.PropertiesUtil;
import com.mall.vo.CartProductVo;
import com.mall.vo.CartVo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by root on 17-10-6.
 */
@Service("iCartService")
public class CartServiceImpl implements ICartService{

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private ProductMapper productMapper;

    public ServerResponse<CartVo> add(Integer userId, Integer productId, Integer count){
        if(productId == null || count == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        Cart cart = cartMapper.selectCartByUserIdAndProductId(userId, productId);
        if(cart == null){
            //这个产品不在购物车中，需要新增这个产品到记录
            Cart cartItem = new Cart();
            cartItem.setCartQuantity(count);
            cartItem.setCartChecked(Const.Cart.CHECKED);
            cartItem.setCartProductId(productId);
            cartItem.setCartUserId(userId);
            cartMapper.insert(cartItem);
        }else {
            //这个产品已经在购物车中
            //如果产品已经存在，那么数量相加
            count = cart.getCartQuantity() + count;
            cart.setCartQuantity(count);
            cartMapper.updateByPrimaryKeySelective(cart);
        }
        return this.list(userId);
    }

    public ServerResponse<CartVo> update(Integer userId, Integer productId, Integer count){
        if(productId == null || count == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        Cart cart = cartMapper.selectCartByUserIdAndProductId(userId, productId);
        if(cart != null){
            cart.setCartQuantity(count);
        }
        cartMapper.updateByPrimaryKeySelective(cart);
        return this.list(userId);
    }

    public ServerResponse<CartVo> deleteProduct(Integer userId, String productIds){
        List<String> productList = Splitter.on(",").splitToList(productIds);
        if(CollectionUtils.isEmpty(productList)){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        cartMapper.deleteByUserIdAndProductIds(userId, productList);
        return this.list(userId);
    }

    public ServerResponse<CartVo> list(Integer userId){
        CartVo cartVo = this.getCartVoLimit(userId);
        return ServerResponse.createBySuccess(cartVo);
    }

    public ServerResponse<CartVo> selectOrUnSelect(Integer userId, Integer productId, Integer checked){
        cartMapper.checkedOrUnCheckedProduct(userId, productId, checked);
        return this.list(userId);
    }

    public ServerResponse<Integer> getCartProductCoount(Integer userId){
        if(userId == null){
            return ServerResponse.createBySuccess(0);
        }
        return ServerResponse.createBySuccess(cartMapper.selectCartProductCount(userId));
    }

    private CartVo getCartVoLimit(Integer userId){
        CartVo cartVo = new CartVo();
        List<Cart> cartList = cartMapper.selectCartByUserId(userId);
        List<CartProductVo> cartProductVosList = Lists.newArrayList();

        BigDecimal cartTotalPrice = new BigDecimal("0");

        if(CollectionUtils.isNotEmpty(cartList)){
            for(Cart cartItem : cartList){
                CartProductVo cartProductVo = new CartProductVo();
                cartProductVo.setVoId(cartItem.getCartId());
                cartProductVo.setVoUserId(userId);
                cartProductVo.setVoProductId(cartItem.getCartProductId());

                Product product = productMapper.selectByPrimaryKey(cartItem.getCartProductId());
                if(product != null){
                    cartProductVo.setVoProductMainImage(product.getProductMainImage());
                    cartProductVo.setVoProductName(product.getProductName());
                    cartProductVo.setVoProductSubtitle(product.getProductSubtitle());
                    cartProductVo.setVoProductStatus(product.getProductStatus());
                    cartProductVo.setVoProductPrice(product.getProductPrice());
                    cartProductVo.setVoProductStock(product.getProductStock());

                    //判断产品库存
                    int buyLimitCount = 0;
                    if(product.getProductStock() >= cartItem.getCartQuantity()){
                        //库存充足的时候
                        buyLimitCount = cartItem.getCartQuantity();
                        cartProductVo.setVoLimitQuantity(Const.Cart.LIMIT_NUM_SUCCESS);
                    }else{
                        buyLimitCount = product.getProductStock();
                        cartProductVo.setVoLimitQuantity(Const.Cart.LIMIT_NUM_FAIL);
                        //购物车中更新有效产品库存
                        Cart cartForQuantity = new Cart();
                        cartForQuantity.setCartId(cartItem.getCartId());
                        cartForQuantity.setCartQuantity(buyLimitCount);
                        cartMapper.updateByPrimaryKeySelective(cartForQuantity);
                    }
                    cartProductVo.setVoQuantity(buyLimitCount);
                    //计算总价格
                    cartProductVo.setVoProductTotalPrice(BigDecimalUtil.mul(product.getProductPrice().doubleValue(), cartProductVo.getVoQuantity()));
                    cartProductVo.setVoProductChecked(cartItem.getCartChecked());
                }
                if(cartItem.getCartChecked() == Const.Cart.CHECKED){
                    //如果已经勾选，那么增加到整个购物车的总价格中
                    cartTotalPrice = BigDecimalUtil.add(cartTotalPrice.doubleValue(), cartProductVo.getVoProductTotalPrice().doubleValue());
                }
                cartProductVosList.add(cartProductVo);
            }

        }
        cartVo.setVoCartTotalPrice(cartTotalPrice);
        cartVo.setVoCartProductVoList(cartProductVosList);
        cartVo.setVoAllChecked(this.getAllCheckedStatus(userId));
        cartVo.setVoImageHost(PropertiesUtil.getProperty("ftp.server.http.prefix"));
        return cartVo;
    }

    private boolean getAllCheckedStatus(Integer userId){
        if(userId == null){
            return false;
        }
        return cartMapper.selectCartProductCheckedStatusByUserId(userId) == 0;
    }

}
