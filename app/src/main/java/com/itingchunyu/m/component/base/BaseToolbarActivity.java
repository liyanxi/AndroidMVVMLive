package com.itingchunyu.m.component.base;

import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.itingchunyu.m.R;
import com.itingchunyu.m.viewmodel.BaseViewModel;

/**
 * @author liyanxi
 * @date 2018/8/16
 * Copyright (c) 2018 www.itingchunyu.com. All rights reserved.
 */
public abstract class BaseToolbarActivity<VM extends BaseViewModel> extends BaseActivity<VM> {

    public static final int CONTENT_VIEW_ID = R.id.content_container;
    private static final int BASE_VIEW_ID = R.layout.activity_base_toolbar;
    private static final FrameLayout.LayoutParams LAYOUT_PARAMS = new FrameLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
    protected FrameLayout mContentView;
    protected Toolbar mToolbar;

    @Override
    public void generateContentView(int layoutResId) {
        setContentView(layoutResId);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(BASE_VIEW_ID);
        initBaseView();
        if (layoutResID != 0) {
            mContentView.addView(obtainChildView(layoutResID), 0, LAYOUT_PARAMS);
        }
    }

    /**
     * 获取壮哉View
     *
     * @param layoutResId 装载View layout id
     * @return view
     */
    protected View obtainChildView(int layoutResId) {
        return getLayoutInflater().inflate(layoutResId, null);
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(getLayoutInflater().inflate(BASE_VIEW_ID, null));
        initBaseView();
        if (view != null) {
            mContentView.addView(view, 0, LAYOUT_PARAMS);
        }
    }

    /**
     * 初始化base view
     */
    private void initBaseView() {
        mContentView = findViewById(R.id.content_container);
        initToolbarView();
    }

    protected TextView mToolbarTitle;

    /**
     * 初始化toolbar view
     */
    private void initToolbarView() {
        mToolbar = findViewById(R.id.toolbar);
        mToolbarTitle = findViewById(R.id.toolbar_title);
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // 让原始的toolbar的title不显示，可以使用
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        mToolbar.setNavigationOnClickListener(v -> {
            //finish activity
            onBackPressed();
        });
        //获取 AndroidManifest.xml label title
        mToolbarTitle.setText(getTitle());
        setupCustomToolbar();
    }

    /**
     * 自定义顶部toolbar
     */
    protected void setupCustomToolbar() {
        // no something to child implementation
    }

    @Override
    public void setTitle(CharSequence title) {
        mToolbarTitle.setText(title);
    }
}
