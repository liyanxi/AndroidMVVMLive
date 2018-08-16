package com.itingchunyu.m.component;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;

import com.itingchunyu.m.BR;
import com.itingchunyu.m.R;
import com.itingchunyu.m.component.base.BaseBindingActivity;
import com.itingchunyu.m.component.user.UserViewModel;
import com.itingchunyu.m.data.model.TestEntity;
import com.itingchunyu.m.databinding.ActivityMainBinding;
import com.itingchunyu.m.live.AbstractResourceObserver;

/**
 * @author liyanxi
 * @date 2018/8/14
 * Copyright (c) 2018 www.itingchunyu.com. All rights reserved.
 */
public class MainActivity extends BaseBindingActivity<ActivityMainBinding, UserViewModel> {

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        viewModel.getHome().observe(this, new AbstractResourceObserver<TestEntity>() {

            @Override
            public void onSuccess(TestEntity data) {
                viewModel.title.setValue(data.toString());
            }

            @Override
            public void onException(String errorMsg, int code, TestEntity data) {

            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
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
