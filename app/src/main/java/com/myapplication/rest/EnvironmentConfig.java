package com.myapplication.rest;

import android.util.Log;

public class EnvironmentConfig {

    public static final String TAG = EnvironmentConfig.class.getSimpleName();

    public static final String ENV = "production";

    public EnvironmentConfig() {
        Log.i(TAG, "ENV: " + ENV);
    }

    public String getBaseUrl() {
        return "http://dummy.restapiexample.com/api/";
    }


}
