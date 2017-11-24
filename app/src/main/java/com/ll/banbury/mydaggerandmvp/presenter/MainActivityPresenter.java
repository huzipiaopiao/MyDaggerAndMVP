package com.ll.banbury.mydaggerandmvp.presenter;

import android.content.SharedPreferences;

import com.ll.banbury.mydaggerandmvp.model.MainActivityModel;
import com.ll.banbury.mydaggerandmvp.model.OnDataListener;
import com.ll.banbury.mydaggerandmvp.model.netbean.WinXinJX;
import com.ll.banbury.mydaggerandmvp.contract.MainActivityContract;
import com.ll.banbury.mydaggerandmvp.retrofit.api.JuheService;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author banbury
 * @version v1.0
 * @created 2017/11/22_16:18.
 * @description
 */

public class MainActivityPresenter implements MainActivityContract.Presenter {
    MainActivityContract.View mView;
    JuheService weiXinService;
    MainActivityModel mainActivityModel;
    private OnDataListener<WinXinJX> onDataListener;

//    @Inject
//    public MainActivityPresenter(MainActivityContract.View mView, JuheService weiXinService) {
//        this.mView = mView;
//        this.weiXinService = weiXinService;
//    }

    @Inject
    public MainActivityPresenter(MainActivityContract.View view, MainActivityModel mainActivityModel) {
        this.mView = view;
        this.mainActivityModel = mainActivityModel;
        this.mainActivityModel.setOnDataListener(new OnDataListener<WinXinJX>() {
            @Override
            public void onComplete() {
                mView.onComplete();
            }

            @Override
            public void onResult(WinXinJX result) {
                mView.showResult(result);
            }

            @Override
            public void onError(String msg) {
                mView.showError(msg);
            }
        });
    }

    public void getWeixinJx(int page) {
//        weiXinService.requestWeixinJx("0abe122b681ea0f17cfd13b91689d46e",page)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<WinXinJX>() {
//                    @Override
//                    public void onSubscribe(@NonNull Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(@NonNull WinXinJX winXinJX) {
//                        mView.showResult(winXinJX);
//                    }
//
//                    @Override
//                    public void onError(@NonNull Throwable e) {
//                        mView.showError(e.getMessage());
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        mView.onComplete();
//                    }
//                });
        mainActivityModel.getWeixinJx(page);
    }
}
