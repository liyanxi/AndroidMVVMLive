package com.itingchunyu.m;

import com.itingchunyu.m.di.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

/**
 * @author liyanxi
 * @date 2018/8/13
 * Copyright (c) 2018 www.itingchunyu.com. All rights reserved.
 */

public class TraderApplication extends DaggerApplication {
    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().application(this).build();
    }
}
