package com.itingchunyu.m.live;

import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import com.itingchunyu.m.data.Resource;

/**
 * 此处对请求包裹数据{@link com.itingchunyu.m.data.Resource}对象，添加观察器{@link AbstractResourceObserver} extends {@link Observer}做一个统一拦截处理,如：登录失效
 *
 * @author liyanxi
 * @date 2018/8/14
 * Copyright (c) 2018 www.itingchunyu.com. All rights reserved.
 */

public abstract class AbstractResourceObserver<T> implements Observer<Resource<T>> {

    @Override
    public void onChanged(@Nullable Resource<T> tResource) {
        if (tResource != null) {
            if (tResource.isSuccess()) {
                onSuccess(tResource.data);
            } else {
                onException(tResource.message, tResource.code, tResource.data);
            }
        }
    }

    /**
     * 请求成功
     *
     * @param data 实际实体
     */
    public abstract void onSuccess(T data);

    /**
     * 异常处理
     *
     * @param errorMsg 异常信息
     * @param code     错误码
     * @param data     错误信息实体
     */
    public abstract void onException(String errorMsg, int code, T data);
}
