package com.itingchunyu.m.component;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;

import com.itingchunyu.m.BR;
import com.itingchunyu.m.R;
import com.itingchunyu.m.component.base.BaseBindingToolbarActivity;
import com.itingchunyu.m.component.user.UserViewModel;
import com.itingchunyu.m.data.model.TestEntity;
import com.itingchunyu.m.databinding.ActivityMainBinding;
import com.itingchunyu.m.live.AbstractResourceObserver;

/**
 * @author liyanxi
 * @date 2018/8/14
 * Copyright (c) 2018 www.itingchunyu.com. All rights reserved.
 */
public class MainActivity extends BaseBindingToolbarActivity<ActivityMainBinding, UserViewModel> {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
        switch (item.getItemId()) {
            case R.id.navigation_home:
                viewModel.title.setValue(getString(R.string.title_home));
                return true;
            case R.id.navigation_dashboard:
                viewModel.title.setValue(getString(R.string.title_dashboard));
                return true;
            case R.id.navigation_notifications:
                viewModel.title.setValue(getString(R.string.title_notifications));
                return true;
            default:
        }
        return false;
    };

    @Override
    public void onBeforeSetContentLayout() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onAfterSetContentLayout(Bundle savedInstanceState) {

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        viewModel.getHome().observe(this, new AbstractResourceObserver<TestEntity>() {

            @Override
            public void onSuccess(TestEntity data) {
                // request success
                viewModel.title.setValue(data.toString());
            }

            @Override
            public void onLoading(boolean isLoading) {
                // request status loading
                viewModel.loading.postValue(isLoading);
            }

            @Override
            public void onError(String errorMsg, String code) {
                // request error/success message
                viewModel.toastMsg.postValue(errorMsg);
            }
        });
    }

    @Override
    public int getVariableViewModelId() {
        return BR.viewModel;
    }

    @Override
    public Class<UserViewModel> getModelClass() {
        return UserViewModel.class;
    }

    @Override
    public void showToast(String msg) {

    }

    @Override
    public void hideWaitDialog() {

    }

    @Override
    public Dialog showWaitDialog() {
        return null;
    }
}
