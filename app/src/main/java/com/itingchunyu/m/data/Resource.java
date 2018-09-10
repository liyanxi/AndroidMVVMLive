package com.itingchunyu.m.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * a generic class that describes a data with a status
 *
 * @author liyanxi
 * @date 2018/8/10
 * Copyright (c) 2018 www.itingchunyu.com. All rights reserved.
 */

public class Resource<T> {
    @NonNull
    private final Status status;
    @Nullable
    public final T data;
    @Nullable
    public final String message;
    public final String code;

    private Resource(@NonNull Status status, @Nullable T data, @Nullable String message, String code) {
        this.status = status;
        this.data = data;
        this.message = message;
        this.code = code;
    }

    public static <T> Resource<T> success(@NonNull T data) {
        return new Resource<>(Status.SUCCESS, data, null, "");
    }

    public static <T> Resource<T> error(String msg, @Nullable T data, String code) {
        return new Resource<>(Status.ERROR, data, msg, code);
    }

    public static <T> Resource<T> loading(@Nullable T data) {
        return new Resource<>(Status.LOADING, data, null, "");
    }

    /**
     * 数据状态是否为成功
     *
     * @return success
     */
    public boolean isSuccess() {
        return status == Status.SUCCESS;
    }

    /**
     * 数据状态是处于加载状态
     *
     * @return loading
     */
    public boolean isLoading() {
        return status == Status.LOADING;
    }
}
