package com.itingchunyu.m.component.base;

/**
 * data binding 相关抽象类
 * @author liyanxi
 * @date 2018/8/17
 * Copyright (c) 2018 www.itingchunyu.com. All rights reserved.
 */
public interface IBindingControl {

    /**
     * the BR id of the variable to be set. For example, if the variable is
     * <code>x</code>, then variableId will be <code>BR.x</code>.
     *
     * @return init ViewModel id
     */
    int getVariableViewModelId();

}
