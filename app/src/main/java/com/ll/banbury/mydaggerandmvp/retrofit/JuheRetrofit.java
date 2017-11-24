package com.ll.banbury.mydaggerandmvp.retrofit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author banbury
 * @version v1.0
 * @created 2017/11/22_17:58.
 * @description
 */

public class JuheRetrofit {

    private static final String Juhe_BASE_URL = "http://v.juhe.cn/";
    private static Retrofit retrofit;

    public JuheRetrofit(OkHttpClient okHttpClient) {
        retrofit = new Retrofit.Builder()
                .baseUrl(Juhe_BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    public Retrofit getRetrofit() {
        return retrofit;
    }
}
