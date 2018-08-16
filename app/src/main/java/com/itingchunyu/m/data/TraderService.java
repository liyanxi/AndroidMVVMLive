package com.itingchunyu.m.data;

import com.itingchunyu.m.data.model.TestEntity;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * api services list
 * @author liyanxi
 * @date 2018/8/10
 * Copyright (c) 2018 www.itingchunyu.com. All rights reserved.
 */

public interface TraderService {

    String HOST = "https://test1api.zhengshijr.com/";

    @GET("api/product/homepage2")
    Call<ApiResponse<TestEntity>> getHome();
}
