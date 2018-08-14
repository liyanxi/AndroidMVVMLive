package com.itingchunyu.m.di;

import com.itingchunyu.m.component.user.UserViewModel;

import dagger.Subcomponent;

/**
 * A sub component to create ViewModels. It is called by the
 * {@link com.itingchunyu.m.viewmodel.ViewModelFactory}.
 */
@Subcomponent
public interface ViewModelSubComponent {
    @Subcomponent.Builder
    interface Builder {
        ViewModelSubComponent build();
    }
    // list view model declaration
    UserViewModel projectUserViewModel();
}
