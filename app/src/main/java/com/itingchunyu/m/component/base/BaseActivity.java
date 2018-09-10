package com.itingchunyu.m.component.base;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;

import com.itingchunyu.m.component.base.interfaces.ILifeCycleControl;
import com.itingchunyu.m.component.base.interfaces.ILoadingControl;
import com.itingchunyu.m.component.base.interfaces.IViewModeControl;
import com.itingchunyu.m.viewmodel.BaseViewModel;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

/**
 * base activity
 *
 * @author liyanxi
 * @date 2018/8/16
 * Copyright (c) 2018 www.itingchunyu.com. All rights reserved.
 */
public abstract class BaseActivity<VM extends BaseViewModel> extends DaggerAppCompatActivity implements ILifeCycleControl, IViewModeControl<VM>, ILoadingControl {

    /**
     * ViewModel
     */
    protected VM viewModel;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        onBeforeSetContentLayout();
        viewModel = obtainViewModel();
        generateContentView(getLayoutId());
        onAfterSetContentLayout(savedInstanceState);
    }

    /**
     * 创建content view
     *
     * @param layoutResId 指定布局id
     */
    protected abstract void generateContentView(int layoutResId);

    /**
     * @return A ViewModel that is an instance of the given type {@code VM}.
     */
    @Override
    public VM obtainViewModel() {
        if (getModelClass() == null) {
            return null;
        }
        return ViewModelProviders.of(this, viewModelFactory).get(getModelClass());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        hideWaitDialog();
    }
}
