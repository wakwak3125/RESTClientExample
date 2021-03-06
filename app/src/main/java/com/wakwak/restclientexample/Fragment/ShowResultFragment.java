package com.wakwak.restclientexample.Fragment;


import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wakwak.restclientexample.Api.API_Connect.ATND_Connect;
import com.wakwak.restclientexample.Api.API_Connect.WeatherConnect;
import com.wakwak.restclientexample.Event.ATNDEvent;
import com.wakwak.restclientexample.Event.WeatherEvent;
import com.wakwak.restclientexample.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShowResultFragment extends Fragment {

    @Bind(R.id.responseField)
    TextView responseField;

    public ShowResultFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show_weather, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // お天気取得APIの発動
        WeatherConnect connect = new WeatherConnect();
        connect.connenct();

        // ATND APIの発動
        ATND_Connect atndConnect = new ATND_Connect();
        atndConnect.connenct();
    }

    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);

    }

    @Override
    public void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Subscribe
    public void onEvent(WeatherEvent weatherEvent) {
        // レスポンスが帰ってきたらそれをTextViewに反映させる。
        if (weatherEvent.isSuccess()) {
            responseField.setText(weatherEvent.getWeather());
        }
    }

    /*@Subscribe
    public void onEvent(ATNDEvent atndEvent) {
        // レスポンスが帰ってきたらそれをTextViewに反映させる。
        if (atndEvent.isSuccess()) {
            responseField.setText(atndEvent.getTitle());
        }
    }*/
}
