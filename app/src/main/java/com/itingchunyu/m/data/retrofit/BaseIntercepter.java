package com.itingchunyu.m.data.retrofit;

import android.os.Build;
import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author liyanxi
 * @date 2018/8/10
 * Copyright (c) 2018 www.itingchunyu.com. All rights reserved.
 */

public class BaseIntercepter implements Interceptor {

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();

        Request.Builder builder = chain.request().newBuilder();
        //todo add header info
        builder.addHeader("platform", "android(" + Build.VERSION.RELEASE + ")");

        Request newRequest = builder.build();

        return chain.proceed(newRequest);
    }
}
