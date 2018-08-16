package com.itingchunyu.m.component.base;

import android.os.Bundle;

import dagger.android.support.DaggerAppCompatActivity;

/**
 * base activity
 *
 * @author liyanxi
 * @date 2018/8/16
 * Copyright (c) 2018 www.itingchunyu.com. All rights reserved.
 */
public abstract class BaseActivity extends DaggerAppCompatActivity implements ILifeCycleControl {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onBeforeSetContentLayout();
        generateContentView(getLayoutId());
        onAfterSetContentLayout(savedInstanceState);
    }

    /**
     * 创建content view
     */
    abstract void generateContentView(int layoutResId);
}
