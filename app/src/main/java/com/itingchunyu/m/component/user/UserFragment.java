package com.itingchunyu.m.component.user;

import android.os.Bundle;

import com.itingchunyu.m.BR;
import com.itingchunyu.m.R;
import com.itingchunyu.m.component.base.BaseBindingFragment;
import com.itingchunyu.m.databinding.FragmentUserBinding;

/**
 * @author liyanxi
 * @date 2018/8/15
 * Copyright (c) 2018 www.itingchunyu.com. All rights reserved.
 */
public class UserFragment extends BaseBindingFragment<FragmentUserBinding, UserViewModel> {

    @Override
    public void onBeforeSetContentLayout() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_user;
    }

    @Override
    public void onAfterSetContentLayout(Bundle savedInstanceState) {

    }

    @Override
    public int getVariableViewModelId() {
        return BR.viewModel;
    }

    @Override
    public Class<UserViewModel> getModelClass() {
        return UserViewModel.class;
    }
}
