package com.itingchunyu.m.component.base;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
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
 * @author liyanxi
 * @date 2018/8/17
 * Copyright (c) 2018 www.itingchunyu.com. All rights reserved.
 */
public abstract class BaseFragment<VM extends BaseViewModel> extends DaggerFragment implements ILifeCycleControl, IViewModeControl<VM> {

    /**
     * ViewModel
     */
    protected VM viewModel;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    View mView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        onBeforeSetContentLayout();
        viewModel = obtainViewModel();
        if (mView == null) {
            if (getLayoutId() != 0) {
                mView = generateContentView(inflater, getLayoutId(), container, false);
            }
        }
        onAfterSetContentLayout(savedInstanceState);
        //缓存的rootView需要判断是否已经被加过parent， 如果有parent需要从parent删除，要不然会发生这个root view已经有parent的错误。
        ViewGroup parent = (ViewGroup) mView.getParent();
        if (parent != null) {
            parent.removeView(mView);
        }
        return mView;
    }

    /**
     * 创建content view
     *
     * @param inflater       The LayoutInflater used to inflate the binding layout.
     * @param layoutId       The layout resource ID of the layout to inflate.
     * @param container         Optional view to be the parent of the generated hierarchy
     *                       (if attachToParent is true), or else simply an object that provides
     *                       a set of LayoutParams values for root of the returned hierarchy
     *                       (if attachToParent is false.)
     * @param attachToParent Whether the inflated hierarchy should be attached to the
     *                       parent parameter. If false, parent is only used to create
     *                       the correct subclass of LayoutParams for the root view in the XML.
     * @return view
     */
    abstract View generateContentView(@NonNull LayoutInflater inflater,
                                      int layoutId, @Nullable ViewGroup container, boolean attachToParent);

    /**
     * A ViewModel that is an instance of the given type {@code VM}.
     *
     * @return VM
     */
    @Override
    public VM obtainViewModel() {
        if (getModelClass() == null) {
            return null;
        }
        return ViewModelProviders.of(this, viewModelFactory).get(getModelClass());
    }

}
