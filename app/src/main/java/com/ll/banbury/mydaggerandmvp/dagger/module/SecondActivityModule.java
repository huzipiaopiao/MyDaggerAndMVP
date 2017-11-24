package com.ll.banbury.mydaggerandmvp.dagger.module;

import com.ll.banbury.mydaggerandmvp.contract.SecondeActivityContract;
import com.ll.banbury.mydaggerandmvp.dagger.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * @author banbury
 * @version v1.0
 * @created 2017/11/23_14:55.
 * @description
 */

@Module
public class SecondActivityModule {
    private SecondeActivityContract.View view;

    public SecondActivityModule(SecondeActivityContract.View view) {
        this.view = view;
    }

    @Provides
    @ActivityScope
    public SecondeActivityContract.View providerView(){
        return view;
    }
}
