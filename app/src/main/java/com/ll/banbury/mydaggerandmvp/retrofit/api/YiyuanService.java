package com.ll.banbury.mydaggerandmvp.retrofit.api;

import com.ll.banbury.mydaggerandmvp.model.netbean.Baisibudejie;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author banbury
 * @version v1.0
 * @created 2017/11/23_14:41.
 * @description
 */

public interface YiyuanService {

    @GET("255-1")
    Observable<Baisibudejie> getBaisibudejieMsg(@Query("showapi_appid")String showapi_appid,
                                                @Query("showapi_sign")String showapi_sign,
                                                @Query("page")String page);
}
