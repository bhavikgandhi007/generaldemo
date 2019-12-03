package com.myapplication.rest;

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

    public void request(final RetrofitManager.METHOD method, boolean cache) {


    }
}
