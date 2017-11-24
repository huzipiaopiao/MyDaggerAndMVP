package com.ll.banbury.mydaggerandmvp.dagger.app;

import android.text.TextUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * @author banbury
 * @version v1.0
 * @created 2017/11/22_17:27.
 * @description
 */
@Module
public class OkhttpModule {

    @Provides
    @Singleton
    @Named("autocache")
    public OkHttpClient providerAutoCacheOkHttpClient() {
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Response response = chain.proceed(request);

                String cacheControl = request.cacheControl().toString();
                if (TextUtils.isEmpty(cacheControl)) {
                    cacheControl = "public, max-age=" + 3600 * 6 + " ,max-stale=2419200";
                }
                return response.newBuilder().header("Cache-Control", cacheControl).removeHeader("Pragma").build();
            }
        };

        return new OkHttpClient.Builder().addNetworkInterceptor(interceptor).retryOnConnectionFailure(true).connectTimeout(10, TimeUnit.SECONDS).build();

    }


    @Provides
    @Singleton
    @Named("default")
    public OkHttpClient providerOkHttpClient() {
        return new OkHttpClient.Builder()
                .addNetworkInterceptor(new HttpLoggingInterceptor())
                .retryOnConnectionFailure(true)
                .connectTimeout(10, TimeUnit.SECONDS)
                .build();
    }
}
