package com.itingchunyu.m.component.base.interfaces;

import com.itingchunyu.m.viewmodel.BaseViewModel;

/**
 * @author liyanxi
 * @date 2018/8/15
 * Copyright (c) 2018 www.itingchunyu.com. All rights reserved.
 */
public interface IViewModeControl<VM extends BaseViewModel> {

    /**
     * The class of the ViewModel to create an instance of it if it is not present.
     *
     * @return model class
     */
    Class<VM> getModelClass();

    /**
     * A ViewModel that is an instance of the given type {@code VM}.
     * @return viewModel extends WM
     */
    VM obtainViewModel();

    /**
     * show common toast
     * @param msg app info
     */
    void showToast(String msg);
}
