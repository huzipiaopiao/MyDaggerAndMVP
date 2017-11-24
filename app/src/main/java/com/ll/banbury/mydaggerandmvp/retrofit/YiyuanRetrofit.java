package com.ll.banbury.mydaggerandmvp.retrofit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author banbury
 * @version v1.0
 * @created 2017/11/23_14:35.
 * @description
 */

public class YiyuanRetrofit {
    private static final String Yiyuan_BASE_URL = "http://route.showapi.com/";
    private static Retrofit retrofit;

    public YiyuanRetrofit(OkHttpClient okHttpClient) {
        retrofit = new Retrofit.Builder()
                .baseUrl(Yiyuan_BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    public Retrofit getRetrofit() {
        return retrofit;
    }
}
