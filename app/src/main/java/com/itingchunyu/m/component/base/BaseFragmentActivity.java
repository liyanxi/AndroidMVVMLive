package com.itingchunyu.m.component.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.itingchunyu.m.viewmodel.BaseViewModel;

/**
 * 容纳fragment的activity容器
 *
 * @author liyanxi
 * @date 2018/8/17
 * Copyright (c) 2018 www.itingchunyu.com. All rights reserved.
 */
public abstract class BaseFragmentActivity<VM extends BaseViewModel> extends BaseToolbarActivity<VM> {

    protected Fragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mFragment == null) {
            mFragment = getFragment();
        }
        if (mFragment != null && getSupportFragmentManager().findFragmentById(CONTENT_VIEW_ID) == null) {
            //如果没有添加过 添加fragment
            getSupportFragmentManager().beginTransaction().add(CONTENT_VIEW_ID, mFragment).commitAllowingStateLoss();
        }
    }

    /**
     * 子类实现获取添加的碎片
     *
     * @return fragment child container
     */
    protected abstract Fragment getFragment();
}
