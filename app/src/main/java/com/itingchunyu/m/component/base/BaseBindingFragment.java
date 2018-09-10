package com.itingchunyu.m.component.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
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
public abstract class BaseBindingFragment<VB extends ViewDataBinding, VM extends BaseViewModel>
        extends BaseFragment<VM> implements IBindingControl {

    /**
     * xml binding
     */
    protected VB binding;
    protected View mView;

    @Override
    View generateContentView(@NonNull LayoutInflater inflater, int layoutId, @Nullable ViewGroup container, boolean attachToParent) {
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);

        // Specify the current activity as the lifecycle owner.
        binding.setLifecycleOwner(this);
        if (viewModel != null && getVariableViewModelId() != 0) {
            binding.setVariable(getVariableViewModelId(), viewModel);
        }
        mView = binding.getRoot();
        return mView;
    }

    @Override
    public void onAfterSetContentLayout(Bundle savedInstanceState) {
        setupToastOrLoadingObserve();
    }


    /**
     * add toast or loading observe
     */
    private void setupToastOrLoadingObserve() {
        if (viewModel != null) {
            viewModel.loading.observe(this, isLoading -> {
                if (isLoading != null && isLoading) {
                    showWaitDialog();
                } else {
                    hideWaitDialog();
                }
            });
            viewModel.toastMsg.observe(this, this::showToast);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding.unbind();
    }
}
