package com.mall.common;

import com.google.common.collect.Sets;

import java.util.Set;

/**
 * Created by root on 7/29/17.
 */
public class Const {
    public static final String CURRENT_USER = "currentUser";

    public static final String EMAIL = "email";
    public static final String USERNAME = "username";

    public interface ProductListOrderBy{
        Set<String> PRICE_ASC_DESC = Sets.newHashSet("product_price|desc", "product_price|asc");
    }

    public interface Cart{
        int CHECKED = 1;//购物车中已选中状态
        int UN_CHECKED = 0;//购物车中未选中状态

        String LIMIT_NUM_FAIL = "LIMIT_NUM_FAIL";
        String LIMIT_NUM_SUCCESS = "LIMIT_NUM_SUCCESS";
    }

    public interface Role{
        int ROLE_CUSTOMER = 0;//普通用户
        int ROLE_ADMIN = 1;//管理员
    }

    public enum ProductStatusEnum{

        ON_SALE(1,"在线");

        private String value;
        private int code;

        ProductStatusEnum(int code, String value){
            this.code = code;
            this.value = value;
        }
        public String getValue() {
            return value;
        }

        public int getCode() {
            return code;
        }

    }
}
