package com.itingchunyu.m.component.base;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.itingchunyu.m.viewmodel.BaseViewModel;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

/**
 * fragment 基础类
 *
 * @author liyanxi
 * @date 2018/8/15
 * Copyright (c) 2018 www.finlendingcloud.com. All rights reserved.
 */
public abstract class BaseBindingFragment<VB extends ViewDataBinding, VM extends BaseViewModel> extends DaggerFragment
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        binding.setVariable(getVariableViewModelId(), viewModel = obtainViewModel());
        return binding.getRoot();
    }

    /**
     * A ViewModel that is an instance of the given type {@code VM}.
     *
     * @return VM
     */
    @Override
    public VM obtainViewModel() {
        return ViewModelProviders.of(this, viewModelFactory).get(getModelClass());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding.unbind();
    }
}
