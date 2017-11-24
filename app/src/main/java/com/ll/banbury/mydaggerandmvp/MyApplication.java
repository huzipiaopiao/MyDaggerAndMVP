package com.ll.banbury.mydaggerandmvp;

import android.app.Application;

import com.ll.banbury.mydaggerandmvp.dagger.app.AppComponent;
import com.ll.banbury.mydaggerandmvp.dagger.app.AppMudule;
import com.ll.banbury.mydaggerandmvp.dagger.app.DaggerAppComponent;

/**
 * @author banbury
 * @version v1.0
 * @created 2017/11/22_16:35.
 * @description
 */

public class MyApplication extends Application {
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        setupAppComponent();
    }

    private void setupAppComponent() {
        appComponent = DaggerAppComponent.builder().appMudule(new AppMudule(this)).build();
    }

    public AppComponent getAppComponent() {
        if (appComponent == null) {
            setupAppComponent();
        }
        return appComponent;
    }




}
