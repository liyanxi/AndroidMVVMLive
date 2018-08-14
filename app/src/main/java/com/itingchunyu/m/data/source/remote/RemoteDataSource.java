package com.itingchunyu.m.data.source.remote;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.itingchunyu.m.data.Resource;
import com.itingchunyu.m.data.TraderService;
import com.itingchunyu.m.data.model.TestEntity;
import com.itingchunyu.m.data.source.DataSourceInterface;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * 数据来源-->网络
 *
 * @author liyanxi
 * @date 2018/8/10
 * Copyright (c) 2018 www.finlendingcloud.com. All rights reserved.
 */

@Singleton
public class RemoteDataSource extends BaseRemoteDataSource implements DataSourceInterface {

    private final TraderService wb;

    @Inject
    RemoteDataSource(@NonNull TraderService wb) {
        this.wb = wb;
    }

    @Override
    public LiveData<Resource<TestEntity>> getHome() {
        return executeRequest(wb.getHome());
    }
}
