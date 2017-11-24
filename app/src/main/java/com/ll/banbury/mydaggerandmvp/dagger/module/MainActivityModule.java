package com.ll.banbury.mydaggerandmvp.dagger.module;

import com.ll.banbury.mydaggerandmvp.contract.MainActivityContract;
import com.ll.banbury.mydaggerandmvp.dagger.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * @author banbury
 * @version v1.0
 * @created 2017/11/22_16:42.
 * @description
 */
@Module
public class MainActivityModule {

    private MainActivityContract.View view;

    public MainActivityModule(MainActivityContract.View view) {
        this.view = view;
    }

    @Provides
    @ActivityScope
    public MainActivityContract.View provideView(){
        return view;
    }

}
