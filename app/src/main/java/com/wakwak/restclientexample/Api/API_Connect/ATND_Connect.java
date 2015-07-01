package com.wakwak.restclientexample.Api.API_Connect;

import android.util.Log;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.bind.DateTypeAdapter;
import com.wakwak.restclientexample.Api.REST_Interfaces.ATND_InterFace;
import com.wakwak.restclientexample.Api.REST_Interfaces.WeatherApi;
import com.wakwak.restclientexample.Event.ATNDEvent;
import com.wakwak.restclientexample.Event.WeatherEvent;
import com.wakwak.restclientexample.Model.ATND_Eintity;
import com.wakwak.restclientexample.Model.Event;
import com.wakwak.restclientexample.Model.WeatherEintity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
public class ATND_Connect {

    public static final String TAG = ATND_Connect.class.getSimpleName();

    public static final String KEY_WORD = "Android";

    private ArrayList<Event> eventList;

    public void connenct() {
        eventList = new ArrayList<Event>();
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .registerTypeAdapter(Date.class, new DateTypeAdapter())
                .create();

        final RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint("http://api.atnd.org/")
                .setConverter(new GsonConverter(gson))
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setLog(new AndroidLog("=NETWORK="))
                .build();

        adapter.create(ATND_InterFace.class).getEvents(KEY_WORD)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ATND_Eintity>() {
                    @Override
                    public void onCompleted() {
                        Log.i(TAG, "Completed");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "Error:" + e.getLocalizedMessage());
                    }

                    @Override
                    public void onNext(ATND_Eintity atndEintity) {
                        Log.i(TAG, "onNext()");
                        if (atndEintity != null) {
                            for (int i = 0; i < atndEintity.events.size(); i++) {
                                String  title       = atndEintity.events.get(i).event.title;
                                Integer event_id    = atndEintity.events.get(i).event_id;

                                Event event = new Event();
                                event.setEvent_id(event_id);
                                event.setTitle(title);

                                eventList.add(event);
                                Log.i(TAG, eventList.get(i).getTitle());
                            }

                        }
                    }
                });
    }

}