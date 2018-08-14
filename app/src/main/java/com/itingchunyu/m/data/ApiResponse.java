package com.itingchunyu.m.data;

/**
 * 前后端接口通信定义接口数据格式
 *
 * @author liyanxi
 * @date 2018/8/10
 * Copyright (c) 2018 www.finlendingcloud.com. All rights reserved.
 */

public class ApiResponse<T> {

    public final int code;
    public final String message;
    public final T data;

    /**
     * 前后端统一规定code==0判定为正常操作成功，否则视为异常情况
     *
     * @return 是否操作成功
     */
    public boolean isSuccessful() {
        return code == 0;
    }

    public ApiResponse(T data, String message, int code) {
        this.data = data;
        this.message = message;
        this.code = code;
    }
}
