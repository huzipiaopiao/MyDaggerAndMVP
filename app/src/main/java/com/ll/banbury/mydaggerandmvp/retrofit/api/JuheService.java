package com.ll.banbury.mydaggerandmvp.retrofit.api;

import com.ll.banbury.mydaggerandmvp.model.netbean.WinXinJX;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author banbury
 * @version v1.0
 * @created 2017/11/23_9:15.
 * @description
 */

public interface JuheService {

    @GET("weixin/query")
    Observable<WinXinJX> requestWeixinJx(@Query("key") String key, @Query("pno") int pno);
}
