package com.ll.banbury.mydaggerandmvp.presenter;

import com.ll.banbury.mydaggerandmvp.contract.SecondeActivityContract;
import com.ll.banbury.mydaggerandmvp.model.netbean.Baisibudejie;
import com.ll.banbury.mydaggerandmvp.retrofit.api.YiyuanService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * @author banbury
 * @version v1.0
 * @created 2017/11/23_14:53.
 * @description
 */

public class SecondeActivityPresenter implements SecondeActivityContract.Presenter {
    private YiyuanService yiyuanService;
    private SecondeActivityContract.View view;

    @Inject
    public SecondeActivityPresenter(YiyuanService yiyuanService, SecondeActivityContract.View view) {
        this.yiyuanService = yiyuanService;
        this.view = view;
    }

    public void getBaisibudejie(int page) {
        yiyuanService.getBaisibudejieMsg("50676", "3b68385c2a7a411a83c4cf79552e7966", page+"")
                .subscribeOn(Schedulers.io())
                .map(new Function<Baisibudejie, List<Baisibudejie.ShowapiResBodyBean.PagebeanBean.ContentlistBean>>() {
                    @Override
                    public List<Baisibudejie.ShowapiResBodyBean.PagebeanBean.ContentlistBean> apply(@NonNull Baisibudejie baisibudejie) throws Exception {
                        if (baisibudejie.getShowapi_res_code() == 0) {
                            return baisibudejie.getShowapi_res_body().getPagebean().getContentlist();
                        }
                        return null;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Baisibudejie.ShowapiResBodyBean.PagebeanBean.ContentlistBean>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull List<Baisibudejie.ShowapiResBodyBean.PagebeanBean.ContentlistBean> contentlistBeen) {
                        view.showResult(contentlistBeen);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        view.showError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        view.onComplete();
                    }
                });


    }
}
