package com.ll.banbury.mydaggerandmvp.dagger.app;

import com.ll.banbury.mydaggerandmvp.retrofit.JuheRetrofit;
import com.ll.banbury.mydaggerandmvp.retrofit.YiyuanRetrofit;
import com.ll.banbury.mydaggerandmvp.retrofit.api.JuheService;
import com.ll.banbury.mydaggerandmvp.retrofit.api.YiyuanService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author banbury
 * @version v1.0
 * @created 2017/11/22_18:18.
 * @description
 */

@Module
public class ServiceModule {

    @Singleton
    @Provides
    public JuheService providerJuheService(JuheRetrofit retrofit) {
        return retrofit.getRetrofit().create(JuheService.class);
    }

    @Singleton
    @Provides
    public YiyuanService providerYiyuanService(YiyuanRetrofit retrofit) {
        return retrofit.getRetrofit().create(YiyuanService.class);
    }
}

