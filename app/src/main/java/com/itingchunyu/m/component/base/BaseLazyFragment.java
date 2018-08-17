package com.itingchunyu.m.component.base;

import android.os.Bundle;

import com.itingchunyu.m.viewmodel.BaseViewModel;

/**
 * 懒加载基础碎片界面
 *
 * @author liyanxi
 * @date 2018/8/17
 * Copyright (c) 2018 www.itingchunyu.com. All rights reserved.
 */
public abstract class BaseLazyFragment<VM extends BaseViewModel> extends BaseFragment<VM> implements ILazyFragmentControl {

    private static final long DEFAULT_TIME_INTERVAL = 30 * 1000;//默认间隔时间30秒
    private boolean isPrepared;
    /**
     * 第一次onResume中的调用onUserVisible避免操作与onFirstUserVisible操作重复
     */
    private boolean isFirstResume = true;
    private boolean isFirstVisible = true;
    private boolean isFirstInvisible = true;

    private long mLastVisibleTime = System.currentTimeMillis();//上一次显示的时间
    private long mTimeInterval = DEFAULT_TIME_INTERVAL;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initPrepare();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isFirstResume) {
            isFirstResume = false;
            return;
        }
        if (getUserVisibleHint()) {
            onUserVisible();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (getUserVisibleHint()) {
            onUserInvisible();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            if (isFirstVisible) {
                isFirstVisible = false;
                initPrepare();
            } else {
                checkLastTime();
                onUserVisible();
            }
        } else {
            if (isFirstInvisible) {
                isFirstInvisible = false;
                onFirstUserInvisible();
            } else {
                //界面不显示 设置上次显示时间
                mLastVisibleTime = System.currentTimeMillis();
                onUserInvisible();
            }
        }
    }

    @Override
    public void checkLastTime() {
        if (System.currentTimeMillis() - mLastVisibleTime > mTimeInterval) {
            onAutoRefresh();
        }
    }

    @Override
    public synchronized void initPrepare() {
        if (isPrepared) {
            onFirstUserVisible();
            lazyData();
        } else {
            isPrepared = true;
        }
    }


    @Override
    public void onFirstUserVisible() {
    }

    @Override
    public void onUserVisible() {
    }

    @Override
    public void onFirstUserInvisible() {
    }


    @Override
    public void onUserInvisible() {
    }

    @Override
    public void onAutoRefresh() {
    }

    @Override
    public void setTimeInterval(long mTimeInterval) {
        this.mTimeInterval = mTimeInterval;
    }
}
