package com.wakwak.restclientexample.Api.API_Connect;

import android.util.Log;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.bind.DateTypeAdapter;
import com.wakwak.restclientexample.Api.REST_Interfaces.WeatherApi;
import com.wakwak.restclientexample.Event.WeatherEvent;
import com.wakwak.restclientexample.Model.WeatherEintity;

import java.util.Date;

import de.greenrobot.event.EventBus;
import retrofit.RestAdapter;
import retrofit.android.AndroidLog;
import retrofit.converter.GsonConverter;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Ryo on 2015/06/28.
 */
public class WeatherConnect {

    public static final String TAG = WeatherConnect.class.getSimpleName();

    public static final String NAME = "weather";
    public static final String LOCATION = "Tokyo,jp";

    public void connenct() {

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .registerTypeAdapter(Date.class, new DateTypeAdapter())
                .create();

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint("http://api.openweathermap.org")
                .setConverter(new GsonConverter(gson))
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setLog(new AndroidLog("=NETWORK="))
                .build();

        adapter.create(WeatherApi.class).getWeather(NAME, LOCATION)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WeatherEintity>() {
                    @Override
                    public void onCompleted() {
                        Log.i(TAG, "Completed");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "Error:" + e.getLocalizedMessage());
                    }

                    @Override
                    public void onNext(WeatherEintity weatherEintity) {
                        Log.i(TAG, "onNext()");
                        if (weatherEintity != null) {
                            Log.i(TAG, weatherEintity.weather.toString());
                            String weather = weatherEintity.weather.get(0).main;
                            EventBus.getDefault().post(new WeatherEvent(true, weather));
                        }
                    }
                });
    }

}