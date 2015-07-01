package com.wakwak.restclientexample.Api.REST_Interfaces;

import com.wakwak.restclientexample.Model.ATND_Eintity;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by Ryo on 2015/06/30.
 */
public interface ATND_InterFace {
    @GET("/events/?format=json")
    public Observable<ATND_Eintity> getEvents(@Query("keyword") String keyword);
}
