package com.ll.banbury.mydaggerandmvp.contract;

import com.ll.banbury.mydaggerandmvp.model.netbean.Baisibudejie;

import java.util.List;

/**
 * @author banbury
 * @version v1.0
 * @created 2017/11/23_14:53.
 * @description
 * 契约类
 * 方便mvp中接口的管理
 */

public interface SecondeActivityContract {

    interface View{
        void showResult(List<Baisibudejie.ShowapiResBodyBean.PagebeanBean.ContentlistBean> contentlistBeen);
        void showError(String error);
        void onComplete();
    }

    interface Presenter{

    }
}
