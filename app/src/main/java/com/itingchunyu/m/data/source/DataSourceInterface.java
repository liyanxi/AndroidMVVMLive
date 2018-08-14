package com.itingchunyu.m.data.source;

import android.arch.lifecycle.LiveData;

import com.itingchunyu.m.data.Resource;
import com.itingchunyu.m.data.model.TestEntity;

/**
 * @author liyanxi
 * @date 2018/8/10
 * Copyright (c) 2018 www.finlendingcloud.com. All rights reserved.
 */

public interface DataSourceInterface {

    /**
     * 获取首页数据
     * @return 返回
     */
    LiveData<Resource<TestEntity>> getHome();
}
