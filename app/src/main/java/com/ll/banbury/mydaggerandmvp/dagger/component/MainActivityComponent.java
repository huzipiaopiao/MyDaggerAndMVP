package com.ll.banbury.mydaggerandmvp.dagger.component;

import com.ll.banbury.mydaggerandmvp.dagger.scope.ActivityScope;
import com.ll.banbury.mydaggerandmvp.dagger.module.MainActivityModule;
import com.ll.banbury.mydaggerandmvp.view.MainActivity;

import dagger.Subcomponent;

/**
 * @author banbury
 * @version v1.0
 * @created 2017/11/22_16:43.
 * @description
 *
 * 第一种使用父类component的方法 @Subcomponent；另一种见SecondActivityComponent
 */
@ActivityScope
@Subcomponent(modules = MainActivityModule.class)
public interface MainActivityComponent {

    void injet(MainActivity mainActivity);
}
