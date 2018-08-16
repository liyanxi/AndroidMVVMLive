package com.itingchunyu.m.data.retrofit;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.itingchunyu.m.data.ApiResponse;
import com.itingchunyu.m.data.Resource;

import retrofit2.Call;

/**
 * 创建请求逻辑统一处理
 *
 * @param <M> 该泛型类标记为一次请求请求响应结果，见{@link ApiResponse#data},
 *            这里规定code==0或code!=0，两种情况假如都有后端响应则信息定义在同一实体类中见{@link AbstractRequestHandler <M>}R泛型
 * @author liyanxi
 * @date 2018/8/14
 * Copyright (c) 2018 www.itingchunyu.com. All rights reserved.
 */

public abstract class AbstractRequestHandler<M> {

    /**
     * fetch service call
     *
     * @return call
     */
    protected abstract Call<ApiResponse<M>> makeRequest();

    public final LiveData<Resource<M>> doRequest() {
        final MutableLiveData<Resource<M>> liveData = new MutableLiveData<>();
        //start request loading
        liveData.setValue(Resource.loading(null));
        makeRequest().enqueue(new ApiCallback<ApiResponse<M>>() {
            @Override
            void handleResponseData(ApiResponse<M> apiResponse) {
                if (apiResponse.isSuccessful()) {
                    //code==0 可能出现data为空或者对应响应信息
                    liveData.setValue(Resource.success(apiResponse.data));
                } else {
                    /*
                     * 异常情况考虑,视为错误,与下面不同的地方在于这种情况下,除了错误信息外数据对象{@link Resource#data}可能不为空
                     */
                    liveData.setValue(Resource.error(apiResponse.message, apiResponse.data, apiResponse.code));
                }
            }

            @Override
            void handleException(@NonNull Exception e) {
                liveData.setValue(Resource.error(e.getMessage(), null, ErrorCode.ERROR_CODE_NET_FAIL));
            }
        });
        return liveData;
    }
}
