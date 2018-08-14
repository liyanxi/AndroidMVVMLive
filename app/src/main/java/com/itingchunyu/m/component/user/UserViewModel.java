package com.itingchunyu.m.component.user;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.itingchunyu.m.data.Resource;
import com.itingchunyu.m.data.model.TestEntity;
import com.itingchunyu.m.data.source.TraderRepository;
import com.itingchunyu.m.viewmodel.BaseViewModel;

import javax.inject.Inject;

/**
 * @author liyanxi
 * @date 2018/8/10
 * Copyright (c) 2018 www.finlendingcloud.com. All rights reserved.
 */

public class UserViewModel extends BaseViewModel {

    @Inject
    UserViewModel(@NonNull Application application, @NonNull TraderRepository mTraderRepository) {
        super(application, mTraderRepository);
    }

    public final MutableLiveData<String> title = new MutableLiveData<>();

    public LiveData<Resource<TestEntity>> getHome() {
        return mTraderRepository.getHome();
    }
}
