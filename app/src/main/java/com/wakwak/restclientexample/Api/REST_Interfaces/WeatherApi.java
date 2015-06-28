package com.wakwak.restclientexample.Api.REST_Interfaces;

import com.wakwak.restclientexample.Model.WeatherEintity;

import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by Ryo on 2015/06/28.
 */
public interface WeatherApi {

    @GET("/data/2.5/{name}")
    public Observable<WeatherEintity> getWeather(@Path("name") String name, @Query("q") String q);

}
