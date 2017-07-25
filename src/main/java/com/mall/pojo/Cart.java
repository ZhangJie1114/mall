package com.mall.pojo;

import java.util.Date;

public class Cart {
    private Integer cartId;

    private Integer cartUserId;

    private Integer cartProductId;

    private Integer cartQuantity;

    private Integer cartChecked;

    private Date cartCreateTime;

    private Date cartUpdateTime;

    public Cart(Integer cartId, Integer cartUserId, Integer cartProductId, Integer cartQuantity, Integer cartChecked, Date cartCreateTime, Date cartUpdateTime) {
        this.cartId = cartId;
        this.cartUserId = cartUserId;
        this.cartProductId = cartProductId;
        this.cartQuantity = cartQuantity;
        this.cartChecked = cartChecked;
        this.cartCreateTime = cartCreateTime;
        this.cartUpdateTime = cartUpdateTime;
    }

    public Cart() {
        super();
    }

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public Integer getCartUserId() {
        return cartUserId;
    }

    public void setCartUserId(Integer cartUserId) {
        this.cartUserId = cartUserId;
    }

    public Integer getCartProductId() {
        return cartProductId;
    }

    public void setCartProductId(Integer cartProductId) {
        this.cartProductId = cartProductId;
    }

    public Integer getCartQuantity() {
        return cartQuantity;
    }

    public void setCartQuantity(Integer cartQuantity) {
        this.cartQuantity = cartQuantity;
    }

    public Integer getCartChecked() {
        return cartChecked;
    }

    public void setCartChecked(Integer cartChecked) {
        this.cartChecked = cartChecked;
    }

    public Date getCartCreateTime() {
        return cartCreateTime;
    }

    public void setCartCreateTime(Date cartCreateTime) {
        this.cartCreateTime = cartCreateTime;
    }

    public Date getCartUpdateTime() {
        return cartUpdateTime;
    }

    public void setCartUpdateTime(Date cartUpdateTime) {
        this.cartUpdateTime = cartUpdateTime;
    }
}