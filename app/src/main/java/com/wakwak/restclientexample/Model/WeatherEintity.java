package com.wakwak.restclientexample.Model;

import java.util.List;

/**
 * Created by Ryo on 2015/06/28.
 */
public class WeatherEintity {

    public String           base;
    public List<Weather>    weather;

    public class Weather {
        public int      id;
        public String   main;
        public String   description;
        public String   icon;
    }

}
