package com.ll.banbury.mydaggerandmvp.dagger.component;

import com.ll.banbury.mydaggerandmvp.dagger.app.AppComponent;
import com.ll.banbury.mydaggerandmvp.dagger.module.SecondActivityModule;
import com.ll.banbury.mydaggerandmvp.dagger.scope.ActivityScope;
import com.ll.banbury.mydaggerandmvp.view.SecondeActivity;

import dagger.Component;

/**
 * @author banbury
 * @version v1.0
 * @created 2017/11/23_14:56.
 * @description
 *
 * 第二种使用父类component的方法，另一种见MainActivityComponent
 *
 * 与MainActivityComponent 的区别是：
 * MainActivityComponent，是用的@Subcomponent，并在AppComponent中申明MainActivityComponent getMainActivityComponent(MainActivityModule mainActivityModule);
 * SecondActivityComponent，用的是@Component + dependencies，并在AppComponent中申明可提供的对象
 *
 * 两者的区别是：
 * 注解@Subcomponent表示子类，并要在对应父Component类中申明（即申明一个方法返回值为子类）
 * 注解@Component + dependencies，表示依赖一个父Component类，并要在这个父Component类中，申明子类中可以使用的对象（即申明一个方法返回值为供子类用的对象）
 *
 * 当然最终的效果是一样的。
 */
@ActivityScope
@Component(modules = SecondActivityModule.class,dependencies = AppComponent.class)
public interface SecondActivityComponent {

    void inject(SecondeActivity secondeActivity);
}
