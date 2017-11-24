package com.ll.banbury.mydaggerandmvp.model;

import com.ll.banbury.mydaggerandmvp.model.netbean.WinXinJX;
import com.ll.banbury.mydaggerandmvp.retrofit.api.JuheService;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author banbury
 * @version v1.0
 * @created 2017/11/23_11:20.
 * @description
 */

public class MainActivityModel {
    private OnDataListener<WinXinJX> onDataListener;
    private JuheService juheService;

    @Inject
    public MainActivityModel(JuheService juheService) {
        this.juheService = juheService;
    }

    public void getWeixinJx(int page) {
        juheService.requestWeixinJx("0abe122b681ea0f17cfd13b91689d46e", page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WinXinJX>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull WinXinJX winXinJX) {
                        if (onDataListener != null) {
                            onDataListener.onResult(winXinJX);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (onDataListener != null) {
                            onDataListener.onError(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {
                        if (onDataListener != null) {
                            onDataListener.onComplete();
                        }
                    }
                });
    }

    public void setOnDataListener(OnDataListener<WinXinJX> onDataListener) {
        this.onDataListener = onDataListener;
    }

}
