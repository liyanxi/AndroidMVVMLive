package com.itingchunyu.m.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.itingchunyu.m.data.source.TraderRepository;

/**
 * @author liyanxi
 * @date 2018/8/14
 * Copyright (c) 2018 www.finlendingcloud.com. All rights reserved.
 */

public class BaseViewModel extends AndroidViewModel {

    protected final TraderRepository mTraderRepository;

    public BaseViewModel(@NonNull Application application, TraderRepository mTraderRepository) {
        super(application);
        this.mTraderRepository = mTraderRepository;
    }
}
