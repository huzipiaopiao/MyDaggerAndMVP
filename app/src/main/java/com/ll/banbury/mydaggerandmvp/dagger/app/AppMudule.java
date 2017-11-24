package com.ll.banbury.mydaggerandmvp.dagger.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.ll.banbury.mydaggerandmvp.Utils;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author banbury
 * @version v1.0
 * @created 2017/11/22_16:38.
 * @description
 */
@Module
public class AppMudule {
    private Context mContext;

    public AppMudule(Context mContext) {
        this.mContext = mContext;
    }

    @Provides
    @Singleton
    public Context ProviderApplication(){
        return mContext;
    }

    @Singleton
    @Provides
    @Named("default")
    public SharedPreferences providerDefaultSharedPreferences(){
        return PreferenceManager.getDefaultSharedPreferences(mContext);
    }

    @Singleton
    @Provides
    @Named("encode")
    public SharedPreferences providerEncodeSharedPreferences(){
        return mContext.getSharedPreferences("encode",Context.MODE_PRIVATE);
    }

    @Singleton
    @Provides
    public Utils getUtils() {
        return new Utils(mContext);
    }
}
