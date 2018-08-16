package com.itingchunyu.m.data.model;

/**
 * @author liyanxi
 * @date 2018/8/13
 * Copyright (c) 2018 www.itingchunyu.com. All rights reserved.
 */

public class TestEntity {

    public int allProductSalesFlag;
    public int messageNum;

    @Override
    public String toString() {
        return allProductSalesFlag + "..//.." + messageNum;
    }
}
