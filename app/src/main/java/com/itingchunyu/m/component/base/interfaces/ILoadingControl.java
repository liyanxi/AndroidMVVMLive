package com.itingchunyu.m.component.base.interfaces;

import android.app.Dialog;

/**
 * <pre>
 * loading dialog
 * Copyright (c) 2017 www.itingchunyu.com. All rights reserved.
 * </pre>
 */
public interface ILoadingControl {

    /**
     * 隐藏等待对话框
     */
    void hideWaitDialog();

    /**
     * 显示等待对话框
     * @return
     */
     Dialog showWaitDialog();
}
