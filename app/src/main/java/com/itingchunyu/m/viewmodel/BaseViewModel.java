package com.itingchunyu.m.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.itingchunyu.m.data.source.TraderRepository;

/**
 * @author liyanxi
 * @date 2018/8/14
 * Copyright (c) 2018 www.itingchunyu.com. All rights reserved.
 */

public class BaseViewModel extends AndroidViewModel {

    protected final TraderRepository mTraderRepository;

    public BaseViewModel(@NonNull Application application, TraderRepository mTraderRepository) {
        super(application);
        this.mTraderRepository = mTraderRepository;
    }

    /**
     * ui request loading status
     */
    public MutableLiveData<Boolean> loading = new MutableLiveData<>();
    /**
     * ui request or verify error info of toast
     */
    public MutableLiveData<String> toastMsg = new MutableLiveData<>();
}
