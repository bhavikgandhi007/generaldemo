package com.myapplication.rest;

import com.myapplication.model.Employees;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RestClient {


    @GET("/employees")
    Call<ArrayList<Employees>> getEmployeeData();

}
