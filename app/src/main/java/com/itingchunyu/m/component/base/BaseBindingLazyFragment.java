package com.itingchunyu.m.component.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.itingchunyu.m.component.base.interfaces.IBindingControl;
import com.itingchunyu.m.viewmodel.BaseViewModel;

/**
 * fragment 基础类
 *
 * @author liyanxi
 * @date 2018/8/15
 * Copyright (c) 2018 www.itingchunyu.com. All rights reserved.
 */
public abstract class BaseBindingLazyFragment<VB extends ViewDataBinding, VM extends BaseViewModel>
        extends BaseLazyFragment<VM> implements IBindingControl {

    /**
     * xml binding
     */
    protected VB binding;

    @Override
    View generateContentView(@NonNull LayoutInflater inflater, int layoutId, @Nullable ViewGroup container, boolean attachToParent) {
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);

        // Specify the current activity as the lifecycle owner.
        binding.setLifecycleOwner(this);
        if (viewModel != null && getVariableViewModelId() != 0) {
            binding.setVariable(getVariableViewModelId(), viewModel);
        }
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding.unbind();
    }
}
