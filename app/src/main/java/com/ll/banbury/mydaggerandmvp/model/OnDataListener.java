package com.ll.banbury.mydaggerandmvp.model;

/**
 * @author banbury
 * @version v1.0
 * @created 2017/11/24_15:06.
 * @description
 */

public interface OnDataListener<T> {
    void onComplete();

    void onResult(T result);

    void onError(String msg);
}
