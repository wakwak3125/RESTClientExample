package com.wakwak.restclientexample.Common;

import android.app.Application;
import android.content.Context;

/**
 * Created by Ryo on 2015/06/28.
 */
public class AppCtrl extends Application {

    private static AppCtrl mInstance;
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        mContext = getApplicationContext();

    }

    public static synchronized Context getContext() {
        return mContext;
    }

    public static synchronized AppCtrl getInstance() {
        return mInstance;
    }

}
