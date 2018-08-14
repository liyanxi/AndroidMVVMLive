package com.itingchunyu.m.component;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.itingchunyu.m.R;
import com.itingchunyu.m.component.base.BaseActivity;
import com.itingchunyu.m.component.user.UserViewModel;
import com.itingchunyu.m.data.model.TestEntity;
import com.itingchunyu.m.live.AbstractResourceObserver;

import javax.inject.Inject;

/**
 * @author liyanxi
 * @date 2018/8/14
 * Copyright (c) 2018 www.finlendingcloud.com. All rights reserved.
 */
public class MainActivity extends BaseActivity {

    private TextView mTextMessage;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

//    ViewDataBinding viewDataBinding;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
                default:
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

//        mTextMessage = findViewById(R.id.message);
//        BottomNavigationView navigation = findViewById(R.id.navigation);
//        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        UserViewModel userViewModel = ViewModelProviders.of(this, viewModelFactory).get(UserViewModel.class);

        userViewModel.getHome().observe(this, new AbstractResourceObserver<TestEntity>() {

            @Override
            public void onSuccess(TestEntity data) {
                Log.e("MainActivity", data.toString());
                userViewModel.title.setValue(data.toString());
            }

            @Override
            public void onException(String errorMsg, int code, TestEntity data) {

            }
        });
//        viewDataBinding.setLifecycleOwner(this);
    }
}
