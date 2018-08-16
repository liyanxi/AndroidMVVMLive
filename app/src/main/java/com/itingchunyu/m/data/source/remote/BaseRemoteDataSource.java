package com.itingchunyu.m.data.source.remote;

import android.arch.lifecycle.LiveData;

import com.itingchunyu.m.data.ApiResponse;
import com.itingchunyu.m.data.Resource;
import com.itingchunyu.m.data.retrofit.AbstractRequestHandler;

import retrofit2.Call;

/**
 * @author liyanxi
 * @date 2018/8/14
 * Copyright (c) 2018 www.itingchunyu.com. All rights reserved.
 */

class BaseRemoteDataSource {

    /**
     * 发出网络执行请求，并返回实际可观察实体
     *
     * @param call service 调用
     * @param <T>  实际转换类型
     * @return
     */
    <T> LiveData<Resource<T>> executeRequest(Call<ApiResponse<T>> call) {
        return new AbstractRequestHandler<T>() {
            @Override
            protected Call<ApiResponse<T>> makeRequest() {
                return call;
            }
        }.doRequest();
    }
}
