package com.itingchunyu.m.component.base;

import com.itingchunyu.m.viewmodel.BaseViewModel;

/**
 * @author liyanxi
 * @date 2018/8/15
 * Copyright (c) 2018 www.finlendingcloud.com. All rights reserved.
 */
public interface IViewModeControl<VM extends BaseViewModel> {

    /**
     * the BR id of the variable to be set. For example, if the variable is
     * <code>x</code>, then variableId will be <code>BR.x</code>.
     *
     * @return init ViewModel id
     */
    int getVariableViewModelId();

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
}
