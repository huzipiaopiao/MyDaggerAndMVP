package com.ll.banbury.mydaggerandmvp.dagger.app;

import com.ll.banbury.mydaggerandmvp.retrofit.JuheRetrofit;
import com.ll.banbury.mydaggerandmvp.retrofit.YiyuanRetrofit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

/**
 * @author banbury
 * @version v1.0
 * @created 2017/11/22_17:59.
 * @description
 */
@Module
public class RetrofitModule {

    @Singleton
    @Provides
    public JuheRetrofit providerJuheRetrofit(@Named("default")OkHttpClient okHttpClient){
        return new JuheRetrofit(okHttpClient);
    }

    @Singleton
    @Provides
    public YiyuanRetrofit providerYiyuanRetrofit(@Named("default")OkHttpClient okHttpClient){
        return new YiyuanRetrofit(okHttpClient);
    }

}
