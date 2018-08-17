package com.itingchunyu.m.component.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;

import com.itingchunyu.m.viewmodel.BaseViewModel;

/**
 * activity 基础类
 *
 * @author liyanxi
 * @date 2018/8/14
 * Copyright (c) 2018 www.itingchunyu.com. All rights reserved.
 */

public abstract class BaseBindingActivity<VB extends ViewDataBinding, VM extends BaseViewModel>
        extends BaseActivity<VM> implements IBindingControl {

    /**
     * xml binding
     */
    protected VB binding;

    @Override
    public void generateContentView(int layoutResId) {
        // Inflate view and obtain an instance of the binding class.
        binding = DataBindingUtil.setContentView(this, layoutResId);

        // Specify the current activity as the lifecycle owner.
        binding.setLifecycleOwner(this);

        if (viewModel != null && getVariableViewModelId() != 0) {
            binding.setVariable(getVariableViewModelId(), viewModel);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding.unbind();
    }
}
