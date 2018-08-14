package com.itingchunyu.m.di;

import android.arch.lifecycle.ViewModelProvider;

import com.itingchunyu.m.viewmodel.ViewModelFactory;

import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;

@Module(subcomponents = ViewModelSubComponent.class)
class AppModule {

    @Singleton
    @Provides
    static ViewModelProvider.Factory provideViewModelFactory(
            ViewModelSubComponent.Builder viewModelSubComponent) {

        return new ViewModelFactory(viewModelSubComponent.build());
    }
}
