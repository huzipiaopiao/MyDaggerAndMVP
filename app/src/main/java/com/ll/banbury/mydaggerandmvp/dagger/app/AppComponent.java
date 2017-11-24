package com.ll.banbury.mydaggerandmvp.dagger.app;

import android.content.SharedPreferences;

import com.ll.banbury.mydaggerandmvp.Utils;
import com.ll.banbury.mydaggerandmvp.dagger.component.MainActivityComponent;
import com.ll.banbury.mydaggerandmvp.dagger.component.SecondActivityComponent;
import com.ll.banbury.mydaggerandmvp.dagger.module.MainActivityModule;
import com.ll.banbury.mydaggerandmvp.retrofit.api.YiyuanService;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Component;

/**
 * @author banbury
 * @version v1.0
 * @created 2017/11/22_16:37.
 * @description
 */
@Singleton
@Component(modules = {AppMudule.class,
        OkhttpModule.class,
        RetrofitModule.class,
        ServiceModule.class})
public interface AppComponent {

    Utils getUtils();

    /**
     * 如果其他的Component用dependencies依赖本Component(如：{@link SecondActivityComponent})，则如果其他Component中需要本Component中提供的对象，则需要提供下面返回对应对象的这种方法
     *
     * @return
     */
    YiyuanService getYiyuanService();

    /**
     * 如果其他的Component用dependencies依赖本Component(如：{@link SecondActivityComponent})，则如果其他Component中需要本Component中提供的对象，则需要提供下面返回对应对象的这种方法
     *
     * @return
     */
    @Named("default")
    SharedPreferences getSharedPreferences();

    /**
     * 如果其他的Component用@SubComponent依赖本Component(如：{@link MainActivityComponent})，则本Component需要提供下面这个返回对应Component对象的方法，参数为对象Component需要的module
     *
     * @param mainActivityModule
     * @return
     */
    MainActivityComponent getMainActivityComponent(MainActivityModule mainActivityModule);
}
