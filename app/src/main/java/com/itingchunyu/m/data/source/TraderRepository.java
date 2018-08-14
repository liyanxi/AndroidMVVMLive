package com.itingchunyu.m.data.source;

import android.arch.lifecycle.LiveData;
import android.util.Log;

import com.itingchunyu.m.data.Resource;
import com.itingchunyu.m.data.model.TestEntity;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author liyanxi
 * @date 2018/8/10
 * Copyright (c) 2018 www.finlendingcloud.com. All rights reserved.
 */
@Singleton
public class TraderRepository implements DataSourceInterface {

    private final DataSourceInterface mRemoteDataSource;

    @Inject
    TraderRepository(@Remote DataSourceInterface remoteDataSource) {
        mRemoteDataSource = remoteDataSource;
    }

    @Override
    public LiveData<Resource<TestEntity>> getHome() {
        //根据需求，指定不同的数据来源
        return mRemoteDataSource.getHome();
    }
}
