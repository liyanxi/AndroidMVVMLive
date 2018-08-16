package com.itingchunyu.m.data.retrofit;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.itingchunyu.m.util.LogUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 统一Api错误处理
 *
 * @author liyanxi
 * @date 2018/8/14
 * Copyright (c) 2018 www.itingchunyu.com. All rights reserved.
 */

public abstract class ApiCallback<T> implements Callback<T> {

    @Override
    public void onResponse(@NonNull Call<T> call, @NonNull Response<T> response) {
        if (response.body() != null && response.isSuccessful()) {
            LogUtil.d(response.toString());
            LogUtil.d(response.body());
            handleResponseData(response.body());
        } else {
            handleError(response);
        }
    }

    @Override
    public void onFailure(@NonNull Call<T> call, @NonNull Throwable t) {
        if (t instanceof Exception) {
            handleException(((Exception) t));
        } else {
            // do something else
        }
    }

    abstract void handleResponseData(T data);

    abstract void handleException(Exception e);

    private void handleError(Response<T> response) {
        handleException(new Exception(response == null || TextUtils.isEmpty(response.message()) ? "网络连接异常，请稍后重试" : response.message()));
    }
}
