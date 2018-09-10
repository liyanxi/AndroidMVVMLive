package com.itingchunyu.m.component.base.interfaces;

import android.os.Bundle;

/**
 * @author liyanxi
 * @date 2018/8/15
 * Copyright (c) 2018 www.itingchunyu.com. All rights reserved.
 */
public interface ILifeCycleControl {

    /**
     * 初始化ui layout 之前操作
     */
    void onBeforeSetContentLayout();

    /**
     * 实际布局id
     *
     * @return layout id
     */
    int getLayoutId();

    /**
     * 初始化ui layout 之后数据
     * @param savedInstanceState bundle
     */
    void onAfterSetContentLayout(Bundle savedInstanceState);
}
