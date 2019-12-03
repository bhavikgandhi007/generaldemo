package com.myapplication.rest;

import android.content.Context;

public class RetrofitManager {


    public enum METHOD {
        GET,
        GETWITHPROGRESS,
        POST,
        POSTWITHPROGRESS,
        PUT,
        PUTWITHPROGRESS,
        PATCH,
        PATCHWITHPROGRESS,
        DELETE,
        DELETEWITHPROGRESS,
        FILEUPLOAD,
        FILEUPLOADWITHPROGRESS,
    }

    public RestClient request(Context context, final METHOD method, boolean cache, boolean isLongCache) {

        RestClient service = null;
        if (cache && (method == METHOD.GET || method == METHOD.GETWITHPROGRESS)) {
            service = new RestService(context).createService(RestClient.class);
            return service;
        } else if(isLongCache && (method == METHOD.GET || method == METHOD.GETWITHPROGRESS)) {
            service = new RestService(context).createLongCacheService(RestClient.class);
            return service;
        } 


        return service;
    }
}
