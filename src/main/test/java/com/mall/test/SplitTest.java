package com.mall.test;

import org.junit.Test;

/**
 * Created by root on 17-10-8.
 */
public class SplitTest {

    @Test
    public void splitTest(){
        String orderBy = "product_price|asc";
        String[] orderByArray = orderBy.split("\\|");
        System.out.println(orderByArray[0]);
        System.out.println(orderByArray[1]);
    }

}
