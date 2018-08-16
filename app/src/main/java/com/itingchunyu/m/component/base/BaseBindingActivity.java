package com.itingchunyu.m.component.base;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;

import com.itingchunyu.m.viewmodel.BaseViewModel;

import javax.inject.Inject;

/**
 * activity 基础类
 *
 * @author liyanxi
 * @date 2018/8/14
 * Copyright (c) 2018 www.itingchunyu.com. All rights reserved.
 */

public abstract class BaseBindingActivity<VB extends ViewDataBinding, VM extends BaseViewModel> extends BaseActivity
        implements ILifeCycleControl, IViewModeControl<VM> {

    /**
     * xml binding
     */
    protected VB binding;

    /**
     * ViewModel
     */
    protected VM viewModel;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @Override
    public void generateContentView(int layoutResId) {
        // Inflate view and obtain an instance of the binding class.
        binding = DataBindingUtil.setContentView(this, layoutResId);

        // Specify the current activity as the lifecycle owner.
        binding.setLifecycleOwner(this);

        binding.setVariable(getVariableViewModelId(), viewModel = obtainViewModel());
    }

    /**
     * @return A ViewModel that is an instance of the given type {@code VM}.
     */
    @Override
    public VM obtainViewModel() {
        return ViewModelProviders.of(this, viewModelFactory).get(getModelClass());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding.unbind();
    }
}
