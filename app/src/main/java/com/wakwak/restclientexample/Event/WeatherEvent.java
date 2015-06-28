package com.wakwak.restclientexample.Event;

/**
 * Created by Ryo on 2015/06/28.
 */
public class WeatherEvent {
    public boolean success;
    public String  weather;

    public WeatherEvent(boolean success, String weather) {
        this.success = success;
        this.weather = weather;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }
}
