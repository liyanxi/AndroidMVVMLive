package com.itingchunyu.m.data.retrofit;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.itingchunyu.c.data.ApiResponse;
import com.itingchunyu.tools.util.JsonUtil;
import com.itingchunyu.tools.util.LogUtil;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
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

        LogUtil.d("onResponse info start");
        LogUtil.d(response.toString());
        LogUtil.d("onResponse info start");
        if (response.isSuccessful()) {
            handleResponseData(response.body(), response.code());
        } else {
            LogUtil.d("http code !== 200");
            ResponseBody responseBody = response.errorBody();
            if (responseBody != null) {
                BufferedSource source = responseBody.source();
                try {
                    source.request(Long.MAX_VALUE); // Buffer the entire body.
                } catch (IOException e) {
                    handleException(e);
                }
                Buffer buffer = source.buffer();
                String errorJson = buffer.clone().readString(Charset.forName("UTF-8"));
                LogUtil.d(errorJson);

                handleResponseData(JsonUtil.deserialize(errorJson, (Type) ApiResponse.class), response.code());
            } else {
                handleError(response);
            }
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

    abstract void handleResponseData(T data, int code);

    abstract void handleException(Exception e);

    private void handleError(Response<T> response) {
        handleException(new Exception(response == null || TextUtils.isEmpty(response.message()) ? "网络连接异常，请稍后重试" : response.message()));
    }
}
