package com.ll.banbury.mydaggerandmvp.contract;

import com.ll.banbury.mydaggerandmvp.model.netbean.WinXinJX;

/**
 * @author banbury
 * @version v1.0
 * @created 2017/11/22_16:19.
 * @description
 *
 * 契约类
 * 方便mvp中接口的管理
 */

public interface MainActivityContract {

    interface View {
        void showResult(WinXinJX result);

        void showError(String error);

        void onComplete();
    }

    interface Presenter {

    }
}
