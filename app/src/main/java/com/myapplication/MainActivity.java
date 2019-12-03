package com.myapplication;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.myapplication.model.Employees;
import com.myapplication.rest.RestClient;
import com.myapplication.rest.RestService;
import com.myapplication.rest.RetroFitServiceGenerator;
import com.myapplication.utils.Utility;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    // Start Calling Rest Api
    public void getAllEmployees(final Context context) {
        if (Utility.isOnline(context)) {
            RestClient service = new RetroFitServiceGenerator(context).createService(RestClient.class);
            Call<ArrayList<Employees>> call = service.getEmployeeData();
            call.enqueue(new Callback<ArrayList<Employees>>() {
                @Override
                public void onResponse(Call<ArrayList<Employees>> call, Response<ArrayList<Employees>> response) {
                    if (response.isSuccessful()) {


                    } else {
                        Utility.showError(MainActivity.this, response.errorBody(), response.code());
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<Employees>> call, Throwable t) {

                }
            });
        } else {
            if (context != null) {

            }
        }
    }


    public void getAllEmployees1(final Context context) {
        if (Utility.isOnline(context)) {
            RestClient service = new RestService(context).updateService(RestClient.class);
            Call<ArrayList<Employees>> call = service.getEmployeeData();
            call.enqueue(new Callback<ArrayList<Employees>>() {
                @Override
                public void onResponse(Call<ArrayList<Employees>> call, Response<ArrayList<Employees>> response) {
                    if (response.isSuccessful()) {


                    } else {
                        Utility.showError(MainActivity.this, response.errorBody(), response.code());
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<Employees>> call, Throwable t) {

                }
            });
        } else {
            if (context != null) {

            }
        }
    }
}
