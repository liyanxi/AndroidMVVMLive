package com.itingchunyu.m.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import static com.itingchunyu.m.data.Status.ERROR;
import static com.itingchunyu.m.data.Status.LOADING;
import static com.itingchunyu.m.data.Status.SUCCESS;

/**
 * a generic class that describes a data with a status
 *
 * @author liyanxi
 * @date 2018/8/10
 * Copyright (c) 2018 www.itingchunyu.com. All rights reserved.
 */

public class Resource<T> {
    @NonNull
    public final Status status;
    @Nullable
    public final T data;
    @Nullable
    public final String message;
    @NonNull
    public final int code;

    private Resource(@NonNull Status status, @Nullable T data, @Nullable String message, @Nullable int code) {
        this.status = status;
        this.data = data;
        this.message = message;
        this.code = code;
    }

    public static <T> Resource<T> success(@NonNull T data) {
        return new Resource<>(SUCCESS, data, null, 0);
    }

    public static <T> Resource<T> error(String msg, @Nullable T data, @Nullable int code) {
        return new Resource<>(ERROR, data, msg, code);
    }

    public static <T> Resource<T> loading(@Nullable T data) {
        return new Resource<>(LOADING, data, null, -1);
    }

    /**
     * 数据状态是否为成功
     *
     * @return
     */
    public boolean isSuccess() {
        return status == SUCCESS;
    }
}
