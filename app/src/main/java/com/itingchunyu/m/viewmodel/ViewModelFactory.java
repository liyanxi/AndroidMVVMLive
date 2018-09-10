/*
 *  Copyright 2017 Google Inc.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.itingchunyu.m.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;
import android.support.v4.util.ArrayMap;

import com.itingchunyu.c.component.MainViewModel;
import com.itingchunyu.c.component.auth.UserAuthViewModel;
import com.itingchunyu.c.component.user.UserViewModel;
import com.itingchunyu.c.component.user.bank.UserBankListViewModel;
import com.itingchunyu.c.component.user.setting.SettingViewModel;
import com.itingchunyu.c.component.user.support.SupportBankViewModel;
import com.itingchunyu.c.component.web.WebViewModel;
import com.itingchunyu.c.di.ViewModelSubComponent;

import java.util.Map;
import java.util.concurrent.Callable;

import javax.inject.Inject;

/**
 * A creator is used to inject the product ID into the ViewModel
 * <p>
 * This creator is to showcase how to inject dependencies into ViewModels. It's not
 * actually necessary in this case, as the product ID can be passed in a public method.
 */
public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final ArrayMap<Class, Callable<? extends ViewModel>> creators;

    @Inject
    public ViewModelFactory(ViewModelSubComponent viewModelSubComponent) {
        creators = new ArrayMap<>();

        // View models cannot be injected directly because they won't be bound to the owner's view model scope.
        creators.put(UserViewModel.class, viewModelSubComponent::projectUserViewModel);
        creators.put(MainViewModel.class, viewModelSubComponent::projectMainViewModel);
        creators.put(UserAuthViewModel.class, viewModelSubComponent::projectUserAuthModel);
        creators.put(UserBankListViewModel.class, viewModelSubComponent::projectUserBankListViewModel);
        creators.put(SettingViewModel.class, viewModelSubComponent::projectSettingViewModel);
        creators.put(WebViewModel.class, viewModelSubComponent::projectWebViewModel);
        creators.put(SupportBankViewModel.class, viewModelSubComponent::projectSupportBankViewModel);
    }

    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        Callable<? extends ViewModel> creator = creators.get(modelClass);
        if (creator == null) {
            for (Map.Entry<Class, Callable<? extends ViewModel>> entry : creators.entrySet()) {
                if (modelClass.isAssignableFrom(entry.getKey())) {
                    creator = entry.getValue();
                    break;
                }
            }
        }
        if (creator == null) {
            throw new IllegalArgumentException("Unknown model class " + modelClass);
        }
        try {
            return (T) creator.call();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
